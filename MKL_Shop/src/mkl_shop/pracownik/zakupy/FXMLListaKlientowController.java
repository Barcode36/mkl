/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.klient.FXMLKlientController;
import mkl_shop.pracownik.modele.Klient;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLListaKlientowController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXListView<Klient> lvKlienci;
    @FXML
    private JFXButton bWybierz;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    public static Integer idKlienta;
    public static Klient k;

    //FXMLListaKlientowController flkc = new FXMLListaKlientowController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bWyjscie.setFocusTraversable(false);
        bWybierz.setFocusTraversable(false);

        Connection conn = DBConnection.Connect();

        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_klienta, imie_klienta, nazwisko_klienta, kod_pocztowy_klienta, "
                    + "miejscowosc_klienta, adres_klienta, telefon_klienta, numer_karty, liczba_punktow "
                    + "FROM klient;");

            while (rs.next()) {
                lvKlienci.getItems().add(new Klient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
            }

            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLListaKlientowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FilteredList<Klient> filteredKlient = new FilteredList<>(lvKlienci.getItems(), e -> true);
        tfWyszukaj.setOnKeyReleased(e -> {
            tfWyszukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredKlient.setPredicate((Predicate<? super Klient>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getImie_klienta().toLowerCase().contains(lcFilter) || k.getNazwisko_klienta().toLowerCase().contains(lcFilter) || k.getNumer_karty().toString().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Klient> sortedKlient = new SortedList<>(filteredKlient);
            lvKlienci.setItems(sortedKlient);
        });

    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void wybierzKlienta(ActionEvent event) throws IOException {
        if (k != null) {
            FXMLZakupyController fzc = new FXMLZakupyController();            
            fzc.setKlient(k);    
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLZakupy.fxml"));
            //fxmlLoader.getNamespace().put("lKlient", k.getImie_klienta()+ " " + k.getNazwisko_klienta() + " (" + k.getNumer_karty() + ")");
            
            FXMLZakupyController.k1 = new Klient (k.getId_klienta(),k.getImie_klienta(),k.getNazwisko_klienta(),k.getKod_pocztowy_klienta(),k.getMiejscowosc_klienta(),k.getAdres_klienta(),k.getTelefon_klienta(),k.getNumer_karty(),k.getLiczba_punktow());
            
            
            Stage stage = (Stage) bWyjscie.getScene().getWindow();
            stage.close();
       
            
        }
    }

    @FXML
    private void wybranyKlient(MouseEvent event) {
        k = lvKlienci.getSelectionModel().getSelectedItem();
        
        //System.out.println(k.getImie_klienta()+ " " + k.getNazwisko_klienta() + " (" + k.getNumer_karty() + ")");
    }
}
