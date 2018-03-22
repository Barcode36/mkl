/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.placowki;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLSzczegolyController implements Initializable {

    @FXML
    private JFXButton Button_Glowna;
    @FXML
    private JFXButton Button_Pracownicy;
    @FXML
    private JFXButton Button_Produkty;
    @FXML
    private JFXButton Button_Sprzedaz;
    @FXML
    private JFXButton Button_Wstecz;
    @FXML
    private Pane Pane_Glowna;
    @FXML
    private Pane Pane_Pracownicy;
    @FXML
    private Pane Pane_Produkty;
    @FXML
    private Pane Pane_Sprzedaz;
    @FXML
    private JFXComboBox<?> Combo_pracownik;
    @FXML
    private JFXTextField Text_Stanowisko;
    @FXML
    private JFXTextField Text_Imie;
    @FXML
    private JFXTextField Text_Nazwisko;
    @FXML
    private JFXTextField Text_Telefon;
    @FXML
    private JFXTextField Text_Pesel;
    @FXML
    private JFXButton Button_Statystyki;
    @FXML
    private TableView<?> Tabela_Produkty;
    @FXML
    private TableColumn<?, ?> Tab_Lp;
    @FXML
    private TableColumn<?, ?> Tab_NazwaProdukty;
    @FXML
    private TableColumn<?, ?> Tab_CenaProduktu;
    @FXML
    private TableColumn<?, ?> Tab_OpisProduktu;
    @FXML
    private TableColumn<?, ?> Tab_Dostepnosc;
    @FXML
    private JFXDatePicker Data_Od;
    @FXML
    private JFXDatePicker Data_Do;
    @FXML
    private JFXButton Button_Szukaj;
    @FXML
    private AreaChart<?, ?> Wykres_SprzedazyProduktuow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane_Glowna.setVisible(true);
        Pane_Pracownicy.setVisible(false);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(false);
    }

    @FXML
    private void goGlowna(ActionEvent event) {
        Pane_Glowna.setVisible(true);
        Pane_Pracownicy.setVisible(false);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(false);

    }

    @FXML
    private void goPracownicy(ActionEvent event) {
        Pane_Glowna.setVisible(false);
        Pane_Pracownicy.setVisible(true);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(false);
    }

    @FXML
    private void goProdukty(ActionEvent event) {
        Pane_Glowna.setVisible(false);
        Pane_Pracownicy.setVisible(false);
        Pane_Produkty.setVisible(true);
        Pane_Sprzedaz.setVisible(false);
    }

    @FXML
    private void goSprzedaz(ActionEvent event) {
        Pane_Glowna.setVisible(false);
        Pane_Pracownicy.setVisible(false);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(true);
    }

    @FXML
    private void wstecz(ActionEvent event) {
        Stage stage = (Stage) Button_Wstecz.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goSzukajPracownika(ActionEvent event) {
    }

    @FXML
    private void goStatystykiPracownika(ActionEvent event) throws IOException {
         Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("src/mkl_shop/manager/pracownicy/FXMLSzczegoly.fxml"));//odnieść się do pakietu pracownicy do pliku FXMLSZCZEGOLY 
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Statystyki.getScene().getWindow());
        stage.showAndWait();
        
    }

    @FXML
    private void goStatystkiProduktu(ActionEvent event) {
    }

}
