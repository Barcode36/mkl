/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.placowki;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

}
