/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.FXMLPracownikController;
import mkl_shop.pracownik.modele.Produkt;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLDodajReklamacjeController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXButton bNowaReklamacja;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextArea tfOpis;
    @FXML
    private JFXTextField tfPrzedmiotReklamacji;
    @FXML
    private FontAwesomeIconView bWybierzProdukt;
    
    public static Produkt p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfPrzedmiotReklamacji.setDisable(true);
        
        
    }    

    /**
     * Dodanie nowej reklamacji oraz walidacja danych.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void dodajReklamacje(ActionEvent event) throws SQLException {
       
        
        Connection conn = DBConnection.Connect();
        
        
        if (!tfOpis.getText().isEmpty() && p != null){
            LocalDate ld = LocalDate.now();
            conn.createStatement().executeUpdate("INSERT INTO reklamacja (id_placowki,id_produktu,id_reklamacji,opis,stan,data) "
                    + "VALUES ("+FXMLPracownikController.idPlacowki+","+p.getId_produktu()+",null,\""+tfOpis.getText()+"\",\"W trakcie\",'"+ ld.toString() +"');");
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
        p = null;
        
        } else {
            JFXButton bCancel1 = new JFXButton("Ok");
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel1), "Błąd", "Wystąpił błąd podczas dodawania reklamacji. \n Sprawdź poprawność wpisanych danych.");
   
        }
        
        
        
       
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    /**
     * Otworzenie okna umożliwiającego wybranie przedmiotu do reklamacji.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void wybierzPrzedmiot(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLPrzedmioty.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowaReklamacja.getScene().getWindow());
        stage.showAndWait();
    }

    /**
     * Odświeżenie danych o produkcie do reklamacji.
     * @param event 
     */
    @FXML
    private void uzupelnijProdukt(MouseEvent event) {
        if (p != null)
            tfPrzedmiotReklamacji.setText("[" + p.getId_produktu() + "] " +p.getNazwa_produktu() + " " + p.getCena_produktu() + " zł");
    }
    
}
