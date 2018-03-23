/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import mkl_shop.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLListaPrzedmiotowController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bDodaj;
    @FXML
    private TableView<?> tablePrzedmioty;
    @FXML
    private TableColumn<?, ?> columnIdPrzedmiotu;
    @FXML
    private TableColumn<?, ?> columnNazwa;
    @FXML
    private TableColumn<?, ?> columnOpis;
    @FXML
    private TableColumn<?, ?> columnNaStanie;
    @FXML
    private TableColumn<?, ?> columnCena;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXTextField tfIlosc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //odblokowac przy wyborze przedmiotu z tabeli oraz wpisaniu ilosci produktow (domyslnie uzupelniac 1)
        
        tfIlosc.setText("1");
        tfIlosc.setFocusTraversable(true);
        
        tfIlosc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty() || !Sprawdzanie.czyLiczby(newValue)) bDodaj.setDisable(true);
                else bDodaj.setDisable(false);
            }
        });
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dodajDoRachunku(ActionEvent event) {
        //dodanie do poprzedniego okna do tabeli produktu
    }


    
}
