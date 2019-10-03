/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.komorka;

import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Arkita
 */
public final class KomorkaController {
   @SuppressWarnings("unchecked")
    private final KomorkaView theView;
    private final KomorkaModel theModel;
    private final ERP_Dao theDao;
    private int row = -1;
    private int typ;
    
        public KomorkaController(ERP_View erp_view) {

            typ = 0;
            this.theView = new KomorkaView(erp_view);
            this.theModel = new KomorkaModel();
            this.theDao = new ERP_Dao();
            
            this.theView.dodaj_komorke(new DodajKomorka());
            this.theView.zmien_dane(new ZmienKomorka());
            this.theView.usun_komorke(new UsunKomorka());
            this.theView.wyczysc_pola(new Wyczysc());
            this.theView.zaznaczono_wiersz(new KomorkaController.ZaznaczonoWiersz());
            this.theView.combobox_typ_action(new Typ());
            OdswiezTabele();
            WyczyscPola();
            theView.setVisible(true);
        }
        
        //CLASSY LISTENERY
        class DodajKomorka implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if((theView.getNazwa().equals("")) || (theView.getNumer().equals("")) || (theView.getAdres().equals(""))){
                    JOptionPane.showMessageDialog(null, "Wprowadź conajmniej wszystkie podstawowe dane dla komórki: nazwę, numer, adres i punkty", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
                }else{
                    theDao.Insert("komorka", theModel.getColumnsNoId(), theView.getValues());
                    OdswiezTabele();
                    WyczyscPola();
                }
            }
        }     
        class ZmienKomorka implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {           
                if(row >= 0){          
                    theDao.Update("komorka", theModel.getColumnsNoId(), theView.getValues(), theView.getId(row));
                    OdswiezTabele();
                    WyczyscPola();
                }
            }  
        }            
        class UsunKomorka implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {                                        
                if(row >= 0){                                 
                    theDao.Delete("komorka", theView.getId(row));
                    OdswiezTabele();
                    WyczyscPola();
                    row = -1;
                }
            }  
        }         
        class ZaznaczonoWiersz implements ListSelectionListener{  
            @Override
            public void valueChanged(ListSelectionEvent e) {    
                if(e.getValueIsAdjusting()==false){           
                    row = theView.getTableSelectedRow();
                    //System.out.println(row);
                    if(row!=-1){
                    theView.buttonsState(true);
                    ArrayList<String> dane;
                    dane = theDao.Read("komorka", theModel.getColumnsNoId(), theView.getId(row));
                    theView.setNazwa(dane.get(0));
                    theView.setNumer(dane.get(1));
                    theView.setAdres(dane.get(2));
                    theView.setPunkty(dane.get(3));
                    theView.setPLN(dane.get(4));

                    }
                }
            }
        }         
        class Wyczysc implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                WyczyscPola();
            }
        }       
        class Typ implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                typ = theView.getTyp();
                OdswiezTabele();
            }
        }       
        //FUNKCJE
        void WyczyscPola(){
            theView.buttonsState(false);
            theView.setNazwa("");
            theView.setAdres("");
            theView.setNumer("");
            theView.setPunkty("");
            theView.setPLN("");
            theView.clear_selection();
        }              
        void OdswiezTabele(){            
            theView.wyczysc_tabele();
            ArrayList<ArrayList<String>> komorki = theDao.ReadAll("komorka", theModel.getColumns());
            int i,lenght = komorki.size();  
                for(i=0;i<lenght;i++){
                    if(String.valueOf(typ).equals(komorki.get(i).get(6)))
                    theView.dodaj_wiersz(komorki.get(i).get(0), komorki.get(i).get(1), komorki.get(i).get(2), komorki.get(i).get(3), komorki.get(i).get(4), komorki.get(i).get(5));
                }
        }
    
}
