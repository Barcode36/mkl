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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.connection.DBConnection;

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

    
    
    private Boolean nowaKarta;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bZapisz.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);
        nowaKarta = false;
        
        //wczytac stary numer karty i pola
        //int id = FXMLKlientController.idKlienta;
        
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_klienta,imie_klienta,nazwisko_klienta,adres_klienta,"
                    + "kod_pocztowy_klienta,miejscowosc_klienta,numer_karty,telefon_klienta FROM klient WHERE "
                    + "id_klienta="+ FXMLKlientController.idKlienta+";");
            rs.next();
            
            tfImie.setText(rs.getString("imie_klienta"));
            tfNazwisko.setText(rs.getString("nazwisko_klienta"));
            tfAdres.setText(rs.getString("adres_klienta"));
            tfKodPocztowy.setText(rs.getString("kod_pocztowy_klienta"));
            tfNrTelefonu.setText(rs.getString("telefon_klienta"));
            tfMiejscowosc.setText(rs.getString("miejscowosc_klienta"));
            tfNumerKarty.setText(rs.getString("numer_karty"));
            
            
            
            
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zapiszZmiany(ActionEvent event) {
        
        //usunac stara karte z bazy, lub wyzerowac je stan i nadac nowy numer
        // if nowakarta true wyrobic nowa, jak false to nie
        
        
        
    }

    @FXML
    private void generujNumer(MouseEvent event) {
        System.out.println("generuje nowy numer karty");
        
        if (!nowaKarta){
            //generowanie nowego numeru, tylko raz na sesje
            
            nowaKarta = true;
        }
        
        
    }
    
}
