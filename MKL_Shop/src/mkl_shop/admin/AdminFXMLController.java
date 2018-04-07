/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Łukasz
 */
public class AdminFXMLController implements Initializable {

    @FXML
    private Button btnZarzadzajKontami;
    @FXML
    private Button btnZarzadzajPlacowkami;
    @FXML
    private Button btnZarzadzajTowarami;
    @FXML
    private Button btnWiadomosci;
    @FXML
    private Button btnWyjscie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void PressedKonta(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("konta/FXMLKonta.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Zarządzaj kontami");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnZarzadzajKontami.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void PressedPlacowki(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("placowki/FXMLPlacowki.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Placówki");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnZarzadzajPlacowkami.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void PressedTowar(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("towar/FXMLTowar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Towar");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnZarzadzajTowarami.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void PressedWiadomosci(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("wiadomosci/FXMLWiadomosci.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Wiadomości");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnWiadomosci.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void PressedWyjdz(MouseEvent event) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }

}
