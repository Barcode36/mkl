/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.kierownik;

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

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLNowyProduktController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfNazwa;
    @FXML
    private JFXTextArea tfOpis;
    @FXML
    private JFXButton bNowyProdukt;
    @FXML
    private JFXTextField tfCena;
    @FXML
    private JFXTextField tfIlosc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
    }

    @FXML
    private void dodajNowy(ActionEvent event) {
    }
    
}
