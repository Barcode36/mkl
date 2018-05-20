/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
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
import javafx.stage.Stage;
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
        
        lSumaWartosc.setText(FXMLZakupyController.getCena().toString());
        
        
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
        
    }

    @FXML
    private void zaznaczonyPrzedmiot(MouseEvent event) {
    }
    
}
