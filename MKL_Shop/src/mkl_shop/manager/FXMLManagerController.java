/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mkl_shop.connection.DBConnection;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
     private DBConnection dc;
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
       dc = new DBConnection();
          java.sql.Connection conn = dc.Connect();
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
    }

    @FXML
    private void otworzKlient(ActionEvent event) {
    }
    
}
