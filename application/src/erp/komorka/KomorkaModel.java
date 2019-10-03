/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.komorka;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class KomorkaModel {
     @SuppressWarnings("unchecked")
    private int idkomorka;
    private String nazwa;
    private String numer;
    private int punkty;
    private String adres;
    
    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    
    public KomorkaModel(){
        columns = new ArrayList<>(Arrays.asList("idkomorka", "nazwa", "numer", "adres", "punkty","pln","typ"));
        columns_noid = new ArrayList<>(Arrays.asList("nazwa", "numer", "adres", "punkty","pln","typ")); 
    }
        
 
    public void setIdkomorka(int idkomorka) {
        this.idkomorka = idkomorka;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public void setNumer(String numer) {
        this.numer = numer;
    }
    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    
    public int getIdkomorka() {
        return idkomorka;
    }
    public String getNazwa() {
        return nazwa;
    }
    public String getNumer() {
        return numer;
    }
    public int getPunkty() {
        return punkty;
    }
    public String getAdres() {
        return adres;
    }
    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getColumnsNoId() {
        return columns_noid;
    }
    
}
