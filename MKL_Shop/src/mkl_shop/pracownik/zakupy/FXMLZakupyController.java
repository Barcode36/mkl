/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.MKL_Shop;
import mkl_shop.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLZakupyController implements Initializable {

    @FXML
    private JFXButton bDoRachunku;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bUsunPrzedmiot;
    @FXML
    private TableView<?> tableRachunek;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXButton bZatwierdz;
    @FXML
    private Label lSuma;
    @FXML
    private Label lSumaWartosc;
    @FXML
    private TableColumn<?, ?> columnIdProduktu;
    @FXML
    private TableColumn<?, ?> columnNazwa;
    @FXML
    private TableColumn<?, ?> columnOpis;
    @FXML
    private TableColumn<?, ?> columnCenaSzt;
    @FXML
    private TableColumn<?, ?> columnIlosc;
    @FXML
    private TableColumn<?, ?> columnCena;
    @FXML
    private JFXCheckBox cStalyKlient;
    @FXML
    private Label lStalyKlient;
    @FXML
    private FontAwesomeIconView bListaKlientow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //bUsunPrzedmiot.setDisable(true);

    }

    @FXML
    private void dodajDoRachunku(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLListaPrzedmiotow.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Lista produktów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bDoRachunku.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void usunZRachunku(ActionEvent event) {
        //usuwanie wybranego przedmiotu + komunikat tak/nie
        
        
        
        JFXButton bOkay = new JFXButton("Tak, usuń");
        JFXButton bCancel = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay,bCancel), "Potwierdź decyzję", "Czy na pewno chcesz usunąć wpis?");
        bOkay.setOnAction((ActionEvent event1) -> {
            try {
                //zrobic usuniecie, ak problem to wywali błąd
                
                
                
            } catch (Exception exp) {
                AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel), "Błąd", "Nie można usunąć pozycji");
            }
        });


    }

    @FXML
    private void zaznaczonyPrzedmiot(MouseEvent event) {
        // jak zaznaczony obiekt z tabeli to odblokowac przycisk usuwania
    }

    @FXML
    private void finalizujTransakcje(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) bWyjscie.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FXMLFinalizacja.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void wybierzKlienta(ActionEvent event) {
        if (cStalyKlient.isSelected())
            bListaKlientow.setDisable(false);
        else
            bListaKlientow.setDisable(true);
    }

    @FXML
    private void wybierzKlientaZListy(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLListaKlientow.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bDoRachunku.getScene().getWindow());
        stage.showAndWait();
        
    }

}
