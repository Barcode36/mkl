/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.pracownik.modele;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BlackHawk
 */
public class Reklamacje {
    
    private IntegerProperty id_reklamacji;
    private IntegerProperty id_placowki;
    private IntegerProperty id_produktu;
    private StringProperty opis_produktu;
    private StringProperty opis;
    private StringProperty stan;
    private DoubleProperty cena_produktu;
    private StringProperty nazwa_produktu;
    private LocalDate data;
    
    
    
    public Reklamacje (Integer id_reklamacji, Integer id_produktu, String opis_produktu,String opis, String stan,Double cena_produktu, String nazwa_produktu, LocalDate data){
        this.id_reklamacji = new SimpleIntegerProperty(id_reklamacji);
        this.id_produktu = new SimpleIntegerProperty(id_produktu);
        this.opis_produktu = new SimpleStringProperty(opis_produktu);
        this.opis = new SimpleStringProperty(opis);
        this.stan = new SimpleStringProperty(stan);
        this.cena_produktu = new SimpleDoubleProperty(cena_produktu);
        this.nazwa_produktu = new SimpleStringProperty(nazwa_produktu);
        this.data = data;
        
    }
    
    
    public Integer getId_produktu(){
        return id_produktu.get();
    }
    
    public Integer getId_reklamacji(){
        return id_reklamacji.get();
    }
    
    public Integer getId_placowki(){
        return id_placowki.get();
    }
    
    public String getOpis_produktu(){
        return opis_produktu.get();
    }
    
    public String getOpis(){
        return opis.get();
    }
    
    public String getStan(){
        return stan.get();
    }
    
    public Double getCena(){
        return cena_produktu.get();
    }
    
    public String getNazwa_produktu(){
        return nazwa_produktu.get();
    }
    
    public LocalDate getData(){
        return data;
    }
    
    
    
    
    
    public void setOpis_produktu(String value){
        opis_produktu.set(value);
    }
    
    public void setStan(String value){
        stan.set(value);
    }
    
    
    
    public IntegerProperty id_produktuProperty(){
        return id_produktu;
    }
    
    public IntegerProperty id_placowkiProperty(){
        return id_placowki;
    }
    
    public IntegerProperty id_reklamacjiProperty(){
        return id_reklamacji;
    }
    
    public StringProperty opis_produktuProperty(){
        return opis_produktu;
    }
    
    public StringProperty stanProperty(){
        return stan;
    }
    
    public DoubleProperty cena_produktuProperty(){
        return cena_produktu;
    }
    
    public StringProperty nazwa_produktuProperty(){
        return nazwa_produktu;
    }
    
    public StringProperty opisProperty(){
        return opis;
    }
    
    
    
    
}
