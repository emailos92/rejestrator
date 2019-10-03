/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.wizyty;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class WizytyModel {
    @SuppressWarnings("unchecked")
    private int idwizyta;
    private String nazwa;
    private String czas;
    private String punkty; 
    
    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    
    public WizytyModel (){
        columns = new ArrayList<>(Arrays.asList("idwizyta", "nazwa", "czas", "punkty", "typ"));
        columns_noid = new ArrayList<>(Arrays.asList("nazwa", "czas", "punkty","typ")); 
    }


    public void setIdwizyta(int idwizyta) {
        this.idwizyta = idwizyta;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public void setCzas(String czas) {
        this.czas = czas;
    }
    public void setPunkty(String punkty) {
        this.punkty = punkty;
    }
     
    
    public int getIdwizyta() {
        return idwizyta;
    }
    public String getNazwa() {
        return nazwa;
    }
    public String getCzas() {
        return czas;
    }
    public String getPunkty() {
        return punkty;
    }
    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getColumnsNoId() {
        return columns_noid;
    }
}
