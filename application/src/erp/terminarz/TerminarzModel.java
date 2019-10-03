/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.terminarz;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class TerminarzModel {
     @SuppressWarnings("unchecked")
    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    private final ArrayList<String> columns_nodata;
    
    public TerminarzModel(){
        columns = new ArrayList<>(Arrays.asList("idkalendarz", "idkomorka", "idpersonel", "idpacjenci", "idwizyta", "data", "godzina", "status", "pierwsza", "uwagi"));
        columns_noid = new ArrayList<>(Arrays.asList(          "idkomorka", "idpersonel", "idpacjenci", "idwizyta", "data", "godzina", "status", "pierwsza", "uwagi")); 
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
