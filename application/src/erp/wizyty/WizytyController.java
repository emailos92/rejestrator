/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.wizyty;

import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Arkita
 */
public final class WizytyController extends JDialog{
     @SuppressWarnings("unchecked")
    private final WizytyView theView;
    private final WizytyModel theModel;
    private final ERP_Dao theDao;
    private int row = -1;
    private int typ;
    
    public WizytyController(ERP_View erp_view) {
        
        this.theView = new WizytyView(erp_view);
        this.theModel = new WizytyModel();
        this.theDao = new ERP_Dao();
        typ = 0;
        
        //listenery
        this.theView.dodaj_wizyta(new WizytyController.DodajWizyta());
        this.theView.zmien_dane(new WizytyController.ZmienWizyta());
        this.theView.usun_wizyta(new WizytyController.UsunWizyta());
        this.theView.wyczysc_pola(new WizytyController.Wyczysc());
        this.theView.combobox_komtyp_action(new WizytyController.Typ());
        this.theView.zaznaczono_wiersz(new WizytyController.ZaznaczonoWiersz());
        OdswiezTabele();
        WyczyscPola();
        theView.setVisible(true);   
    }
    
    class DodajWizyta implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(typ == 0)
            {
                JOptionPane.showMessageDialog(null, "Wybierz typ komórki", "Informacja", JOptionPane.INFORMATION_MESSAGE);    
            }
            else
            {
                if((theView.getNazwa().equals("")) || (theView.getCzas().equals("")) || (theView.getPunkty().equals(""))){
                    JOptionPane.showMessageDialog(null, "Wprowadź wszystkie dane dla wizyty: nazwę, czas i punkty.", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
                }else{
                    theDao.Insert("wizyta", theModel.getColumnsNoId(), theView.getValues());
                    OdswiezTabele();
                    WyczyscPola();
                }
            } 
        }  
    }
    class ZmienWizyta implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            if(row >= 0){
                if((theView.getNazwa().equals("")) || (theView.getCzas().equals("")) || (theView.getPunkty().equals(""))){
                    JOptionPane.showMessageDialog(null, "Wprowadź wszystkie dane dla wizyty: nazwę, czas i punkty.", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
                }else{
                    theDao.Update("wizyta", theModel.getColumnsNoId(), theView.getValues(), theView.getId(row));               
                    OdswiezTabele();
                    WyczyscPola();
                }
            }
        }  
    } 
    class UsunWizyta implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                        
            if(row >= 0){                               
                theDao.Delete("wizyta",theView.getId(row));                         
                OdswiezTabele();
                WyczyscPola();
                row = -1;
            }
        }  
    }  
    class Wyczysc implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent e) {    
            WyczyscPola();
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
                ArrayList<String> dane = new ArrayList<>();
                dane = theDao.Read("wizyta", theModel.getColumnsNoId(), theView.getId(row));                             
                theView.setNazwa(dane.get(0));
                theView.setCzas(dane.get(1));
                theView.setPunkty(dane.get(2));
                }
           }
        }
    } 
    
    class Typ implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent e) {    
            typ = theView.getTyp();
            if(typ == 0){
                theView.setEnabledDodaj(false);
            }
            else{
                theView.setEnabledDodaj(true);
            }
            OdswiezTabele();
        }
    } 
    
    void OdswiezTabele(){            
        theView.wyczysc_tabele();
        ArrayList<ArrayList<String>> wizyty = theDao.ReadAll("wizyta", theModel.getColumns());
        int i,lenght = wizyty.size();
        for(i=0;i<lenght;i++){
            if(String.valueOf(typ).equals(wizyty.get(i).get(4)))
            {
                theView.dodaj_wiersz(wizyty.get(i).get(0), wizyty.get(i).get(1), wizyty.get(i).get(2), wizyty.get(i).get(3));
            }
        }                                 
    }   

    void WyczyscPola(){
        theView.buttonsState(false);
        theView.setNazwa("");
        theView.setCzas("");
        theView.setPunkty("");
        theView.clear_selection();
    }
    
}
