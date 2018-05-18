/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.pracownik.modele.Produkt;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLDodajReklamacjeController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXButton bNowaReklamacja;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextArea tfOpis;
    @FXML
    private JFXTextField tfPrzedmiotReklamacji;
    @FXML
    private FontAwesomeIconView bWybierzProdukt;
    
    public static Produkt p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfPrzedmiotReklamacji.setDisable(true);
        
        
    }    

    @FXML
    private void dodajReklamacje(ActionEvent event) {
        //insert do bazy :)
       
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void wybierzPrzedmiot(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLPrzedmioty.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowaReklamacja.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void uzupelnijProdukt(MouseEvent event) {
        if (p != null)
            tfPrzedmiotReklamacji.setText("[" + p.getId_produktu() + "] " +p.getNazwa_produktu() + " " + p.getCena_produktu() + " zł");
    }
    
}
