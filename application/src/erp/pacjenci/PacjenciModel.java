/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.pacjenci;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class PacjenciModel {
     @SuppressWarnings("unchecked")
    private int idpacjenci;
    private String nazwisko;
    private String imie;
    private String pesel; 
    private String telefon;
    private String adres;
    private String uwagi;
    private String nowy;
    private String lok;

    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    
    public PacjenciModel(){
        columns = new ArrayList<>(Arrays.asList("idpacjenci", "nazwisko", "imie", "pesel", "telefon", "adres", "plec", "lok"));
        columns_noid = new ArrayList<>(Arrays.asList("nazwisko", "imie", "pesel", "telefon", "adres", "plec", "lok")); 
    }
    
    public void setIdpacjenci(int idpacjenci) {
        this.idpacjenci = idpacjenci;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    public void setTelefon(String telefon1) {
        this.telefon = telefon1;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }
    public void setNowy(String nowy) {
        this.nowy = nowy;
    }
    
    public int getIdpacjenci() {
        return idpacjenci;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public String getImie() {
        return imie;
    }
    public String getPesel() {
        return pesel;
    }
    public String getTelefon() {
        return telefon;
    }
    public String getAdres() {
        return adres;
    }
    public String getUwagi() {
        return uwagi;
    }  
    public String getNowy() {
        return nowy;
    }
    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getColumnsNoId() {
        return columns_noid;
    }
    
}