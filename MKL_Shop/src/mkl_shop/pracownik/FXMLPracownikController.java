/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLPracownikController implements Initializable {

    @FXML
    private JFXButton bZakupy;
    @FXML
    private JFXButton bReklamacje;
    @FXML
    private JFXButton bProdukty;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bKlient;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bZakupy.setFocusTraversable(false);
        bKlient.setFocusTraversable(false);
        bProdukty.setFocusTraversable(false);
        bReklamacje.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);
        
        
    }    

    @FXML
    private void zrobZakupy(ActionEvent event) {
        
    }

    @FXML
    private void otworzReklamacje(ActionEvent event) {
        
    }

    @FXML
    private void otworzProdukty(ActionEvent event) {
        
    }

    @FXML
    private void wylogujSie(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void otworzKlient(ActionEvent event) {
        
    }
    
}
