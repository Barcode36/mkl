/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.kierownik;

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
import mkl_shop.MKL_Shop;
import mkl_shop.pracownik.FXMLPracownikController;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLKierownikController implements Initializable {

    @FXML
    private JFXButton Button_Wyjscie;
    

    public static Integer idPracownika;
    public static Integer idPlacowki;
    @FXML
    private JFXButton bPracownicy;
    @FXML
    private JFXButton bProdukty;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void obsluga(ActionEvent event) throws IOException {
        
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(MKL_Shop.class.getResource("pracownik/FXMLPracownik.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel reklamacji");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Wyjscie.getScene().getWindow());
        stage.showAndWait();
        FXMLPracownikController.idKierownika = idPracownika;
        FXMLPracownikController.idPlacowki = idPlacowki;
        FXMLPracownikController.idPracownika = idPracownika;
        
    }

    @FXML
    private void otworzPracownicy(ActionEvent event) throws IOException {
                Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(MKL_Shop.class.getResource("kierownik/FXMLPracownicy.fxml"));
        stage.setScene(new Scene(root));
        //stage.setTitle("Panel reklamacji");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Wyjscie.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void otworzProdukty(ActionEvent event) throws IOException {
                Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(MKL_Shop.class.getResource("kierownik/FXMLProdukty.fxml"));
        stage.setScene(new Scene(root));
        //stage.setTitle("Panel reklamacji");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Wyjscie.getScene().getWindow());
        stage.showAndWait();
    }
    
}
