/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.modele.Klient;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLKlientController implements Initializable {

    @FXML
    private JFXButton bNowyKlient;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXListView<Klient> lvKlienci;
    @FXML
    private MenuItem cmEdytuj;
    @FXML
    private MenuItem cmUsun;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    public static Integer idKlienta;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bWyjscie.setFocusTraversable(false);
        bNowyKlient.setFocusTraversable(false);
        tfWyszukaj.setFocusTraversable(false);

        Connection conn = DBConnection.Connect();

        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_klienta, imie_klienta, nazwisko_klienta, kod_pocztowy_klienta, "
                    + "miejscowosc_klienta, adres_klienta, telefon_klienta, id_klienta as numer_karty, liczba_punktow "
                    + "FROM klient;");

            while (rs.next()) {
                lvKlienci.getItems().add(new Klient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
            }

            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLKlientController.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Otworzenie nowego okna, umożliwiającego dodanie nowego klienta.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void dodajKlienta(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLDodajKlienta.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel dodawania klienta");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowyKlient.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    /**
     * Otworzenie nowego okna umożliwiającego edycję wybranego z listy klienta.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void edytuj(ActionEvent event) throws IOException {
        if (lvKlienci.getSelectionModel().getSelectedItem() != null) {

            idKlienta = lvKlienci.getSelectionModel().getSelectedItem().getId_klienta();

            Stage stage;
            Parent root;

            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("FXMLEdytujKlienta.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Panel dodawania klienta");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(bNowyKlient.getScene().getWindow());
            stage.showAndWait();

        }
    }
    
    
    /**
     * Usunięcie klienta wybranego z listy i wyświetlenie okna dialogowego z potwierdzeniem naszej operacji.
     * @param event 
     */
    @FXML
    private void usun(ActionEvent event) {
       

        if (lvKlienci.getSelectionModel().getSelectedItem() != null) {

            JFXButton bCancel = new JFXButton("Nie");
            JFXButton bOkay = new JFXButton("Tak");
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Usuwanie klienta.", "Czy na pewno chcesz usunąć zaznaczonego klienta?");

            bOkay.setOnAction((ActionEvent event1) -> {
                try {
                    Connection conn = DBConnection.Connect();
                    int id = lvKlienci.getSelectionModel().getSelectedItem().getId_klienta();
                    lvKlienci.getItems().remove(lvKlienci.getSelectionModel().getSelectedIndex());
                    
                    conn.createStatement().executeUpdate("DELETE FROM klient WHERE id_klienta=" + id + ";");

                    conn.close();
                } catch (Exception exp) {
                    
                }
            });

        }

    }

}
