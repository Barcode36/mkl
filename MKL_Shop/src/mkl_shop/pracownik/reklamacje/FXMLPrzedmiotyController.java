/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLPrzedmiotyController implements Initializable {

    @FXML
    private JFXListView<?> lvPrzedmioty;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXButton bWybierz;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        
        
        
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
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void wybierz(ActionEvent event) {
    }
    
}
