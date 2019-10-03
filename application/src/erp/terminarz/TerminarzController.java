/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erp.terminarz;
import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Arkita
 */
public class TerminarzController {
     @SuppressWarnings("unchecked")
    private final TerminarzView theView;
    private final TerminarzModel theModel;
    private final ERP_Dao theDao;
    private int row = -1;
    
    ArrayList<ArrayList<String>> cb_personel;
    ArrayList<ArrayList<String>> cb_komorka;
    ArrayList<ArrayList<String>> cb_pacjenci;
    ArrayList<ArrayList<String>> cb_wizyta;    
    ArrayList<String> column_personel;
    ArrayList<String> column_komorka;
    ArrayList<String> column_pacjenci;
    ArrayList<String> column_wizyta;
    DateFormat sdf;
    CustomComparator komparator;
            
    float punkty,czas,pln;
    int ilosc_wizyt;
    
    public TerminarzController(ERP_View erp_view)
    {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        
        this.theView = new TerminarzView(erp_view);
        this.theModel = new TerminarzModel();
        this.theDao = new ERP_Dao();
        theView.setEnabledUsun(false);
        
        this.column_personel = new ArrayList<>(Arrays.asList("idpersonel", "nazwisko_imie"));
        this.column_komorka = new ArrayList<>(Arrays.asList("idkomorka", "nazwa", "numer", "pln","typ"));
        this.column_pacjenci = new ArrayList<>(Arrays.asList("idpacjenci", "nazwisko", "imie", "pesel", "adres", "telefon"));
        this.column_wizyta = new ArrayList<>(Arrays.asList("idwizyta", "nazwa", "punkty", "czas", "typ"));
        FillCombo();
        
        this.theView.checkbox_dataod(new DataOd());
        this.theView.checkbox_datado(new DataDo());
        this.theView.checkbox_urodzonyod(new UrodzonyOd());
        this.theView.checkbox_urodzonydo(new UrodzonyDo());
        
        this.theView.komorka_checkbox(new KomorkaCheckbox());
        this.theView.pacjent_checkbox(new PacjentCheckbox());
        this.theView.telefon_checkbox(new TelefonCheckbox());
        this.theView.data_checkbox(new DataCheckbox());
        this.theView.uwagi_checkbox(new UwagiCheckbox());
        this.theView.personel_checkbox(new PersonelCheckbox());
        this.theView.adres_checkbox(new AdresCheckbox());
        this.theView.wizyta_checkbox(new WizytaCheckbox());
        this.theView.godzina_checkbox(new GodzinaCheckbox());
        
        this.theView.button_pokaz(new Pokaz());
        this.theView.button_wyczysc(new Wyczysc());
        this.theView.button_dopliku(new DoPliku());
        this.theView.checkbox_kolejka(new Kolejka());
        this.theView.txtfield_szukaj(new SzukajTabela());
        
        this.theView.combobox_komorka(new Komorka());
        this.theView.combobox_personel(new Personel());
        this.theView.combobox_pacjent(new Pacjent());
        this.theView.button_usun(new Usun());
        this.theView.zaznaczono_wiersz(new ZaznaczonoWiersz());
        
        theView.setVisible(true);
    }

    class KomorkaCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.margeColumns();
        }          
    } 
    class PacjentCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {   
            theView.margeColumns();
        }
    } 
    class TelefonCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {   
            theView.margeColumns();
        }
    }
    class DataCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            theView.margeColumns();
        }
    }
    class UwagiCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            theView.margeColumns();
        }
    }
    class PersonelCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {   
            theView.margeColumns();
        }
    }
    class AdresCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            theView.margeColumns();
        }
    }
    class WizytaCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.margeColumns();
        }
    }
    class GodzinaCheckbox implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.margeColumns();
        }
    }
    
    class DataOd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckboxDataOd()){
                theView.setEnabledDataOd(true);
            }
            else{
                theView.setEnabledDataOd(false);
            }
        }
    }
    class DataDo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckboxDataDo()){
                theView.setEnabledDataDo(true);
            }
            else{
                theView.setEnabledDataDo(false);
            }            
        }
    }
    class UrodzonyOd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckboxUrodzonyOd()){
                theView.setEnabledUrodzonyOd(true);
            }
            else{
                theView.setEnabledUrodzonyOd(false);
            }            
        }
    }
    class UrodzonyDo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckboxUrodzonyDo()){
                theView.setEnabledUrodzonyDo(true);
            }
            else{
                theView.setEnabledUrodzonyDo(false);
            }              
        }
    }
    class Kolejka implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getCheckboxKolejka()){ 
                theView.setEnabledCheckboxDataOd(false);
                theView.setEnabledCheckboxDataDo(false);
                theView.setEnabledDataOd(false);
                theView.setEnabledDataDo(false);
                theView.setSelectedCheckboxDataOd(false);
                theView.setSelectedCheckboxDataDo(false);
            }
            else{
                theView.setEnabledCheckboxDataOd(true);
                theView.setEnabledCheckboxDataDo(true);
                //theView.setEnabledDataOd(true);
                //theView.setEnabledDataDo(true);
            }              
        }
    }
    class Komorka implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.isEnabledComboboxKomorka())
            {
                if(theView.getKomorka().equals("Wybierz komórkę"))
                {
                    theView.setEnabledComboboxWizyta(false);
                }
                else
                {
                    String typ = null;
                    int i,lenght;
                    lenght = cb_komorka.size();
                    for(i=0;i<lenght;i++)
                    {
                        if(theView.getKomorka().equals(cb_komorka.get(i).get(1) + " / " + cb_komorka.get(i).get(2)))
                        {
                            typ = cb_komorka.get(i).get(4);
                        }
                    }

                    theView.deleteAllWizyta();
                
                    lenght = cb_wizyta.size();
                    for(i=0;i<lenght;i++){
                        if(cb_wizyta.get(i).get(4).equals(typ))
                        {
                            theView.addItemWizyta(cb_wizyta.get(i).get(1) + " / " + cb_wizyta.get(i).get(2) + " / " + cb_wizyta.get(i).get(3));
                        }
                    }
                    theView.setEnabledComboboxWizyta(true);
                }
            } 
        }
    }
    class Personel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            

        }
    }
    class Usun implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                        
            if(row >= 0){                               
                
                //PacjenciDao theDao = new PacjenciDao();
                //theDao.deleteFromPacjenci(theView.getPeselFromTable(row));                           

                theView.setEnabledUsun(false);
                theDao.Delete("kalendarz", theView.getId(row));
                wyczysc();
                
                funkcja_pokaz();
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
                    theView.setEnabledUsun(true);            
                }
           }
        }
    }     
    class Pokaz implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            funkcja_pokaz();
        }
    }
    class Wyczysc implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {  
           wyczysc();
        }
    }
    class DoPliku implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            ArrayList<String> columns, col;
            ArrayList<ArrayList<String>> values;
            int i,j,lenght_col, lenght_row;
            columns = new ArrayList<>();
            col = new ArrayList<>();
            values = new ArrayList<>();
            String workspace = null;
            
            if(theView.getCheckboxKomorka()==true){
                columns.add("5");
                col.add("Komorka");
            }
            if(theView.getCheckboxPersonel()==true){
                columns.add("6");
                col.add("Personel");
            }           
            if(theView.getCheckboxPacjent()==true){
                columns.add("7");
                col.add("Pacjent");
            }     
            if(theView.getCheckboxAdres()==true){
                columns.add("8");
                col.add("Adres");
            }        
            if(theView.getCheckboxTelefon()==true){
                columns.add("9");
                col.add("Telefon");
            }   
            if(theView.getCheckboxWizyta()==true){
                columns.add("10");
                col.add("Wizyta");
            }   
            if(theView.getCheckboxData()==true){
                columns.add("11");
                col.add("Data");
            } 
            if(theView.getCheckboxGodzina()==true){
                columns.add("12");
                col.add("Godzina");
            } 
            if(theView.getCheckboxUwagi()==true){
                col.add("1");
                col.add("Uwagi");
                columns.add("13");
                columns.add("14");
            }
            
            values = theView.getValuesAt(columns);
            lenght_col = columns.size();
            lenght_row = values.size();
            
            try {
                workspace = new File(".").getCanonicalPath()+"\\";
            } catch (IOException ex) {
                Logger.getLogger(TerminarzController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try (FileWriter writer = new FileWriter("wizyty.csv")) {
                
                for(j=0;j<lenght_col;j++){
                    writer.append(col.get(j));
                    if(lenght_col!=(j-1))
                        writer.append(';');
                }
                writer.append('\n');
                
                for(i=0;i<lenght_row;i++){
                    for(j=0;j<lenght_col;j++){
                        writer.append(values.get(i).get(j));
                        if(lenght_col!=(j-1))
                            writer.append(';');
                    }  
                    writer.append('\n');
                }
                
                writer.append("Punkty:;;"+String.valueOf(punkty)+'\n');
                writer.append("Czas:;;"+String.valueOf(czas)+'\n');
                writer.append("PLN:;;"+String.valueOf(pln)+'\n');
                writer.append("Ilość wizyt:;;"+String.valueOf(ilosc_wizyt)+'\n');
                
                writer.flush();
                
            } catch (IOException ex) {
                Logger.getLogger(TerminarzController.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Wizyty wyexportowane do pliku wizyty.csv w katalogu głównym programu.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
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

            //if (text.trim().length() == 0) {
                //theView.setRowSorterer(null);
            //} else {
                theView.setRowSorterer(text);
            //}
        }
        @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }      
    class Pacjent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(theView.getEnabledComboboxPacjent()){
                if(theView.getPacjent().equals("Wybierz pacjenta")){
                    theView.setEnabledPacjent(true);
                }
                else{
                    theView.setEnabledPacjent(false);
                }
            }                   
        }
    }

    private void FillCombo(){
         
        cb_komorka = theDao.ReadAll("komorka", column_komorka);
        cb_personel = theDao.ReadAll("personel", column_personel);
        cb_pacjenci = theDao.ReadAll("pacjenci", column_pacjenci);
        cb_wizyta = theDao.ReadAll("wizyta", column_wizyta);
        komparator = new CustomComparator();
        cb_pacjenci.sort(komparator);
        cb_wizyta.sort(komparator);
        cb_komorka.sort(komparator);
        cb_personel.sort(komparator);
        
        int i,lenght; 
        theView.deleteAllKomorka();
        theView.deleteAllPersonel();
        theView.deleteAllWizyta();
        theView.deleteAllPacjent();

        lenght = this.cb_komorka.size();
        for(i=0;i<lenght;i++){
            theView.addItemKomorka(cb_komorka.get(i).get(1) + " / " + cb_komorka.get(i).get(2));
        }
        lenght = this.cb_personel.size();
        for(i=0;i<lenght;i++){
            theView.addItemPersonel(cb_personel.get(i).get(1));
        }
        lenght = this.cb_pacjenci.size();
        for(i=0;i<lenght;i++){
            theView.addItemPacjent(cb_pacjenci.get(i).get(1) + " " + cb_pacjenci.get(i).get(2) + " " + cb_pacjenci.get(i).get(3));
        }
        theView.setEnabledComboboxWizyta(false);
        //lenght = this.cb_wizyta.size();
        //for(i=0;i<lenght;i++){
        //    theView.addItemWizyta(cb_wizyta.get(i).get(1) + " / " + cb_wizyta.get(i).get(2) + " / " + cb_wizyta.get(i).get(3));
        //}
    }
    private void put_table(ArrayList<String> temp){
        float zl_pkt;
        ArrayList<String> tmp;
        ArrayList<String> wiersz = new ArrayList<>(Arrays.asList(temp.get(0),temp.get(1),temp.get(2),temp.get(3),temp.get(4)));

        tmp=theDao.Read("komorka", column_komorka, Integer.parseInt(temp.get(1)));
        if(tmp.get(0).equals("-1")){
            System.out.println("Brak komórki - komórka usunięta");
            wiersz.add("Usunięta!");
            zl_pkt = 0;
        }
        else
        {
            wiersz.add(tmp.get(1) + " / " + tmp.get(2));                                        //komórka
            zl_pkt=Float.parseFloat(tmp.get(3));
        }  
        
        tmp=theDao.Read("personel", column_personel, Integer.parseInt(temp.get(2)));
        if(tmp.get(0).equals("-1")){
            wiersz.add("Usunięty!");
        }
        else
        {
            wiersz.add(tmp.get(1));                                                             //personel
        }  
        
        tmp=theDao.Read("pacjenci", column_pacjenci, Integer.parseInt(temp.get(3)));
        if(tmp.get(0).equals("-1")){
            wiersz.add("Usunięty!");
            wiersz.add("brak");
            wiersz.add("brak");
        }
        else
        {
            wiersz.add(tmp.get(1) + " " + tmp.get(2) + " " + tmp.get(3));                       //pacjent             
            wiersz.add(tmp.get(4));                                                             //adres
            wiersz.add(tmp.get(5));                                                             //telefon                                                     //personel
        }  
        tmp=theDao.Read("wizyta", column_wizyta, Integer.parseInt(temp.get(4)));
        if(tmp.get(0).equals("-1")){
            wiersz.add("Usunięta!");
        }
        else
        {
            wiersz.add(tmp.get(1) + " / " + tmp.get(2) + " / " + tmp.get(3));                   //wizyta
        }
        
        wiersz.add(temp.get(5));
        wiersz.add(temp.get(6));
        if(temp.get(8).equals("Pierwsza"))
            wiersz.add("x");
        else
            wiersz.add("");
        wiersz.add(temp.get(9));
        
        String status = theDao.Read("kalendarz", "status", Integer.parseInt(temp.get(0)));      //read status wizyty
        
        if(status.equals("Kolejka"))
        {
            wiersz.add("K");
        }
        else
        {
            wiersz.add("W");
        }
  
        theView.dodaj_wiersz(wiersz);
        ilosc_wizyt++;
        punkty += Float.parseFloat(tmp.get(2));
        czas += Float.parseFloat(tmp.get(3));
        pln += zl_pkt;
    } 
    private void funkcja_pokaz(){
        String data_od="NULL", data_do="NULL", urodzony_od="NULL", urodzony_do="NULL";
            Date date = new Date();
            String today = sdf.format(date);
            String urodzony;
            int data_od_dzien=0, data_od_miesiac=0, data_od_rok=0;
            int data_do_dzien=0, data_do_miesiac=0, data_do_rok=0;
            int urodzony_od_dzien=0, urodzony_od_miesiac=0, urodzony_od_rok=0;
            int urodzony_do_dzien=0, urodzony_do_miesiac=0, urodzony_do_rok=0;
            ArrayList<ArrayList<String>> temp;
            ArrayList<String> wiersz,tmp;
            ArrayList<String> item, data;
            item = new ArrayList<>();
            data = new ArrayList<>();
            String kom_sel,per_sel,pac_sel,wiz_sel;
            String id_kom="NULL", id_per="NULL", id_pac="NULL", id_wiz="NULL";
            int i, lenght;
            
            kom_sel=theView.getKomorka();
            per_sel=theView.getPersonel();
            pac_sel=theView.getPacjent();
            wiz_sel=theView.getWizyta();
            
            theView.wyczysc_terminarz();
            
            if(kom_sel.equals("Wybierz komórkę")){
                //theView.setColumnWidth("Komórka", 80);
            }
            else{
                //theView.setColumnWidth("Komórka", 0);
            }
            if(per_sel.equals("Wybierz personel")){
                //theView.setColumnWidth("Personel", 80);
            }
            else{
                //theView.setColumnWidth("Personel", 0);
            }
            if(pac_sel.equals("Wybierz pacjenta")){
                //theView.setColumnWidth("Pacjent", 80);
            }
            else{
                //theView.setColumnWidth("Pacjent", 0);
            }
            if(wiz_sel.equals("Wybierz wizytę")){
                //theView.setColumnWidth("Wizyta", true);
            }
            else{
                //theView.setColumnWidth("Wizyta", false);
            }
            //if(theView.getCheckboxKolejka()==true){
                //theView.setColumnWidth("Data", false);
                //theView.setColumnWidth("Godz.", false);                
            //}
            //else{
                //theView.setColumnWidth("Data", 80);
                //theView.setColumnWidth("Godz.", 50);
            //}
            if(theView.getCheckboxPierwsza()==true){
               // theView.setColumnWidth("1", false);               
            }
            else{
               // theView.setColumnWidth("1", 20);
            }
            
            lenght = cb_komorka.size();
            for(i=0;i<lenght;i++)
            {
                if((cb_komorka.get(i).get(1) + " / " + cb_komorka.get(i).get(2)).equals(kom_sel))
                {
                    id_kom = cb_komorka.get(i).get(0);
                }
            } 
            lenght = cb_personel.size();
            for(i=0;i<lenght;i++)
            {
                if(cb_personel.get(i).get(1).equals(per_sel))
                {
                    id_per = cb_personel.get(i).get(0);
                }
            }   
            lenght = cb_pacjenci.size();
            for(i=0;i<lenght;i++)
            {
                if((cb_pacjenci.get(i).get(1) + " " + cb_pacjenci.get(i).get(2) + " " + cb_pacjenci.get(i).get(3)).equals(pac_sel))
                {
                    id_pac = cb_pacjenci.get(i).get(0);
                }
            }            
            lenght = cb_wizyta.size();
            for(i=0;i<lenght;i++)
            {
                if((cb_wizyta.get(i).get(1) + " / " + cb_wizyta.get(i).get(2) + " / " + cb_wizyta.get(i).get(3)).equals(wiz_sel))
                {
                    id_wiz = cb_wizyta.get(i).get(0);
                }
            }           

            if(id_kom!="NULL"){
                item.add("idkomorka");
                data.add(id_kom);
            }
            if(id_per!="NULL"){
                item.add("idpersonel");
                data.add(id_per);
            }
            if(id_pac!="NULL"){
                item.add("idpacjenci");
                data.add(id_pac);
            }            
            if(id_wiz!="NULL"){
                item.add("idwizyta");
                data.add(id_wiz);
            }    
            if(theView.getCheckboxKolejka()){       //pokaz tylko kolejke
                item.add("status");
                data.add("Kolejka");
            }
            /*else{
                item.add("status");
                data.add("Wizyta");
            }*/
            if(theView.getCheckboxPierwsza()){
                item.add("pierwsza");
                data.add("Pierwsza");
            }            
            
            if(theView.getCheckboxDataOd()){
                data_od = theView.getDataOd();
                data_od_dzien = Integer.parseInt(data_od.substring(0, 2));
                data_od_miesiac = Integer.parseInt(data_od.substring(3, 5));
                data_od_rok = Integer.parseInt(data_od.substring(6, 10));
            }
            if(theView.getCheckboxDataDo()){
                data_do = theView.getDataDo();
                data_do_dzien = Integer.parseInt(data_do.substring(0, 2));
                data_do_miesiac = Integer.parseInt(data_do.substring(3, 5));
                data_do_rok = Integer.parseInt(data_do.substring(6, 10));
            }
            if(theView.getCheckboxUrodzonyOd()){
                urodzony_od = theView.getUrodzonyOd();
                urodzony_od_dzien = Integer.parseInt(urodzony_od.substring(0, 2));
                urodzony_od_miesiac = Integer.parseInt(urodzony_od.substring(3, 5));
                urodzony_od_rok = Integer.parseInt(urodzony_od.substring(6, 10));
            }
            if(theView.getCheckboxUrodzonyDo()){
                urodzony_do = theView.getUrodzonyDo();
                urodzony_do_dzien = Integer.parseInt(urodzony_do.substring(0, 2));
                urodzony_do_miesiac = Integer.parseInt(urodzony_do.substring(3, 5));
                urodzony_do_rok = Integer.parseInt(urodzony_do.substring(6, 10));
            }
            
            punkty = 0;
            czas = 0;
            pln = 0;
            ilosc_wizyt = 0;
            
            temp = theDao.ReadAllWhere("kalendarz", theModel.getColumns(), item, data);
            lenght = temp.size();
            //System.out.println(lenght);
            for(i=0;i<lenght;i++){
                
                boolean put;
                
                int data_dzien = 0,data_miesiac = 0,data_rok = 0;
                int urodzony_dzien = 0,urodzony_miesiac = 0,urodzony_rok = 0;
                
                if(data_od != "NULL" || data_do != "NULL"){
                    data_dzien = Integer.parseInt(temp.get(i).get(5).substring(0, 2));
                    data_miesiac = Integer.parseInt(temp.get(i).get(5).substring(3, 5));
                    data_rok = Integer.parseInt(temp.get(i).get(5).substring(6, 10));
                }
                
                if(urodzony_od != "NULL" || urodzony_do != "NULL"){
                    urodzony=theDao.Read("pacjenci", "pesel", Integer.parseInt(temp.get(i).get(3)));
                    urodzony_dzien = Integer.parseInt(urodzony.substring(4, 6));
                    urodzony_miesiac = Integer.parseInt(urodzony.substring(2, 4));
                    urodzony_rok = Integer.parseInt(urodzony.substring(0, 2));                
                }
                
                if(urodzony_rok > Integer.parseInt(today.substring(8, 10)))
                    urodzony_rok += 1900;
                else
                    urodzony_rok += 2000;

                if(data_od != "NULL" && data_do != "NULL"){
                    put = (
                            (data_rok > data_od_rok) ||
                            (data_miesiac > data_od_miesiac && data_rok == data_od_rok) ||
                            (data_dzien >= data_od_dzien && data_miesiac == data_od_miesiac && data_rok == data_od_rok)
                          )
                          &&
                          (
                            (data_rok < data_do_rok) ||
                            (data_miesiac < data_do_miesiac && data_rok == data_do_rok) ||
                            (data_dzien <= data_do_dzien && data_miesiac == data_do_miesiac && data_rok == data_do_rok)
                          );
                }
                else if(data_od!="NULL"){
                    put = (data_rok > data_od_rok) || 
                          (data_miesiac > data_od_miesiac && data_rok == data_od_rok) ||
                          (data_dzien >= data_od_dzien && data_miesiac == data_od_miesiac && data_rok == data_od_rok);
                }
                else if(data_do!="NULL"){
                    put = (data_rok < data_do_rok) ||
                          (data_miesiac < data_do_miesiac && data_rok == data_do_rok) ||
                          (data_dzien <= data_do_dzien && data_miesiac == data_do_miesiac && data_rok == data_do_rok);
                }
                else{
                    put = true;
                }  
                
                              
                if(put == true){

                    if(urodzony_od != "NULL" && urodzony_do != "NULL"){                       
                        put = (
                                (urodzony_rok > urodzony_od_rok) ||
                                (urodzony_miesiac > urodzony_od_miesiac && urodzony_rok == urodzony_od_rok) ||
                                (urodzony_dzien >= urodzony_od_dzien && urodzony_miesiac == urodzony_od_miesiac && urodzony_rok == urodzony_od_rok)
                              )
                              &&
                              (
                                (urodzony_rok < urodzony_do_rok) ||
                                (urodzony_miesiac < urodzony_do_miesiac && urodzony_rok == urodzony_do_rok) ||
                                (urodzony_dzien <= urodzony_do_dzien && urodzony_miesiac == urodzony_do_miesiac && urodzony_rok == urodzony_do_rok)
                              );
                    }
                    else if(urodzony_od!="NULL"){
                        put = (urodzony_rok > urodzony_od_rok) ||
                              (urodzony_miesiac > urodzony_od_miesiac && urodzony_rok == urodzony_od_rok) ||
                              (urodzony_dzien >= urodzony_od_dzien && urodzony_miesiac == urodzony_od_miesiac && urodzony_rok == urodzony_od_rok);
                              
                    }
                    else if(urodzony_do!="NULL"){
                        put = (urodzony_rok < urodzony_do_rok) ||
                              (urodzony_miesiac < urodzony_do_miesiac && urodzony_rok == urodzony_do_rok) ||
                              (urodzony_dzien <= urodzony_do_dzien && urodzony_miesiac == urodzony_do_miesiac && urodzony_rok == urodzony_do_rok);  
                    }    
                }

                if( (theView.getPlec()!="Wybierz płeć") && (put == true) ){
                    if(theView.getPlec().equals(theDao.Read("pacjenci", "plec", Integer.parseInt(temp.get(i).get(3)))) ){}else{
                        put = false;
                    } 
                }
                
                if( (theView.getLok()!="Wybierz lok") && (put == true) ){
                    if(theView.getLok().equals(theDao.Read("pacjenci", "lok", Integer.parseInt(temp.get(i).get(3)))) ){}else{
                        put = false;
                    }
                }

                if(put == true){
                    put_table(temp.get(i)); 
                }
               
                theView.setPunkty(String.valueOf(punkty));
                theView.setCzas(String.valueOf(czas));
                theView.setPLN(String.valueOf(pln));
                theView.setIloscWizyt(String.valueOf(ilosc_wizyt));
            }
        }        
    private void wyczysc(){
        theView.clear_selection(); 
        theView.setPunkty("");
            theView.setCzas("");
            theView.setIloscWizyt("");
            theView.setSzukaj("");
            theView.setPLN("");
            theView.wyczysc_terminarz();
            theView.setSelectedCheckboxDataOd(false);
            theView.setEnabledDataOd(false);
            theView.setSelectedCheckboxDataDo(false);
            theView.setEnabledDataDo(false);
            theView.setItemKomorka("Wybierz komórkę");
            theView.setItemPersonel("Wybierz personel");
            theView.setItemPacjent("Wybierz pacjenta");
            theView.setItemWizyta("Wybierz wizytę");
            theView.setPlec("Wybierz płeć");
            theView.setLok("Wybierz płeć");
            theView.setEnabledPacjent(true);
            theView.setEnabledUrodzonyOd(false);
            theView.setSelectedCheckboxUrodzonyOd(false);
            theView.setEnabledUrodzonyDo(false);
            theView.setSelectedCheckboxUrodzonyDo(false);
            //theView.setSelectedCheckboxKolejka(false);
            //theView.setSelectedCheckboxPierwsza(false);
            theView.setEnabledUsun(false);
            row = -1;
    }
    
    public class CustomComparator implements Comparator<ArrayList<String>> {
    @Override
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            return o1.get(1).compareTo(o2.get(1));
        }
    }
}
