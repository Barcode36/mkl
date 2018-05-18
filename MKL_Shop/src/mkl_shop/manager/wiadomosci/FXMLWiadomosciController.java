/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.wiadomosci;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import mkl_shop.connection.DBConnection;
import mkl_shop.manager.klasy.Alert;
import mkl_shop.manager.klasy.Ladowanie_danych;
import mkl_shop.manager.placowki.FXMLPlacowkiController;

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
    private TableView<WiadomosciClassa> Tabela_Wiadomosci;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Lp;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Nadawca;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Temat;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Data;
    @FXML
    private Pane Pane_tresc;
    @FXML
    private JFXTextField Text_Field_Nadawca;
    @FXML
    private JFXTextField Text_Field_Temat;
    @FXML
    private JFXTextArea Text_Area_Wiadomosc;
    @FXML
    private JFXButton Button_Usun;
    @FXML
    private Pane Pane_Wyslane;
    @FXML
    private TableView<WiadomosciClassa> Tabela_Wyslane;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Lp_Wyslane;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Adresta_Wyslane;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Temat_Wyslane;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Data_Wyslane;
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
    private JFXTextField Text_Temat;
    @FXML
    private JFXTextArea Text_Tresc_Wiadomosci;
    @FXML
    private JFXButton Button_Wyslij_Nowa;
    @FXML
    private JFXButton Button_Anuluj;
    DBConnection dc;
    private ObservableList<WiadomosciClassa> wiadomosc_lista;
    private ObservableList<WiadomosciClassa> wiadomosc_lista_wyslane;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_Status;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_ID;
    @FXML
    private TableColumn<WiadomosciClassa, String> Tab_ID_Wyslane;
    int id_wlasciela = 5;
    Ladowanie_danych dane_combo;
    @FXML
    private JFXComboBox ComboOdbiorca;
    int id_nadawacy;
    Date currentDate = new Date();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane_Skrzynka.setVisible(true);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(false);
        Pane_tresc.setVisible(false);
        Pane_tresc_wyslane.setVisible(false);
        load_skyrznke(id_wlasciela);
        load_wyslane(id_wlasciela);
        loadwiadomosciwyslane(id_wlasciela);
        loadwiadomosciodebrane(id_wlasciela);
        dc = new DBConnection();
        dane_combo = new Ladowanie_danych();

    }

    @FXML
    private void goWstecz(ActionEvent event) {
        Stage stage = (Stage) Button_Wstecz.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goSkrzynka(ActionEvent event) {
        Pane_Skrzynka.setVisible(true);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(false);
        Pane_tresc_wyslane.setVisible(false);

    }

    @FXML
    private void goWyslanie(ActionEvent event) {
        Pane_Skrzynka.setVisible(false);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(true);
        Pane_tresc.setVisible(false);
        load_wyslane(id_wlasciela);
    }

    @FXML
    private void goNowaWiadomosc(ActionEvent event) {
        Pane_Skrzynka.setVisible(false);
        Pane_Wiadomosci.setVisible(true);
        Pane_Wyslane.setVisible(false);
        Pane_tresc.setVisible(false);
        Pane_tresc_wyslane.setVisible(false);
        dane_combo.ladujCombo("SELECT CONCAT(imie_pracownika,\" \",nazwisko_pracownika,\" \",rola)AS Dane,id_pracownika FROM pracownik where rola='Menager' or rola='Kierownik' order by id_pracownika", ComboOdbiorca, "Dane");
       loadinfoplacowka(id_wlasciela);

    }

    @FXML
    private void WysalnieWiadomosci(ActionEvent event) {

        try {
            java.sql.Connection con = dc.Connect();
            Statement statement = con.createStatement();
            String odbiorca = (dane_combo.ladujliste("SELECT id_pracownika FROM Pracownik where rola='Menager' or rola='Kierownik' ORDER by id_pracownika", "id_pracownika").get(ComboOdbiorca.getSelectionModel().getSelectedIndex()).toString());
            String temat = Text_Temat.getText().toString();
            String tresc = Text_Tresc_Wiadomosci.getText().toString();
            if (!(odbiorca.isEmpty() || temat.isEmpty() || tresc.isEmpty())) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(currentDate);
                if (temat.length() <= 150) {
                    statement.executeUpdate("INSERT INTO `wiadomosci` (`id_pracownika_nadawcy`, `id_pracownika_odbiorca`, `temat_wiadomosci`, `tresc_wiadomosci`, `Data`, `status_wiadomosci`) "
                            + "VALUES (" + id_wlasciela + ", " + odbiorca + ", '" + temat + "', '" + tresc + "', '" + dateString + "', 'Nieodebrana')");
                    // load_wyslane(id_wlasciela);
                    Text_Temat.clear();
                    Text_Tresc_Wiadomosci.clear();
                     load_wyslane(id_wlasciela);
                } else {
                    System.out.println("Za długi temat");
                }
            } else {
                Alert.bledy("Sprawdź czy zostały wypełnioen wszystkie pola", "Błąd wysłania wiadomości");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    @FXML
    private void Anuluj_wyslanie(ActionEvent event) {
        Pane_Skrzynka.setVisible(true);
        Pane_Wiadomosci.setVisible(false);
        Pane_Wyslane.setVisible(false);
        Pane_tresc.setVisible(false);
        Pane_tresc_wyslane.setVisible(false);
        Text_Temat.clear();
        Text_Tresc_Wiadomosci.clear();
    }

    public void load_skyrznke(int id_wlasciela) {

        Tab_Lp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WiadomosciClassa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WiadomosciClassa, String> p) {
                return new ReadOnlyObjectWrapper(Tabela_Wiadomosci.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        Tab_Lp.setSortable(false);
        try {
            java.sql.Connection con = dc.Connect();
            wiadomosc_lista = FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("Select Concat(p.imie_pracownika,\" \", p.nazwisko_pracownika, \" \",p.rola)As Nadawca, "
                    + "w.temat_wiadomosci,w.data,w.status_wiadomosci, w.id_wiadomosci "
                    + "from wiadomosci w, pracownik p "
                    + "where  p.id_pracownika=w.id_pracownika_nadawcy and w.id_pracownika_odbiorca=" + id_wlasciela);
            while (res.next()) {
                wiadomosc_lista.add(new WiadomosciClassa(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5)));

            }
            Tab_Nadawca.setCellValueFactory(new PropertyValueFactory<>("id_nadawcy"));
            Tab_Temat.setCellValueFactory(new PropertyValueFactory<>("temat_wiadomosci"));
            Tab_Data.setCellValueFactory(new PropertyValueFactory<>("data"));
            Tab_Status.setCellValueFactory(new PropertyValueFactory<>("status_wiadomosci"));
            Tab_ID.setCellValueFactory(new PropertyValueFactory<>("id_wiadomosci"));
            Tabela_Wiadomosci.setItems(null);
            Tabela_Wiadomosci.setItems(wiadomosc_lista);

            con.close();

        } catch (SQLException ex) {
            System.out.println("Error:" + ex);

        }

    }

    public void loadwiadomosciodebrane(int id_wlasciela) {
        Tabela_Wiadomosci.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int id;
                id = Tabela_Wiadomosci.getSelectionModel().getSelectedItem().getId_wiadomosci();
                try {
                    java.sql.Connection con = dc.Connect();
                    ResultSet res = con.createStatement().executeQuery("Select Concat(p.imie_pracownika,\" \", p.nazwisko_pracownika, \" \",p.rola)As Nadawca,"
                            + "w.temat_wiadomosci,w.tresc_wiadomosci,w.id_pracownika_nadawcy "
                            + "from wiadomosci w, pracownik p "
                            + "where p.id_pracownika=w.id_pracownika_nadawcy and w.id_wiadomosci='" + id + "'");

                    while (res.next()) {
                        Text_Field_Nadawca.setText(res.getString(1));
                        Text_Field_Temat.setText(res.getString(2));
                        Text_Area_Wiadomosc.setText(res.getString(3));
                        id_nadawacy = res.getInt(4);
                    }
                    Pane_tresc.setVisible(true);
                    String query = "UPDATE `wiadomosci` SET `status_wiadomosci`='Odebrane' where id_wiadomosci=" + id;
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    load_skyrznke(id_wlasciela);
                } catch (SQLException ex) {
                    System.err.print(ex);
                }

            }
        });
    }

    public void load_wyslane(int id_wlasciela) {      
        Tab_Lp_Wyslane.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WiadomosciClassa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WiadomosciClassa, String> p) {
                return new ReadOnlyObjectWrapper(Tabela_Wyslane.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        Tab_Lp_Wyslane.setSortable(false);
        try {
            java.sql.Connection con = dc.Connect();
            wiadomosc_lista_wyslane = FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("SELECT Concat(p.imie_pracownika,\" \",p.nazwisko_pracownika,\" \",p.rola)as Adresat ,w.temat_wiadomosci,w.data,w.id_wiadomosci "
                    + "from wiadomosci w, pracownik p "
                    + "where w.id_pracownika_nadawcy=" + id_wlasciela + " and p.id_pracownika=w.id_pracownika_odbiorca");
            while (res.next()) {
                wiadomosc_lista_wyslane.add(new WiadomosciClassa(res.getString(1), res.getString(2), res.getString(3), res.getInt(4)));

            }
            Tab_Adresta_Wyslane.setCellValueFactory(new PropertyValueFactory<>("id_odbiorcy"));
            Tab_Temat_Wyslane.setCellValueFactory(new PropertyValueFactory<>("temat_wiadomosci"));
            Tab_Data_Wyslane.setCellValueFactory(new PropertyValueFactory<>("data"));
            Tab_ID_Wyslane.setCellValueFactory(new PropertyValueFactory<>("id_wiadomosci"));

            Tabela_Wyslane.setItems(null);
            Tabela_Wyslane.setItems(wiadomosc_lista_wyslane);

            con.close();

        } catch (SQLException ex) {
            System.out.println("Error:" + ex);

        }

    }

    public void loadwiadomosciwyslane(int id_wlasciela) {
        Tabela_Wyslane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int id;
                try {
                    id = Tabela_Wyslane.getSelectionModel().getSelectedItem().getId_wiadomosci();
                    java.sql.Connection con = dc.Connect();
                    ResultSet res = con.createStatement().executeQuery("Select Concat(p.imie_pracownika,\" \", p.nazwisko_pracownika, \" \",p.rola)As Adresat,"
                            + "w.temat_wiadomosci,w.tresc_wiadomosci "
                            + "from wiadomosci w, pracownik p "
                            + "where w.id_pracownika_nadawcy=" + id_wlasciela + " and p.id_pracownika=w.id_pracownika_odbiorca and w.id_wiadomosci='" + id + "'");

                    while (res.next()) {
                        Text_Field_Adresta.setText(res.getString(1));
                        Text_Field_Temat_Wyslane.setText(res.getString(2));
                        Text_Area_Wiadomosc_Wyslane.setText(res.getString(3));

                    }
                    Pane_tresc_wyslane.setVisible(true);
                } catch (Exception ex) {
                    //  System.err.print(ex);
                }

            }
        });
    }

    @FXML
    private void goUsun_Odebrane(ActionEvent event) {
        Pane_tresc.setVisible(false);
        try {
            java.sql.Connection con = dc.Connect();

            int id_odebrane_delete = Tabela_Wiadomosci.getSelectionModel().getSelectedItem().getId_wiadomosci();
            System.out.println(id_odebrane_delete + "istniej");
            String query = "Delete From wiadomosci where id_wiadomosci=" + id_odebrane_delete;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.executeUpdate();
            load_skyrznke(id_wlasciela);
            load_wyslane(id_wlasciela);
            Pane_tresc_wyslane.setVisible(false);

        } catch (Exception e) {

        }
    }

    @FXML
    private void goUsunWyslane(ActionEvent event) {
        Pane_tresc.setVisible(false);
        try {
            java.sql.Connection con = dc.Connect();
            int id_odebrane_delete = Tabela_Wyslane.getSelectionModel().getSelectedItem().getId_wiadomosci();
            String query = "Delete From wiadomosci where id_wiadomosci=" + id_odebrane_delete;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.executeUpdate();
            load_skyrznke(id_wlasciela);
            load_wyslane(id_wlasciela);
            Pane_tresc_wyslane.setVisible(false);
        } catch (Exception e) {

        }
    }

    public void loadinfoplacowka(int id_wlasciela) {
        try {
            java.sql.Connection con = dc.Connect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("Select Concat(p.imie_pracownika,\" \", p.nazwisko_pracownika, \" \",p.rola)As Nadawca "                    
                    + "from wiadomosci w, pracownik p "
                    + "where  p.id_pracownika=w.id_pracownika_nadawcy and w.id_pracownika_nadawcy=" + id_wlasciela);

            while (rs.next()) {
                Text_Nadawca.setText(rs.getString(1));
            }
            ps.close();
            rs.close();
            con.close();
      
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPlacowkiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
