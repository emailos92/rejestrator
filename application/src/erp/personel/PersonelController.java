/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.personel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import erp.ERP_Dao;
import erp.ERP_View;
/**
 *
 * @author Arkita
 */
public final class PersonelController {
     @SuppressWarnings("unchecked")
    private final PersonelView theView;
    private final PersonelModel theModel;
    private final ERP_Dao theDao;
    private int row = -1;
    
    public PersonelController(ERP_View erp_view) {

        this.theView = new PersonelView(erp_view);
        this.theModel = new PersonelModel();
        this.theDao = new ERP_Dao();
        //listenery
        this.theView.dodaj_personel(new DodajPersonel());
        this.theView.zmien_dane(new ZmienPersonel());
        this.theView.usun_personel(new UsunPersonel());
        this.theView.wyczysc_pola(new Wyczysc());
        this.theView.zaznaczono_wiersz(new ZaznaczonoWiersz());
        this.theView.txtfield_szukaj(new SzukajTabela());   
        OdswiezTabele();
        WyczyscPola();
        this.theView.setVisible(true);
    }
    
        //CLASSY LISTENERY
    class DodajPersonel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if((theView.getNazwiskoImie().equals("")) || (theView.getTelefon().equals("")) || (theView.getAdres().equals(""))){
                JOptionPane.showMessageDialog(null, "Wprowadź wszystkie dane dla pracownika: nazwisko, imię, adres i telefon.", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
            }else{
                theDao.Insert("personel", theModel.getColumnsNoId(), theView.getValues());
                OdswiezTabele();
                WyczyscPola();
            }
                
        }  
    }
    class ZmienPersonel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            if(row >= 0){
                if((theView.getNazwiskoImie().equals("")) || (theView.getTelefon().equals("")) || (theView.getAdres().equals(""))){
                    JOptionPane.showMessageDialog(null, "Wprowadź wszystkie dane dla pracownika: nazwisko, imię, adres i telefon.", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
                }else{             
                    theDao.Update("personel", theModel.getColumnsNoId(), theView.getValues(), theView.getId(row));
                    OdswiezTabele();
                    WyczyscPola();
                }
            }
        }  
    } 
    class UsunPersonel implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                        
            if(row >= 0){                               
                theDao.Delete("personel", theView.getId(row));
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
                dane = theDao.Read("personel", theModel.getColumnsNoId(), theView.getId(row));
                theView.setNazwiskoImie(dane.get(0));
                theView.setAdres(dane.get(1));
                theView.setTelefon(dane.get(2));
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
    
    void OdswiezTabele(){            
        theView.wyczysc_tabele();
        ArrayList<ArrayList<String>> personel = theDao.ReadAll("personel", theModel.getColumns());
        int i,lenght = personel.size();
        for(i=0;i<lenght;i++){
            theView.dodaj_wiersz(personel.get(i).get(0), personel.get(i).get(1), personel.get(i).get(2), personel.get(i).get(3));
        }                        
    }   

    void WyczyscPola(){
        theView.buttonsState(false);
        theView.setNazwiskoImie("");
        theView.setTelefon("");
        theView.setAdres(""); 
        theView.clear_selection();
    }
}
