/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.grafik;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class GrafikModel {
    @SuppressWarnings("unchecked")
    private final ArrayList<String> columns;
    private final ArrayList<String> columns_noid;
    private final ArrayList<String> columns_noids;
    
    public GrafikModel(){
        columns = new ArrayList<>(Arrays.asList(
                "idgrafik", "idpersonel", "idkomorka", "punkty", "czas", 
                
                "pon_co","pon_od","pon_do","pon_h", 
                "wto_co","wto_od","wto_do","wto_h", 
                "sro_co","sro_od","sro_do","sro_h",
                "czw_co","czw_od","czw_do","czw_h",
                "pia_co","pia_od","pia_do","pia_h",
                "sob_co","sob_od","sob_do","sob_h",
                "nie_co","nie_od","nie_do","nie_h"));

        columns_noid = new ArrayList<>(Arrays.asList(
                "idpersonel", "idkomorka", "punkty", "czas", 
                
                "pon_co","pon_od","pon_do","pon_h", 
                "wto_co","wto_od","wto_do","wto_h", 
                "sro_co","sro_od","sro_do","sro_h",
                "czw_co","czw_od","czw_do","czw_h",
                "pia_co","pia_od","pia_do","pia_h",
                "sob_co","sob_od","sob_do","sob_h",
                "nie_co","nie_od","nie_do","nie_h"));
        columns_noids = new ArrayList<>(Arrays.asList(
                "punkty", "czas", 
                
                "pon_co","pon_od","pon_do","pon_h", 
                "wto_co","wto_od","wto_do","wto_h", 
                "sro_co","sro_od","sro_do","sro_h",
                "czw_co","czw_od","czw_do","czw_h",
                "pia_co","pia_od","pia_do","pia_h",
                "sob_co","sob_od","sob_do","sob_h",
                "nie_co","nie_od","nie_do","nie_h"));
    }

    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getColumnsNoId() {
        return columns_noid;
    }
    public ArrayList<String> getColumnsNoIds() {
        return columns_noids;
    }
 
}
