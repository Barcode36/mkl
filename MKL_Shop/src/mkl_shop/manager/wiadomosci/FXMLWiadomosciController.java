/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.wiadomosci;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLWiadomosciController implements Initializable {

    @FXML
    private JFXButton Button_Skrzynka;
    @FXML
    private JFXButton Button_Wyslane;
    @FXML
    private JFXButton Button_Wiadomosci;
    @FXML
    private JFXButton Button_Wstecz;
    @FXML
    private Pane Pane_Skrzynka;
    @FXML
    private TableView<?> Tabela_Wiadomosci;
    @FXML
    private TableColumn<?, ?> Tab_Lp;
    @FXML
    private TableColumn<?, ?> Tab_Nadawca;
    @FXML
    private TableColumn<?, ?> Tab_Temat;
    @FXML
    private TableColumn<?, ?> Tab_Data;
    @FXML
    private Pane Pane_tresc;
    @FXML
    private JFXTextField Text_Field_Nadawca;
    @FXML
    private JFXTextField Text_Field_Temat;
    @FXML
    private JFXTextArea Text_Area_Wiadomosc;
    @FXML
    private JFXButton Button_Odpisz;
    @FXML
    private JFXButton Button_Usun;
    @FXML
    private Pane Pane_Wyslane;
    @FXML
    private TableView<?> Tabela_Wyslane;
    @FXML
    private TableColumn<?, ?> Tab_Lp_Wyslane;
    @FXML
    private TableColumn<?, ?> Tab_Adresta_Wyslane;
    @FXML
    private TableColumn<?, ?> Tab_Temat_Wyslane;
    @FXML
    private TableColumn<?, ?> Tab_Data_Wyslane;
    @FXML
    private Pane Pane_tresc_wyslane;
    @FXML
    private JFXTextField Text_Field_Adresta;
    @FXML
    private JFXTextField Text_Field_Temat_Wyslane;
    @FXML
    private JFXTextArea Text_Area_Wiadomosc_Wyslane;
    @FXML
    private JFXButton Button_Usun_Wyslanie;
    @FXML
    private Pane Pane_Wiadomosci;
    @FXML
    private JFXTextField Text_Nadawca;
    @FXML
    private JFXTextField Text_Odbioraca;
    @FXML
    private JFXTextField Text_Temat;
    @FXML
    private JFXTextArea Text_Tresc_Wiadomosci;
    @FXML
    private JFXButton Button_Wyslij_Nowa;
    @FXML
    private JFXButton Button_Anuluj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane_Skrzynka.setVisible(true);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(false);
    }

    @FXML
    private void goWstecz(ActionEvent event) {
        Stage stage = (Stage) Button_Wstecz.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goOdpisz(ActionEvent event) {
    }

    @FXML
    private void goUsun(ActionEvent event) {
    }

    @FXML
    private void goSkrzynka(ActionEvent event) {
        Pane_Skrzynka.setVisible(true);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(false);

    }

    @FXML
    private void goWyslanie(ActionEvent event) {
        Pane_Skrzynka.setVisible(false);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(true);

    }

    @FXML
    private void goNowaWiadomosc(ActionEvent event) {
        Pane_Skrzynka.setVisible(false);
        Pane_Wiadomosci.setVisible(true);
        Pane_Wyslane.setVisible(false);

    }

    @FXML
    private void Anuluj_wyslanie(ActionEvent event) {
              Pane_Skrzynka.setVisible(true);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(false);
    }

}
