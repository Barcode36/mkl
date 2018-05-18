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
    private IntegerProperty telefon_placowki;
    private StringProperty kod_pocztowy_placowki;
    private StringProperty miejscowosc_placowki;
    private StringProperty adres_placowki;

    //      ***Konstruktory***
    public Placowka(int id_placowki, int telefon_placowki, String kod_pocztowy_placowki, String miejscowosc_placowki, String adres_placowki) {
        this.id_placowki = new SimpleIntegerProperty(id_placowki);
        this.telefon_placowki = new SimpleIntegerProperty(telefon_placowki);
        this.kod_pocztowy_placowki = new SimpleStringProperty(kod_pocztowy_placowki);
        this.miejscowosc_placowki = new SimpleStringProperty(miejscowosc_placowki);
        this.adres_placowki = new SimpleStringProperty(adres_placowki);
    }

    //      ***Gettery***
    public int getId_placowki() {
        return id_placowki.get();
    }

    public int getTelefon_placowki() {
        return telefon_placowki.get();
    }

    public String getKod_pocztowy_placowki() {
        return kod_pocztowy_placowki.get();
    }

    public String getMiejscowosc_placowki() {
        return miejscowosc_placowki.get();
    }

    public String getAdres_placowki() {
        return adres_placowki.get();
    }

    //      ***Settery***
    public void setTelefon_placowki(int value) {
        telefon_placowki.set(value);
    }

    public void setKod_pocztowy_placowki(String value) {
        kod_pocztowy_placowki.set(value);
    }

    public void setMiejscowosc_placowki(String value) {
        miejscowosc_placowki.set(value);
    }

    public void setAdres_placowki(String value) {
        adres_placowki.set(value);
    }
    
        //      ***Properties***
    
    public IntegerProperty id_placowkiProperty(){
        return id_placowki;
    }
       public IntegerProperty telefon_placowkiProperty(){
        return telefon_placowki;
    }
    
    public StringProperty kod_pocztowy_placowkiProperty(){
        return kod_pocztowy_placowki;
    }
    public StringProperty miejscowosc_placowkiProperty(){
        return miejscowosc_placowki;
    }
        public StringProperty adres_placowkiProperty(){
        return adres_placowki;
    }
}
