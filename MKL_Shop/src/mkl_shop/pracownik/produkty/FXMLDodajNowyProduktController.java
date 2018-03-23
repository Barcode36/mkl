/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.produkty;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
public class FXMLDodajNowyProduktController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfNazwa;
    @FXML
    private JFXTextArea tfOpis;
    @FXML
    private JFXButton bZamowProdukt;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zamowProdukt(ActionEvent event) {
        //wyslanie prosby o zamowienie nowego produktu do kierownika placówki
        
    }
    
}
