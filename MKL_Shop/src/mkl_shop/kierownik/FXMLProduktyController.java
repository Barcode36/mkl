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
import javafx.scene.control.MenuItem;
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
public class FXMLProduktyController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private TableView<?> tableProdukty;
    @FXML
    private TableColumn<?, ?> columnIDProduktu;
    @FXML
    private TableColumn<?, ?> columnNazwa;
    @FXML
    private TableColumn<?, ?> columnCena;
    @FXML
    private TableColumn<?, ?> columnIlosc;
    @FXML
    private TableColumn<?, ?> columnOpis;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfSzukaj;
    @FXML
    private JFXButton bNowyProdukt;

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

    @FXML
    private void zamowNowyProdukt(ActionEvent event) {
    }
    
}
