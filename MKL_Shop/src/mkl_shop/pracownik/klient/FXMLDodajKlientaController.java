/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

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
    private void dodajKlienta(ActionEvent event) {
        
        
        
    }
    
}
