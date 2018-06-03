/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.FXMLPracownikController;
import mkl_shop.pracownik.modele.Produkt;
import mkl_shop.pracownik.produkty.FXMLProduktyController;
import mkl_shop.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLListaPrzedmiotowController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bDodaj;
    @FXML
    private TableView<Produkt> tablePrzedmioty;
    @FXML
    private TableColumn<Produkt, Integer> columnIdPrzedmiotu;
    @FXML
    private TableColumn<Produkt, String> columnNazwa;
    @FXML
    private TableColumn<Produkt, String> columnOpis;
    @FXML
    private TableColumn<Produkt, Integer> columnNaStanie;
    @FXML
    private TableColumn<Produkt, Double> columnCena;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXTextField tfIlosc;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    private ObservableList<Produkt> dataProdukt;
    public static Produkt p = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //odblokowac przy wyborze przedmiotu z tabeli oraz wpisaniu ilosci produktow (domyslnie uzupelniac 1)
        bDodaj.setDisable(true);
        tfIlosc.setText("1");
        tfIlosc.setFocusTraversable(true);

        tfIlosc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty() || !Sprawdzanie.czyLiczby(newValue)) {
                    bDodaj.setDisable(true);
                } else {
                    bDodaj.setDisable(false);
                }
            }
        });

        dataProdukt = FXCollections.observableArrayList();
        boolean dodaj = true;
        Connection conn = DBConnection.Connect();

        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT produkty.id_produktu, produkty.nazwa_produktu, produkty.cena_produktu, produkty.opis_produktu, "
                    + "placowka_produkt.id_placowki, placowka_produkt.ilosc_produktow, placowka_produkt.id_produktu, placowka_produkt.id_placowka_produkt,"
                    + " placowka.id_placowki FROM produkty,placowka_produkt,placowka WHERE placowka.id_placowki = " + FXMLPracownikController.idPlacowki + " "
                    + "AND placowka.id_placowki = placowka_produkt.id_placowki AND placowka_produkt.id_produktu = produkty.id_produktu;");

            while (rs.next()) {
                for (Produkt p1 : FXMLZakupyController.dataRachunek) {
                    if (p1.getId_produktu() == rs.getInt(1)) {
                        dodaj = false;
                    }
                }
                if (dodaj) {
                    dataProdukt.add(new Produkt(rs.getInt("produkty.id_produktu"), rs.getString("produkty.nazwa_produktu"), rs.getDouble("produkty.cena_produktu"), rs.getString("produkty.opis_produktu"), rs.getInt("placowka_produkt.ilosc_produktow")));

                }
                dodaj = true;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLProduktyController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        for (Produkt p1 : dataProdukt){
//            if (FXMLZakupyController.dataRachunek.contains(p1))
//                dataProdukt.remove(p1);
//        }
        columnIdPrzedmiotu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnNaStanie.setCellValueFactory(new PropertyValueFactory<>("ilosc_produktow"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));

        tablePrzedmioty.setItems(null);
        tablePrzedmioty.setItems(dataProdukt);

        FilteredList<Produkt> filteredProdukt = new FilteredList<>(tablePrzedmioty.getItems(), e -> true);
        tfWyszukaj.setOnKeyReleased(e -> {
            tfWyszukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredProdukt.setPredicate((Predicate<? super Produkt>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getNazwa_produktu().toLowerCase().contains(lcFilter) || k.getId_produktu().toString().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produkt> sortedProdukty = new SortedList<>(filteredProdukt);
            tablePrzedmioty.setItems(sortedProdukty);
        });

    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dodajDoRachunku(ActionEvent event) {
        if (Integer.parseInt(tfIlosc.getText()) <= tablePrzedmioty.getSelectionModel().getSelectedItem().getIlosc_produktow()) {
            Double suma = Integer.parseInt(tfIlosc.getText()) * tablePrzedmioty.getSelectionModel().getSelectedItem().getCena_produktu();
            p = new Produkt(tablePrzedmioty.getSelectionModel().getSelectedItem().getId_produktu(), tablePrzedmioty.getSelectionModel().getSelectedItem().getNazwa_produktu(), tablePrzedmioty.getSelectionModel().getSelectedItem().getCena_produktu(), tablePrzedmioty.getSelectionModel().getSelectedItem().getOpis_produktu(), Integer.parseInt(tfIlosc.getText()), suma,tablePrzedmioty.getSelectionModel().getSelectedItem().getIlosc_produktow());
            FXMLZakupyController.dataRachunek.add(p);
            Stage stage = (Stage) bWyjscie.getScene().getWindow();
            stage.close();
        } else {
            JFXButton bOkay = new JFXButton("Ok");
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay), "Wystąpił błąd!", "Próba dodania do rachunku nie powiodła się. \nSprawdź czy żądana ilość jest na stanie.");
        }
    }

    @FXML
    private void zaznaczProdukt(MouseEvent event) {
        if (tablePrzedmioty.getSelectionModel().getSelectedItem() != null) {
            bDodaj.setDisable(false);
        }
    }

}
