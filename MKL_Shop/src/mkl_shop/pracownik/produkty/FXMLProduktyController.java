/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.produkty;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLProduktyController implements Initializable {

    @FXML
    private TableView<?> tableProdukty;
    @FXML
    private TableColumn<?, ?> columnIDProduktu;
    @FXML
    private TableColumn<?, ?> columnNazwa;
    @FXML
    private TableColumn<?, ?> columnCena;
    @FXML
    private TableColumn<?, ?> columnIlosc;
    @FXML
    private TableColumn<?, ?> columnOpis;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfSzukaj;
    @FXML
    private MenuItem miZapotrzebowanie;
    @FXML
    private JFXButton bNowyProdukt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
                /*
        
        FilteredList<Produkty> filteredProdukt = new FilteredList <>(listView.getItems(), e->true);
        tfSzukaj.setOnKeyReleased(e->{
            tfSzukaj.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredProdukt.setPredicate((Predicate<? super Produkty>) k->{
                    if (newValue==null || newValue.isEmpty()){
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getNazwa().toLowerCase().contains(lcFilter) || k.getIdProduktu().toLowerCase().contains(lcFilter) ){
                        return true;
                    }
                    return false;
                });
                });
            SortedList<Produkty> sortedProdukty = new SortedList<>(filteredProdukt);            
            tableProdukty.setItems(sortedProdukty);            
        });
        
        */
        
        
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
          Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void zglosZapotrzebowanie(ActionEvent event) {
        //wyslanie wiadomosci do gory o brakach w magazynie i wyswietlenie komunikatu o wysłąniu prośby
        
    }

    @FXML
    private void zamowNowyProdukt(ActionEvent event) throws IOException {
        //wyslanie wiadomosci do góry o prośbie dodania nowego produktu
        
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLDodajNowyProdukt.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel zamawiania produktu");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowyProdukt.getScene().getWindow());
        stage.showAndWait();
        
    }
    
}
