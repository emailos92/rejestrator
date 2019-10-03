/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.kalendarz;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class KalendarzModel {
     @SuppressWarnings("unchecked")
    private int idkalendarz;
    private int idpersonel; 
    private int idkomorka;
    private int idpacjenci; 
    private int idwizyta;
    private String data;
    private String godzina_od;
    private String godzina_do;
    private String pierwsza;
    private String odwolana;
    private String uwagi;
    
    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    private final ArrayList<String> columns_nodata;
            
    public KalendarzModel(){
        columns = new ArrayList<>(Arrays.asList("idkalendarz", "idpersonel", "idkomorka", "idpacjenci", "idwizyta", "data", "godzina", "status", "pierwsza", "uwagi"));
        columns_noid = new ArrayList<>(Arrays.asList(          "idpersonel", "idkomorka", "idpacjenci", "idwizyta", "data", "godzina", "status", "pierwsza", "uwagi")); 
        columns_nodata = new ArrayList<>(Arrays.asList("idkalendarz",                     "idpacjenci", "idwizyta",         "godzina", "status", "pierwsza", "uwagi")); 
    }  
    
    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getColumnsNoId() {
        return columns_noid;
    }
    public ArrayList<String> getColumnsNoData() {
        return columns_nodata;
    }
}
