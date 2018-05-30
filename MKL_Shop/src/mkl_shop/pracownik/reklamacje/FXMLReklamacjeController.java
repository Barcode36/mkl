/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.reklamacje;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.FXMLPracownikController;
import mkl_shop.pracownik.modele.Reklamacje;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLReklamacjeController implements Initializable {

    @FXML
    private TableView<Reklamacje> tableReklamacje;
    @FXML
    private TableColumn<Reklamacje, Integer> columnIdReklamacji;
    @FXML
    private TableColumn<Reklamacje, String> columnOpis;
    @FXML
    private TableColumn<Reklamacje, Integer> columnIdProduktu;
    @FXML
    private TableColumn<Reklamacje, String> columnNazwaProduktu;
    @FXML
    private TableColumn<Reklamacje, Double> columnCenaProduktu;
    @FXML
    private TableColumn<Reklamacje, LocalDate> columnData;
    @FXML
    private TableColumn<Reklamacje, String> columnStatus;
    @FXML
    private JFXButton bNowaReklamacja;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXTextArea tfOpis;
    @FXML
    private MenuItem cmFinalizacja;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    Reklamacje r;
    ObservableList <Reklamacje> dataReklamacje;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfOpis.setFocusTraversable(true);
        //tfOpis.setDisable(true);
        tfOpis.setEditable(false);
        tfOpis.setText(" ");
        
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReklamacjeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        FilteredList<Reklamacje> filteredReklamacje = new FilteredList<>(tableReklamacje.getItems(), e -> true);
        tfWyszukaj.setOnKeyReleased(e -> {
            tfWyszukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredReklamacje.setPredicate((Predicate<? super Reklamacje>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getNazwa_produktu().toLowerCase().contains(lcFilter) || k.getId_reklamacji().toString().contains(lcFilter) || k.getData().toString().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Reklamacje> sortedReklamacje = new SortedList<>(filteredReklamacje);
            tableReklamacje.setItems(sortedReklamacje);
        });
        
        
        
    }    

    @FXML
    private void nowaReklamacja(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLDodajReklamacje.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowaReklamacja.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void finalizujReklamacje(ActionEvent event) throws SQLException {
        
        //zmiana stanu
        //wyslanie wiadomosci o przyjeciu reklamacji do kierownika lub odrzuceniu
        
        JFXButton bCancel = new JFXButton("Odrzuć");
        JFXButton bOkay = new JFXButton("Akceptuj");
        
        if (r.getStan().equals("W trakcie")){
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay,bCancel), "Finalizacja reklamacji", "Co chcesz zrobić z wybraną reklamacją?");
        
       
        
        bOkay.setOnAction((ActionEvent event1) -> {
            try {
                Connection conn = DBConnection.Connect();
                String tresc = "Przyjęliśmy reklamację produktu nr{"+r.getId_produktu()+"} "+ r.getNazwa_produktu() +" opis reklamacji: " + r.getOpis();
                LocalDate ld = LocalDate.now();
                conn.createStatement().executeUpdate("UPDATE reklamacja SET stan='Przyjęto' WHERE id_reklamacji="+r.getId_reklamacji()+";");
                conn.createStatement().executeUpdate("INSERT INTO wiadomosci (id_wiadomosci,id_pracownika_nadawcy,id_pracownika_odbiorca,temat_wiadomosci,tresc_wiadomosci,Data,status_wiadomosci) "
                        + "VALUES (null,"+FXMLPracownikController.idPracownika+","+FXMLPracownikController.idKierownika+",'Przyjęto reklamację','"+tresc+"','"+ld.toString()+"','Nieodebrana');");
                System.out.println("Przyjmuje");
                conn.close();
            } catch (Exception exp) {
                //nieobsłużone
                //System.out.println(exp);
            }
        });
        
        
        
        bCancel.setOnAction((ActionEvent event1) -> {
            try {
                Connection conn = DBConnection.Connect();
                String tresc = "Odrzuciliśmy reklamację produktu nr{"+r.getId_produktu()+"} "+ r.getNazwa_produktu() +" opis reklamacji: " + r.getOpis();
                LocalDate ld = LocalDate.now();
                conn.createStatement().executeUpdate("UPDATE reklamacja SET stan='Odrzucono' WHERE id_reklamacji="+r.getId_reklamacji()+";");
                conn.createStatement().executeUpdate("INSERT INTO wiadomosci (id_wiadomosci,id_pracownika_nadawcy,id_pracownika_odbiorca,temat_wiadomosci,tresc_wiadomosci,Data,status_wiadomosci) "
                        + "VALUES (null,"+FXMLPracownikController.idPracownika+","+FXMLPracownikController.idKierownika+",'Odrzucono reklamację','"+tresc+"','"+ld.toString()+"','Nieodebrana');");

                
                conn.close();
            } catch (Exception exp) {
                //nieobsłużone
            }
        });
        }
        
        
        
    }

    @FXML
    private void odswiez(MouseEvent event) throws SQLException {
        refresh();
        
        
    }
    
    
    
    private void refresh() throws SQLException{
        dataReklamacje = FXCollections.observableArrayList();
        Connection conn = DBConnection.Connect();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT id_reklamacji,produkty.id_produktu as id_produktu,opis_produktu,opis,stan,nazwa_produktu,cena_produktu,data,id_placowki FROM reklamacja,produkty WHERE id_placowki="+FXMLPracownikController.idPlacowki+" AND reklamacja.id_produktu=produkty.id_produktu;");
        
        while (rs.next()){
            dataReklamacje.add(new Reklamacje(rs.getInt("id_reklamacji"), rs.getInt("id_produktu"), rs.getString("opis_produktu"),rs.getString("opis"), rs.getString("stan"),rs.getDouble("cena_produktu"),rs.getString("nazwa_produktu"), ((Date) rs.getObject("data")).toLocalDate()));
        }       
        
        
        rs.close();
        conn.close();
        
        
        columnIdReklamacji.setCellValueFactory(new PropertyValueFactory<>("id_reklamacji"));
        columnIdProduktu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        columnCenaProduktu.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnNazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("stan"));
        
        
        tableReklamacje.setItems(null);
        tableReklamacje.setItems(dataReklamacje);
        
        
        
    }

    @FXML
    private void wybierzReklamacje(MouseEvent event) {
        
        if (tableReklamacje.getSelectionModel().getSelectedItem() != null){
            tfOpis.setText(tableReklamacje.getSelectionModel().getSelectedItem().getOpis());
            r = tableReklamacje.getSelectionModel().getSelectedItem();
        }
        
        
    }
    
    
    
}
