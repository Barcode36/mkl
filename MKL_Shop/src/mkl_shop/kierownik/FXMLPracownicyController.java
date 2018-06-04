/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.kierownik;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLPracownicyController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private TableView<?> tablePracownicy;
    @FXML
    private TableColumn<?, ?> columnIDPracownika;
    @FXML
    private TableColumn<?, ?> columnImie;
    @FXML
    private TableColumn<?, ?> columnNazwisko;
    @FXML
    private TableColumn<?, ?> columnNrTel;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfSzukaj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void zaznaczProdukt(MouseEvent event) {
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
    }
    
}
