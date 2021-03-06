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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkl_shop.MKL_Shop;
import mkl_shop.alert.AlertMaker;
import mkl_shop.connection.DBConnection;
import mkl_shop.pracownik.FXMLPracownikController;
import mkl_shop.pracownik.modele.Klient;
import mkl_shop.pracownik.modele.Produkt;
import static mkl_shop.pracownik.zakupy.FXMLListaKlientowController.k;

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
    private TableView<Produkt> tableRachunek;
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
    private TableColumn<Produkt, Integer> columnIdProduktu;
    @FXML
    private TableColumn<Produkt, String> columnNazwa;
    @FXML
    private TableColumn<Produkt, String> columnOpis;
    @FXML
    private TableColumn<Produkt, Double> columnCenaSzt;
    @FXML
    private TableColumn<Produkt, Integer> columnIlosc;
    @FXML
    private TableColumn<Produkt, Double> columnCena;
    @FXML
    private JFXCheckBox cStalyKlient;
    @FXML
    private Label lStalyKlient;
    @FXML
    private FontAwesomeIconView bListaKlientow;

    public static ObservableList<Produkt> dataRachunek;
    public static Klient k1;
    public static Double rabat = 1.0;
    Integer idKlienta;
    private static Double price;
    @FXML
    private JFXButton bNagroda;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataRachunek = FXCollections.observableArrayList();
        odswiezRachunek();
       if (!dataRachunek.isEmpty()){
           bNagroda.setDisable(true);
       }

      
        dataRachunek.addListener(new ListChangeListener<Produkt>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produkt> c) {
                Double suma = 0.0;
                
               if (!dataRachunek.isEmpty()){
           bNagroda.setDisable(true);}
           else {bNagroda.setDisable(false);}
                
                while (c.next()) {

                    for (Produkt p1 : dataRachunek) {
                        suma += p1.getSuma();
                    }
                    suma = suma * rabat;
                    lSumaWartosc.setText(suma.toString() + " zł");
                    setCena(suma);
                }
            }

        });

    }
    
    /** 
     * Przypisanie do pola price nowej wartości typu Double.
     * @param cena 
     */
    private void setCena(double cena){
        price = cena;
    }
    
    /**
     * Umożliwia otrzymanie aktualnej ceny.
     * @return Zwraca cenę, typu double.
     */
    public static Double getCena(){
        return price;
    }

    /**
     * Otwiera nowe okno umożliwiające dodawanie produktów do bieżącego rachunku.
     * @param event
     * @throws IOException 
     */
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
        k1 = null;
    }

    /**
     * Usuwanie z rachunku wybranego przedmiotu.
     * @param event 
     */
    @FXML
    private void usunZRachunku(ActionEvent event) {
       
        JFXButton bOkay = new JFXButton("Tak, usuń");
        JFXButton bCancel = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdź decyzję", "Czy na pewno chcesz usunąć wpis?");
        bOkay.setOnAction((ActionEvent event1) -> {
            try {
                

                tableRachunek.getItems().remove(tableRachunek.getSelectionModel().getSelectedIndex());
                
            } catch (Exception exp) {
                AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel), "Błąd", "Nie można usunąć pozycji");
            }
        });

    }

    @FXML
    private void zaznaczonyPrzedmiot(MouseEvent event) {
        
    }

    /**
     * Wyświetla okienko z pytaniem, czy chcemy sfinalizowac transakcję i dodanie tego do bazy danych.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void finalizujTransakcje(ActionEvent event) throws IOException {
        
        
        if (!dataRachunek.isEmpty()){

            JFXButton bOkay = new JFXButton("Tak");
        JFXButton bCancel = new JFXButton("Anuluj");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdzenie transakcji", "Czy na pewno chcesz zatwierdzić transakcję?");
        bOkay.setOnAction((ActionEvent event1) -> {
            
            
            
                try {
                    Stage stage = (Stage) bWyjscie.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLFinalizacja.fxml"));
                    stage.setScene(new Scene(root));
                    
                  
                    
                    Connection conn = DBConnection.Connect();
                    String insertSQL;
                    
                    if (cStalyKlient.isSelected() && k1 != null){
                       
                        Integer punkty = k1.getLiczba_punktow() + (int)(getCena()/1);
                        
                        conn.createStatement().executeUpdate("UPDATE klient SET liczba_punktow="+punkty+" WHERE id_klienta="+k1.getId_klienta()+";");
                        
                        insertSQL = "INSERT INTO transakcja (id_transakcji,id_pracownika,id_klienta,data,calkowity_koszt) VALUES "
                                + "(null,"+FXMLPracownikController.idPracownika+","+k1.getId_klienta()+",'"+LocalDate.now().toString()+"',"+getCena()+");";
                        
                        
                    } else {
                      
                        
                        insertSQL = "INSERT INTO transakcja (id_transakcji,id_pracownika,id_klienta,data,calkowity_koszt) VALUES "
                                + "(null,"+FXMLPracownikController.idPracownika+",null,'"+LocalDate.now().toString()+"',"+getCena()+")";
                    }
                    
                    
                   
                    String generatedColumns[] = { "id_transakcji" };
                    PreparedStatement stmtInsert = conn.prepareStatement(insertSQL, generatedColumns);
                    
                    stmtInsert.executeUpdate();
                    ResultSet rs = stmtInsert.getGeneratedKeys();
                    int idTransakcji = 0;
                    if (rs.next()) {
                        idTransakcji = rs.getInt(1);
                        System.out.println(idTransakcji);
                    }
                    
             
                    
                    for (Produkt produkt : dataRachunek){
                        if (produkt.getId_produktu()>0){
                        conn.createStatement().executeUpdate("INSERT INTO transakcja_produkty (id_transakcja_produkty,id_produktu,id_transakcji,ilosc_produktow,cena_transakcji) "
                                + "VALUES (null,"+produkt.getId_produktu()+","+idTransakcji+","+produkt.getSztuki()+",'"+produkt.getSuma()+"');");
                        
                     
                        
                        int aktualnyStan = produkt.getIlosc_produktow() - produkt.getSztuki();
                        
                        conn.createStatement().executeUpdate("UPDATE placowka_produkt SET ilosc_produktow="+aktualnyStan+" WHERE id_placowki="+FXMLPracownikController.idPlacowki+" AND id_produktu="+produkt.getId_produktu()+";");
                        }}
                    
                    
                    rabat = 1.0;
                    rs.close();
                    stmtInsert.close();
                    conn.close();
                    k1 = null;
                } catch (IOException ex) {
                    Logger.getLogger(FXMLZakupyController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLZakupyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
            });
            
            
        } else {
            JFXButton bCancel1 = new JFXButton("Ok");
            AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel1), "Błąd", "Aby sfinalizować transakcję, na rachunku muszą znajdować się produkty.");
   
        }
        
        
        
        
        
        
    }

    
    /**
     * Sprawdza czy jest ustawiony jakiś klient.
     * @param event 
     */
    @FXML
    private void wybierzKlienta(ActionEvent event) {
        if (cStalyKlient.isSelected()) {
            bListaKlientow.setDisable(false);
        } else {
            bListaKlientow.setDisable(true);
        }
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

    
    /**
     * Przypisuje id klienta podanego jako parametr do pola idKlienta.
     * @param k Obiekt klasy Klient.
     */
    public void setKlient(Klient k) {
        idKlienta = k.getId_klienta();
       
    }
/**
 * Odświeżanie rachunku.
 */
    public void odswiezRachunek() {

        columnIdProduktu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnCenaSzt.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));
        columnIlosc.setCellValueFactory(new PropertyValueFactory<>("sztuki"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("suma"));

        tableRachunek.setItems(null);
        tableRachunek.setItems(dataRachunek);

    }

    @FXML
    private void uzupelnijKlienta(MouseEvent event) {
        if (k1 != null)
            lStalyKlient.setText(k1.getImie_klienta()+ " " + k1.getNazwisko_klienta() + " (" + k1.getNumer_karty() + ") " + k1.getLiczba_punktow() + "pkt");
        
        if (rabat != 1.0){
            bListaKlientow.setDisable(true);
            cStalyKlient.setDisable(true);
        }
      
    }

    @FXML
    private void wybierzNagrode(ActionEvent event) throws IOException {
        if (k1 != null){
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLPunkty.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel klientów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bWyjscie.getScene().getWindow());
        stage.showAndWait();
        }
    }

}
