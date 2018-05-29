/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.kierownik.FXMLKierownikController;
import mkl_shop.manager.FXMLManagerController;
import mkl_shop.pracownik.FXMLPracownikController;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLLogowanieController implements Initializable {

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXPasswordField tfHaslo;
    @FXML
    private JFXTextField tfLogin;
    @FXML
    private JFXButton bZaloguj;
    @FXML
    private JFXButton bWyjscie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void zaloguj(ActionEvent event) throws IOException {
        String rola = "";
        boolean sprawdzenie = false;
        Integer idPracownika = 0;
        Integer idPlacowki = 0;
        
        Connection conn = DBConnection.Connect();
        
        
        try {
            
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT login,haslo_administratora FROM administrator WHERE login='"+tfLogin.getText()+"' AND haslo_administratora='"+tfHaslo.getText()+"';");
            
            if (rs.isBeforeFirst()){
                //rs.next();
                rola = "admin";
            }
            
            
            
            
            if (!rola.equals("admin")){
                rs = ps.executeQuery("SELECT id_pracownika,id_placowki,login,haslo,rola FROM pracownik WHERE login='"+tfLogin.getText()+"' AND haslo='"+tfHaslo.getText()+"';");
                if(rs.isBeforeFirst()){
                    rs.next();
                    System.out.println(rs.getString("rola"));
                    rola = rs.getString("rola");
                    idPracownika = rs.getInt("id_pracownika");
                    idPlacowki = rs.getInt("id_placowki");
                }
                
            }
            Stage stage;
        Parent root;
            
            
            switch(rola){
                case "admin":
                    //okno admina                   
                    
                    
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    
                    root = FXMLLoader.load(getClass().getResource("admin/AdminFXML.fxml"));
                    root.getStylesheets().add(mkl_shop.admin.AdminFXMLController.class.getResource("fxmladmin.css").toExternalForm());
                    stage.setScene(new Scene(root));
                    
                    stage.centerOnScreen();
                    break;
                case "Menager":
                    //okno managera
                    FXMLManagerController.idManagera=idPracownika;
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("manager/FXMLManager.fxml"));
                    stage.setScene(new Scene(root));
                    
                    stage.centerOnScreen();
                    break;
                case "Kierownik":
                    //okno kierownika
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("kierownik/FXMLKierownik.fxml"));
                    
                    stage.setScene(new Scene(root));     
                    
                    stage.centerOnScreen();
                    
                    FXMLKierownikController.idPlacowki = idPlacowki;
                    FXMLKierownikController.idPracownika = idPracownika;
                    break;
                case "sprzedawca":
                    //okno pracownika
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("pracownik/FXMLPracownik.fxml"));
                    stage.setScene(new Scene(root));
                    
                    stage.centerOnScreen();
                    
                    
                    FXMLPracownikController.idPlacowki = idPlacowki;
                    FXMLPracownikController.idPracownika = idPracownika;
                    break;
                default :
                    JFXButton bCancel1 = new JFXButton("Ok");
                    AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel1), "Błąd", "Sprawdź poprawność wpisanego loginu i hasła!");
                    break;
            }
            
            
            
            
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLogowanieController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        
    }

    @FXML
    private void wyjscie(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }
    
}
