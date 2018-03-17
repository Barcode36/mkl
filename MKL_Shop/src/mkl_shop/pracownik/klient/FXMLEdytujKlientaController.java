/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLEdytujKlientaController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bZapisz;
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
    private FontAwesomeIconView bNumerKarty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bZapisz.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);
        
        
        //wczytac stary numer karty i pola
        
        
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zapiszZmiany(ActionEvent event) {
        
        //usunac stara karte z bazy, lub wyzerowac je stan i nadac nowy numer
        
        
    }

    @FXML
    private void generujNumer(MouseEvent event) {
        System.out.println("generuje nowy numer karty");
        
        
        
    }
    
}
