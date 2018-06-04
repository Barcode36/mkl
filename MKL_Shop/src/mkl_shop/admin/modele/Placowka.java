/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.admin.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Łukasz
 */
public class Placowka {

    private IntegerProperty id_placowki;
    private StringProperty telefon_placowki;
    private StringProperty kod_pocztowy_placowki;
    private StringProperty miejscowosc_placowki;
    private StringProperty adres_placowki;

    //      ***Konstruktory***
    /**
     * Konstruktor potrzebny do wyswietlenia danych z tabeli Placowka
     *
     * @param id_placowki
     * @param telefon_placowki
     * @param kod_pocztowy_placowki
     * @param miejscowosc_placowki
     * @param adres_placowki
     */
    public Placowka(int id_placowki, String telefon_placowki, String kod_pocztowy_placowki, String miejscowosc_placowki, String adres_placowki) {
        this.id_placowki = new SimpleIntegerProperty(id_placowki);
        this.telefon_placowki = new SimpleStringProperty(telefon_placowki);
        this.kod_pocztowy_placowki = new SimpleStringProperty(kod_pocztowy_placowki);
        this.miejscowosc_placowki = new SimpleStringProperty(miejscowosc_placowki);
        this.adres_placowki = new SimpleStringProperty(adres_placowki);
    }

    //      ***Gettery***
    /**
     * Metoda pobierająca id placówki
     *
     * @return id_placowki
     */
    public int getId_placowki() {
        return id_placowki.get();
    }

    /**
     * Metoda pobierająca telefon placówki
     * @return telefon_placowki
     */
    public String getTelefon_placowki() {
        return telefon_placowki.get();
    }

    /**
     * Metoda pobierająca kod pocztowy placówki
     * @return kod_pocztowy_placowki
     */
    public String getKod_pocztowy_placowki() {
        return kod_pocztowy_placowki.get();
    }

    /**
     * Metoda pobierająca miejscowość placówki
     * @return miejscowosc_placowki
     */ 
    public String getMiejscowosc_placowki() {
        return miejscowosc_placowki.get();
    }

    /**
     * Metoda pobierająca adres placówki
     * @return adres_placowki
     */
    public String getAdres_placowki() {
        return adres_placowki.get();
    }

    //      ***Settery***
    /**
     * Metoda ustawiająca telefon placówki
     * @param value
     */
    public void setTelefon_placowki(String value) {
        telefon_placowki.set(value);
    }

    /**
     * Metoda ustawiająca kod pocztowy placówki
     * @param value
     */
    public void setKod_pocztowy_placowki(String value) {
        kod_pocztowy_placowki.set(value);
    }

    /**
     * Metoda ustawiająca miejscowość placówki
     * @param value
     */
    public void setMiejscowosc_placowki(String value) {
        miejscowosc_placowki.set(value);
    }

    /**
     * Metoda ustawiająca adres placówki
     * @param value
     */
    public void setAdres_placowki(String value) {
        adres_placowki.set(value);
    }

    //      ***Properties***
    /**
     * Metoda potrzebna do wyświetlenia id placówki z bazy w aplikacji
     * @return id_placowki
     */
    public IntegerProperty id_placowkiProperty() {
        return id_placowki;
    }

    /**
     * Metoda potrzebna do wyświetlenia telefonu placówki z bazy w aplikacji
     * @return
     */
    public StringProperty telefon_placowkiProperty() {
        return telefon_placowki;
    }

    /**
     * Metoda potrzebna do wyświetlenia kodu pocztowego placówki z bazy w aplikacji
     * @return kod_pocztowy_placowki
     */
    public StringProperty kod_pocztowy_placowkiProperty() {
        return kod_pocztowy_placowki;
    }

    /**
     * Metoda potrzebna do wyświetlenia miejscowości placówki z bazy w aplikacji
     * @return miejscowosc_placowki
     */ 
    public StringProperty miejscowosc_placowkiProperty() {
        return miejscowosc_placowki;
    }

    /**
     * Metoda potrzebna do wyświetlenia adresu placówki z bazy w aplikacji
     * @return adres_placowki
     */
    public StringProperty adres_placowkiProperty() {
        return adres_placowki;
    }
}
