/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.klient.FXMLKlientController;
import mkl_shop.pracownik.modele.Klient;
import mkl_shop.pracownik.modele.Produkt;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLPrzedmiotyController implements Initializable {

    @FXML
    private JFXListView<Produkt> lvPrzedmioty;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXButton bWybierz;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    
    private Produkt p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bWybierz.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);

        Connection conn = DBConnection.Connect();

        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_produktu,nazwa_produktu,cena_produktu,opis_produktu FROM produkty;");

            while (rs.next()) {
                lvPrzedmioty.getItems().add(new Produkt(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
            }

            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLKlientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        

        lvPrzedmioty.setCellFactory(param -> new ListCell<Produkt>() {
            @Override
            protected void updateItem(Produkt item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId_produktu() == null) {
                    setText(null);
                } else {
                    setText("[" + item.getId_produktu() + "] " + item.getNazwa_produktu() + "\n" + item.getOpis_produktu() + "\n" + item.getCena_produktu() + " zł");
                }
            }
        });
        
        
        
        
        
        
        

        FilteredList<Produkt> filteredProdukt = new FilteredList<>(lvPrzedmioty.getItems(), e -> true);
        tfWyszukaj.setOnKeyReleased(e -> {
            tfWyszukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredProdukt.setPredicate((Predicate<? super Produkt>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getId_produktu().toString().contains(lcFilter) || k.getNazwa_produktu().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produkt> sortedProdukt = new SortedList<>(filteredProdukt);
            lvPrzedmioty.setItems(sortedProdukt);
        });

    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    /**
     * Wybranie przedmiotu do reklamacji i przekazanie go do okna dodawania reklamacji.
     * @param event 
     */
    @FXML
    private void wybierz(ActionEvent event) {
        
        if (p != null){
        
            FXMLDodajReklamacjeController.p = p;
            Stage stage = (Stage) bWyjscie.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Utworzenie instancji klasy i przypisanie do niej wartości obiektu wybranego z listy.
     * @param event 
     */
    @FXML
    private void zaznaczPrzedmiot(MouseEvent event) {
        p = lvPrzedmioty.getSelectionModel().getSelectedItem();
    }

}
