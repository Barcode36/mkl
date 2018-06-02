/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLDodajKlientaController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bDodaj;
    @FXML
    private JFXTextField tfImie;
    @FXML
    private JFXTextField tfKodPocztowy;
    @FXML
    private JFXTextField tfAdres;
    @FXML
    private JFXTextField tfNumerKarty;
    @FXML
    private JFXTextField tfMiejscowosc;
    @FXML
    private JFXTextField tfNrTelefonu;
    @FXML
    private JFXTextField tfNazwisko;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bDodaj.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);


        //wczytac nowy numer dla karty
        //dodac nowa karte z zerowa iloscia punktow
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dodajKlienta(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.Connect();
        
        if (Sprawdzanie.czyLitery(tfImie.getText()) && Sprawdzanie.czyLitery(tfNazwisko.getText()) && Sprawdzanie.czyKodPocztowy(tfKodPocztowy.getText()) && Sprawdzanie.czyLitery(tfMiejscowosc.getText()) && !tfAdres.getText().isEmpty() && Sprawdzanie.czyLiczby(tfNrTelefonu.getText()) && tfNrTelefonu.getText().length()==9 ){
            conn.createStatement().executeUpdate("INSERT INTO klient (id_klienta, imie_klienta, nazwisko_klienta, kod_pocztowy_klienta, miejscowosc_klienta,adres_klienta,"
                    + " telefon_klienta, liczba_punktow) VALUES (NULL,'"+tfImie.getText()+"','"+tfNazwisko.getText()+"','"+tfKodPocztowy.getText()+"','"+tfMiejscowosc.getText()+"'"
                            + ",'"+tfAdres.getText()+"','"+tfNrTelefonu.getText()+"',0);");
            
            Stage stage = (Stage) bWyjscie.getScene().getWindow();
            stage.close();
            
        } else {
            //alert
            JFXButton bOkay2 = new JFXButton("OK");
                AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay2), "Wystąpił błąd.", "Sprawdź czy pola zostały uzupełnione prawidłowo.");
            
            
        }
        
        //powrót do innego okna
        
        
    }
    
}
