/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.modele.Produkt;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLPunktyController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXRadioButton rbNagroda1;
    @FXML
    private ToggleGroup rbNagroda;
    @FXML
    private JFXRadioButton rbNagroda2;
    @FXML
    private JFXRadioButton rbNagroda3;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bZatwierdz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int punkty = FXMLZakupyController.k1.getLiczba_punktow();
        if (punkty < 2000) {
            rbNagroda1.setDisable(true);
            rbNagroda2.setDisable(true);
            rbNagroda3.setDisable(true);
        } else if (punkty >= 2000 & punkty < 3000) {
            rbNagroda2.setDisable(true);
            rbNagroda3.setDisable(true);
        } else if (punkty >= 3000 && punkty < 4000) {
            rbNagroda3.setDisable(true);
        }
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zatwierdzNagrode(ActionEvent event) throws SQLException {
      

        JFXButton bOkay = new JFXButton("Tak");
        JFXButton bCancel = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdź decyzję", "Czy jesteś pewien, że chcesz wykorzystać punkty?");
        bOkay.setOnAction((ActionEvent event1) -> {
            try {

                int pktNew;
                int pkt = 0;
                String nazwa = "";
                if (rbNagroda1.isSelected()) {
                    pkt = 2000;
                    FXMLZakupyController.rabat = 0.9;
                    nazwa = "Rabat 10%";
                }
                if (rbNagroda2.isSelected()) {
                    pkt = 3000;
                    FXMLZakupyController.rabat = 0.8;
                    nazwa = "Rabat 20%";
                }
                if (rbNagroda2.isSelected()) {
                    pkt = 4000;
                    FXMLZakupyController.rabat = 0.75;
                    nazwa = "Rabat 25% oraz gadżety";
                }

                pktNew = FXMLZakupyController.k1.getLiczba_punktow() - pkt;
                FXMLZakupyController.k1.setLiczba_punktow(pkt);

                Connection conn = DBConnection.Connect();

                conn.createStatement().executeUpdate("UPDATE klient SET liczba_punktow=" + pktNew + " WHERE id_klienta=" + FXMLZakupyController.k1.getId_klienta() + ";");
                Produkt pro = new Produkt(0, nazwa, 0.0, "Nagroda", 1, 0.0, 1);
                FXMLZakupyController.dataRachunek.add(pro);

                conn.close();

                Stage stage = (Stage) bWyjscie.getScene().getWindow();
                stage.close();

            } catch (Exception exp) {
        
            }
        });
    }

}
