/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mkl_shop.admin.modele.Pracownik;
import mkl_shop.admin.modele.Placowka;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.klient.FXMLEdytujKlientaController;

/**
 * FXML Controller class
 *
 * @author Łukasz
 */
public class AdminFXMLController implements Initializable {

    @FXML
    private TableView<Pracownik> tableKonta;
    @FXML
    private TableColumn<Pracownik, String> colImie;
    @FXML
    private TableColumn<Pracownik, String> colNazwisko;
    @FXML
    private TableColumn<Pracownik, Integer> colPesel;
    @FXML
    private TableColumn<Pracownik, Integer> colNrTel;
    @FXML
    private TableColumn<Pracownik, Integer> colPlacowka;
    @FXML
    private TableColumn<Pracownik, String> colLogin;
    @FXML
    private JFXTextField txSzukaj;
    @FXML
    private JFXButton btnDodajKonto;
    @FXML
    private JFXButton btnUsunKonto;
    @FXML
    private JFXButton btnWyczyscKonta;
    @FXML
    private JFXButton btnAktualizujKonto;
    @FXML
    private JFXTextField txImie;
    @FXML
    private JFXTextField txNazwisko;
    @FXML
    private JFXTextField txPesel;
    @FXML
    private JFXTextField txNumerTel;
    @FXML
    private JFXTextField txPlacowka;
    @FXML
    private JFXTextField txLogin;
    @FXML
    private JFXTextField txHaslo;
    @FXML
    private TableView<Placowka> tablePlacowki;
    @FXML
    private TableColumn<Placowka, String> colMiejscowosc;
    @FXML
    private TableColumn<Placowka, String> colAdres;
    @FXML
    private TableColumn<Placowka, String> colKodPocztowy;
    @FXML
    private TableColumn<Placowka, Integer> colTelKontaktowyPlacowki;
    @FXML
    private JFXTextField txMiejscowosc;
    @FXML
    private JFXTextField txAdres;
    @FXML
    private JFXTextField txKodPocztowy;
    @FXML
    private JFXTextField txTelefonKontaktowyPlacowki;
    @FXML
    private JFXButton btnDodajPlacowke;
    @FXML
    private JFXButton btnUsunPlacowke;
    @FXML
    private JFXButton btnAktualizujPlacowke;
    @FXML
    private JFXButton btnWyczyscPlacowka;
    @FXML
    private JFXButton btnWyjscie;
    @FXML
    private JFXComboBox<String> cbRola;
    private ObservableList<Pracownik> data;
    private ObservableList<Placowka> data1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbRola.getItems().addAll(
                "Pracownik",
                "Kierownik",
                "Menadżer"
        );
        LoadDataPracownik();
    }

    public void LoadDataPracownik() {
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_pracownika,imie_pracownika,nazwisko_pracownika,pesel_pracownika,"
                    + "telefon_pracownika,login,haslo,rola,adres_placowki FROM pracownik,placowka where pracownik.id_placowki = "
                    + "placowka.id_placowki  " + ";");
            while (rs.next()) {
                data.add(new Pracownik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            colImie.setCellValueFactory(new PropertyValueFactory<>("imie_pracownika"));
            colNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko_pracownika"));
            colPesel.setCellValueFactory(new PropertyValueFactory<>("pesel_pracownika"));
            colNrTel.setCellValueFactory(new PropertyValueFactory<>("telefon_pracownika"));
            colPlacowka.setCellValueFactory(new PropertyValueFactory<>("adres_placowki"));
            colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));

            tableKonta.setItems(null);
            tableKonta.setItems(data);

            tableKonta.setOnMousePressed((MouseEvent event) -> {
                if (tableKonta.getSelectionModel().getSelectedItem() != null) {

                    txImie.setText(tableKonta.getSelectionModel().getSelectedItem().getImie_pracownika());
                    txNazwisko.setText(tableKonta.getSelectionModel().getSelectedItem().getNazwisko_pracownika());
                    int p2 = tableKonta.getSelectionModel().getSelectedItem().getPesel_pracownika();
                    txPesel.setText(Integer.toString(p2));
                    int tel2 = tableKonta.getSelectionModel().getSelectedItem().getTelefon_pracownika();
                    txNumerTel.setText(Integer.toString(tel2));
                    txLogin.setText(tableKonta.getSelectionModel().getSelectedItem().getLogin());
                    txHaslo.setText(tableKonta.getSelectionModel().getSelectedItem().getHaslo());
                    cbRola.setValue(tableKonta.getSelectionModel().getSelectedItem().getRola());
                    txPlacowka.setText(tableKonta.getSelectionModel().getSelectedItem().getAdres_placowki());
                }
            });

            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void LoadDataPlacowka() {
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * from placowka;");
            while (rs.next()) {
                data1.add(new Placowka(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            colMiejscowosc.setCellValueFactory(new PropertyValueFactory<>("miejscowosc_placowki"));
            colAdres.setCellValueFactory(new PropertyValueFactory<>("adres_placowki"));
            colKodPocztowy.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy_placowki"));
            colTelKontaktowyPlacowki.setCellValueFactory(new PropertyValueFactory<>("telefon_placowki"));

            tablePlacowki.setItems(null);
            tablePlacowki.setItems(data1);

            tablePlacowki.setOnMousePressed((MouseEvent event) -> {
                if (tablePlacowki.getSelectionModel().getSelectedItem() != null) {

                    txMiejscowosc.setText(tablePlacowki.getSelectionModel().getSelectedItem().getMiejscowosc_placowki());
                    txAdres.setText(tablePlacowki.getSelectionModel().getSelectedItem().getAdres_placowki());
                    txKodPocztowy.setText(tablePlacowki.getSelectionModel().getSelectedItem().getKod_pocztowy_placowki());
                    int p2 = tablePlacowki.getSelectionModel().getSelectedItem().getTelefon_placowki();
                    txTelefonKontaktowyPlacowki.setText(Integer.toString(p2));
                }
            });

            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void DodajKonto(ActionEvent event) {

    }

    @FXML
    private void UsunKonto(ActionEvent event) {
    }

    @FXML
    private void WyczyscPolaKonta(ActionEvent event) {
        txImie.setText("");
        txNazwisko.setText("");
        txPesel.setText("");
        txNumerTel.setText("");
        txLogin.setText("");
        txHaslo.setText("");
        cbRola.setValue("Pracownik");
        txPlacowka.setText("");
    }

    @FXML
    private void AktualizujKonto(ActionEvent event) {
    }

    @FXML
    private void DodajPlacowke(ActionEvent event) {
    }

    @FXML
    private void UsunPlacowke(ActionEvent event) {
    }

    @FXML
    private void AktualizujPlacowke(ActionEvent event) {
    }

    @FXML
    private void WyczyscPolaPlacowka(ActionEvent event) {

    }

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }

}
