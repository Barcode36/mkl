/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.MKL_Shop;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.modele.Klient;
import mkl_shop.pracownik.modele.Produkt;
import static mkl_shop.pracownik.zakupy.FXMLListaKlientowController.k;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLZakupyController implements Initializable {

    @FXML
    private JFXButton bDoRachunku;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bUsunPrzedmiot;
    @FXML
    private TableView<Produkt> tableRachunek;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXButton bZatwierdz;
    @FXML
    private Label lSuma;
    @FXML
    private Label lSumaWartosc;
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
    private JFXCheckBox cStalyKlient;
    @FXML
    private Label lStalyKlient;
    @FXML
    private FontAwesomeIconView bListaKlientow;

    //public FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLZakupy.fxml"));
    public static ObservableList<Produkt> dataRachunek;
    public static Klient k1;
    Integer idKlienta;
    private static Double price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(FXMLListaPrzedmiotowController.p.getOpis_produktu());
        //bUsunPrzedmiot.setDisable(true);

        //lStalyKlient.setText("c");
        dataRachunek = FXCollections.observableArrayList();
        odswiezRachunek();

        // ***
//        if (FXMLListaKlientowController.k != null) {
//            lStalyKlient.setText(k1.getImie_klienta() + " " + k1.getNazwisko_klienta() + " (" + k1.getNumer_karty() + ")");
//        }
        // ***
        dataRachunek.addListener(new ListChangeListener<Produkt>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produkt> c) {
                Double suma = 0.0;
                while (c.next()) {

                    for (Produkt p1 : dataRachunek) {
                        suma += p1.getSuma();
                    }
                    lSumaWartosc.setText(suma.toString() + " zł");
                    setCena(suma);
                }
            }

        });

    }
    
    private void setCena(double cena){
        price = cena;
    }
    
    public static Double getCena(){
        return price;
    }

    @FXML
    private void dodajDoRachunku(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLListaPrzedmiotow.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Lista produktów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bDoRachunku.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
        k1 = null;
    }

    @FXML
    private void usunZRachunku(ActionEvent event) {
        //usuwanie wybranego przedmiotu + komunikat tak/nie

        JFXButton bOkay = new JFXButton("Tak, usuń");
        JFXButton bCancel = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdź decyzję", "Czy na pewno chcesz usunąć wpis?");
        bOkay.setOnAction((ActionEvent event1) -> {
            try {
                //zrobic usuniecie, ak problem to wywali błąd

                tableRachunek.getItems().remove(tableRachunek.getSelectionModel().getSelectedIndex());
                //update nowej ceny rachunku :)
            } catch (Exception exp) {
                AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel), "Błąd", "Nie można usunąć pozycji");
            }
        });

    }

    @FXML
    private void zaznaczonyPrzedmiot(MouseEvent event) {
        // jak zaznaczony obiekt z tabeli to odblokowac przycisk usuwania

        //odswiezRachunek();
    }

    @FXML
    private void finalizujTransakcje(ActionEvent event) throws IOException {
        
        
        if (!dataRachunek.isEmpty()){

            JFXButton bOkay = new JFXButton("Tak");
        JFXButton bCancel = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdzenie transakcji", "Czy na pewno chcesz zatwierdzić transakcję?");
        bOkay.setOnAction((ActionEvent event1) -> {
            
            
            
                try {
                    Stage stage = (Stage) bWyjscie.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLFinalizacja.fxml"));
                    stage.setScene(new Scene(root));
                    
                    //insert do bazy i update , staly klient i punkty
                    //k1 dataRachunek price -> pola
                    
                    Connection conn = DBConnection.Connect();
                    
                    
                    if (cStalyKlient.isSelected()){
                        //inserty do ilosci punktow na karcie klienta
                        
                        
                    }
                    
                    //inserty do koszyka(transakcje)
                    
                    
                    
                    conn.close();
                    k1 = null;
                } catch (IOException ex) {
                    Logger.getLogger(FXMLZakupyController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLZakupyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
            });
            
            
        } else {
            JFXButton bCancel1 = new JFXButton("Ok");
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel1), "Błąd", "Aby sfinalizować transakcję, na rachunku muszą znajdować się produkty.");
   
        }
        
        
        
        
        
        
    }

    @FXML
    private void wybierzKlienta(ActionEvent event) {
        if (cStalyKlient.isSelected()) {
            bListaKlientow.setDisable(false);
        } else {
            bListaKlientow.setDisable(true);
        }
    }

    @FXML
    private void wybierzKlientaZListy(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLListaKlientow.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bDoRachunku.getScene().getWindow());
        stage.showAndWait();

    }

    public void setKlient(Klient k) {
        idKlienta = k.getId_klienta();
        //System.out.println(idKlienta);
        //this.lStalyKlient.setText("1iofajeof aewfjewoif");
        //lStalyKlient.setText(k1.getImie_klienta()+ " " + k1.getNazwisko_klienta() + " (" + k1.getNumer_karty() + ")");
    }

    public void odswiezRachunek() {

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
    private void uzupelnijKlienta(MouseEvent event) {
        if (k1 != null)
            lStalyKlient.setText(k1.getImie_klienta()+ " " + k1.getNazwisko_klienta() + " (" + k1.getNumer_karty() + ") " + k1.getLiczba_punktow() + "pkt");
    }

}
