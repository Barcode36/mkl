/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLKlientController implements Initializable {

    @FXML
    private JFXButton bNowyKlient;
    @FXML
    private JFXButton bEdycja;
    @FXML
    private JFXTextField tfWyszukaj;

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bEdycja.setDisable(true);
        bEdycja.setFocusTraversable(false);
        bNowyKlient.setFocusTraversable(false);
        tfWyszukaj.setFocusTraversable(false);
        
    }    

    @FXML
    private void dodajKlienta(ActionEvent event) {
        
    }

    @FXML
    private void edytujKlienta(ActionEvent event) {
        
    }
    
}
