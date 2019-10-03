/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.grafik;

import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arkita
 */
public class GrafikController {
     @SuppressWarnings("unchecked")
    private final GrafikView theView;
    private final GrafikModel theModel;
    private final ERP_Dao theDao;
    ArrayList<ArrayList<String>> personel;
    ArrayList<ArrayList<String>> komorka;
    ArrayList<String> column_personel;
    ArrayList<String> column_komorka;
    private int row = -1;
    
    public GrafikController(ERP_View erp_view) {

        this.theView = new GrafikView(erp_view);
        this.theModel = new GrafikModel();
        this.theDao = new ERP_Dao();

        this.column_personel = new ArrayList<>(Arrays.asList("idpersonel", "nazwisko_imie"));
        this.column_komorka = new ArrayList<>(Arrays.asList("idkomorka", "nazwa", "numer"));
        this.personel = theDao.ReadAll("personel", column_personel);
        this.komorka = theDao.ReadAll("komorka", column_komorka);
        //listenery
        this.theView.dodaj(new Dodaj());
        this.theView.zmien(new Zmien());
        this.theView.usun(new Usun());
        this.theView.wyczysc(new Wyczysc());   
        this.theView.zaznaczono_wiersz(new ZaznaczonoWiersz());
        this.theView.checkbox_pon_action(new CheckBoxPon());
        this.theView.checkbox_wto_action(new CheckBoxWto());
        this.theView.checkbox_sro_action(new CheckBoxSro());
        this.theView.checkbox_czw_action(new CheckBoxCzw());
        this.theView.checkbox_pia_action(new CheckBoxPia());
        this.theView.checkbox_sob_action(new CheckBoxSob());
        this.theView.checkbox_nie_action(new CheckBoxNie());
        FillCombo();
        OdswiezTabele();
        WyczyscPola();
        theView.buttonsState(false);
        theView.setVisible(true); 

    }   
    
    class Dodaj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if((theView.getKomorka().equals("Domyślna")) || (theView.getPersonel().equals("Domyślny")) ){
                JOptionPane.showMessageDialog(null, "Wybierz personel i komórkę", "Informacja", JOptionPane.INFORMATION_MESSAGE);                
            }else{             
                int i,lenght;
                lenght = personel.size();
                String graf_id;
                String per_id = null, per = theView.getPersonel();
                for(i=0;i<lenght;i++){
                    if(personel.get(i).get(1).equals(per)){
                        per_id = personel.get(i).get(0);
                    }
                }
                lenght = komorka.size();
                String kom_id=null, kom = theView.getKomorka();
                for(i=0;i<lenght;i++){
                    if((komorka.get(i).get(1) + " / " + komorka.get(i).get(2)).equals(kom)){
                        kom_id = komorka.get(i).get(0);
                    }
                }
                graf_id=kom_id+per_id;
                
                ArrayList<String> values;
                values = new ArrayList<>(Arrays.asList
                        (graf_id,per_id,kom_id));
                
                if(theView.getPunkty().equals(""))
                    values.add("0");
                else
                    values.add(theView.getPunkty());
                if(theView.getCzas().equals(""))
                    values.add("0");
                else
                    values.add(theView.getCzas());
                
                values.add(theView.getPonCo());
                values.add(theView.getPonOd());
                values.add(theView.getPonDo());
                if(theView.getCheckBoxPon())
                    values.add(theView.getPonH());
                else
                    values.add("0");
                
                values.add(theView.getWtoCo());
                values.add(theView.getWtoOd());
                values.add(theView.getWtoDo());
                if(theView.getCheckBoxWto())
                    values.add(theView.getWtoH());
                else
                    values.add("0"); 
                
                values.add(theView.getSroCo());
                values.add(theView.getSroOd());
                values.add(theView.getSroDo());
                if(theView.getCheckBoxSro())
                    values.add(theView.getSroH());
                else
                    values.add("0"); 
                
                values.add(theView.getCzwCo());
                values.add(theView.getCzwOd());
                values.add(theView.getCzwDo());
                if(theView.getCheckBoxCzw())
                    values.add(theView.getCzwH());
                else
                    values.add("0"); 
                
                values.add(theView.getPiaCo());
                values.add(theView.getPiaOd());
                values.add(theView.getPiaDo());
                if(theView.getCheckBoxPia())
                    values.add(theView.getPiaH());
                else
                    values.add("0"); 
                
                values.add(theView.getSobCo());
                values.add(theView.getSobOd());
                values.add(theView.getSobDo());
                if(theView.getCheckBoxSob())
                    values.add(theView.getSobH());
                else
                    values.add("0"); 
                
                values.add(theView.getNieCo());
                values.add(theView.getNieOd());
                values.add(theView.getNieDo());
                if(theView.getCheckBoxNie())
                    values.add(theView.getNieH());
                else
                    values.add("0"); 
                
                //sprawdzic czy w tabeli nie ma takiego id
                //if id w tabeli
                boolean can = theView.searchForPK(graf_id);
                if(can==true){
                    JOptionPane.showMessageDialog(null, "Istnieje już pracownik przypisany do tej komórki, znajdziesz go w tabeli.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }else{
                theDao.Insert("grafik", theModel.getColumns(), values);
                OdswiezTabele();
                WyczyscPola();
                }
                
            }
                
        }  
    }
    class Zmien implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            if(row >= 0){  
            
                ArrayList<String> values;
                values = new ArrayList<>();
                
                if(theView.getPunkty().equals(""))
                    values.add("0");
                else
                    values.add(theView.getPunkty());
                if(theView.getCzas().equals(""))
                    values.add("0");
                else
                    values.add(theView.getCzas());
                
                values.add(theView.getPonCo());
                values.add(theView.getPonOd());
                values.add(theView.getPonDo());
                if(theView.getCheckBoxPon())
                    values.add(theView.getPonH());
                else
                    values.add("0");
                
                values.add(theView.getWtoCo());
                values.add(theView.getWtoOd());
                values.add(theView.getWtoDo());
                if(theView.getCheckBoxWto())
                    values.add(theView.getWtoH());
                else
                    values.add("0"); 
                
                values.add(theView.getSroCo());
                values.add(theView.getSroOd());
                values.add(theView.getSroDo());
                if(theView.getCheckBoxSro())
                    values.add(theView.getSroH());
                else
                    values.add("0"); 
                
                values.add(theView.getCzwCo());
                values.add(theView.getCzwOd());
                values.add(theView.getCzwDo());
                if(theView.getCheckBoxCzw())
                    values.add(theView.getCzwH());
                else
                    values.add("0"); 
                
                values.add(theView.getPiaCo());
                values.add(theView.getPiaOd());
                values.add(theView.getPiaDo());
                if(theView.getCheckBoxPia())
                    values.add(theView.getPiaH());
                else
                    values.add("0"); 
                
                values.add(theView.getSobCo());
                values.add(theView.getSobOd());
                values.add(theView.getSobDo());
                if(theView.getCheckBoxSob())
                    values.add(theView.getSobH());
                else
                    values.add("0"); 
                
                values.add(theView.getNieCo());
                values.add(theView.getNieOd());
                values.add(theView.getNieDo());
                if(theView.getCheckBoxNie())
                    values.add(theView.getNieH());
                else
                    values.add("0"); 
                
                theDao.Update("grafik", theModel.getColumnsNoIds(), values, theView.getId(row));
                OdswiezTabele();
                WyczyscPola();
            }
            
        }  
    }
    class Usun implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                        
            if(row >= 0){                               
                theDao.Delete("grafik", theView.getId(row));
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
                if(row!=-1){
                int graf_id = theView.getId(row);
                //int per_id = theView.getIdPer(row);
                //int kom_id = theView.getIdKom(row);
                    
                ArrayList<String> dane;
                dane = theDao.Read("grafik", theModel.getColumnsNoIds(), graf_id);
                
                theView.buttonsState(true);
                theView.setPunkty(dane.get(0));
                theView.setCzas(dane.get(1));             
                theView.setPersonelValue(theView.getPersonelFromTable(row));
                theView.setKomorkaValue(theView.getKomorkaFromTable(row));
                theView.setPersonelComboboxEnabled(false);
                theView.setKomorkaComboboxEnabled(false);
                
                if(dane.get(2).equals("")){
                    theView.setCheckBoxPon(false);
                    theView.setEnabledPonCo(false);
                    theView.setEnabledPonOd(false);
                    theView.setEnabledPonDo(false);
                    theView.setEnabledPonH(false);
                    theView.setPonCo("");
                    theView.setPonOd("");
                    theView.setPonDo("");
                    theView.setPonH("");
                }else{
                    theView.setCheckBoxPon(true);
                    theView.setEnabledPonCo(true);
                    theView.setEnabledPonOd(true);
                    theView.setEnabledPonDo(true);
                    theView.setEnabledPonH(true); 
                    theView.setPonCo(dane.get(2));
                    theView.setPonOd(dane.get(3));
                    theView.setPonDo(dane.get(4));
                    theView.setPonH(dane.get(5));
                }
                
                if(dane.get(6).equals("")){
                     theView.setCheckBoxWto(false);
                    theView.setEnabledWtoCo(false);
                    theView.setEnabledWtoOd(false);
                    theView.setEnabledWtoDo(false);
                    theView.setEnabledWtoH(false);
                    theView.setWtoCo("");
                    theView.setWtoOd("");
                    theView.setWtoDo("");
                    theView.setWtoH("");
                }else{
                    theView.setCheckBoxWto(true);
                    theView.setEnabledWtoCo(true);
                    theView.setEnabledWtoOd(true);
                    theView.setEnabledWtoDo(true);
                    theView.setEnabledWtoH(true); 
                    theView.setWtoCo(dane.get(6));
                    theView.setWtoOd(dane.get(7));
                    theView.setWtoDo(dane.get(8));
                    theView.setWtoH(dane.get(9));
                }                
                
                if(dane.get(10).equals("")){
                    theView.setCheckBoxSro(false);
                    theView.setEnabledSroCo(false);
                    theView.setEnabledSroOd(false);
                    theView.setEnabledSroDo(false);
                    theView.setEnabledSroH(false);
                    theView.setSroCo("");
                    theView.setSroOd("");
                    theView.setSroDo("");
                    theView.setSroH("");
                }else{
                    theView.setCheckBoxSro(true);
                    theView.setEnabledSroCo(true);
                    theView.setEnabledSroOd(true);
                    theView.setEnabledSroDo(true);
                    theView.setEnabledSroH(true); 
                    theView.setSroCo(dane.get(10));
                    theView.setSroOd(dane.get(11));
                    theView.setSroDo(dane.get(12));
                    theView.setSroH(dane.get(13));
                }
                
                if(dane.get(14).equals("")){
                    theView.setCheckBoxCzw(false);
                    theView.setEnabledCzwCo(false);
                    theView.setEnabledCzwOd(false);
                    theView.setEnabledCzwDo(false);
                    theView.setEnabledCzwH(false);
                    theView.setCzwCo("");
                    theView.setCzwOd("");
                    theView.setCzwDo("");
                    theView.setCzwH("");
                }else{
                    theView.setCheckBoxCzw(true);
                    theView.setEnabledCzwCo(true);
                    theView.setEnabledCzwOd(true);
                    theView.setEnabledCzwDo(true);
                    theView.setEnabledCzwH(true);
                    theView.setCzwCo(dane.get(14));
                    theView.setCzwOd(dane.get(15));
                    theView.setCzwDo(dane.get(16));
                    theView.setCzwH(dane.get(17));
                }
                
                if(dane.get(18).equals("")){  
                    theView.setCheckBoxPia(false);
                    theView.setEnabledPiaCo(false);
                    theView.setEnabledPiaOd(false);
                    theView.setEnabledPiaDo(false);
                    theView.setEnabledPiaH(false);
                    theView.setPiaCo("");
                    theView.setPiaOd("");
                    theView.setPiaDo("");
                    theView.setPiaH("");
                }else{
                    theView.setCheckBoxPia(true);
                    theView.setEnabledPiaCo(true);
                    theView.setEnabledPiaOd(true);
                    theView.setEnabledPiaDo(true);
                    theView.setEnabledPiaH(true);
                    theView.setPiaCo(dane.get(18));
                    theView.setPiaOd(dane.get(19));
                    theView.setPiaDo(dane.get(20));
                    theView.setPiaH(dane.get(21));
                }      
                if(dane.get(22).equals("")){
                    theView.setCheckBoxSob(false);
                    theView.setEnabledSobCo(false);
                    theView.setEnabledSobOd(false);
                    theView.setEnabledSobDo(false);
                    theView.setEnabledSobH(false);
                    theView.setSobCo("");
                    theView.setSobOd("");
                    theView.setSobDo("");
                    theView.setSobH("");
                }else{
                    theView.setCheckBoxSob(true);
                    theView.setEnabledSobCo(true);
                    theView.setEnabledSobOd(true);
                    theView.setEnabledSobDo(true);
                    theView.setEnabledSobH(true);
                    theView.setSobCo(dane.get(22));
                    theView.setSobOd(dane.get(23));
                    theView.setSobDo(dane.get(24));
                    theView.setSobH(dane.get(25));
                } 
                if(dane.get(26).equals("")){
                    theView.setCheckBoxNie(false);
                    theView.setEnabledNieCo(false);
                    theView.setEnabledNieOd(false);
                    theView.setEnabledNieDo(false);
                    theView.setEnabledNieH(false);
                    theView.setNieCo("");
                    theView.setNieOd("");
                    theView.setNieDo("");
                    theView.setNieH("");
                }else{
                    theView.setCheckBoxNie(true);
                    theView.setEnabledNieCo(true);
                    theView.setEnabledNieOd(true);
                    theView.setEnabledNieDo(true);
                    theView.setEnabledNieH(true);
                    theView.setNieCo(dane.get(26));
                    theView.setNieOd(dane.get(27));
                    theView.setNieDo(dane.get(28));
                    theView.setPiaH(dane.get(29));
                }
                
                
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
    
    class CheckBoxPon implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxPon()==true){
                theView.setEnabledPonCo(true);
                theView.setEnabledPonOd(true);
                theView.setEnabledPonDo(true);
                theView.setEnabledPonH(true);
                theView.setPonCo("00:30");
                theView.setPonOd("07:00");
                theView.setPonDo("15:00");
                theView.setPonH("1");
            }else{
                theView.setEnabledPonCo(false);
                theView.setEnabledPonOd(false);
                theView.setEnabledPonDo(false);
                theView.setEnabledPonH(false);
                theView.setPonCo("");
                theView.setPonOd("");
                theView.setPonDo("");
                theView.setPonH("");
            }
        }
    }  
    class CheckBoxWto implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxWto()==true){
                theView.setEnabledWtoCo(true);
                theView.setEnabledWtoOd(true);
                theView.setEnabledWtoDo(true);
                theView.setEnabledWtoH(true);
                theView.setWtoCo("00:30");
                theView.setWtoOd("07:00");
                theView.setWtoDo("15:00");
                theView.setWtoH("1");
            }else{
                theView.setEnabledWtoCo(false);
                theView.setEnabledWtoOd(false);
                theView.setEnabledWtoDo(false);
                theView.setEnabledWtoH(false);
                theView.setWtoCo("");
                theView.setWtoOd("");
                theView.setWtoDo("");
                theView.setWtoH("");
            }            
        }
    }
    class CheckBoxSro implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxSro()==true){
                theView.setEnabledSroCo(true);
                theView.setEnabledSroOd(true);
                theView.setEnabledSroDo(true);
                theView.setEnabledSroH(true);
                theView.setSroCo("00:30");
                theView.setSroOd("07:00");
                theView.setSroDo("15:00");
                theView.setSroH("1");
            }else{
                theView.setEnabledSroCo(false);
                theView.setEnabledSroOd(false);
                theView.setEnabledSroDo(false);
                theView.setEnabledSroH(false);
                theView.setSroCo("");
                theView.setSroOd("");
                theView.setSroDo("");
                theView.setSroH("");
            }            
        }
    }
    class CheckBoxCzw implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxCzw()==true){
                theView.setEnabledCzwCo(true);
                theView.setEnabledCzwOd(true);
                theView.setEnabledCzwDo(true);
                theView.setEnabledCzwH(true);
                theView.setCzwCo("00:30");
                theView.setCzwOd("07:00");
                theView.setCzwDo("15:00");
                theView.setCzwH("1");
            }else{
                theView.setEnabledCzwCo(false);
                theView.setEnabledCzwOd(false);
                theView.setEnabledCzwDo(false);
                theView.setEnabledCzwH(false);
                theView.setCzwCo("");
                theView.setCzwOd("");
                theView.setCzwDo("");
                theView.setCzwH("");
            }            
        }
    }
    class CheckBoxPia implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxPia()==true){
                theView.setEnabledPiaCo(true);
                theView.setEnabledPiaOd(true);
                theView.setEnabledPiaDo(true);
                theView.setEnabledPiaH(true);
                theView.setPiaCo("00:30");
                theView.setPiaOd("07:00");
                theView.setPiaDo("15:00");
                theView.setPiaH("1");
            }else{
                theView.setEnabledPiaCo(false);
                theView.setEnabledPiaOd(false);
                theView.setEnabledPiaDo(false);
                theView.setEnabledPiaH(false);
                theView.setPiaCo("");
                theView.setPiaOd("");
                theView.setPiaDo("");
                theView.setPiaH("");
            }            
        }
    }
    class CheckBoxSob implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxSob()==true){
                theView.setEnabledSobCo(true);
                theView.setEnabledSobOd(true);
                theView.setEnabledSobDo(true);
                theView.setEnabledSobH(true);
                theView.setSobCo("00:30");
                theView.setSobOd("07:00");
                theView.setSobDo("15:00");
                theView.setSobH("1");
            }else{
                theView.setEnabledSobCo(false);
                theView.setEnabledSobOd(false);
                theView.setEnabledSobDo(false);
                theView.setEnabledSobH(false);
                theView.setSobCo("");
                theView.setSobOd("");
                theView.setSobDo("");
                theView.setSobH("");
            }            
        }
    }
    class CheckBoxNie implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxNie()==true){
                theView.setEnabledNieCo(true);
                theView.setEnabledNieOd(true);
                theView.setEnabledNieDo(true);
                theView.setEnabledNieH(true);
                theView.setNieCo("00:30");
                theView.setNieOd("07:00");
                theView.setNieDo("15:00");
                theView.setNieH("1");
            }else{
                theView.setEnabledNieCo(false);
                theView.setEnabledNieOd(false);
                theView.setEnabledNieDo(false);
                theView.setEnabledNieH(false);
                theView.setNieCo("");
                theView.setNieOd("");
                theView.setNieDo("");
                theView.setNieH("");
            }            
        }
    }
    
    private void FillCombo(){
        int i,lenght; 
        theView.deleteAllKomorka();
        theView.deleteAllPersonel();
        lenght = this.personel.size();
        for(i=0;i<lenght;i++){
            theView.addItemPersonel(personel.get(i).get(1));
            //System.out.print(personel.get(i).get(1));
        }   
        lenght = this.komorka.size();
        for(i=0;i<lenght;i++){
            theView.addItemKomorka(komorka.get(i).get(1) + " / " + komorka.get(i).get(2));
            //System.out.print(komorka.get(i).get(1));
        } 
    }    
    
    private void WyczyscPola(){
        
        theView.buttonsState(false);
        theView.setKomorka(0);
        theView.setPersonel(0);
        theView.setPunkty("");
        theView.setCzas("");
        theView.setPersonelComboboxEnabled(true);
        theView.setKomorkaComboboxEnabled(true);
        
        theView.setCheckBoxPon(false);theView.setEnabledPonCo(false);theView.setEnabledPonOd(false);theView.setEnabledPonDo(false);theView.setEnabledPonH(false);        
        theView.setCheckBoxWto(false);theView.setEnabledWtoCo(false);theView.setEnabledWtoOd(false);theView.setEnabledWtoDo(false);theView.setEnabledWtoH(false);
        theView.setCheckBoxSro(false);theView.setEnabledSroCo(false);theView.setEnabledSroOd(false);theView.setEnabledSroDo(false);theView.setEnabledSroH(false);        
        theView.setCheckBoxCzw(false);theView.setEnabledCzwCo(false);theView.setEnabledCzwOd(false);theView.setEnabledCzwDo(false);theView.setEnabledCzwH(false);        
        theView.setCheckBoxPia(false);theView.setEnabledPiaCo(false);theView.setEnabledPiaOd(false);theView.setEnabledPiaDo(false);theView.setEnabledPiaH(false);        
        theView.setCheckBoxSob(false);theView.setEnabledSobCo(false);theView.setEnabledSobOd(false);theView.setEnabledSobDo(false);theView.setEnabledSobH(false);        
        theView.setCheckBoxNie(false);theView.setEnabledNieCo(false);theView.setEnabledNieOd(false);theView.setEnabledNieDo(false);theView.setEnabledNieH(false);
        
        theView.setPonCo("");theView.setPonOd("");theView.setPonDo("");theView.setPonH("");
        theView.setWtoCo("");theView.setWtoOd("");theView.setWtoDo("");theView.setWtoH("");
        theView.setSroCo("");theView.setSroOd("");theView.setSroDo("");theView.setSroH("");
        theView.setCzwCo("");theView.setCzwOd("");theView.setCzwDo("");theView.setCzwH("");
        theView.setPiaCo("");theView.setPiaOd("");theView.setPiaDo("");theView.setPiaH("");
        theView.setSobCo("");theView.setSobOd("");theView.setSobDo("");theView.setSobH("");
        theView.setNieCo("");theView.setNieOd("");theView.setNieDo("");theView.setNieH("");

        theView.clear_selection();
    }  
   
    private void OdswiezTabele(){       
        theView.wyczysc_tabele();
        int per_id, kom_id;
        int i,lenght;
        
        ArrayList<ArrayList<String>> grafik = theDao.ReadAll("grafik", theModel.getColumns());
        lenght = grafik.size();
        
        for(i=0;i<lenght;i++){
            per_id = Integer.valueOf(grafik.get(i).get(1));
            kom_id = Integer.valueOf(grafik.get(i).get(2));
            ArrayList<String> thisper = theDao.Read("personel", column_personel, per_id);
            ArrayList<String> thiskom = theDao.Read("komorka", column_komorka, kom_id);
            theView.dodaj_wiersz(grafik.get(i).get(0), grafik.get(i).get(1), grafik.get(i).get(2), thisper.get(1), thiskom.get(1) + " / " + thiskom.get(2), grafik.get(i).get(3) , grafik.get(i).get(4));
        } 
    }
   
}
