/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BlackHawk
 */
public class Klient {
    
    private IntegerProperty id_klienta;
    private StringProperty imie_klienta;
    private StringProperty nazwisko_klienta;
    private StringProperty kod_pocztowy_klienta;
    private StringProperty miejscowosc_klienta;
    private StringProperty adres_klienta;
    private StringProperty telefon_klienta;
    private IntegerProperty numer_karty;
    private IntegerProperty liczba_punktow;
    
    
    
    //      ***Konstruktory***
    
    public Klient (int id_klienta, String imie_klienta,String nazwisko_klienta, String kod_pocztowy_klienta, String miejscowosc_klienta, String adres_klienta, String telefon_klienta, int numer_karty, int liczba_punktow){
        this.id_klienta = new SimpleIntegerProperty(id_klienta);
        this.imie_klienta = new SimpleStringProperty(imie_klienta);
        this.nazwisko_klienta = new SimpleStringProperty(nazwisko_klienta);
        this.kod_pocztowy_klienta = new SimpleStringProperty(kod_pocztowy_klienta);
        this.miejscowosc_klienta = new SimpleStringProperty(miejscowosc_klienta);
        this.adres_klienta = new SimpleStringProperty(adres_klienta);
        this.telefon_klienta = new SimpleStringProperty(telefon_klienta);
        this.numer_karty = new SimpleIntegerProperty(numer_karty);
        this.liczba_punktow = new SimpleIntegerProperty(liczba_punktow);
    }
    
    
    //      ***Gettery***
    public int getId_klienta(){
        return id_klienta.get();
    }
    
    public String getImie_klienta(){
        return imie_klienta.get();
    }
    
    public String getNazwisko_klienta(){
        return nazwisko_klienta.get();
    }
    
    public String getKod_pocztowy_klienta(){
        return kod_pocztowy_klienta.get();
    }
    
    public String getMiejscowosc_klienta(){
        return miejscowosc_klienta.get();
    }
    
    public String getAdres_klienta(){
        return adres_klienta.get();
    }
    
    public String getTelefon_klienta(){
        return telefon_klienta.get();
    }
    
    public Integer getNumer_karty(){
        return numer_karty.get();
    }
    
    public int getLiczba_punktow(){
        return liczba_punktow.get();
    }
    
    
    
    //      ***Settery***
    
    public void setImie_klienta(String value){
        imie_klienta.set(value);
    }
    
    
    public void setNazwisko_klienta (String value){
        nazwisko_klienta.set(value);
    }
    
    public void setKod_pocztowy_klienta(String value){
        kod_pocztowy_klienta.set(value);
    }
    
    public void setMiejscowosc_klienta (String value){
        miejscowosc_klienta.set(value);
    }
    
    public void setAdres_klienta (String value){
        adres_klienta.set(value);
    }
    
    public void setTelefon_klienta (String value){
        telefon_klienta.set(value);
    }
    
    public void setNumer_karty (int value){
        numer_karty.set(value);
    }
    
    public void setLiczba_punktow (int value){
        liczba_punktow.set(value);
    }
    
    
    
    //      ***Properties***
    
    public IntegerProperty id_klientaProperty(){
        return id_klienta;
    }
    
    public StringProperty imie_klientaProperty(){
        return imie_klienta;
    }
    
    public StringProperty nazwisko_klientaProperty(){
        return nazwisko_klienta;
    }
    
    public StringProperty kod_pocztowy_klientaProperty(){
        return kod_pocztowy_klienta;
    }
    
    public StringProperty miejscowosc_klientaProperty(){
        return miejscowosc_klienta;
    }
    
    public StringProperty adres_klientaProperty(){
        return adres_klienta;
    }
    
    public StringProperty telefon_klientaProperty(){
        return telefon_klienta;
    }
    
    public IntegerProperty numer_kartyProperty(){
        return numer_karty;
    }
    
    public IntegerProperty liczba_punktowProperty(){
        return liczba_punktow;
    }

    @Override
    public String toString() {
        return imie_klienta.get() + " " + nazwisko_klienta.get() + ", " + kod_pocztowy_klienta.get() + " " + miejscowosc_klienta.get() + "\n" + adres_klienta.get() + "\n Tel. " + telefon_klienta.get() + "\n Nr karty : " + numer_karty.get() + ", punkty : " + liczba_punktow.get();
    }
    
    
    
}
