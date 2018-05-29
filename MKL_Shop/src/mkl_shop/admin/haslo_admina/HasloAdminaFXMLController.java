/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin.haslo_admina;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Łukasz
 */
public class HasloAdminaFXMLController implements Initializable {

    @FXML
    private JFXPasswordField psStareHaslo;
    @FXML
    private JFXPasswordField psNoweHaslo;
    @FXML
    private JFXPasswordField psPotwierdzNoweHaslo;
    @FXML
    private JFXButton btnOk;
    @FXML
    private JFXButton btnWyjscie;
    @FXML
    private AnchorPane apMain;
    @FXML
    private StackPane spMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Akceptuj(ActionEvent event) {
        JFXButton bOk = new JFXButton("TAK");
        JFXButton bExit = new JFXButton("NIE");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk, bExit), "Potwierdź decyzję", "Czy na pewno chcesz zmienić hasło administratora?");
        bOk.setOnAction((ActionEvent event1) -> {
            try {

            } catch (Exception exp) {

            }
        });

    }

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }

}
