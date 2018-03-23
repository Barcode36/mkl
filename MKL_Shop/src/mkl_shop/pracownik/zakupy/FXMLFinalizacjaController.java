/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLFinalizacjaController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bPdf;
    @FXML
    private TableView<?> tableRachunek;
    @FXML
    private TableColumn<?, ?> columnIdProduktu;
    @FXML
    private TableColumn<?, ?> columnNazwa;
    @FXML
    private TableColumn<?, ?> columnOpis;
    @FXML
    private TableColumn<?, ?> columnCenaSzt;
    @FXML
    private TableColumn<?, ?> columnIlosc;
    @FXML
    private TableColumn<?, ?> columnCena;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void eksportujPdf(ActionEvent event) {
        
    }

    @FXML
    private void zaznaczonyPrzedmiot(MouseEvent event) {
    }
    
}
