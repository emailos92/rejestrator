/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.grafik;

import erp.ERP_View;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;
import library.AutoCompletion;
import library.CyclingSpinnerListModel;
import library.MyOwnClasses.*;


/**
 *
 * @author Arkita
 */
public class GrafikView extends javax.swing.JFrame {
    
    DefaultTableModel grafik_table_model;
    DefaultTableModel terminarz_table_model;
    TableRowSorter<TableModel> grafik_row_sorter; 
    Font font_bold;
    Font font;
    ERP_View erp_view;
    /**
     * Creates new form HarmonogramView
     */
    public GrafikView(ERP_View erp_view) {
        this.erp_view = erp_view;
        initComponents();   
        font_bold = new Font( "Calibri", Font.BOLD, 14);
        font = new Font( "Calibri", Font.PLAIN, 14);
        table_model_init();
        txtfields_init();    
        spinners_init();
        checkbox_init();
        AutoCompletion.enable(combobox_personel);
        AutoCompletion.enable(combobox_komorka);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);    
    }
    
    public String getKomorka() {
        return combobox_komorka.getSelectedItem().toString();
    }
    public String getPersonel() {
        return combobox_personel.getSelectedItem().toString();
    }
    public String getPunkty() {
        return txtfield_punkty.getText();
    }
    public String getCzas() {
        return txtfield_czas.getText();
    }
    public String getPersonelFromTable(int row){
        return table_grafik.getValueAt(row, 3).toString();
    }
    public String getKomorkaFromTable(int row){
        return table_grafik.getValueAt(row, 4).toString();
    }
    public int getTableSelectedRow(){
        return table_grafik.getSelectedRow();
    }
    public boolean getCheckBoxPon(){
        return checkbox_pon.isSelected();
    }
    public boolean getCheckBoxWto(){
        return checkbox_wto.isSelected();
    }
    public boolean getCheckBoxSro(){
        return checkbox_sro.isSelected();
    }
    public boolean getCheckBoxCzw(){
        return checkbox_czw.isSelected();
    }
    public boolean getCheckBoxPia(){
        return checkbox_pia.isSelected();
    }
    public boolean getCheckBoxSob(){
        return checkbox_sob.isSelected();
    }
    public boolean getCheckBoxNie(){
        return checkbox_nie.isSelected();
    }
    public int getId(int row){
        return Integer.valueOf((String)table_grafik.getValueAt(row, 0));
    }
    public int getIdPer(int row){
        return Integer.valueOf((String)table_grafik.getValueAt(row, 1));
    }
    public int getIdKom(int row){
        return Integer.valueOf((String)table_grafik.getValueAt(row, 2));
    }
    
    public String getPonCo(){
        return pon_co.getValue().toString();
    }    
    public String getPonOd(){
        return pon_od.getValue().toString(); 
    }
    public String getPonDo(){
        return pon_do.getValue().toString();
    }
    public String getPonH(){
        return pon_h.getValue().toString();
    }       
    public String getWtoCo(){
        return wto_co.getValue().toString();
    }    
    public String getWtoOd(){
        return wto_od.getValue().toString();
    }
    public String getWtoDo(){
        return wto_do.getValue().toString();
    }
    public String getWtoH(){
        return wto_h.getValue().toString();
    } 
    public String getSroCo(){
        return sro_co.getValue().toString();
    }    
    public String getSroOd(){
        return sro_od.getValue().toString();
    }
    public String getSroDo(){
        return sro_do.getValue().toString();
    }
    public String getSroH(){
        return sro_h.getValue().toString();
    }
    public String getCzwCo(){
        return czw_co.getValue().toString();
    }    
    public String getCzwOd(){
        return czw_od.getValue().toString();
    }
    public String getCzwDo(){
        return czw_do.getValue().toString();
    }
    public String getCzwH(){
        return czw_h.getValue().toString();
    } 
    public String getPiaCo(){
        return pia_co.getValue().toString();
    }    
    public String getPiaOd(){
        return pia_od.getValue().toString();
    }
    public String getPiaDo(){
        return pia_do.getValue().toString();
    }
    public String getPiaH(){
        return pia_h.getValue().toString();
    }
    public String getSobCo(){
        return sob_co.getValue().toString();
    }    
    public String getSobOd(){
        return sob_od.getValue().toString();
    }
    public String getSobDo(){
        return sob_do.getValue().toString();
    }
    public String getSobH(){
        return sob_h.getValue().toString();
    }
    public String getNieCo(){
        return nie_co.getValue().toString();
    }   
    public String getNieOd(){
        return nie_od.getValue().toString();
    }
    public String getNieDo(){
        return nie_do.getValue().toString();
    }
    public String getNieH(){
        return nie_h.getValue().toString();
    }   

    public boolean searchForPK(String id){
        int i,lenght = table_grafik.getRowCount();
        for(i=0;i<lenght;i++){
            if(table_grafik.getValueAt(i, 0).equals(id)){
                return true;
            }
        }
        return false;
    }
    public void buttonsState(boolean status){
        button_usun.setEnabled(status);
        button_zmien.setEnabled(status);
        button_dodaj.setEnabled(!status);
    }

    public void setKomorka(int index) {
        this.combobox_komorka.setSelectedIndex(index);
    }
    public void setPersonel(int index) {
        this.combobox_personel.setSelectedIndex(index);
    }
    public void setPunkty(String punkty) {
        this.txtfield_punkty.setText(punkty);
    }
    public void setCzas(String czas) {
        this.txtfield_czas.setText(czas);
    }
 
    public void addItemKomorka(String item){
        this.combobox_komorka.addItem(item);
    }
    public void addItemPersonel(String item){
        this.combobox_personel.addItem(item);
    }
    
    public void deleteAllKomorka(){
        this.combobox_komorka.removeAllItems();
        this.combobox_komorka.addItem("Domyślna");
    }
    public void deleteAllPersonel(){
        this.combobox_personel.removeAllItems();
        this.combobox_personel.addItem("Domyślny");
    }   
    
    public void setKomorkaValue(String value){
        this.combobox_komorka.setSelectedItem(value);
    }
    public void setPersonelValue(String value){
        this.combobox_personel.setSelectedItem(value);
    }

    public void setKomorkaComboboxEnabled(boolean enabled){
        this.combobox_komorka.setEnabled(enabled);
    }
    public void setPersonelComboboxEnabled(boolean enabled){
        this.combobox_personel.setEnabled(enabled);
    }
    
    public void setCheckBoxPon(boolean selected){
        checkbox_pon.setSelected(selected);
    }
    public void setCheckBoxWto(boolean selected){
        checkbox_wto.setSelected(selected);
    }
    public void setCheckBoxSro(boolean selected){
        checkbox_sro.setSelected(selected);
    }
    public void setCheckBoxCzw(boolean selected){
        checkbox_czw.setSelected(selected);
    }
    public void setCheckBoxPia(boolean selected){
        checkbox_pia.setSelected(selected);
    }
    public void setCheckBoxSob(boolean selected){
        checkbox_sob.setSelected(selected);
    }
    public void setCheckBoxNie(boolean selected){
        checkbox_nie.setSelected(selected);
    }
    
    public void setPonCo(String data){
        pon_co.getModel().setValue(data);
    }
    public void setPonOd(String data){
        pon_od.getModel().setValue(data);
    }
    public void setPonDo(String data){
        pon_do.getModel().setValue(data);
    }
    public void setPonH(String data){
        pon_h.getModel().setValue(data);
    }
    
    public void setWtoCo(String data){
        wto_co.getModel().setValue(data);
    }
    public void setWtoOd(String data){
        wto_od.getModel().setValue(data);
    }
    public void setWtoDo(String data){
        wto_do.getModel().setValue(data);
    }
    public void setWtoH(String data){
        wto_h.getModel().setValue(data);
    }
    
    public void setSroCo(String data){
        sro_co.getModel().setValue(data);
    }
    public void setSroOd(String data){
        sro_od.getModel().setValue(data);
    }
    public void setSroDo(String data){
        sro_do.getModel().setValue(data);
    }
    public void setSroH(String data){
        sro_h.getModel().setValue(data);
    }    
  
    public void setCzwCo(String data){
        czw_co.getModel().setValue(data);
    }
    public void setCzwOd(String data){
        czw_od.getModel().setValue(data);
    }
    public void setCzwDo(String data){
        czw_do.getModel().setValue(data);
    }
    public void setCzwH(String data){
        czw_h.getModel().setValue(data);
    }    

    public void setPiaCo(String data){
        pia_co.getModel().setValue(data);
    }
    public void setPiaOd(String data){
        pia_od.getModel().setValue(data);
    }
    public void setPiaDo(String data){
        pia_do.getModel().setValue(data);
    }
    public void setPiaH(String data){
        pia_h.getModel().setValue(data);
    }    

    public void setSobCo(String data){
        sob_co.getModel().setValue(data);
    }
    public void setSobOd(String data){
        sob_od.getModel().setValue(data);
    }
    public void setSobDo(String data){
        sob_do.getModel().setValue(data);
    }
    public void setSobH(String data){
        sob_h.getModel().setValue(data);
    }    

    public void setNieCo(String data){
        nie_co.getModel().setValue(data);
    }
    public void setNieOd(String data){
        nie_od.getModel().setValue(data);
    }
    public void setNieDo(String data){
        nie_do.getModel().setValue(data);
    }
    public void setNieH(String data){
        nie_h.getModel().setValue(data);
    }    
    
    public void setEnabledPonCo(boolean enabled){
        pon_co.setEnabled(enabled);   
    }
    public void setEnabledWtoCo(boolean enabled){
        wto_co.setEnabled(enabled);  
    }
    public void setEnabledSroCo(boolean enabled){
        sro_co.setEnabled(enabled);  
    }
    public void setEnabledCzwCo(boolean enabled){
        czw_co.setEnabled(enabled);  
    }
    public void setEnabledPiaCo(boolean enabled){
        pia_co.setEnabled(enabled);  
    }
    public void setEnabledSobCo(boolean enabled){
        sob_co.setEnabled(enabled);  
    }
    public void setEnabledNieCo(boolean enabled){
        nie_co.setEnabled(enabled);  
    }
    
    public void setEnabledPonOd(boolean enabled){
        pon_od.setEnabled(enabled);   
    }
    public void setEnabledWtoOd(boolean enabled){
        wto_od.setEnabled(enabled);   
    }
    public void setEnabledSroOd(boolean enabled){
        sro_od.setEnabled(enabled);   
    }
    public void setEnabledCzwOd(boolean enabled){
        czw_od.setEnabled(enabled);   
    }
    public void setEnabledPiaOd(boolean enabled){
        pia_od.setEnabled(enabled);   
    }
    public void setEnabledSobOd(boolean enabled){
        sob_od.setEnabled(enabled);   
    }
    public void setEnabledNieOd(boolean enabled){
        nie_od.setEnabled(enabled);   
    }

    public void setEnabledPonDo(boolean enabled){
        pon_do.setEnabled(enabled);   
    }
    public void setEnabledWtoDo(boolean enabled){
        wto_do.setEnabled(enabled);   
    }
    public void setEnabledSroDo(boolean enabled){
        sro_do.setEnabled(enabled);   
    }
    public void setEnabledCzwDo(boolean enabled){
        czw_do.setEnabled(enabled);   
    }
    public void setEnabledPiaDo(boolean enabled){
        pia_do.setEnabled(enabled);   
    }
    public void setEnabledSobDo(boolean enabled){
        sob_do.setEnabled(enabled);   
    }
    public void setEnabledNieDo(boolean enabled){
        nie_do.setEnabled(enabled);   
    }
   
    public void setEnabledPonH(boolean enabled){
        pon_h.setEnabled(enabled);   
    }
    public void setEnabledWtoH(boolean enabled){
        wto_h.setEnabled(enabled);   
    }
    public void setEnabledSroH(boolean enabled){
        sro_h.setEnabled(enabled);   
    }
    public void setEnabledCzwH(boolean enabled){
        czw_h.setEnabled(enabled);   
    }
    public void setEnabledPiaH(boolean enabled){
        pia_h.setEnabled(enabled);   
    }
    public void setEnabledSobH(boolean enabled){
        sob_h.setEnabled(enabled);   
    }
    public void setEnabledNieH(boolean enabled){
        nie_h.setEnabled(enabled);   
    }
      

    public void setEnabledCheckBoxPon(boolean enabled){
        checkbox_pon.setEnabled(enabled);
    }
    public void setEnabledCheckBoxWto(boolean enabled){
        checkbox_wto.setEnabled(enabled);
    }
    public void setEnabledCheckBoxSro(boolean enabled){
        checkbox_sro.setEnabled(enabled);
    }
    public void setEnabledCheckBoxCzw(boolean enabled){
        checkbox_czw.setEnabled(enabled);
    }
    public void setEnabledCheckBoxPia(boolean enabled){
        checkbox_pia.setEnabled(enabled);
    }
    public void setEnabledCheckBoxSob(boolean enabled){
        checkbox_sob.setEnabled(enabled);
    }
    public void setEnabledCheckBoxNie(boolean enabled){
        checkbox_nie.setEnabled(enabled);
    }
    
    void dodaj(ActionListener listenForDodajPacjenta){
        button_dodaj.addActionListener(listenForDodajPacjenta);
    }  
    void zmien(ActionListener listenForZmienDane){
        button_zmien.addActionListener(listenForZmienDane);
    }        
    void usun(ActionListener listenForUsunPacjenta){
        button_usun.addActionListener(listenForUsunPacjenta);
    }   
    void wyczysc(ActionListener listenForWyczyscPola){
        button_wyczysc.addActionListener(listenForWyczyscPola);
    }
    void zaznaczono_wiersz(ListSelectionListener listenForZaznaczonoWiersz){
        table_grafik.getSelectionModel().addListSelectionListener(listenForZaznaczonoWiersz);
    }
    void clear_selection(){
        this.table_grafik.clearSelection();
    }
    void checkbox_pon_action(ActionListener listen){
        checkbox_pon.addActionListener(listen);
    }
    void checkbox_wto_action(ActionListener listen){
        checkbox_wto.addActionListener(listen);
    }
    void checkbox_sro_action(ActionListener listen){
        checkbox_sro.addActionListener(listen);
    }
    void checkbox_czw_action(ActionListener listen){
        checkbox_czw.addActionListener(listen);
    }
    void checkbox_pia_action(ActionListener listen){
        checkbox_pia.addActionListener(listen);
    }
    void checkbox_sob_action(ActionListener listen){
        checkbox_sob.addActionListener(listen);
    }
    void checkbox_nie_action(ActionListener listen){
        checkbox_nie.addActionListener(listen);
    }
    void pon_co_action(ChangeListener listen){
        pon_co.addChangeListener(listen);
    }
    void wto_co_action(ChangeListener listen){
        wto_co.addChangeListener(listen);
    }
    void sro_co_action(ChangeListener listen){
        sro_co.addChangeListener(listen);
    }
    void czw_co_action(ChangeListener listen){
        czw_co.addChangeListener(listen);
    }
    void pia_co_action(ChangeListener listen){
        pia_co.addChangeListener(listen);
    }
    void sob_co_action(ChangeListener listen){
        sob_co.addChangeListener(listen);
    }
    void nie_co_action(ChangeListener listen){
        nie_co.addChangeListener(listen);
    }
       
    public void dodaj_wiersz(String id, String idpersonel, String idkomorka, String personel, String komorka, String czas, String punkty){       
        grafik_table_model.addRow(new String[]{id, idpersonel, idkomorka, personel, komorka, czas, punkty});
    }    
    public void wyczysc_tabele(){
       grafik_table_model.setRowCount(0);
    }    
    private void table_model_init(){
        grafik_table_model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        
        grafik_table_model.addColumn("Id");
        grafik_table_model.addColumn("Idpersonel");
        grafik_table_model.addColumn("Idkomorka");
        
        grafik_table_model.addColumn("Personel");
        grafik_table_model.addColumn("Komórka");
        grafik_table_model.addColumn("Punkty");
        grafik_table_model.addColumn("Czas");                    
        table_grafik.setModel(grafik_table_model);
        
        table_grafik.getColumn("Id").setWidth(0);
        table_grafik.getColumn("Id").setMaxWidth(0);
        table_grafik.getColumn("Id").setMinWidth(0);
        table_grafik.getColumn("Id").setPreferredWidth(0);
        table_grafik.getColumn("Idpersonel").setWidth(0);
        table_grafik.getColumn("Idpersonel").setMaxWidth(0);
        table_grafik.getColumn("Idpersonel").setMinWidth(0);
        table_grafik.getColumn("Idpersonel").setPreferredWidth(0);
        table_grafik.getColumn("Idkomorka").setWidth(0);
        table_grafik.getColumn("Idkomorka").setMaxWidth(0);
        table_grafik.getColumn("Idkomorka").setMinWidth(0);
        table_grafik.getColumn("Idkomorka").setPreferredWidth(0);
        table_grafik.getColumn("Czas").setMaxWidth(60);
        table_grafik.getColumn("Czas").setMinWidth(60);
        table_grafik.getColumn("Punkty").setMaxWidth(60);
        table_grafik.getColumn("Punkty").setMinWidth(60);
        table_grafik.getColumn("Personel").setMaxWidth(200);
        table_grafik.getColumn("Personel").setMinWidth(200);
        
        //table_grafik.getColumnModel().setColumnMargin(10);
        table_grafik.setIntercellSpacing(new Dimension(10,10));
        
        table_grafik.setAutoCreateRowSorter(true); 
        grafik_row_sorter = new TableRowSorter<>(table_grafik.getModel());
        table_grafik.setRowSorter(grafik_row_sorter);
        //table_pacjenci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
        table_grafik.setRowSelectionAllowed(true);
        table_grafik.setDefaultRenderer(String.class, new VisitorRenderer());
        table_grafik.setFont(font);
        table_grafik.getTableHeader().setFont(font_bold);
        table_grafik.setRowHeight(25);
    }      
    private void txtfields_init(){
        ((AbstractDocument) txtfield_czas.getDocument()).setDocumentFilter(new CzasDocumentFilter());
        ((AbstractDocument) txtfield_punkty.getDocument()).setDocumentFilter(new PunktyDocumentFilter()); 
    }    
    
    public void set_spinner_model(String[] list, int i)
    {
             if(i==0){pon_do.setModel(new CyclingSpinnerListModel(list));}
        else if(i==1){wto_do.setModel(new CyclingSpinnerListModel(list));}
        else if(i==2){sro_do.setModel(new CyclingSpinnerListModel(list));}
        else if(i==3){czw_do.setModel(new CyclingSpinnerListModel(list));}
        else if(i==4){pia_do.setModel(new CyclingSpinnerListModel(list));}
        else if(i==5){sob_do.setModel(new CyclingSpinnerListModel(list));}
        else if(i==6){nie_do.setModel(new CyclingSpinnerListModel(list));}
    }

    private void spinners_init(){
        String[] hours,co,h;
        hours = new String[] { "",
                               "00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30",
                               "04:00","04:30","05:00","05:30","06:00","06:30","07:00","07:30",
                               "08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30",
                               "12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30",
                               "16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30",
                               "20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"
                             };

        co = new String[] {"",
                            "00:05","00:10","00:15","00:20",
                            "00:25","00:30","00:35","00:40",
                            "00:45","00:50","00:55","01:00",
                            "01:05","01:10","01:15","01:20",
                            "01:25","01:30"
        };  
        h = new String[] {"",
                            "1","2","3","4",
                            "5","6","7","8",
                            "9","10","11","12",
                            "13","14","15","16",
                            "17","18","19","20"};

        pon_co.setModel(new CyclingSpinnerListModel(co));
        pon_od.setModel(new CyclingSpinnerListModel(hours));
        pon_do.setModel(new CyclingSpinnerListModel(hours));
        pon_h.setModel(new CyclingSpinnerListModel(h));
        
        wto_co.setModel(new CyclingSpinnerListModel(co));
        wto_od.setModel(new CyclingSpinnerListModel(hours));
        wto_do.setModel(new CyclingSpinnerListModel(hours));
        wto_h.setModel(new CyclingSpinnerListModel(h));        
        
        sro_co.setModel(new CyclingSpinnerListModel(co));
        sro_od.setModel(new CyclingSpinnerListModel(hours));
        sro_do.setModel(new CyclingSpinnerListModel(hours));
        sro_h.setModel(new CyclingSpinnerListModel(h));

        czw_co.setModel(new CyclingSpinnerListModel(co));
        czw_od.setModel(new CyclingSpinnerListModel(hours));
        czw_do.setModel(new CyclingSpinnerListModel(hours));
        czw_h.setModel(new CyclingSpinnerListModel(h));

        pia_co.setModel(new CyclingSpinnerListModel(co));
        pia_od.setModel(new CyclingSpinnerListModel(hours));
        pia_do.setModel(new CyclingSpinnerListModel(hours));
        pia_h.setModel(new CyclingSpinnerListModel(h));

        sob_co.setModel(new CyclingSpinnerListModel(co));
        sob_od.setModel(new CyclingSpinnerListModel(hours));
        sob_do.setModel(new CyclingSpinnerListModel(hours));
        sob_h.setModel(new CyclingSpinnerListModel(h));
        
        nie_co.setModel(new CyclingSpinnerListModel(co));
        nie_od.setModel(new CyclingSpinnerListModel(hours));
        nie_do.setModel(new CyclingSpinnerListModel(hours));
        nie_h.setModel(new CyclingSpinnerListModel(h));

    }   
    
    private void checkbox_init(){
        setCheckBoxPon(false);
        setCheckBoxWto(false);
        setCheckBoxSro(false);
        setCheckBoxCzw(false);
        setCheckBoxPia(false);
        setCheckBoxSob(false);
        setCheckBoxNie(false);
        
        setEnabledPonCo(false);
        setEnabledPonOd(false);
        setEnabledPonDo(false);
        setEnabledPonH(false);
        
        setEnabledWtoCo(false);
        setEnabledWtoOd(false);
        setEnabledWtoDo(false);
        setEnabledWtoH(false);
        
        setEnabledSroCo(false);
        setEnabledSroOd(false);
        setEnabledSroDo(false);
        setEnabledSroH(false);
     
        setEnabledCzwCo(false);
        setEnabledCzwOd(false);
        setEnabledCzwDo(false);
        setEnabledCzwH(false);
        
        setEnabledPiaCo(false);
        setEnabledPiaOd(false);
        setEnabledPiaDo(false);
        setEnabledPiaH(false);

        setEnabledSobCo(false);
        setEnabledSobOd(false);
        setEnabledSobDo(false);
        setEnabledSobH(false);

        setEnabledNieCo(false);
        setEnabledNieOd(false);
        setEnabledNieDo(false);
        setEnabledNieH(false);
    }

    
    
    @SuppressWarnings("deprecation")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_grafik = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        label_personel = new javax.swing.JLabel();
        combobox_komorka = new javax.swing.JComboBox();
        label_komorka = new javax.swing.JLabel();
        combobox_personel = new javax.swing.JComboBox();
        label_punkty = new javax.swing.JLabel();
        txtfield_punkty = new javax.swing.JTextField();
        label_czas = new javax.swing.JLabel();
        txtfield_czas = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        button_wyczysc = new javax.swing.JButton();
        button_zmien = new javax.swing.JButton();
        button_usun = new javax.swing.JButton();
        button_dodaj = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        nie_co = new javax.swing.JSpinner();
        sob_co = new javax.swing.JSpinner();
        pia_co = new javax.swing.JSpinner();
        czw_co = new javax.swing.JSpinner();
        sro_co = new javax.swing.JSpinner();
        wto_co = new javax.swing.JSpinner();
        pon_co = new javax.swing.JSpinner();
        label_co = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        label_nie = new javax.swing.JLabel();
        checkbox_nie = new javax.swing.JCheckBox();
        checkbox_sob = new javax.swing.JCheckBox();
        checkbox_pia = new javax.swing.JCheckBox();
        checkbox_czw = new javax.swing.JCheckBox();
        checkbox_sro = new javax.swing.JCheckBox();
        checkbox_wto = new javax.swing.JCheckBox();
        checkbox_pon = new javax.swing.JCheckBox();
        label_sob = new javax.swing.JLabel();
        label_pia = new javax.swing.JLabel();
        label_czw = new javax.swing.JLabel();
        label_sro = new javax.swing.JLabel();
        label_wto = new javax.swing.JLabel();
        label_pon = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        nie_od = new javax.swing.JSpinner();
        sob_od = new javax.swing.JSpinner();
        pia_od = new javax.swing.JSpinner();
        czw_od = new javax.swing.JSpinner();
        sro_od = new javax.swing.JSpinner();
        wto_od = new javax.swing.JSpinner();
        pon_od = new javax.swing.JSpinner();
        label_od = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        nie_do = new javax.swing.JSpinner();
        sob_do = new javax.swing.JSpinner();
        pia_do = new javax.swing.JSpinner();
        czw_do = new javax.swing.JSpinner();
        sro_do = new javax.swing.JSpinner();
        wto_do = new javax.swing.JSpinner();
        pon_do = new javax.swing.JSpinner();
        label_do = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        label_nfz = new javax.swing.JLabel();
        nie_h = new javax.swing.JSpinner();
        sob_h = new javax.swing.JSpinner();
        pia_h = new javax.swing.JSpinner();
        czw_h = new javax.swing.JSpinner();
        sro_h = new javax.swing.JSpinner();
        wto_h = new javax.swing.JSpinner();
        pon_h = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        table_grafik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_grafik.setSelectionBackground(new java.awt.Color(204, 204, 255));
        table_grafik.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(table_grafik);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        label_personel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_personel.setText("Personel:");

        combobox_komorka.setEditable(true);
        combobox_komorka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        label_komorka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_komorka.setText("Komórka:");

        combobox_personel.setEditable(true);
        combobox_personel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        label_punkty.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_punkty.setText("Punkty:");

        txtfield_punkty.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_czas.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_czas.setText("Czas:");

        txtfield_czas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_komorka)
                    .addComponent(label_personel)
                    .addComponent(label_punkty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combobox_komorka, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtfield_punkty, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(label_czas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfield_czas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(combobox_personel, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combobox_komorka, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_komorka))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_personel)
                    .addComponent(combobox_personel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_punkty)
                    .addComponent(txtfield_punkty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_czas)
                    .addComponent(txtfield_czas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        button_wyczysc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_wyczysc.setText("Wyczyść");

        button_zmien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_zmien.setText("Zmień");

        button_usun.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_usun.setText("Usuń");

        button_dodaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_dodaj.setText("Dodaj");
        button_dodaj.setPreferredSize(new java.awt.Dimension(100, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_zmien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_wyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_wyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(button_zmien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        label_co.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_co.setText("Co:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_co)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nie_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sob_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pia_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(czw_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sro_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wto_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pon_co, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_co)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pon_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wto_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sro_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(czw_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pia_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sob_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nie_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        label_nie.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_nie.setText("Nie:");

        label_sob.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_sob.setText("Sob:");

        label_pia.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_pia.setText("Pią:");

        label_czw.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_czw.setText("Czw:");

        label_sro.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_sro.setText("Śro:");

        label_wto.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_wto.setText("Wto:");

        label_pon.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_pon.setText("Pon:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_nie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_nie))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_pon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_pon))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_sob)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_sob))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_pia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_pia))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_czw)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_czw))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_sro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_sro))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_wto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_wto))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_pon)
                    .addComponent(label_pon))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_wto)
                    .addComponent(label_wto))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_sro)
                    .addComponent(label_sro))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_czw)
                    .addComponent(label_czw))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_pia)
                    .addComponent(label_pia))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_sob)
                    .addComponent(label_sob))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_nie)
                    .addComponent(label_nie))
                .addGap(11, 11, 11))
        );

        label_od.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_od.setText("Od:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_od)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nie_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sob_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pia_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(czw_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sro_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wto_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pon_od, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_od)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pon_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wto_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sro_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(czw_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pia_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sob_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nie_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        label_do.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_do.setText("Do:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_do)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nie_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sob_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pia_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(czw_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sro_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wto_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pon_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_do)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pon_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wto_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sro_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(czw_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pia_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sob_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nie_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        label_nfz.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_nfz.setText("NFZ:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_nfz)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nie_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sob_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pia_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(czw_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sro_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wto_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pon_h, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_nfz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pon_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wto_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sro_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(czw_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pia_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sob_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nie_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        erp_view.setEnabled(true);        // TODO add your handling code here:
        erp_view.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */


    //private library.AutoCompleteJComboBox combobox_komorka;
    //private library.AutoCompleteJComboBox combobox_personel;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_dodaj;
    private javax.swing.JButton button_usun;
    private javax.swing.JButton button_wyczysc;
    private javax.swing.JButton button_zmien;
    private javax.swing.JCheckBox checkbox_czw;
    private javax.swing.JCheckBox checkbox_nie;
    private javax.swing.JCheckBox checkbox_pia;
    private javax.swing.JCheckBox checkbox_pon;
    private javax.swing.JCheckBox checkbox_sob;
    private javax.swing.JCheckBox checkbox_sro;
    private javax.swing.JCheckBox checkbox_wto;
    private javax.swing.JComboBox combobox_komorka;
    private javax.swing.JComboBox combobox_personel;
    private javax.swing.JSpinner czw_co;
    private javax.swing.JSpinner czw_do;
    private javax.swing.JSpinner czw_h;
    private javax.swing.JSpinner czw_od;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_co;
    private javax.swing.JLabel label_czas;
    private javax.swing.JLabel label_czw;
    private javax.swing.JLabel label_do;
    private javax.swing.JLabel label_komorka;
    private javax.swing.JLabel label_nfz;
    private javax.swing.JLabel label_nie;
    private javax.swing.JLabel label_od;
    private javax.swing.JLabel label_personel;
    private javax.swing.JLabel label_pia;
    private javax.swing.JLabel label_pon;
    private javax.swing.JLabel label_punkty;
    private javax.swing.JLabel label_sob;
    private javax.swing.JLabel label_sro;
    private javax.swing.JLabel label_wto;
    private javax.swing.JSpinner nie_co;
    private javax.swing.JSpinner nie_do;
    private javax.swing.JSpinner nie_h;
    private javax.swing.JSpinner nie_od;
    private javax.swing.JSpinner pia_co;
    private javax.swing.JSpinner pia_do;
    private javax.swing.JSpinner pia_h;
    private javax.swing.JSpinner pia_od;
    private javax.swing.JSpinner pon_co;
    private javax.swing.JSpinner pon_do;
    private javax.swing.JSpinner pon_h;
    private javax.swing.JSpinner pon_od;
    private javax.swing.JSpinner sob_co;
    private javax.swing.JSpinner sob_do;
    private javax.swing.JSpinner sob_h;
    private javax.swing.JSpinner sob_od;
    private javax.swing.JSpinner sro_co;
    private javax.swing.JSpinner sro_do;
    private javax.swing.JSpinner sro_h;
    private javax.swing.JSpinner sro_od;
    private javax.swing.JTable table_grafik;
    private javax.swing.JTextField txtfield_czas;
    private javax.swing.JTextField txtfield_punkty;
    private javax.swing.JSpinner wto_co;
    private javax.swing.JSpinner wto_do;
    private javax.swing.JSpinner wto_h;
    private javax.swing.JSpinner wto_od;
    // End of variables declaration//GEN-END:variables
}
