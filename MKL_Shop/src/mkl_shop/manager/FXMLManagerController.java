/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.connection.DBConnection;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //private DBConnection dc;
    @FXML
    private JFXButton Button_Placowki;
    @FXML
    private JFXButton Button_Wiadomosci;

    @FXML
    private JFXButton Button_Wyjscie;
    @FXML
    private JFXButton Button_Pracownicy;
    @FXML
    private JFXButton Button_Raporty;
    
    public static Integer idManagera;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //dc = new DBConnection();
        java.sql.Connection conn = DBConnection.Connect();

        Button_Placowki.setFocusTraversable(false);
        Button_Wiadomosci.setFocusTraversable(false);

        Button_Wyjscie.setFocusTraversable(false);
        Button_Pracownicy.setFocusTraversable(false);
        Button_Raporty.setFocusTraversable(false);

    }

    @FXML
    private void goPlacowki(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("placowki/FXMLPlacowki.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Pracownicy.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void goWiadomosci(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("wiadomosci/FXMLWiadomosci.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Pracownicy.getScene().getWindow());
       stage.showAndWait();
  }

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goPracownicy(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("pracownicy/FXMLPracownicy.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Pracownicy.getScene().getWindow());
        stage.showAndWait();

    }

    @FXML
    private void goRaporty(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("raporty/FXMLRaporty.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Pracownicy.getScene().getWindow());
        stage.showAndWait();
    }

}
