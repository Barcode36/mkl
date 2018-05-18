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
public class Pracownik {
        
    private IntegerProperty id_pracownika;
    private StringProperty imie_pracownika;
    private StringProperty nazwisko_pracownika;
    private IntegerProperty pesel_pracownika;
    private IntegerProperty telefon_pracownika;
    private StringProperty login;
    private StringProperty haslo;
    private StringProperty rola;
    private StringProperty adres_placowki;
    
    
    
    //      ***Konstruktory***
    
    public Pracownik (int id_pracownika, String imie_pracownika,String nazwisko_pracownika, int pesel_pracownika, int telefon_pracownika, String login, String haslo, String rola, String adres_placowki){
        this.id_pracownika = new SimpleIntegerProperty(id_pracownika);
        this.imie_pracownika = new SimpleStringProperty(imie_pracownika);
        this.nazwisko_pracownika = new SimpleStringProperty(nazwisko_pracownika);
        this.pesel_pracownika = new SimpleIntegerProperty(pesel_pracownika);
        this.telefon_pracownika = new SimpleIntegerProperty(telefon_pracownika);
        this.login = new SimpleStringProperty(login);
        this.haslo = new SimpleStringProperty(haslo);
        this.rola = new SimpleStringProperty(rola);
        this.adres_placowki = new SimpleStringProperty(adres_placowki);
    }
    
    
    //      ***Gettery***
    public int getId_pracownika(){
        return id_pracownika.get();
    }
    
    public String getImie_pracownika(){
        return imie_pracownika.get();
    }
    
    public String getNazwisko_pracownika(){
        return nazwisko_pracownika.get();
    }
    
    public int getPesel_pracownika(){
        return pesel_pracownika.get();
    }
    
    public int getTelefon_pracownika(){
        return telefon_pracownika.get();
    }
    
    public String getLogin(){
        return login.get();
    }
    
    public String getHaslo(){
        return haslo.get();
    }
    
    public String getRola(){
        return rola.get();
    }
    
    public String getAdres_placowki(){
        return adres_placowki.get();
    }
    
    
    
    //      ***Settery***
    
    public void setImie_pracownika(String value){
        imie_pracownika.set(value);
    }
    
    public void setNazwisko_pracownika (String value){
        nazwisko_pracownika.set(value);
    }
    
    public void setPesel_pracownika(int value){
        pesel_pracownika.set(value);
    }
    
    public void setTelefon_pracownika (int value){
        telefon_pracownika.set(value);
    }
    
    public void setLogin (String value){
        login.set(value);
    }
    
    public void setHaslo (String value){
        haslo.set(value);
    }
    
    public void setRola(String value){
        rola.set(value);
    }
    
    public void setAdres_placowki (String value){
        adres_placowki.set(value);
    }
    
    
    
    //      ***Properties***
    
    public IntegerProperty id_pracownikaProperty(){
        return id_pracownika;
    }
    
    public StringProperty imie_pracownikaProperty(){
        return imie_pracownika;
    }
    
    public StringProperty nazwisko_pracownikaProperty(){
        return nazwisko_pracownika;
    }
    
    public IntegerProperty pesel_pracownikaProperty(){
        return pesel_pracownika;
    }
    
    public IntegerProperty telefon_pracownikaProperty(){
        return telefon_pracownika;
    }
    
    public StringProperty loginProperty(){
        return login;
    }
    
    public StringProperty hasloProperty(){
        return haslo;
    }
    
    public StringProperty rolaProperty(){
        return rola;
    }
    
    public StringProperty adres_placowkiProperty(){
        return adres_placowki;
    }
}
