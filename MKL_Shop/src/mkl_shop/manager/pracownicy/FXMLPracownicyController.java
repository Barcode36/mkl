/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.pracownicy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLPracownicyController implements Initializable {

    @FXML
    private JFXButton Button_Wyjscie;
    @FXML
    private JFXTextField TextField_Szukaj;
    @FXML
    private JFXListView<?> LIstView_Pracownicy;
    @FXML
    private MenuItem Menu_Szczegoly;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Button_Wyjscie.setFocusTraversable(false);
       TextField_Szukaj.setFocusTraversable(false);
        
        
        /*
        
        FilteredList<Klient> filteredKlient = new FilteredList <>(listView.getItems(), e->true);
        txSzukaj.setOnKeyReleased(e->{
            txSzukaj.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredKlient.setPredicate((Predicate<? super Klient>) k->{
                    if (newValue==null || newValue.isEmpty()){
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getImie().toLowerCase().contains(lcFilter) || k.getNazwisko().toLowerCase().contains(lcFilter) || k.getPlacowka().toLowerCase().contains(lcFilter)){
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
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Szczegoly(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLSzczegoly.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel dodawania klienta");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Wyjscie.getScene().getWindow());
        stage.showAndWait();
        
    }
    
}
