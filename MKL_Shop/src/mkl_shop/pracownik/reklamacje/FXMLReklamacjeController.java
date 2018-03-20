/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLReklamacjeController implements Initializable {

    @FXML
    private TableView<?> tableReklamacje;
    @FXML
    private TableColumn<?, ?> columnIdReklamacji;
    @FXML
    private TableColumn<?, ?> columnOpis;
    @FXML
    private TableColumn<?, ?> columnIdTransakcji;
    @FXML
    private TableColumn<?, ?> columnIdProduktu;
    @FXML
    private TableColumn<?, ?> columnNazwaProduktu;
    @FXML
    private TableColumn<?, ?> columnCenaProduktu;
    @FXML
    private TableColumn<?, ?> columnData;
    @FXML
    private TableColumn<?, ?> columnStatus;
    @FXML
    private JFXButton bNowaReklamacja;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXTextArea tfOpis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nowaReklamacja(ActionEvent event) {
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }
    
}
