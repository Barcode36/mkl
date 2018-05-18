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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mkl_shop.admin.modele.Pracownik;
import mkl_shop.admin.modele.Placowka;
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
    private JFXComboBox<?> cbRola;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void DodajKonto(ActionEvent event) {
    }

    @FXML
    private void UsunKonto(ActionEvent event) {
    }

    @FXML
    private void WyczyscPolaKonta(ActionEvent event) {
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
