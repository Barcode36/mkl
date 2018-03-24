/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.modele.Klient;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLKlientController implements Initializable {

    @FXML
    private JFXButton bNowyKlient;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXListView<Klient> lvKlienci;
    @FXML
    private MenuItem cmEdytuj;
    @FXML
    private MenuItem cmUsun;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    
    
    private ObservableList <Klient> dataKlienci;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bWyjscie.setFocusTraversable(false);
        bNowyKlient.setFocusTraversable(false);
        tfWyszukaj.setFocusTraversable(false);
        
        dataKlienci = FXCollections.observableArrayList();
        
        Connection conn = DBConnection.Connect();
        
        
        /*
        
        FilteredList<Klient> filteredKlient = new FilteredList <>(listView.getItems(), e->true);
        txSzukaj.setOnKeyReleased(e->{
            txSzukaj.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredKlient.setPredicate((Predicate<? super Klient>) k->{
                    if (newValue==null || newValue.isEmpty()){
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getImie().toLowerCase().contains(lcFilter) || k.getNazwisko().toLowerCase().contains(lcFilter) || k.getNrKarty().toLowerCase().contains(lcFilter)){
                        return true;
                    }
                    return false;
                });
                });
            SortedList<Klient> sortedKlient = new SortedList<>(filteredKlient);            
            listView.setItems(sortedKlient);            
        });
        
        */
        
        
        
        
    }    

    @FXML
    private void dodajKlienta(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLDodajKlienta.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel dodawania klienta");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowyKlient.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void edytuj(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLEdytujKlienta.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel dodawania klienta");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowyKlient.getScene().getWindow());
        stage.showAndWait();
        
        
    }

    @FXML
    private void usun(ActionEvent event) {
        //okno dialogowe TAK/NIE czy usunac klienta
        
        
    }
    
}
