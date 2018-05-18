/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkl_shop.manager.wiadomosci;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yser
 */
public class WiadomosciClassa {

    private int id_wiadomosci;
    private String id_nadawcy;
    private String id_odbiorcy;
    private String temat_wiadomosci;
    private String tresc_wiadomosci;
    private String data;
    private String status_wiadomosci;

    public WiadomosciClassa(String id_nadawcy, String temat_wiadomosci, String data, String status_wiadomosci, int id_wiadomosci) {
        this.id_nadawcy = id_nadawcy;
        this.temat_wiadomosci = temat_wiadomosci;
        this.data = data;
        this.status_wiadomosci = status_wiadomosci;
        this.id_wiadomosci = id_wiadomosci;
    }

    public WiadomosciClassa(String id_odbiorcy, String temat_wiadomosci, String data, int id_wiadomosci) {
        this.id_odbiorcy = id_odbiorcy;
        this.temat_wiadomosci = temat_wiadomosci;
        this.data = data;
        this.id_wiadomosci = id_wiadomosci;
    }

    public WiadomosciClassa(int id_wiadomosci, String id_nadawcy, String id_odbiorcy, String temat_wiadomosci, String tresc_wiadomosci, String status_wiadomosci, String data) {
        this.id_wiadomosci = id_wiadomosci;
        this.id_nadawcy = id_nadawcy;
        this.id_odbiorcy = id_odbiorcy;
        this.temat_wiadomosci = temat_wiadomosci;
        this.tresc_wiadomosci = tresc_wiadomosci;
        this.status_wiadomosci = status_wiadomosci;
        this.data = data;
    }

    public int getId_wiadomosci() {
        return id_wiadomosci;
    }

    public void setId_wiadomosci(int id_wiadomosci) {
        this.id_wiadomosci = id_wiadomosci;
    }

    public String getId_nadawcy() {
        return id_nadawcy;
    }

    public void setId_nadawcy(String id_nadawcy) {
        this.id_nadawcy = id_nadawcy;
    }

    public String getId_odbiorcy() {
        return id_odbiorcy;
    }

    public void setId_odbiorcy(String id_odbiorcy) {
        this.id_odbiorcy = id_odbiorcy;
    }

    public String getTemat_wiadomosci() {
        return temat_wiadomosci;
    }

    public void setTemat_wiadomosci(String temat_wiadomosci) {
        this.temat_wiadomosci = temat_wiadomosci;
    }

    public String getTresc_wiadomosci() {
        return tresc_wiadomosci;
    }

    public void setTresc_wiadomosci(String tresc_wiadomosci) {
        this.tresc_wiadomosci = tresc_wiadomosci;
    }

    public String getStatus_wiadomosci() {
        return status_wiadomosci;
    }

    public void setStatus_wiadomosci(String status_wiadomosci) {
        this.status_wiadomosci = status_wiadomosci;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
