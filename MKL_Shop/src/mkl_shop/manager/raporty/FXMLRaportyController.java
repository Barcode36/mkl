/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.raporty;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mkl_shop.connection.DBConnection;
import mkl_shop.manager.klasy.Alert;
import mkl_shop.manager.klasy.Ladowanie_danych;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLRaportyController implements Initializable {

    @FXML
    private JFXButton Button_Statystki_Pracownika;
    @FXML
    private JFXButton Button_Statystki_Placowki;
    @FXML
    private JFXButton Button_Statystki_Produkty;
    @FXML
    private JFXButton Button_Statystki_Wyjscie;
    @FXML
    private Pane Pane_Pracownicy;
    @FXML
    private JFXComboBox Combo_Placowki_Pracownicy;
    @FXML
    private JFXDatePicker Data_Od_Pracownicy;
    @FXML
    private JFXDatePicker Data_Do_Pracownicy;
    @FXML
    private JFXButton Button_Szukaj_Pracownicy;
    @FXML
    private BarChart<String, Double> Wykres_Pracownicy;
    @FXML
    private Pane Pane_Placowki;
    @FXML
    private JFXDatePicker Data_od_Placowka;
    @FXML
    private JFXDatePicker Data_do_Placowka;
    @FXML
    private JFXButton Button_Szukaj_Placowki;
    @FXML
    private Pane Pane_Produkty;
    @FXML
    private JFXComboBox Combo_Placowki_Produkty;
    @FXML
    private JFXDatePicker Data_Od_Produkty;
    @FXML
    private JFXDatePicker Data_Do_Produkty;
    @FXML
    private JFXButton Button_Szukaj_Produkty;
    @FXML
    private BarChart<String, Double> Wykres_Produkty;
    DBConnection dc;
    Ladowanie_danych dane_combo;

    boolean flaga = false;
    @FXML
    private BarChart<String, Double> Wykres_placowki;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane_Pracownicy.setVisible(true);
        Pane_Placowki.setVisible(false);
        Pane_Produkty.setVisible(false);
        dc = new DBConnection();
        dane_combo = new Ladowanie_danych();
        dane_combo.ladujCombo("SELECT CONCAT(miejscowosc_placowki,\" \",adres_placowki)AS Adres,id_placowki FROM placowka order by id_placowki", Combo_Placowki_Pracownicy, "Adres");
        dane_combo.ladujCombo("SELECT CONCAT(miejscowosc_placowki,\" \",adres_placowki)AS Adres,id_placowki FROM placowka order by id_placowki", Combo_Placowki_Produkty, "Adres");
        String query = "Select  p.imie_pracownika, p.nazwisko_pracownika, sum(t.calkowity_koszt)as Wynik_Sprzedazy "
                + "From pracownik p, placowka l , transakcja t "
                + "Where p.id_pracownika=t.id_pracownika and l.id_placowki=l.id_placowki  group by p.imie_pracownika, p.nazwisko_pracownika";
        wykres_metoda(query, Wykres_Pracownicy);
    }

    @FXML
    private void goStatsPracownicy(ActionEvent event) {
        Pane_Pracownicy.setVisible(true);
        Pane_Placowki.setVisible(false);
        Pane_Produkty.setVisible(false);
        String query = "Select  p.imie_pracownika, p.nazwisko_pracownika, sum(t.calkowity_koszt)as Wynik_Sprzedazy "
                + "From pracownik p, placowka l , transakcja t "
                + "Where p.id_pracownika=t.id_pracownika and l.id_placowki=l.id_placowki  group by p.imie_pracownika, p.nazwisko_pracownika";
        wykres_metoda(query, Wykres_Pracownicy);

    }

    @FXML
    private void goStatsPlacowka(ActionEvent event) {
        Pane_Pracownicy.setVisible(false);
        Pane_Placowki.setVisible(true);
        Pane_Produkty.setVisible(false);
        String query = "Select t.id_pracownika, CONCAT(p.miejscowosc_placowki,\" \",p.adres_placowki)AS Adres, sum(t.calkowity_koszt)as Dochod "
                + "from placowka p , transakcja t, pracownik r"
                + " where t.id_pracownika=r.id_pracownika and r.id_placowki=p.id_placowki and t.data "
                + "GROUP by p.miejscowosc_placowki, p.adres_placowki";
        wykres_metoda(query, Wykres_placowki);
    }

    @FXML
    private void goStatsProdukty(ActionEvent event) {
        Pane_Pracownicy.setVisible(false);
        Pane_Placowki.setVisible(false);
        Pane_Produkty.setVisible(true);
        String query = "SELECT  t.data, p.nazwa_produktu, sum(z.ilosc_produktow) as Sprzedano "
                + "from produkty p, placowka l, transakcja_produkty z, transakcja t, placowka_produkt x "
                + "where t.id_transakcji=z.id_transakcji AND z.id_produktu=p.id_produktu "
                + "and  l.id_placowki=x.id_placowki and x.id_produktu=p.id_produktu "
                + "GROUP by p.nazwa_produktu";
        wykres_metoda(query, Wykres_Produkty);
    }

    @FXML
    private void goWstecz(ActionEvent event) {
        Stage stage = (Stage) Button_Statystki_Wyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void StatsPracownika(ActionEvent event) {
        try {
            String index = (dane_combo.ladujliste("SELECT id_placowki FROM Placowka ORDER by id_placowki", "id_placowki").get(Combo_Placowki_Pracownicy.getSelectionModel().getSelectedIndex()).toString());
            String dataod = Data_Od_Pracownicy.getValue().toString();
            String datado = Data_Do_Pracownicy.getValue().toString();
            String query = "Select  p.imie_pracownika, p.nazwisko_pracownika, sum(t.calkowity_koszt)as Wynik_Sprzedazy "
                    + "From pracownik p, placowka l , transakcja t "
                    + "Where l.id_placowki=" + index + " and t.data BETWEEN '" + dataod + "' and '" + datado + "' "
                    + "And p.id_pracownika=t.id_pracownika and p.id_placowki=l.id_placowki  group by p.imie_pracownika, p.nazwisko_pracownika";
            wykres_metoda(query, Wykres_Pracownicy);
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                String dataod = Data_Od_Pracownicy.getValue().toString();
                String datado = Data_Do_Pracownicy.getValue().toString();
                String query = "Select  p.imie_pracownika, p.nazwisko_pracownika, sum(t.calkowity_koszt)as Wynik_Sprzedazy "
                        + "From pracownik p, placowka l , transakcja t "
                        + "Where p.id_pracownika=t.id_pracownika and p.id_placowki=l.id_placowki and t.data BETWEEN '" + dataod + "' and '" + datado + "'  group by p.imie_pracownika, p.nazwisko_pracownika";
                wykres_metoda(query, Wykres_Pracownicy);
            } catch (NullPointerException ex) {
                String query = "Select  p.imie_pracownika, p.nazwisko_pracownika, sum(t.calkowity_koszt)as Wynik_Sprzedazy "
                        + "From pracownik p, placowka l , transakcja t "
                        + "Where p.id_pracownika=t.id_pracownika and p.id_placowki=l.id_placowki  group by p.imie_pracownika, p.nazwisko_pracownika";
                wykres_metoda(query, Wykres_Pracownicy);
            }
        } catch (NullPointerException e) {
            String index = (dane_combo.ladujliste("SELECT id_placowki FROM Placowka ORDER by id_placowki", "id_placowki").get(Combo_Placowki_Pracownicy.getSelectionModel().getSelectedIndex()).toString());
            String query = "Select  p.imie_pracownika, p.nazwisko_pracownika, sum(t.calkowity_koszt)as Wynik_Sprzedazy "
                    + "From pracownik p, placowka l , transakcja t "
                    + "Where p.id_pracownika=t.id_pracownika and p.id_placowki=l.id_placowki  group by p.imie_pracownika, p.nazwisko_pracownika";
            wykres_metoda(query, Wykres_Pracownicy);

        } catch (Exception e) {

        }
    }

    @FXML
    private void StatsPlacowka(ActionEvent event) {
        try {
            String dataod = Data_od_Placowka.getValue().toString();
            String datado = Data_do_Placowka.getValue().toString();
            String query = "Select t.id_pracownika, CONCAT(p.miejscowosc_placowki,\" \",p.adres_placowki)AS Adres, sum(t.calkowity_koszt)as Dochod "
                    + "from placowka p , transakcja t, pracownik r"
                    + " where t.id_pracownika=r.id_pracownika and r.id_placowki=p.id_placowki and t.data "
                    + "BETWEEN '" + dataod + "' and '" + datado + "' "
                    + "GROUP by p.miejscowosc_placowki, p.adres_placowki";
            wykres_metoda(query, Wykres_placowki);
        } catch (NullPointerException ex) {
            String query = "Select t.id_pracownika, CONCAT(p.miejscowosc_placowki,\" \",p.adres_placowki)AS Adres, sum(t.calkowity_koszt)as Dochod "
                    + "from placowka p , transakcja t, pracownik r"
                    + " where t.id_pracownika=r.id_pracownika and r.id_placowki=p.id_placowki and t.data "
                    + "GROUP by p.miejscowosc_placowki, p.adres_placowki";
            wykres_metoda(query, Wykres_placowki);
        }

    }

    @FXML
    private void StatsProdukty(ActionEvent event) {
        //
        try {
            String index = (dane_combo.ladujliste("SELECT id_placowki FROM Placowka ORDER by id_placowki", "id_placowki").get(Combo_Placowki_Produkty.getSelectionModel().getSelectedIndex()).toString());
            String dataod = Data_Od_Produkty.getValue().toString();
            String datado = Data_Do_Produkty.getValue().toString();
            String query = "SELECT  t.data, p.nazwa_produktu, sum(z.ilosc_produktow) as Sprzedano "
                    + "from produkty p, placowka l, transakcja_produkty z, transakcja t, placowka_produkt x "
                    + "where t.id_transakcji=z.id_transakcji AND z.id_produktu=p.id_produktu "
                    + "and l.id_placowki=" + index + " and l.id_placowki=x.id_placowki and x.id_produktu=p.id_produktu "
                    + "and t.data BETWEEN '" + dataod + "' and '" + datado + "' GROUP by p.nazwa_produktu";
            wykres_metoda(query, Wykres_Produkty);
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                String dataod = Data_Od_Produkty.getValue().toString();
                String datado = Data_Do_Produkty.getValue().toString();
                String query = "SELECT  t.data, p.nazwa_produktu, sum(z.ilosc_produktow) as Sprzedano "
                        + "from produkty p, placowka l, transakcja_produkty z, transakcja t, placowka_produkt x "
                        + "where t.id_transakcji=z.id_transakcji AND z.id_produktu=p.id_produktu "
                        + "and  l.id_placowki=x.id_placowki and x.id_produktu=p.id_produktu and t.data BETWEEN '" + dataod + "' and '" + datado + "' GROUP by p.nazwa_produktu";
                wykres_metoda(query, Wykres_Produkty);
            } catch (NullPointerException ex) {
                String query = "SELECT  t.data, p.nazwa_produktu, sum(z.ilosc_produktow) as Sprzedano "
                        + "from produkty p, placowka l, transakcja_produkty z, transakcja t, placowka_produkt x "
                        + "where t.id_transakcji=z.id_transakcji AND z.id_produktu=p.id_produktu "
                        + "and  l.id_placowki=x.id_placowki and x.id_produktu=p.id_produktu "
                        + "GROUP by p.nazwa_produktu";
                wykres_metoda(query, Wykres_Produkty);
            }

        } catch (NullPointerException e) {

            String query = "SELECT  t.data, p.nazwa_produktu, sum(z.ilosc_produktow) as Sprzedano "
                    + "from produkty p, placowka l, transakcja_produkty z, transakcja t, placowka_produkt x "
                    + "where t.id_transakcji=z.id_transakcji AND z.id_produktu=p.id_produktu "
                    + "and  l.id_placowki=x.id_placowki and x.id_produktu=p.id_produktu "
                    + "GROUP by p.nazwa_produktu";
            wykres_metoda(query, Wykres_Produkty);

        } catch (Exception e) {

        }
    }

    public void wykres_metoda(String query, BarChart Wykres) {
        Wykres.getData().removeAll(Collections.singleton(Wykres.getData().setAll()));
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            java.sql.Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(2), rs.getDouble(3)));
            }
            Wykres.getData().add(series);

        } catch (Exception e) {
            System.out.println("Nie udało się pobrać danych" + e);

        }
    }
}
