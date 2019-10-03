/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.pacjenci;

import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Arkita
 */
public final class PacjenciController {
    @SuppressWarnings("unchecked")
    private final PacjenciView theView;
    private final PacjenciModel theModel;
    private final ERP_Dao theDao;
    private int row = -1;
    CustomComparator komparator;
            
    public PacjenciController(ERP_View erp_view) {
        
        this.theView = new PacjenciView(erp_view);
        this.theModel = new PacjenciModel();
        this.theDao = new ERP_Dao();
        this.komparator = new CustomComparator();
        
        //listenery
        this.theView.dodaj_pacjenta(new DodajPacjent());
        this.theView.zmien_dane(new ZmienPacjent());
        this.theView.usun_pacjenta(new UsunPacjent());
        this.theView.wyczysc_pola(new Wyczysc());
        this.theView.zaznaczono_wiersz(new ZaznaczonoWiersz());
        this.theView.txtfield_szukaj(new SzukajTabela());
        this.theView.odswiez_liste(new Odswiez());
        OdswiezTabele();
        WyczyscPola();
        theView.setVisible(true);
    }
    
    //CLASSY LISTENERY
    class DodajPacjent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if((theView.getNazwisko().equals("")) || (theView.getImie().equals("")) || (theView.getPesel().equals(""))){
                JOptionPane.showMessageDialog(null, "Wprowadź conajmniej wszystkie podstawowe dane dla pacjenta: nazwisko, imię, pesel i telefon.", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
            }
            else if(theView.getPesel().length() < 11)
            {
                JOptionPane.showMessageDialog(null, "Pesel powinien mieć 11 cyfr", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {                           
                theDao.Insert("pacjenci", theModel.getColumnsNoId(), theView.getValues());
                //System.out.print(theModel.getColumnsNoId());
                OdswiezTabele();
                WyczyscPola();
            }                
        }  
    }
    class ZmienPacjent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            if(row >= 0){  
                if((theView.getNazwisko().equals("")) || (theView.getImie().equals("")) || (theView.getPesel().equals(""))){
                    JOptionPane.showMessageDialog(null, "Wprowadź conajmniej wszystkie podstawowe dane dla pacjenta: nazwisko, imię, pesel i telefon.", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
                }else{
                    theDao.Update("pacjenci", theModel.getColumnsNoId(), theView.getValues(), theView.getId(row));                         
                    OdswiezTabele();
                    WyczyscPola();
                }
            }
        }  
    }   
    class UsunPacjent implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                        
            if(row >= 0){                               
                //PacjenciDao theDao = new PacjenciDao();
                //theDao.deleteFromPacjenci(theView.getPeselFromTable(row));                           
                theDao.Delete("pacjenci", theView.getId(row));
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
    
    class Odswiez implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent e) {    
            OdswiezTabele();
        }
    } 
    
    class ZaznaczonoWiersz implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
           if(e.getValueIsAdjusting()==false){           
                row = theView.getTableSelectedRow();
                //theView.checkboxStatus(true);
                if(row!=-1){
                    theView.buttonsState(true);
                    ArrayList<String> dane;
                    dane = theDao.Read("pacjenci", theModel.getColumnsNoId(), theView.getId(row));
                    theView.setValues(dane);            
                }
           }
        }
    }
    class SzukajTabela implements DocumentListener{
            
        @Override
        public void insertUpdate(DocumentEvent e) {
            String text = theView.getSzukaj();

            if (text.trim().length() == 0) {
                theView.setRowSorterer(null);
            } else {
                theView.setRowSorterer(text);
            }
        }
            
        @Override
        public void removeUpdate(DocumentEvent e) {
            String text = theView.getSzukaj();

            if (text.trim().length() == 0) {
                theView.setRowSorterer(null);
            } else {
                theView.setRowSorterer(text);
            }
        }
        @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }  

    //FUNKCJE
    void OdswiezTabele(){            
        theView.wyczysc_tabele();
        //theView.checkboxStatus(false);
        ArrayList<ArrayList<String>> pacjenci = theDao.ReadAll("pacjenci", theModel.getColumns());
        pacjenci.sort(komparator);
        int i,lenght = pacjenci.size();
        for(i=0;i<lenght;i++){
            theView.dodaj_wiersz(pacjenci.get(i).get(0), pacjenci.get(i).get(1), pacjenci.get(i).get(2), pacjenci.get(i).get(3),
                                 pacjenci.get(i).get(4), pacjenci.get(i).get(5), pacjenci.get(i).get(6), pacjenci.get(i).get(7));
        } 
        theView.setStatus("W bazie znajduje się aktualnie "+String.valueOf(lenght) + " pacjentów.");
    }          
    void WyczyscPola(){
        theView.buttonsState(false);
        theView.setNazwisko("");
        theView.setImie("");
        theView.setPesel("");
        theView.setTelefon("");
        theView.setAdres("");
        theView.setSzukaj(""); 
        theView.clear_selection();
        //theView.checkboxStatus(false);
    }
    
    public class CustomComparator implements Comparator<ArrayList<String>> {
    @Override
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            return o1.get(1).compareTo(o2.get(1));
        }
    }
  
}
