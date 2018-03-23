/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML
    private MenuItem cmFinalizacja;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfOpis.setFocusTraversable(true);
        tfOpis.setDisable(true);
        tfOpis.setText(" ");
    }    

    @FXML
    private void nowaReklamacja(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLDodajReklamacje.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowaReklamacja.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void finalizujReklamacje(ActionEvent event) {
        
    }
    
}
