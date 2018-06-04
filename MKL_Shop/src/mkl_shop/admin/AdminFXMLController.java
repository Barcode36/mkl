/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.MKL_Shop;
import mkl_shop.admin.modele.Pracownik;
import mkl_shop.admin.modele.Placowka;
import mkl_shop.admin.modele.Wiadomosci;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.manager.klasy.Alert;
import mkl_shop.manager.klasy.Ladowanie_danych;
import mkl_shop.manager.wiadomosci.WiadomosciClassa;
import mkl_shop.pracownik.klient.FXMLEdytujKlientaController;
import mkl_shop.pracownik.modele.Klient;
import mkl_shop.sprawdzanie.Sprawdzanie;

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
    private JFXButton btnAktualizujPlacowke;
    @FXML
    private JFXButton btnWyczyscPlacowka;
    @FXML
    private JFXButton btnWyjscie;
    @FXML
    private JFXComboBox<String> cbRola;
    private ObservableList<Pracownik> data;
    private ObservableList<Placowka> data1;
    private ObservableList<Wiadomosci> data2;
    @FXML
    private JFXButton btnHasloAdmina;
    Ladowanie_danych dane_combo;
    @FXML
    private JFXComboBox<String> cbPlacowka;
    @FXML
    private TableColumn<Wiadomosci, String> colStatus;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    JFXButton bOk = new JFXButton("OK");
    @FXML
    private JFXButton btnAktywuj;
    @FXML
    private TableView<Wiadomosci> tableWiadomosci;
    @FXML
    private TableColumn<Wiadomosci, String> colNadawca;
    @FXML
    private TableColumn<Wiadomosci, String> colTemat;
    @FXML
    private TableColumn<Wiadomosci, String> colData;
    @FXML
    private TextArea taTresc;
    @FXML
    private JFXButton btnOdpowiedz;
    @FXML
    private TextArea taOdpowiedz;
    @FXML
    private JFXTextField txTemat;
    Date currentDate = new Date();
    @FXML
    private Label labelTresc;
    @FXML
    private TableColumn<Pracownik, String> colStatusKonta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbRola.getItems().addAll(
                "sprzedawca",
                "Kierownik",
                "Menager"
        );
        LoadDataPracownik();
        LoadDataPlacowka();
        LoadDataWiadomosci();
        dane_combo = new Ladowanie_danych();
        dane_combo.ladujCombo("SELECT CONCAT(adres_placowki)AS Adres,id_placowki FROM placowka order by id_placowki", cbPlacowka, "Adres");

        FilteredList<Pracownik> filteredPracownik = new FilteredList<>(tableKonta.getItems(), e -> true);
        txSzukaj.setOnKeyReleased(e -> {
            txSzukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredPracownik.setPredicate((Predicate<? super Pracownik>) p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (p.getImie_pracownika().toLowerCase().contains(lcFilter) || p.getNazwisko_pracownika().toLowerCase().contains(lcFilter) || p.getAdres_placowki().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Pracownik> sortedPracownik = new SortedList<>(filteredPracownik);
            tableKonta.setItems(sortedPracownik);
        });
    }
/** 
 * Metoda wczytująca dane do tabeli Pracownicy.
 */
    public void LoadDataPracownik() {
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            data = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery("SELECT id_pracownika,imie_pracownika,nazwisko_pracownika,pesel_pracownika,telefon_pracownika,login,haslo,rola,adres_placowki,status_konta FROM pracownik,placowka where pracownik.id_placowki = placowka.id_placowki  and imie_pracownika != 'Administrator';");
            while (rs.next()) {
                data.add(new Pracownik(rs.getInt("id_pracownika"), rs.getString("imie_pracownika"), rs.getString("nazwisko_pracownika"), rs.getString("pesel_pracownika"), rs.getString("telefon_pracownika"), rs.getString("login"), rs.getString("haslo"), rs.getString("rola"), rs.getString("adres_placowki"), rs.getString("status_konta")));
            }
            colImie.setCellValueFactory(new PropertyValueFactory<>("imie_pracownika"));
            colNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko_pracownika"));
            colPesel.setCellValueFactory(new PropertyValueFactory<>("pesel_pracownika"));
            colNrTel.setCellValueFactory(new PropertyValueFactory<>("telefon_pracownika"));
            colPlacowka.setCellValueFactory(new PropertyValueFactory<>("adres_placowki"));
            colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            colStatusKonta.setCellValueFactory(new PropertyValueFactory<>("status_konta"));

            tableKonta.setItems(null);
            tableKonta.setItems(data);

            tableKonta.setOnMousePressed((MouseEvent event) -> {
                if (tableKonta.getSelectionModel().getSelectedItem() != null) {

                    txImie.setText(tableKonta.getSelectionModel().getSelectedItem().getImie_pracownika());
                    txNazwisko.setText(tableKonta.getSelectionModel().getSelectedItem().getNazwisko_pracownika());
                    txPesel.setText(tableKonta.getSelectionModel().getSelectedItem().getPesel_pracownika());
                    txNumerTel.setText(tableKonta.getSelectionModel().getSelectedItem().getTelefon_pracownika());
                    txLogin.setText(tableKonta.getSelectionModel().getSelectedItem().getLogin());
                    txHaslo.setText(tableKonta.getSelectionModel().getSelectedItem().getHaslo());
                    cbRola.setValue(tableKonta.getSelectionModel().getSelectedItem().getRola());
                    cbPlacowka.setValue(tableKonta.getSelectionModel().getSelectedItem().getAdres_placowki());
                }
            });
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * Metoda wczytująca dane do tabeli Placowki.
 */
    public void LoadDataPlacowka() {
        Connection conn = DBConnection.Connect();
        try {
            ResultSet rs;
            try (Statement ps = conn.createStatement()) {
                data1 = FXCollections.observableArrayList();
                rs = ps.executeQuery("SELECT * from placowka;");
                while (rs.next()) {
                    data1.add(new Placowka(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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
                        txTelefonKontaktowyPlacowki.setText(tablePlacowki.getSelectionModel().getSelectedItem().getTelefon_placowki());
                    }
                });
                conn.close();
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * Metoda wczytująca dane do tabeli Wiadomosci.
 */
    public void LoadDataWiadomosci() {
        Connection conn = DBConnection.Connect();
        taTresc.setVisible(false);
        labelTresc.setVisible(false);
        try {
            data2 = FXCollections.observableArrayList();
            ResultSet res = conn.createStatement().executeQuery("Select Concat(p.imie_pracownika,\" \", p.nazwisko_pracownika, \" \",p.rola)As Nadawca, "
                    + "w.temat_wiadomosci,w.data,w.status_wiadomosci, w.id_wiadomosci, w.tresc_wiadomosci "
                    + "from wiadomosci w, pracownik p "
                    + "where  p.id_pracownika=w.id_pracownika_nadawcy and w.id_pracownika_odbiorca=9");
            while (res.next()) {
                data2.add(new Wiadomosci(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6)));

            }
            colNadawca.setCellValueFactory(new PropertyValueFactory<>("id_nadawcy"));
            colTemat.setCellValueFactory(new PropertyValueFactory<>("temat_wiadomosci"));
            colData.setCellValueFactory(new PropertyValueFactory<>("data"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status_wiadomosci"));
            tableWiadomosci.setItems(null);
            tableWiadomosci.setItems(data2);
            tableWiadomosci.setOnMousePressed((MouseEvent event) -> {
                if (tableWiadomosci.getSelectionModel().getSelectedItem() != null) {
                    taTresc.setVisible(true);
                    labelTresc.setVisible(true);
                    taTresc.setText(tableWiadomosci.getSelectionModel().getSelectedItem().getTresc_wiadomosci());
                }
            });
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * Metoda zajmująca się obsługą przycisku dodania konta.
 * @param event 
 */
    @FXML
    private void DodajKonto(ActionEvent event) {
        if (txImie.getText().isEmpty() || txNazwisko.getText().isEmpty() || txPesel.getText().isEmpty() || txNumerTel.getText().isEmpty() || txLogin.getText().isEmpty() || txHaslo.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Uzupełnij wszystkie wymagane pola.");
        } else if (Sprawdzanie.peselPoprawny(txPesel.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Podany pesel jest nieprawidłowy.");
        } else if (Sprawdzanie.telefonPoprawny(txNumerTel.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Poprawny numer telefonu zawiera 9 cyfr.");
        } else {
            try {
                Connection conn = DBConnection.Connect();
                Statement ps = conn.createStatement();
                ResultSet rs = ps.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
                rs.next();
                conn.createStatement().executeUpdate("INSERT INTO pracownik(id_pracownika, imie_pracownika, nazwisko_pracownika, pesel_pracownika, telefon_pracownika,login,haslo,rola,id_placowki,status_konta) Values (null,'" + txImie.getText() + "','" + txNazwisko.getText() + "','" + txPesel.getText() + "','" + txNumerTel.getText() + "','" + txLogin.getText() + "','" + txHaslo.getText() + "','" + cbRola.getValue() + "','" + rs.getInt(1) + "','" + "aktywne')");
                LoadDataPracownik();
                conn.close();
            } catch (SQLException e) {
                System.err.println("ERROR" + e);
            }
        }

    }
/**
 * Metoda czyszcząca pola związane z tworzeniem/edycją kont.
 * @param event 
 */
    @FXML
    private void WyczyscPolaKonta(ActionEvent event) {
        txImie.setText("");
        txNazwisko.setText("");
        txPesel.setText("");
        txNumerTel.setText("");
        txLogin.setText("");
        txHaslo.setText("");
        cbRola.setValue("");
        cbPlacowka.setValue("");
    }
/**
 * Metoda zajmująca się obsługą przycisku aktualizacji konta.
 * @param event 
 */
    @FXML
    private void AktualizujKonto(ActionEvent event) {

        if (tableKonta.getSelectionModel().getSelectedItem() == null) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Wybierz konto do aktualizacji.");
        } else if (txImie.getText().isEmpty() || txNazwisko.getText().isEmpty() || txPesel.getText().isEmpty() || txNumerTel.getText().isEmpty() || txLogin.getText().isEmpty() || txHaslo.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Uzupełnij wszystkie wymagane pola.");
        } else if (Sprawdzanie.peselPoprawny(txPesel.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Podany pesel jest nieprawidłowy.");
        } else if (Sprawdzanie.telefonPoprawny(txNumerTel.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Poprawny numer telefonu zawiera 9 cyfr.");
        } else {
            try {
                Connection conn = DBConnection.Connect();
                Statement ps2 = conn.createStatement();
                ResultSet rs = ps2.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
                rs.next();
                String query = "Update Pracownik set imie_pracownika = ?, nazwisko_pracownika = ?, pesel_pracownika =?,  telefon_pracownika = ?,login = ?, haslo = ?, rola = ?, id_placowki =? where id_pracownika = '" + tableKonta.getSelectionModel().getSelectedItem().getId_pracownika() + "'";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, txImie.getText());
                ps.setString(2, txNazwisko.getText());
                ps.setLong(3, Long.parseLong(txPesel.getText()));
                ps.setInt(4, Integer.parseInt(txNumerTel.getText()));
                ps.setString(5, txLogin.getText());
                ps.setString(6, txHaslo.getText());
                ps.setString(7, cbRola.getValue());
                ps.setInt(8, rs.getInt(1));
                ps.execute();
                ps.close();
                LoadDataPracownik();
            } catch (SQLException ex) {

                System.out.println("error" + ex);

            }
        }
    }
/**
 * Metoda zajmująca się obsługą przycisku aktywacji konta.
 * @param event 
 */
    @FXML
    private void AktywujKonto(ActionEvent event) {
        if (tableKonta.getSelectionModel().getSelectedItem() == null) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktywacją/dezaktywacją", "Wybierz konto do aktywacji/dezaktywacji.");
        }
        try {
            Connection conn = DBConnection.Connect();
            Statement ps2 = conn.createStatement();
            ResultSet rs = ps2.executeQuery("SELECT status_konta from pracownik where id_pracownika = '" + tableKonta.getSelectionModel().getSelectedItem().getId_pracownika() + "'");
            rs.next();
            String st = "nieaktywne";
            if (rs.getString(1).equals("aktywne")) {
                st = "nieaktywne";
            } else {
                st = "aktywne";
            }
            String query = "Update Pracownik set status_konta = ? where id_pracownika = '" + tableKonta.getSelectionModel().getSelectedItem().getId_pracownika() + "'";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, st);
            ps.execute();
            ps.close();
            LoadDataPracownik();
        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }
    }
/**
 * Metoda zajmująca się obsługą przycisku dodania nowej placówki.
 * @param event 
 */
    @FXML
    private void DodajPlacowke(ActionEvent event) {
        if (txTelefonKontaktowyPlacowki.getText().isEmpty() || txKodPocztowy.getText().isEmpty() || txMiejscowosc.getText().isEmpty() || txAdres.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Uzupełnij wszystkie wymagane pola.");
        } else if (Sprawdzanie.czyKodPocztowy(txKodPocztowy.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Podany kod pocztowy ma niepoprawny format.");
        } else if (Sprawdzanie.telefonPoprawny(txTelefonKontaktowyPlacowki.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Poprawny numer telefonu zawiera 9 cyfr.");
        } else {
            try {
                Connection conn = DBConnection.Connect();
                conn.createStatement().executeUpdate("INSERT INTO placowka(id_placowki, telefon_placowki, kod_pocztowy_placowki, miejscowosc_placowki, adres_placowki) Values (null,'" + txTelefonKontaktowyPlacowki.getText() + "','" + txKodPocztowy.getText() + "','" + txMiejscowosc.getText() + "','" + txAdres.getText() + "')");
                LoadDataPlacowka();
                conn.close();
            } catch (SQLException e) {
                System.err.println("ERROR" + e);
            }
        }
    }
/**
 * Metoda zajmująca się obsługą przycisku aktualizacji placówki.
 * @param event 
 */
    @FXML
    private void AktualizujPlacowke(ActionEvent event) {
        if (tablePlacowki.getSelectionModel().getSelectedItem() == null) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Wybierz placówkę do aktualizacji.");
        } else if (txTelefonKontaktowyPlacowki.getText().isEmpty() || txKodPocztowy.getText().isEmpty() || txMiejscowosc.getText().isEmpty() || txAdres.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Uzupełnij wszystkie wymagane pola.");
        } else if (Sprawdzanie.czyKodPocztowy(txKodPocztowy.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Podany kod pocztowy ma niepoprawny format.");
        } else if (Sprawdzanie.telefonPoprawny(txTelefonKontaktowyPlacowki.getText()) == false) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Poprawny numer telefonu zawiera 9 cyfr.");
        } else {
            try {
                Connection conn = DBConnection.Connect();
                String query = "Update placowka set telefon_placowki = ?, kod_pocztowy_placowki = ?, miejscowosc_placowki =?,  adres_placowki = ? where id_placowki = '" + tablePlacowki.getSelectionModel().getSelectedItem().getId_placowki() + "'";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(txTelefonKontaktowyPlacowki.getText()));
                ps.setString(2, txKodPocztowy.getText());
                ps.setString(3, txMiejscowosc.getText());
                ps.setString(4, txAdres.getText());
                ps.execute();
                ps.close();
                LoadDataPlacowka();
            } catch (SQLException ex) {

                System.out.println("error" + ex);

            }
        }
    }
/**
 * Metoda zajmująca się obsługą przycisku czyszczenia pól tekstowych.
 * @param event 
 */
    @FXML
    private void WyczyscPolaPlacowka(ActionEvent event) {
        txMiejscowosc.setText("");
        txAdres.setText("");
        txKodPocztowy.setText("");
        txTelefonKontaktowyPlacowki.setText("");
    }
/**
 * Metoda zajmująca się obsługą przycisku zamknięcia okna aplikacji.
 * @param event 
 */
    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }
/**
 * Metoda zajmująca się obsługą przycisku zmiany hasła administratora.
 * @param event
 * @throws IOException 
 */
    @FXML
    private void ZmienHasloAdmina(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("haslo_admina/HasloAdminaFXML.fxml"));
        root.getStylesheets().add(MKL_Shop.class.getResource("admin/fxmladmin.css").toExternalForm());
        stage.setScene(new Scene(root));
        stage.setTitle("Panel managera");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnHasloAdmina.getScene().getWindow());
        stage.showAndWait();
    }
/**
 * Metoda zajmująca się obsługą przycisku odpowiedzi na wiadomość.
 * @param event
 * @throws SQLException 
 */
    @FXML
    private void Odpowiedz(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.Connect();
        Statement statement = conn.createStatement();

        tableWiadomosci.setOnMousePressed((MouseEvent event1) -> {
            if (tableWiadomosci.getSelectionModel().getSelectedItem() != null) {
                try {
                    String query = "UPDATE `wiadomosci` SET `status_wiadomosci`='Odebrane' where id_wiadomosci=" + tableWiadomosci.getSelectionModel().getSelectedItem().getId_wiadomosci();
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    LoadDataWiadomosci();

                } catch (SQLException ex) {
                    Logger.getLogger(AdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        if (txTemat.getText().isEmpty() || taOdpowiedz.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z odpowiedzią", "Uzupełnij wszystkie wymagane pola.");
        } else {
            String odbiorca = tableWiadomosci.getSelectionModel().getSelectedItem().getId_nadawcy();
            String temat = txTemat.getText().toString();
            String tresc = taOdpowiedz.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(currentDate);
            Statement ps2 = conn.createStatement();
            ResultSet rs = ps2.executeQuery("SELECT id_pracownika_nadawcy from wiadomosci where id_wiadomosci = '" + tableWiadomosci.getSelectionModel().getSelectedItem().getId_wiadomosci() + "'");
            rs.next();
            statement.executeUpdate("INSERT INTO `wiadomosci` (`id_pracownika_nadawcy`, `id_pracownika_odbiorca`, `temat_wiadomosci`, `tresc_wiadomosci`, `Data`, `status_wiadomosci`) "
                    + "VALUES (" + 9 + ", " + rs.getInt(1) + ", '" + temat + "', '" + tresc + "', '" + dateString + "', 'Nieodebrana')");
            taOdpowiedz.clear();
            txTemat.clear();
        }
    }
}
