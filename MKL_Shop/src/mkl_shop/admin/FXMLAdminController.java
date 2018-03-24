/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Łukasz
 */
public class FXMLAdminController implements Initializable {

    @FXML
    private JFXButton btnPlacowki;
    @FXML
    private JFXButton btnWiadomosci;
    @FXML
    private JFXButton btnHasla;
    @FXML
    private JFXButton btnWyjscie;
    @FXML
    private JFXButton btnPracownicy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void Wyjscie(ActionEvent event) {
    }


    @FXML
    private void zarzadzajPlacowki(ActionEvent event) {
    }

    @FXML
    private void zarzadzajWiadomosci(ActionEvent event) {
    }

    @FXML
    private void zarzadzajHasla(ActionEvent event) {
    }
    
}
