/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin.haslo_admina;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;

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
/**
 * Metoda zajmująca się obsługą przycisku potwierdzenia zmiany hasła.
 * @param event
 * @throws SQLException 
 */
    @FXML
    private void Akceptuj(ActionEvent event) throws SQLException {
        JFXButton bOkk = new JFXButton("OK");
        JFXButton bOk = new JFXButton("OK");
        Connection conn = DBConnection.Connect();
        Statement ps2 = conn.createStatement();
        ResultSet rs = ps2.executeQuery("SELECT haslo_administratora from administrator");
        rs.next();
        if (psStareHaslo.getText().isEmpty() || psNoweHaslo.getText().isEmpty() || psPotwierdzNoweHaslo.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkk), "Błąd ze zmianą hasła", "Uzupełnij wszystkie wymagane pola.");
        } else if (psStareHaslo.getText().equals(rs.getString(1)) && psNoweHaslo.getText().equals(psPotwierdzNoweHaslo.getText())) {
            String query = "UPDATE `administrator` SET `haslo_administratora`=" + psNoweHaslo.getText();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "POTWIERDZENIE", "Zmiana hasła administratora została przeprowadzona pomyślnie.");
            bOk.setOnAction((ActionEvent event1) -> {
                Stage stage = (Stage) btnWyjscie.getScene().getWindow();
                stage.close();
            });
        } else {
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkk), "Błąd ze zmianą hasła", "Podano niepoprawne stare hasło lub nowe hasła nie są identyczne.");
        }
    }
/**
 * Metoda zajmująca się obsługą przycisku zamknięcia okna zmiany hasła administratora.
 * @param event 
 */
    @FXML
    private void Wyjscie(ActionEvent event
    ) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }

}
