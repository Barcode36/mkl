/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.placowki;

import com.jfoenix.controls.JFXButton;
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
 * @author Yser
 */
public class FXMLPlacowkiController implements Initializable {

    @FXML
    private TableView<?> Tabela_Placowki;
    @FXML
    private MenuItem Menu_Szczegoly;
    @FXML
    private TableColumn<?, ?> Kolumna_Lp;
    @FXML
    private TableColumn<?, ?> Kolumna_kod_pocztowy;
    @FXML
    private TableColumn<?, ?> Kolumna_Miejscowosc;
    @FXML
    private TableColumn<?, ?> Kolumna_Adres;
    @FXML
    private TableColumn<?, ?> Kolumna_Telefon;
    @FXML
    private JFXTextField TextField_Placowki;
    @FXML
    private JFXButton Button_Wyjscie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Button_Wyjscie.setFocusTraversable(false);
       TextField_Placowki.setFocusTraversable(false);
    
    }    

    @FXML
    private void goSzczegoly(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLSzczegoly.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel dodawania klienta");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Wyjscie.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void wyjscie(ActionEvent event) {
          Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }
    
}
