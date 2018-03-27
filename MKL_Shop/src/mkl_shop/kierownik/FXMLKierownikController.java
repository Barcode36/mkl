/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.kierownik;

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
public class FXMLKierownikController implements Initializable {

    @FXML
    private JFXButton Button_Wyjscie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }
    
}
