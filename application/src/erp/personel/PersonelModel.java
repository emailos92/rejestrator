/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.personel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class PersonelModel {
     @SuppressWarnings("unchecked")
    private int idpersonel;
    private String nazwisko_imie;
    private String adres; 
    private String telefon;
    
    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    
    public PersonelModel(){
        columns = new ArrayList<>(Arrays.asList("idpersonel", "nazwisko_imie", "adres", "telefon"));
        columns_noid = new ArrayList<>(Arrays.asList("nazwisko_imie", "adres", "telefon")); 
    }

    public void setIdpersonel(int idpersonel) {
        this.idpersonel = idpersonel;
    }
    public void setNazwiskoImie(String nazwisko_imie) {
        this.nazwisko_imie = nazwisko_imie;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    public int getIdpersonel() {
        return idpersonel;
    }
    public String getNazwiskoImie() {
        return nazwisko_imie;
    }
    public String getAdres() {
        return adres;
    }
    public String getTelefon() {
        return telefon;
    }
    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getColumnsNoId() {
        return columns_noid;
    }
    
}
