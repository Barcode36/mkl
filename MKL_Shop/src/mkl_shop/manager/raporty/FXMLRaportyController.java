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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private JFXComboBox<?> Combo_Placowki_Pracownicy;
    @FXML
    private JFXDatePicker Data_Od_Pracownicy;
    @FXML
    private JFXDatePicker Data_Do_Pracownicy;
    @FXML
    private JFXButton Button_Szukaj_Pracownicy;
    @FXML
    private BarChart<?, ?> Wykres_Pracownicy;
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
    private JFXComboBox<?> Combo_Placowki_Produkty;
    @FXML
    private JFXDatePicker Data_Od_Produkty;
    @FXML
    private JFXDatePicker Data_Do_Produkty;
    @FXML
    private JFXButton Button_Szukaj_Produkty;
    @FXML
    private BarChart<?, ?> Wykres_Produkty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Pane_Pracownicy.setVisible(true);
        Pane_Placowki.setVisible(false);
        Pane_Produkty.setVisible(false);

    }

    @FXML
    private void goStatsPracownicy(ActionEvent event) {
        Pane_Pracownicy.setVisible(true);
        Pane_Placowki.setVisible(false);
        Pane_Produkty.setVisible(false);
    }

    @FXML
    private void goStatsPlacowka(ActionEvent event) {
        Pane_Pracownicy.setVisible(false);
        Pane_Placowki.setVisible(true);
        Pane_Produkty.setVisible(false);
    }

    @FXML
    private void goStatsProdukty(ActionEvent event) {
        Pane_Pracownicy.setVisible(false);
        Pane_Placowki.setVisible(false);
        Pane_Produkty.setVisible(true);
    }

    @FXML
    private void goWstecz(ActionEvent event) {
        Stage stage = (Stage) Button_Statystki_Wyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void StatsPracownika(ActionEvent event) {
    }

    @FXML
    private void StatsPlacowka(ActionEvent event) {
    }

    @FXML
    private void StatsProdukty(ActionEvent event) {
    }

}
