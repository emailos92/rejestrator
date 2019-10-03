/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.kalendarz;

import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Arkita
 */
public final class KalendarzController {
     @SuppressWarnings("unchecked")
    private final KalendarzView theView;
    private final KalendarzModel theModel;
    private  final ERP_Dao theDao;  
    private int row = -1, tab = -1, set = -1, draw_wiz = 0;
    private float punkty,czas;
    private String id_grafik, id_per, id_kom, id_wiz, id_pac, idkalendarz, typ_kom;
    private int selected = 0;
    private int a=0,b=0,c=0;
    private int [] color_control;
    private int draw = 1;
    
    ArrayList<String> data_values;          //selected, poniedzialek .... niedziela
    ArrayList<ArrayList<String>> cb_personel;
    ArrayList<ArrayList<String>> cb_komorka;
    ArrayList<ArrayList<String>> cb_pacjenci;
    ArrayList<ArrayList<String>> cb_wizyta;
    ArrayList<String> infografik;  
    ArrayList<String> infowizyta; 
    ArrayList<String> pacjent;
    ArrayList<String> personel;
    ArrayList<String> komorka;
    ArrayList<String> wizyta;
    ArrayList<ArrayList<String>> wizyty;
    ArrayList<String> item;
    ArrayList<String> data;
    
    String per_sel, kom_sel, pac_sel, wiz_sel;
    
    ArrayList<String> column_personel;
    ArrayList<String> column_komorka;
    ArrayList<String> column_pacjenci;
    ArrayList<String> column_wizyta;
    ArrayList<String> column_grafik;
    CustomComparator komparator;
    
    public KalendarzController(ERP_View erp_view) {
        
        this.theView = new KalendarzView(erp_view);
        this.theModel = new KalendarzModel();
        this.theDao = new ERP_Dao();
        
        color_control = new int[3];
        this.column_grafik = new ArrayList<>(Arrays.asList("punkty", "czas", "pon_od", "pon_do", "wto_od", "wto_do", "sro_od", "sro_do", "czw_od", "czw_do", "pia_od", "pia_do", "sob_od", "sob_do", "nie_od", "nie_do", "pon_co", "pon_h", "wto_co", "wto_h", "sro_co", "sro_h", "czw_co", "czw_h", "pia_co", "pia_h", "sob_co", "sob_h", "nie_co", "nie_h"));
        this.column_personel = new ArrayList<>(Arrays.asList("idpersonel", "nazwisko_imie"));
        this.column_komorka = new ArrayList<>(Arrays.asList("idkomorka", "nazwa", "numer", "typ"));
        this.column_pacjenci = new ArrayList<>(Arrays.asList("idpacjenci", "nazwisko", "imie", "pesel", "adres", "telefon"));
        this.column_wizyta = new ArrayList<>(Arrays.asList("idwizyta", "nazwa", "punkty", "czas", "typ"));
        
        this.cb_personel = new ArrayList<>();
        this.cb_komorka = theDao.ReadAll("komorka", column_komorka);
        this.cb_pacjenci = theDao.ReadAll("pacjenci", column_pacjenci);
        this.cb_wizyta = theDao.ReadAll("wizyta", column_wizyta);
        komparator = new CustomComparator();
        cb_pacjenci.sort(komparator);
        cb_wizyta.sort(komparator);
        cb_komorka.sort(komparator);
        cb_personel.sort(komparator);
        
        FillCombo();
        
        this.theView.dodaj_wizyte(new DodajWizyte());
        this.theView.zmien_wizyte(new ZmienWizyte());
        this.theView.usun_wizyte(new UsunWizyte());
        this.theView.wyczysc_pola(new Wyczysc());
        this.theView.pon_select(new PonSel());
        this.theView.wto_select(new WtoSel());
        this.theView.sro_select(new SroSel());
        this.theView.czw_select(new CzwSel());
        this.theView.pia_select(new PiaSel());
        this.theView.sob_select(new SobSel());
        this.theView.nie_select(new NieSel());
        this.theView.kol_select(new KolSel());
        this.theView.checkbox_kolejka_action(new CheckBoxKolejka());
        this.theView.combobox_godzina_action(new ComboBoxGodzina());
        this.theView.combobox_personel_action(new PersonelChange());
        this.theView.combobox_komorka_action(new KomorkaChange());
        this.theView.combobox_pacjent_action(new PacjentChange());
        this.theView.combobox_wizyta_action(new WizytaChange());
        this.theView.calendar(new CalendarDataChanged());
        
        data_values = theView.getDate();
        theView.setVisible(true);
    }
    
    
        //CLASSY LISTENERY
    class DodajWizyte implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            boolean can = true;
            int i, lenght;
            
            String data = theView.getDate().get(0);                                                       //odczytanie daty
            ArrayList<String> wizyty = theDao.ReadAllWhere("kalendarz", "idpacjenci", "data", data);      //odczytaj pacjentów na odczytanią datę
            lenght = wizyty.size();                                                                       //ile pacjentów
            
            for(i=0;i<lenght;i++)
            {
                if(wizyty.get(i).equals(id_pac))
                {
                    can = false;
                }
            }
            
            if(can == true)
            {
                ArrayList<String> values = new ArrayList<>();


                values.add(id_per);
                values.add(id_kom);
                values.add(id_pac);
                values.add(id_wiz);
                //if(theView.getCheckBoxKolejka() == true){
                //    values.add("brak");
                //    values.add("brak");
                //}else{

                    values.add(data);
                    values.add(theView.getGodzina());
                //}

                if(theView.getCheckBoxKolejka() == true){
                    values.add("Kolejka"); //status
                }else{
                    values.add("Wizyta"); //status
                }

                if(theView.getCheckBoxPierwsza() == true)
                    values.add("Pierwsza"); 
                else
                    values.add("");

                values.add(theView.getUwagi());

                theDao.Insert("kalendarz", theModel.getColumnsNoId(), values);

                theView.setWizytaValue("Wybierz wizytę");
                theView.setPacjentValue("Wybierz pacjenta");
                theView.setCheckBoxKolejka(false);
                theView.setCheckBoxPierwsza(false);
                wyczysc();
                draw_wizyty();     
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Pacjent jest już umówiony na wizytę tego dnia, wybierz inną datę.", "Informacja", JOptionPane.INFORMATION_MESSAGE); 
            }
        }  
    }
    class ZmienWizyte implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            boolean can = true;
            int i, lenght;
            
            String data = theView.getDate().get(0);                                                       //odczytanie daty
            String data_read = theDao.Read("kalendarz", "data", Integer.parseInt(idkalendarz));
            String idpac_read = theDao.Read("kalendarz", "idpacjenci", Integer.parseInt(idkalendarz));
            
            if(data_read.equals(data) && idpac_read.equals(id_pac))
            {
                //nie zmienia się pacjent oraz data - nie trzeba sprawdzać czy można zmienić wizytę
            } 
            else
            {
                ArrayList<String> wizyty = theDao.ReadAllWhere("kalendarz", "idpacjenci", "data", data);      //odczytaj pacjentów na odczytanią datę
                lenght = wizyty.size();                                                                       //ile pacjentów

                for(i=0;i<lenght;i++)
                {
                    if(wizyty.get(i).equals(id_pac))
                    {
                        can = false;
                    }
                }
            }
            
            if(can == true)
            {
                ArrayList<String> values = new ArrayList<>();

                values.add(id_per);
                values.add(id_kom);
                values.add(id_pac);
                values.add(id_wiz);
                //if(theView.getCheckBoxKolejka() == true){
                //    values.add("brak");
                //    values.add("brak");
                //}else{
                    values.add(theView.getDate().get(0));
                    values.add(theView.getGodzina());
                //}

                if(theView.getCheckBoxKolejka() == true){
                    values.add("Kolejka"); //status
                }else{
                    values.add("Wizyta"); //status
                }

                if(theView.getCheckBoxPierwsza() == true)
                    values.add("Pierwsza"); 
                else
                    values.add("");

                values.add(theView.getUwagi());

                theDao.Update("kalendarz",  theModel.getColumnsNoId(), values, Integer.parseInt(idkalendarz));

                theView.setWizytaValue("Wybierz wizytę");
                theView.setPacjentValue("Wybierz pacjenta");
                theView.setCheckBoxKolejka(false);
                theView.setCheckBoxPierwsza(false);
                draw_wizyty();
                wyczysc();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Pacjent jest już umówiony na wizytę tego dnia, wybierz inną datę.", "Informacja", JOptionPane.INFORMATION_MESSAGE); 
            }
        }  
    }   
    class UsunWizyte implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                                                 
            theDao.Delete("kalendarz", Integer.parseInt(idkalendarz));
            wyczysc();
            draw_wizyty();            
        }  
    }    
    class Wyczysc implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent e) {    
            wyczysc();
        }
    }         
    
    class PonSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
            if(e.getValueIsAdjusting()==true)
            {
                tab = 1;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {           
                row = theView.getPonSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }
           }
        }
    } 
    class WtoSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
            if(e.getValueIsAdjusting()==true)
            {
                tab = 2;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getWtoSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                } 
            }
        }
    } 
    class SroSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {     
            if(e.getValueIsAdjusting()==true)
            {
                tab = 3;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getSroSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }                 
            }
        }
    } 
    class CzwSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
            if(e.getValueIsAdjusting()==true)
            {
                tab = 4;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getCzwSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }                 
            }
        }
    } 
    class PiaSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {            
            if(e.getValueIsAdjusting()==true)
            {
                tab = 5;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getPiaSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }                 
            }
        }
    } 
    class SobSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
            if(e.getValueIsAdjusting()==true)
            {
                tab = 6;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getSobSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }                 
            }
        }
    } 
    class NieSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
            if(e.getValueIsAdjusting()==true)
            {
                tab = 7;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getNieSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }    
            }
        }
    } 
    class KolSel implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
            if(e.getValueIsAdjusting()==true)
            {
                tab = 8;
                theView.deselectTable(tab);
            }
            if(e.getValueIsAdjusting()==false)
            {
                row = theView.getKolSelectedRow();
                if(row!=-1){
                      selected = 1;
                      Selected();
                }                
            }
        }
    } 
    
    class CalendarDataChanged implements PropertyChangeListener{
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            data_values = theView.getDate();
            if(id_per != "NULL" && id_kom != "NULL")
            {
                if(draw == 1)
                {
                    draw_wizyty();
                }
                else
                {
                    draw++;
                    update_hours();
                }
            }
            else if(id_kom != "NULL")
            {
                calc_komorka(id_kom);
            }
        }
    }
    class KomorkaChange implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(theView.getComboKomoIsEnabled())
            {
                kom_sel = theView.getKomorka();
                int i,lenght;
                id_kom = "NULL";
                lenght = cb_komorka.size();
                for(i=0;i<lenght;i++)
                {
                    if((cb_komorka.get(i).get(1) + " / " + cb_komorka.get(i).get(2)).equals(kom_sel))
                    {
                        id_kom = cb_komorka.get(i).get(0);
                        typ_kom = cb_komorka.get(i).get(3);
                    }
                }       
                if(id_kom!="NULL")
                {
                    theView.setPersonelValue("Wybierz personel");
                    ArrayList <String> columns = new ArrayList<>(Arrays.asList("idkomorka", "nazwa", "numer", "adres", "punkty"));
                    ArrayList <String> temp = theDao.Read("komorka",columns,Integer.parseInt(id_kom));
                    theView.setControlTable("  "+temp.get(1) + " / " + temp.get(2), 0);
                    theView.setControlTable("  "+String.valueOf(calc_komorka(temp.get(0))) + " / " + temp.get(4), 1);
                    FillComboPersonel();
                    FillComboWizyta();
                }
                else
                {
                    color_control[0]=0;
                    theView.setTableControlColor(color_control);
                    theView.setPersonelValue("Wybierz personel");
                    theView.setPersonelComboboxEnabled(false);
                    theView.setControlTable("", 0);
                    theView.setControlTable("", 1);
                }
            }
        }
    }
    class PersonelChange implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(theView.getComboPersIsEnabled())
            {
                per_sel = theView.getPersonel();
                int i,lenght;
                id_per = "NULL";
                lenght = cb_personel.size();
                for(i=0;i<lenght;i++)
                {
                    if(cb_personel.get(i).get(1).equals(per_sel))
                    {
                    id_per = cb_personel.get(i).get(0);
                    }
                }      
                if(id_per!="NULL")
                {
                    id_grafik = id_kom + id_per;
                    if(selected==0)
                    {
                        draw_wizyty();
                    }
                    theView.setPacjentComboboxEnabled(true);
                    theView.setWizytaComboboxEnabled(true);
                    theView.setEnabledComboBoxGodzina(true);
                    ArrayList <String> columns = new ArrayList<>(Arrays.asList("idpersonel", "nazwisko_imie", "adres", "telefon"));
                    ArrayList <String> temp = theDao.Read("personel",columns,Integer.parseInt(id_per));
                    theView.setControlTable("  "+temp.get(1),2);
                }
                else
                {
                    id_grafik = "NULL";
                    theView.wyczysc_kolejka();
                    theView.wyczysc_kalendarz();
                    color_control[1]=0;
                    color_control[2]=0;
                    theView.setTableControlColor(color_control);
                    theView.setControlTable("",2);
                    theView.setControlTable("",3);
                    theView.setControlTable("",4);
                    theView.setPacjentValue("Wybierz pacjenta");
                    theView.setWizytaValue("Wybierz wizytę");
                    theView.setPacjentComboboxEnabled(false);
                    theView.setWizytaComboboxEnabled(false);
                    
                }
            }    
        }
    }
    class PacjentChange implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(theView.getComboPacjIsEnabled())
            {
                pac_sel = theView.getPacjent();
                int i,lenght;
                id_pac = "NULL";
                lenght = cb_pacjenci.size();
                for(i=0;i<lenght;i++)
                {
                    if((cb_pacjenci.get(i).get(1) + " " + cb_pacjenci.get(i).get(2) + " " + cb_pacjenci.get(i).get(3)).equals(pac_sel))
                    {
                        id_pac = cb_pacjenci.get(i).get(0);
                    }
                }
                if(id_pac!="NULL")
                {
                    ArrayList<String> columns = new ArrayList<>(Arrays.asList("idpacjenci", "nazwisko", "imie", "pesel", "telefon", "adres"));
                    ArrayList<String> temp = theDao.Read("pacjenci",columns,Integer.parseInt(id_pac));
                    theView.setControlTable("  "+temp.get(1) + " " + temp.get(2),5);
                    theView.setControlTable("  "+temp.get(3),6);
                    theView.setControlTable("  "+temp.get(4),7);
                    theView.setControlTable("  "+temp.get(5),8);
                }
                else
                {
                    theView.setControlTable("",5);
                    theView.setControlTable("",6);
                    theView.setControlTable("",7);
                    theView.setControlTable("",8);
                }
                Status();
            }
        }
    }
    class WizytaChange implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(theView.getComboWizyIsEnabled())
            {
                wiz_sel = theView.getWizyta();
                int i,lenght;
                id_wiz = "NULL";
                lenght = cb_wizyta.size();
                for(i=0;i<lenght;i++)
                {
                    if((cb_wizyta.get(i).get(1) + " / " + cb_wizyta.get(i).get(2) + " / " + cb_wizyta.get(i).get(3)).equals(wiz_sel))
                    {
                        id_wiz = cb_wizyta.get(i).get(0);
                    }
                }
                
                if(id_wiz!="NULL")
                {
                    ArrayList <String> columns = new ArrayList<>(Arrays.asList("idwizyta", "nazwa", "punkty", "czas"));
                    ArrayList <String> temp = theDao.Read("wizyta",columns,Integer.parseInt(id_wiz));
                    theView.setControlTable("  "+temp.get(1),9);
                    theView.setControlTable("  "+temp.get(2) + " pkt" + " / " + temp.get(3) + " h",10);
                }
                else
                {
                    theView.setControlTable("",9);
                    theView.setControlTable("",10);
                }
                Status();
            }
        }
    }
    
    private void FillCombo(){
        
        int i,lenght; 
        theView.deleteAllKomorka();
        theView.deleteAllPersonel();
        theView.deleteAllWizyta();
        theView.deleteAllPacjent();
        id_per = "NULL";
        id_kom = "NULL";
        id_wiz = "NULL";
        id_pac = "NULL";
 
        //status();
           
        lenght = this.cb_komorka.size();
        for(i=0;i<lenght;i++){
            theView.addItemKomorka(cb_komorka.get(i).get(1) + " / " + cb_komorka.get(i).get(2));
        }
        lenght = this.cb_pacjenci.size();
        for(i=0;i<lenght;i++){
            theView.addItemPacjent(cb_pacjenci.get(i).get(1) + " " + cb_pacjenci.get(i).get(2) + " " + cb_pacjenci.get(i).get(3));
        }
    }
    
    private void FillComboWizyta(){
        boolean enabled = theView.getEnabledComboboxWizyta();
        theView.setWizytaComboboxEnabled(false);
            int i, lenght;
            theView.deleteAllWizyta();
            lenght = this.cb_wizyta.size();
            for(i=0;i<lenght;i++){
                if(cb_wizyta.get(i).get(4).equals(typ_kom))
                {
                    theView.addItemWizyta(cb_wizyta.get(i).get(1) + " / " + cb_wizyta.get(i).get(2) + " / " + cb_wizyta.get(i).get(3));
                }
            }
        theView.setWizytaComboboxEnabled(enabled);
    }
    
    private void FillComboPersonel(){
        theView.setPersonelComboboxEnabled(false);
        theView.deleteAllPersonel();
        id_per = "NULL";
        cb_personel.clear();
        ArrayList<String> person;  
        ArrayList<String> pers = theDao.ReadAllWhere("grafik", "idpersonel", "idkomorka", id_kom);
        int i, lenght = pers.size();
        for(i=0;i<lenght;i++)
        {
            person=theDao.Read("personel", column_personel, Integer.parseInt(pers.get(i)));
            cb_personel.add(person);
        }
        lenght = this.cb_personel.size();
        for(i=0;i<lenght;i++){
            theView.addItemPersonel(cb_personel.get(i).get(1));
        }
        theView.setPersonelComboboxEnabled(true);//enable event personel change
    }
    
    class ComboBoxGodzina implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            

        }
    }
    class CheckBoxKolejka implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckBoxKolejka()==true){
                //theView.setGodzina("- - -");
                //theView.setEnabledComboBoxGodzina(false);
            }else{
                //theView.setEnabledComboBoxGodzina(true);
            }
        }
    }
    
    private void Status(){
        if(selected == 0 && id_pac != "NULL" && id_wiz != "NULL")
        {
            theView.setDodajButtonEnabled(true);
            theView.setEnabledCheckBoxKolejka(true);
            theView.setEnabledComboBoxGodzina(true);
            theView.setEnabledCheckBoxPierwsza(true);
        }
        else
        {
            theView.setDodajButtonEnabled(false);
            theView.setEnabledCheckBoxKolejka(false);
            //theView.setEnabledComboBoxGodzina(false);
            theView.setEnabledCheckBoxPierwsza(false);
        }

        
    }    
    private void Selected(){
        if(selected==1)
        {
            idkalendarz = theView.getId(tab, row);
            theView.setPersonelComboboxEnabled(false);
            theView.setKomorkaComboboxEnabled(false);
            ArrayList<String> temp,columns;
            
            columns = new ArrayList<>(Arrays.asList("idpersonel","idkomorka","idpacjenci","idwizyta","data","godzina","status","pierwsza","uwagi"));
            temp = theDao.Read("kalendarz", columns, Integer.parseInt(theView.getId(tab, row)));
            //System.out.println(temp);
            id_per=temp.get(0);
            id_kom=temp.get(1);
            id_pac=temp.get(2);
            id_wiz=temp.get(3);

            theView.setControlTable("  "+temp.get(4),11);    //data
            theView.setControlTable("  "+temp.get(5),12);    //godzina
            theView.setControlTable("  "+temp.get(6) + " " + temp.get(7),13);    //pierwsza - kolejka - odwolana
            theView.setUwagi(temp.get(8));              //uwagi
            
            //if(temp.get(4).equals("brak"))
            //{} else
            //{ 
                draw = 0;
                theView.setDate(temp.get(4));
            //}
            
            //if(temp.get(5).equals("brak"))
            //{
            //    theView.setGodzina("- - -");
            //} 
            //else
            //{
                theView.setGodzina(temp.get(5));
            //}
            
            if(temp.get(6).equals("Kolejka"))
            {
                theView.setCheckBoxKolejka(true);
                //theView.setEnabledComboBoxGodzina(false);
            } 
            else
            {
                theView.setCheckBoxKolejka(false);
                //theView.setEnabledComboBoxGodzina(true);
            }
            
            if(temp.get(7).equals("Pierwsza"))
            {
                theView.setCheckBoxPierwsza(true);
            } 
            else
            {
                theView.setCheckBoxPierwsza(false);
            }
            
            //System.out.println("after ifs");
            
            //columns = new ArrayList<>(Arrays.asList("idkomorka", "nazwa", "numer", "adres", "punkty"));
            //temp = theDao.Read("komorka",columns,Integer.parseInt(id_kom));
            //theView.setKomorkaValue(temp.get(1) + " / " + temp.get(2));

            //columns = new ArrayList<>(Arrays.asList("idpersonel", "nazwisko_imie", "adres", "telefon"));
            //temp = theDao.Read("personel",columns,Integer.parseInt(id_per));
            //theView.setPersonelValue(temp.get(1));
            //if(tab != 8){
            //columns = new ArrayList<>(Arrays.asList("idpacjenci", "nazwisko", "imie", "pesel"));
            temp = theDao.Read("pacjenci",column_pacjenci,Integer.parseInt(id_pac));
            theView.setPacjentValue((temp.get(1) + " " + temp.get(2) + " " + temp.get(3)));
            //System.out.println("after 1 dao");
            //columns = new ArrayList<>(Arrays.asList("idwizyta", "nazwa", "punkty", "czas"));
            temp = theDao.Read("wizyta",column_wizyta,Integer.parseInt(id_wiz));
            theView.setWizytaValue(temp.get(1) + " / " + temp.get(2) + " / " + temp.get(3));
            //System.out.println("after 2 dao");
            //}
            
            theView.setDodajButtonEnabled(false);
            theView.setUsunButtonEnabled(true);
            theView.setZmienButtonEnabled(true);
            theView.setWyczyscButtonEnabled(true);

            theView.setEnabledCheckBoxKolejka(true);
            //theView.setEnabledComboBoxGodzina(true);
            theView.setEnabledCheckBoxPierwsza(true);
        }
    }
    
    private float calc_komorka(String idkomorka){
        float pkt = 0;
        int lenght,i,j;
        float pkt_kom = Float.parseFloat(theDao.Read("komorka", "punkty", Integer.parseInt(idkomorka)));

        item = new ArrayList<>(Arrays.asList("idkomorka", "data"));
        data = new ArrayList<>(Arrays.asList(idkomorka, ""));
        
        for(j=1;j<=Integer.parseInt(data_values.get(8));j++)
        {
            if(j<10)
            {
                data.set(1, "0" + String.valueOf(j) + "/" + data_values.get(9) + "/" + data_values.get(10));
            }
            else
            {
                data.set(1, String.valueOf(j) + "/" + data_values.get(9) + "/" + data_values.get(10));
            }
            ArrayList<String> visits = theDao.ReadAllWhere("kalendarz", "idwizyta" , item, data);
            lenght = visits.size();
            for(i=0;i<lenght;i++){                           
                pkt += Float.parseFloat(theDao.Read("wizyta", "punkty", Integer.valueOf(visits.get(i))));
            }
        }
        
        if(pkt>pkt_kom)
        {
            a=2;
        }
        else
        {
            a=1;
        }
        
        color_control[0]=a;
        color_control[1]=0;
        color_control[2]=0;
        theView.setTableControlColor(color_control);
        
        return pkt;
    }
    private void draw_wizyty(){
        
        int i,j,lenght;
        float t_pon, t_wto, t_sro, t_czw, t_pia, t_sob, t_nie;
        punkty = 0; czas = 0;
        t_pon = 0; t_wto = 0; t_sro = 0; t_czw = 0; t_pia = 0; t_sob = 0; t_nie = 0; 
        theView.wyczysc_kalendarz();
        theView.wyczysc_kolejka();
        
        item = new ArrayList<>(Arrays.asList("idpersonel", "idkomorka", "status"));
        data = new ArrayList<>(Arrays.asList(id_per, id_kom, "Kolejka"));
        
        wizyty = theDao.ReadAllWhere("kalendarz", theModel.getColumns(), item, data);
        lenght = wizyty.size();
        //System.out.println(wizyty);
        //System.out.println(lenght);
            for(i=0;i<lenght;i++){                                        
                
            pacjent = theDao.Read( "pacjenci", column_pacjenci, Integer.valueOf(wizyty.get(i).get(3)) );
            System.out.println("pacjent: " + pacjent);
            wizyta = theDao.Read( "wizyta", column_wizyta, Integer.valueOf(wizyty.get(i).get(4)) );
            System.out.println("wizyta: " + wizyta);
            personel = theDao.Read( "personel", column_personel, Integer.valueOf(wizyty.get(i).get(1)) );
            System.out.println("personel: " + personel);
            komorka = theDao.Read( "komorka", column_komorka, Integer.valueOf(wizyty.get(i).get(2)) );
            System.out.println("komorka: " + komorka);
            
            if( komorka.get(0).equals("-1") || 
                personel.get(0).equals("-1") || 
                pacjent.get(0).equals("-1") || 
                wizyta.get(0).equals("-1")
                    )
            {
                System.out.println("Brak pacjenta/wizyty/komorki/personelu w DB - usuwam wizytę");
                theDao.Delete("kalendarz", Integer.parseInt(wizyty.get(i).get(0)));
            }
            else
            {
                theView.dodaj_wiersz_kolejka(wizyty.get(i).get(0), pacjent.get(1), pacjent.get(2), pacjent.get(3), pacjent.get(5));
            }
        }
        
        
        item = new ArrayList<>(Arrays.asList("idpersonel", "idkomorka", "data"));
        for(j=1;j<8;j++)
        {
            data.set(2, data_values.get(j));
            
            wizyty = theDao.ReadAllWhere("kalendarz", theModel.getColumns(), item, data);
            System.out.println(wizyty);
            lenght = wizyty.size();
            //System.out.println(lenght);
            
            for(i=0;i<lenght;i++){                           

                pacjent = theDao.Read( "pacjenci", column_pacjenci, Integer.valueOf(wizyty.get(i).get(3)) );
                //System.out.println(pacjent.get(0));
                wizyta = theDao.Read( "wizyta", column_wizyta, Integer.valueOf(wizyty.get(i).get(4)) );
                //System.out.println(wizyta.get(0));
                personel = theDao.Read( "personel", column_personel, Integer.valueOf(wizyty.get(i).get(1)) );
                //System.out.println(wizyta.get(0));
                komorka = theDao.Read( "komorka", column_komorka, Integer.valueOf(wizyty.get(i).get(2)) );
                //System.out.println(komorka);
                
                if( komorka.get(0).equals("-1") || 
                    personel.get(0).equals("-1") || 
                    pacjent.get(0).equals("-1") || 
                    wizyta.get(0).equals("-1"))
                {
                    System.out.println("Brak pacjenta/wizyty/komorki/personelu w DB - usuwam wizytę");
                    theDao.Delete("kalendarz", Integer.parseInt(wizyty.get(i).get(0)));
                }
                //else if(wizyty.get(i).get(7).equals("Kolejka")) //kolejka
                //{}
                else        //wizyta
                {
                    czas += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                    if(j==1)
                    {    
                        t_pon += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_pon(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                    else if(j==2)
                    {    
                        t_wto += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_wto(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                    else if(j==3)
                    {    
                        t_sro += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_sro(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                    else if(j==4)
                    {    
                        t_czw += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_czw(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                    else if(j==5)
                    {    
                        t_pia += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_pia(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                    else if(j==6)
                    {    
                        t_sob += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_sob(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                    else if(j==7)  
                    {    
                        t_nie += Float.parseFloat(theDao.Read("wizyta", column_wizyta, Integer.parseInt(wizyty.get(i).get(4))).get(3));
                        theView.dodaj_wiersz_nie(wizyty.get(i).get(0), wizyty.get(i).get(6));
                    }
                }
            }
        }
        
        
        infografik = theDao.Read("grafik", column_grafik, Integer.parseInt(id_grafik));
        update_hours();
        
        ArrayList<String> info = new ArrayList<>();
        if(infografik.get(17).equals("0"))
            info.add("");
        else
            info.add(infografik.get(2) + " - " + infografik.get(3));
        if(infografik.get(19).equals("0"))
            info.add("");
        else
            info.add(infografik.get(4) + " - " + infografik.get(5));        
        if(infografik.get(21).equals("0"))
            info.add("");
        else
            info.add(infografik.get(6) + " - " + infografik.get(7));        
        if(infografik.get(23).equals("0"))
            info.add("");
        else
            info.add(infografik.get(8) + " - " + infografik.get(9));  
        if(infografik.get(25).equals("0"))
            info.add("");
        else
            info.add(infografik.get(10) + " - " + infografik.get(11));        
        if(infografik.get(27).equals("0"))
            info.add("");
        else
            info.add(infografik.get(12) + " - " + infografik.get(13));        
        if(infografik.get(29).equals("0"))
            info.add("");
        else
            info.add(infografik.get(14) + " - " + infografik.get(15));        
        
        ArrayList<String> t_week = new ArrayList<>(Arrays.asList( t_pon + " / " + infografik.get(17),
                                                                  t_wto + " / " + infografik.get(19),
                                                                  t_sro + " / " + infografik.get(21),
                                                                  t_czw + " / " + infografik.get(23),
                                                                  t_pia + " / " + infografik.get(25),
                                                                  t_sob + " / " + infografik.get(27),
                                                                  t_nie + " / " + infografik.get(29)));
        
        int[] color = new int[7]; 
        if(infografik.get(17).equals("0")) 
            if(t_pon>0)
            color[0]=2;
            else
            color[0]=0;
        else if(t_pon>Float.parseFloat(infografik.get(17)))
            color[0]=2;
        else
            color[0]=1;
        
        if(infografik.get(19).equals("0")) 
            if(t_wto>0)
            color[1]=2;
            else
            color[1]=0;
        else if(t_wto>Float.parseFloat(infografik.get(19)))
            color[1]=2;
        else
            color[1]=1;
        
        if(infografik.get(21).equals("0")) 
            if(t_sro>0)
            color[2]=2;
            else
            color[2]=0;
        else if(t_sro>Float.parseFloat(infografik.get(21)))
            color[2]=2;
        else
            color[2]=1;
        
        if(infografik.get(23).equals("0")) 
            if(t_czw>0)
            color[3]=2;
            else
            color[3]=0;
        else if(t_czw>Float.parseFloat(infografik.get(23)))
            color[3]=2;
        else
            color[3]=1;
        
        if(infografik.get(25).equals("0")) 
            if(t_pia>0)
            color[4]=2;
            else
            color[4]=0;
        else if(t_pia>Float.parseFloat(infografik.get(25)))
            color[4]=2;
        else
            color[4]=1;
        
        if(infografik.get(27).equals("0")) 
            if(t_sob>0)
            color[5]=2;
            else
            color[5]=0;
        else if(t_sob>Float.parseFloat(infografik.get(27)))
            color[5]=2;
        else
            color[5]=1;
        
        if(infografik.get(29).equals("0")) 
            if(t_nie>0)
            color[6]=2;
            else
            color[6]=0;
        else if(t_nie>Float.parseFloat(infografik.get(29)))
            color[6]=2;
        else
            color[6]=1;
        
        theView.setTableInfoColor(color);

        theView.set_info(data_values,info,t_week);        
        
        for(j=1;j<=Integer.parseInt(data_values.get(8));j++)
        {
            if(j<10)
            {
                data.set(2, "0" + String.valueOf(j) + "/" + data_values.get(9) + "/" + data_values.get(10));
            }
            else
            {
                data.set(2, String.valueOf(j) + "/" + data_values.get(9) + "/" + data_values.get(10));
            }
            ArrayList<String> visits = theDao.ReadAllWhere("kalendarz", "idwizyta" , item, data);
            lenght = visits.size();
            for(i=0;i<lenght;i++){                           
                punkty += Float.parseFloat(theDao.Read("wizyta", "punkty", Integer.valueOf(visits.get(i))));
            }
        }
        ArrayList <String> columns = new ArrayList<>(Arrays.asList("punkty", "czas"));
        ArrayList <String> temp = theDao.Read("grafik",columns,Integer.parseInt(id_grafik));
        
        calc_komorka(id_kom);
        if(punkty>Float.parseFloat(temp.get(0)))
        {
            b=2;
        }
        else
        {
            b=1;
        }
        if(czas>Float.parseFloat(temp.get(1)))
        {
            c=2;
        }
        else
        {
            c=1;
        }        
        theView.setControlTable("  "+punkty + " / " + temp.get(0),3);
        theView.setControlTable("  "+czas + " / " + temp.get(1),4);
        
        calc_komorka_punkty();
        color_control[0]=a;
        color_control[1]=b;
        color_control[2]=c;
        theView.setTableControlColor(color_control);

    }   
    private void update_hours(){
        infografik = theDao.Read("grafik", column_grafik, Integer.parseInt(id_grafik));
        
        ArrayList <String> hours = new ArrayList<>();
        String day_od="", day_do="", day_co="", day_h="";
        int tmp_h, tmp_m, do_h, do_m;
        String day_tmp="", tmp_hours="", tmp_minutes="";
        
        int day_of_week = Integer.parseInt(data_values.get(11));
        switch (day_of_week)
        {
            case 2:
                day_od = infografik.get(2);
                day_do = infografik.get(3);
                day_co = infografik.get(16);
                day_h = infografik.get(17);
            break;
            case 3:
                day_od = infografik.get(4);
                day_do = infografik.get(5);     
                day_co = infografik.get(18);
                day_h = infografik.get(19);
            break;
            case 4:
                day_od = infografik.get(6);
                day_do = infografik.get(7);
                day_co = infografik.get(20);
                day_h = infografik.get(21);
            break;
            case 5:
                day_od = infografik.get(8);
                day_do = infografik.get(9);
                day_co = infografik.get(22);
                day_h = infografik.get(23);
            break;
            case 6:
                day_od = infografik.get(10);
                day_do = infografik.get(11);
                day_co = infografik.get(24);
                day_h = infografik.get(25);
            break;
            case 7:
                day_od = infografik.get(12);
                day_do = infografik.get(13);
                day_co = infografik.get(26);
                day_h = infografik.get(27);
            break;
            case 1:
                day_od = infografik.get(14);
                day_do = infografik.get(15);
                day_co = infografik.get(28);
                day_h = infografik.get(29);
            break;
            default:
            break;
        }
        
        if(day_h.equals("0")){}
        else
        {
            tmp_h = Integer.parseInt(day_od.substring(0,2));
            tmp_m = Integer.parseInt(day_od.substring(3,5));
            do_h = Integer.parseInt(day_do.substring(0,2));
            do_m = Integer.parseInt(day_do.substring(3,5));
            //System.out.print(tmp_h);
            //System.out.print(tmp_m);
            //System.out.print(do_h);
            //System.out.print(do_m);
            
            while(tmp_h < do_h || (tmp_h==do_h && tmp_m < do_m))
            {
                
                if(tmp_h < 10)
                    tmp_hours = "0" + String.valueOf(tmp_h);
                else
                    tmp_hours = String.valueOf(tmp_h);
                if(tmp_m < 10)
                    tmp_minutes = "0" + String.valueOf(tmp_m);
                else
                    tmp_minutes = String.valueOf(tmp_m);             
                day_tmp = tmp_hours + ":" + tmp_minutes;
                
                hours.add(day_tmp);

                tmp_m += Integer.parseInt(day_co.substring(3,5));
                if(tmp_m >= 60){
                    tmp_m -= 60;
                    tmp_h += 1;
                }
                tmp_h += Integer.parseInt(day_co.substring(0,2));
                //System.out.print(tmp_h);
                //System.out.print(tmp_m);
                
            }
        }
        //System.out.print(hours);
        theView.setComboboxModel(hours);
        
        
    }
    private void wyczysc(){
            theView.deselectTable(0);
            selected = 0;
            theView.setUsunButtonEnabled(false);
            theView.setZmienButtonEnabled(false);
            theView.setWyczyscButtonEnabled(false);
            theView.setPacjentValue("Wybierz pacjenta");
            theView.setWizytaValue("Wybierz wizytę");
            theView.setControlTable("", 11);
            theView.setControlTable("", 12);
            theView.setControlTable("", 13);
            theView.setUwagi("");
            theView.setGodzina("- - -");
            theView.setCheckBoxKolejka(false);
            theView.setCheckBoxPierwsza(false);
            theView.setPersonelComboboxEnabled(true);
            theView.setKomorkaComboboxEnabled(true);
            idkalendarz = "NULL";
    }
    
    public class CustomComparator implements Comparator<ArrayList<String>> {
    @Override
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            return o1.get(1).compareTo(o2.get(1));
        }
    }
    
    private void calc_komorka_punkty(){
        
        float pkt = 0;
        int lenght,i,j;
        float pkt_kom = Float.parseFloat(theDao.Read("komorka", "punkty", Integer.parseInt(id_kom)));

        item = new ArrayList<>(Arrays.asList("idkomorka", "data"));
        data = new ArrayList<>(Arrays.asList(id_kom, ""));
        
        for(j=1;j<=Integer.parseInt(data_values.get(8));j++)
        {
            if(j<10)
            {
                data.set(1, "0" + String.valueOf(j) + "/" + data_values.get(9) + "/" + data_values.get(10));
            }
            else
            {
                data.set(1, String.valueOf(j) + "/" + data_values.get(9) + "/" + data_values.get(10));
            }
            ArrayList<String> visits = theDao.ReadAllWhere("kalendarz", "idwizyta" , item, data);
            lenght = visits.size();
            for(i=0;i<lenght;i++){                           
                pkt += Float.parseFloat(theDao.Read("wizyta", "punkty", Integer.valueOf(visits.get(i))));
            }
        }
        
        if(pkt>pkt_kom)
        {
            a=2;
        }
        else
        {
            a=1;
        }
        
        //color_control[0]=a;
        //color_control[1]=0;
        //color_control[2]=0;
        //theView.setTableControlColor(color_control);
        theView.setControlTable("  "+String.valueOf(pkt) + " / " + pkt_kom, 1);
    }
    
}


