/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mkl_shop.alert.AlertMaker;
import mkl_shop.pracownik.modele.Produkt;
import static mkl_shop.pracownik.zakupy.FXMLZakupyController.dataRachunek;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLFinalizacjaController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bPdf;
    @FXML
    private TableView<Produkt> tableRachunek;
    @FXML
    private TableColumn<Produkt, Integer> columnIdProduktu;
    @FXML
    private TableColumn<Produkt, String> columnNazwa;
    @FXML
    private TableColumn<Produkt, String> columnOpis;
    @FXML
    private TableColumn<Produkt, Double> columnCenaSzt;
    @FXML
    private TableColumn<Produkt, Integer> columnIlosc;
    @FXML
    private TableColumn<Produkt, Double> columnCena;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    public static ObservableList<Produkt> dataRachunek;
    @FXML
    private Label lSuma;
    @FXML
    private Label lSumaWartosc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataRachunek = FXCollections.observableArrayList();
        dataRachunek = FXMLZakupyController.dataRachunek;
        
        lSumaWartosc.setText(FXMLZakupyController.getCena().toString() + " zł");
        
        
        columnIdProduktu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnCenaSzt.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));
        columnIlosc.setCellValueFactory(new PropertyValueFactory<>("sztuki"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("suma"));

        tableRachunek.setItems(null);
        tableRachunek.setItems(dataRachunek);
        
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void eksportujPdf(ActionEvent event) {
        
        Stage stage = (Stage) bPdf.getScene().getWindow();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as PDF");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);
        
        if (!saveLoc.getName().endsWith(".pdf")) {
                saveLoc = new File(saveLoc.getAbsolutePath() + ".pdf");
            }
        
        final File saveLoc2 = saveLoc;
        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream(saveLoc));

            document.open();

            
            
            
            
            PdfPTable table = new PdfPTable(5); 
            
            //szerokosc
            float[] columnWidths = {0.5f, 2f, 1f, 1f, 1f};

            table.setWidths(columnWidths);
            
            
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font helvetica16=new Font(helvetica,12);
            
            

            PdfPCell cell1 = new PdfPCell(new Paragraph("ID",helvetica16));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Nazwa",helvetica16));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Cena szt.",helvetica16));            
            PdfPCell cell4 = new PdfPCell(new Paragraph("Ilość",helvetica16));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Cena",helvetica16));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);            
            table.addCell(cell4);
            table.addCell(cell5);
            
            String sztuki;
            
            for (Produkt pr : dataRachunek){
                table.addCell(new Paragraph(" "+pr.getId_produktu().toString(),helvetica16));
                table.addCell(new Paragraph(pr.getNazwa_produktu(),helvetica16));
                table.addCell(new Paragraph(pr.getCena_produktu().toString(),helvetica16));
                sztuki = pr.getSztuki()+"";
                table.addCell(new Paragraph(sztuki,helvetica16));
                table.addCell(new Paragraph(pr.getSuma().toString(),helvetica16));
            }
            
            table.addCell(new Paragraph(" "));
            table.addCell(new Paragraph(" "));
            table.addCell(new Paragraph(" "));
            table.addCell(new Paragraph("Suma",helvetica16));
            table.addCell(new Paragraph(lSumaWartosc.getText(),helvetica16));
            

            
            document.add(table);

            document.close();
            
            
            
            
            
        JFXButton okayBtn = new JFXButton("Anuluj");
        JFXButton openBtn = new JFXButton("Tak");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(openBtn, okayBtn), "Wygenerowano rachunek.", "Czy chcesz go otworzyć?");
        openBtn.setOnAction((ActionEvent event1) -> {
            try {
                Desktop.getDesktop().open(saveLoc2);
            } catch (Exception exp) {
                
            }
        });
            
            
            
            
            
            
            
        } catch(Exception e){
JFXButton openBtn = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(openBtn), "Błąd", "Podczas wykonywania operacji wystąpił nieoczekiwany błąd.");
        }
    }
        
        
        
        
        
        
        
        
        
    

    @FXML
    private void zaznaczonyPrzedmiot(MouseEvent event) {
    }
    
}
