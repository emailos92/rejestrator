/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.kalendarz;

import erp.ERP_View;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.SortOrder;
import javax.swing.Timer;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import library.AutoCompletion;
import library.MyOwnClasses.CustomRenderer;
import library.MyOwnClasses.InfoRenderer;
import library.MyOwnClasses.VisitorRenderer;

/**
 *
 * @author Arkita
 */
public class KalendarzView extends javax.swing.JFrame {
    
    DefaultTableModel control_table_model;
    DefaultTableModel pon_table_model;
    DefaultTableModel wto_table_model;
    DefaultTableModel sro_table_model;
    DefaultTableModel czw_table_model;
    DefaultTableModel pia_table_model;
    DefaultTableModel sob_table_model;
    DefaultTableModel nie_table_model;
    DefaultTableModel info_table_model;
    DefaultTableModel kolejka_table_model;
    TableRowSorter<TableModel> sorter_pon;
    TableRowSorter<TableModel> sorter_wto;
    TableRowSorter<TableModel> sorter_sro;
    TableRowSorter<TableModel> sorter_czw;
    TableRowSorter<TableModel> sorter_pia;
    TableRowSorter<TableModel> sorter_sob;
    TableRowSorter<TableModel> sorter_nie;
    ClockLabel clock;
    
    DefaultTableModel dtm;
    TableRowSorter<TableModel> kalendarz_row_sorter;
    DateFormat sdf, day_sdf, month_sdf, year_sdf;
    Font font_bold;
    Font font;
    Calendar cal;
    DefaultTableCellRenderer centerRenderer;
    ClockLabel zegar;
    ERP_View erp_view;
            
    public KalendarzView(ERP_View erp_view) {
        this.erp_view = erp_view;
        initComponents();

        font_bold = new Font( "Calibri", Font.BOLD, 14);
        font = new Font( "Calibri", Font.PLAIN, 14);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        day_sdf = new SimpleDateFormat("dd");
        month_sdf = new SimpleDateFormat("MM");
        year_sdf = new SimpleDateFormat("yyyy");
        cal = Calendar.getInstance();
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        
        combobox_init();
        combobox_godzina_init();
        table_control_init();
        table_kalendarz_init();
        table_kolejka_init();
        sorter_init();
        
        zegar = new ClockLabel();
        //this.panel_clock.add(clock, BorderLayout.NORTH);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public ArrayList<String> getDate() {

        ArrayList<String> daty = new ArrayList<>();
        
        Date data = calendar.getDate();
        //data
        daty.add(sdf.format(data));
        cal.setTime(data); 
        cal.set(cal.DAY_OF_WEEK, Calendar.MONDAY);
            daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.TUESDAY);
            daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.WEDNESDAY);
            daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.THURSDAY);
            daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.FRIDAY);
            daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.SATURDAY);
            daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.SUNDAY);
            daty.add(sdf.format(cal.getTime()));   
        cal.setTime(data); 
        //last_day_of_month
        daty.add(String.valueOf(cal.getActualMaximum(cal.DAY_OF_MONTH)));
        //month
        daty.add(month_sdf.format(cal.getTime()));
        //year
        daty.add(year_sdf.format(cal.getTime()));
        
        data = calendar.getDate();
        cal.setTime(data);

        daty.add( String.valueOf(cal.get(Calendar.DAY_OF_WEEK)) );
        //System.out.print(cal.get(Calendar.DAY_OF_WEEK));
        return daty;
    }
    public void setDate(String d){
        Date data = null;
        if(d.equals("today"))
        {
            Calendar cale;
            cale = Calendar.getInstance();
            data = cale.getTime();
        }
        else
        {
            try
            {
                data = sdf.parse(d);
            } 
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        this.calendar.setDate(data);
    }
    
    public boolean getCheckBoxPierwsza(){
        return checkbox_pierwsza.isSelected();
    }    
    public boolean getCheckBoxKolejka(){
        return checkbox_kolejka.isSelected();
    }
    public void setCheckBoxPierwsza(boolean selected){
        checkbox_pierwsza.setSelected(selected);
    }
    public void setCheckBoxKolejka(boolean selected){
        checkbox_kolejka.setSelected(selected);
    }
    
    public void setEnabledComboBoxGodzina(boolean enabled){
        combobox_godzina.setEnabled(enabled);
    }
    public void setEnabledCheckBoxKolejka(boolean enabled){
        checkbox_kolejka.setEnabled(enabled);
    }
    public void setEnabledCheckBoxPierwsza(boolean enabled){
        checkbox_pierwsza.setEnabled(enabled);
    }  
    
    public void setGodzina(String a){
        combobox_godzina.setSelectedItem(a);
    } 
    public String getGodzina(){
        return combobox_godzina.getSelectedItem().toString();
    }
    public void setComboboxModel(ArrayList <String> hours){
        int i,lenght;
        deleteAllGodzina();
        lenght=hours.size();
        for(i=0;i<lenght;i++)
        {
            addItemGodzina(hours.get(i));
        }
    }
    
    public void setPersonelComboboxEnabled(boolean enabled){
        this.combobox_personel.setEnabled(enabled);
    }
    public void setKomorkaComboboxEnabled(boolean enabled){
        this.combobox_komorka.setEnabled(enabled);
    }
    public void setPacjentComboboxEnabled(boolean enabled){
        this.combobox_pacjent.setEnabled(enabled);
    }
    public void setWizytaComboboxEnabled(boolean enabled){
        this.combobox_wizyta.setEnabled(enabled);
    }  
    public void setDodajButtonEnabled(boolean enabled){
        this.button_dodaj.setEnabled(enabled);
    }
    public void setUsunButtonEnabled(boolean enabled){
        this.button_usun.setEnabled(enabled);
    }
    public void setZmienButtonEnabled(boolean enabled){
        this.button_zmien.setEnabled(enabled);
    }
    public void setWyczyscButtonEnabled(boolean enabled){
        this.button_wyczysc.setEnabled(enabled);
    }
    public void setControlTable(String data,int row){
        control_table_model.setValueAt(data, row, 1);
        //table_control.repaint();
    }
    
    public void setRowHeightPon(int row, int height){
        table_pon.setRowHeight(row, height);
    }

    public void deselectTable(int tab){
        if(tab != 1)
        {
            table_pon.clearSelection();
        }
        if(tab != 2)
        {
            table_wto.clearSelection();
        }
        if(tab != 3)
        {
            table_sro.clearSelection();
        }
        if(tab != 4)
        {
            table_czw.clearSelection();
        }
        if(tab != 5)
        {
            table_pia.clearSelection();
        }   
        if(tab != 6)
        {
            table_sob.clearSelection();
        }
        if(tab != 7)
        {
            table_nie.clearSelection();
        }
        if(tab != 8)
        {
            table_kolejka.clearSelection();
        }
    }
    
    public boolean getEnabledComboboxWizyta(){
        return combobox_wizyta.isEnabled();
    }
    
    public String getPersonel(){
        return combobox_personel.getSelectedItem().toString();
    }
    public String getKomorka(){
        return combobox_komorka.getSelectedItem().toString();   
    }
    public String getPacjent(){
        return combobox_pacjent.getSelectedItem().toString();
    }
    public String getWizyta(){
        return combobox_wizyta.getSelectedItem().toString();
    }
    public String getUwagi(){
        return txtarea_uwagi.getText();
    }
    public void setUwagi(String data){
        txtarea_uwagi.setText(data);
    }
    
    public int getPonSelectedRow(){
        return table_pon.getSelectedRow();
    }
    public int getWtoSelectedRow(){
        return table_wto.getSelectedRow();
    } 
    public int getSroSelectedRow(){
        return table_sro.getSelectedRow();
    } 
    public int getCzwSelectedRow(){
        return table_czw.getSelectedRow();
    } 
    public int getPiaSelectedRow(){
        return table_pia.getSelectedRow();
    } 
    public int getSobSelectedRow(){
        return table_sob.getSelectedRow();
    } 
    public int getNieSelectedRow(){
        return table_nie.getSelectedRow();
    } 
    public int getKolSelectedRow(){
        return table_kolejka.getSelectedRow();
    } 
    public boolean getComboPersIsEnabled(){
        return combobox_personel.isEnabled();
    }
    public boolean getComboPacjIsEnabled(){
        return combobox_pacjent.isEnabled();
    }
    public boolean getComboWizyIsEnabled(){
        return combobox_wizyta.isEnabled();
    }
    public boolean getComboKomoIsEnabled(){
        return combobox_komorka.isEnabled();
    }
        
    public String getId(int tab, int row){
        String res="";
        if(tab == 1)
        {
            res = (String) table_pon.getValueAt(row, 0);
        }
        if(tab == 2)
        {
            res = (String) table_wto.getValueAt(row, 0);
        }
        if(tab == 3)
        {
            res = (String) table_sro.getValueAt(row, 0);
        }
        if(tab == 4)
        {
            res = (String) table_czw.getValueAt(row, 0);
        }
        if(tab == 5)
        {
            res = (String) table_pia.getValueAt(row, 0);
        }
        if(tab == 6)
        {
            res = (String) table_sob.getValueAt(row, 0);
        }
        if(tab == 7)
        {
            res = (String) table_nie.getValueAt(row, 0);
        }
        if(tab == 8)
        {
            res = (String) table_kolejka.getValueAt(row, 0);
        }
        return res;
    }    
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void addItemKomorka(String item){
        this.combobox_komorka.addItem(item);
    }
    public void addItemPersonel(String item){
        this.combobox_personel.addItem(item);
    }
    public void addItemWizyta(String item){
        this.combobox_wizyta.addItem(item);
    }
    public void addItemPacjent(String item){
        this.combobox_pacjent.addItem(item);
    }
    public void addItemGodzina(String item){
        combobox_godzina.addItem(item);
    }
    public void deleteAllKomorka(){
        this.combobox_komorka.removeAllItems();
        this.combobox_komorka.addItem("Wybierz komórkę");
        this.combobox_komorka.setSelectedIndex(0);
    }
    public void deleteAllPersonel(){
        this.combobox_personel.removeAllItems();
        this.combobox_personel.addItem("Wybierz personel");
        this.combobox_personel.setSelectedIndex(0);
    }
    public void deleteAllWizyta(){
        this.combobox_wizyta.removeAllItems();
        this.combobox_wizyta.addItem("Wybierz wizytę");
        this.combobox_wizyta.setSelectedIndex(0);
    }
    public void deleteAllPacjent(){
        this.combobox_pacjent.removeAllItems();
        this.combobox_pacjent.addItem("Wybierz pacjenta");
        this.combobox_pacjent.setSelectedIndex(0);
    }
    public void deleteAllGodzina(){
        this.combobox_godzina.removeAllItems();
        this.combobox_godzina.addItem("- - -");
        this.combobox_godzina.setSelectedIndex(0);
    }
    public void setKomorkaValue(String value){
        this.combobox_komorka.setSelectedItem(value);
    }
    public void setPersonelValue(String value){
        this.combobox_personel.setSelectedItem(value);
    }
    public void setWizytaValue(String value){
        this.combobox_wizyta.setSelectedItem(value);
    }
    public void setPacjentValue(String value){
        this.combobox_pacjent.setSelectedItem(value);
    }
    
    //LISTENERY
    void checkbox_pierwsza_action(ActionListener listen){
        checkbox_pierwsza.addActionListener(listen);
    }
    void checkbox_kolejka_action(ActionListener listen){
        checkbox_kolejka.addActionListener(listen);
    }
    void calendar(PropertyChangeListener listen){
        calendar.addPropertyChangeListener(listen);
    }
    void combobox_personel_action(ActionListener listen){
        combobox_personel.addActionListener(listen);
    }
    void combobox_komorka_action(ActionListener listen){
        combobox_komorka.addActionListener(listen);
    }
    void combobox_pacjent_action(ActionListener listen){
        combobox_pacjent.addActionListener(listen);
    }
    void combobox_wizyta_action(ActionListener listen){
        combobox_wizyta.addActionListener(listen);
    }
    void combobox_godzina_action(ActionListener listen){
        combobox_godzina.addActionListener(listen);
    }
    
    void dodaj_wizyte(ActionListener listen){
        button_dodaj.addActionListener(listen);
    }  
    void zmien_wizyte(ActionListener listen){
        button_zmien.addActionListener(listen);
    }        
    void usun_wizyte(ActionListener listen){
        button_usun.addActionListener(listen);
    }   
    void wyczysc_pola(ActionListener listen){
        button_wyczysc.addActionListener(listen);
    }   
    void pon_select(ListSelectionListener listen){
        table_pon.getSelectionModel().addListSelectionListener(listen);
    }
    void wto_select(ListSelectionListener listen){
        table_wto.getSelectionModel().addListSelectionListener(listen);
    }
    void sro_select(ListSelectionListener listen){
        table_sro.getSelectionModel().addListSelectionListener(listen);
    }
    void czw_select(ListSelectionListener listen){
        table_czw.getSelectionModel().addListSelectionListener(listen);
    }
    void pia_select(ListSelectionListener listen){
        table_pia.getSelectionModel().addListSelectionListener(listen);
    }
    void sob_select(ListSelectionListener listen){
        table_sob.getSelectionModel().addListSelectionListener(listen);
    }
    void nie_select(ListSelectionListener listen){
        table_nie.getSelectionModel().addListSelectionListener(listen);
    }
    void kol_select(ListSelectionListener listen){
        table_kolejka.getSelectionModel().addListSelectionListener(listen);
    }
    
    public void setTableInfoColor(int[] color)
    {
        int i,j;
        
        for(j=0;j<7;j++)
        {
            for(i=0;i<4;i++)
            {
                table_info.getColumnModel().getColumn(j).setCellRenderer(new InfoRenderer(i,color[j]));
            }
        }
    }
    
    public void setTableControlColor(int [] color){    //0-white, 1-green, 2-red
        for(int i=0;i<18;i++)
        {
            for(int j=0;j<2;j++)
                table_control.getColumnModel().getColumn(j).setCellRenderer(new CustomRenderer(i, color));
        }
        table_control.repaint();
    }
    
    
    public void dodaj_wiersz_pon(String id, String godz){       
        pon_table_model.addRow(new String[]{id,godz});
    }
    public void dodaj_wiersz_wto(String id, String godz){       
        wto_table_model.addRow(new String[]{id,godz});
    }
    public void dodaj_wiersz_sro(String id, String godz){       
        sro_table_model.addRow(new String[]{id,godz});
    }
    public void dodaj_wiersz_czw(String id, String godz){       
        czw_table_model.addRow(new String[]{id,godz});
    }
    public void dodaj_wiersz_pia(String id, String godz){       
        pia_table_model.addRow(new String[]{id,godz});
    }
    public void dodaj_wiersz_sob(String id, String godz){       
        sob_table_model.addRow(new String[]{id,godz});
    }
    public void dodaj_wiersz_nie(String id, String godz){       
        nie_table_model.addRow(new String[]{id,godz});
    }
    public void set_info(ArrayList<String> daty, ArrayList<String> czas, ArrayList<String> nfz){       
        info_table_model.setValueAt("  "+daty.get(1), 1, 0);
        info_table_model.setValueAt("  "+daty.get(2), 1, 1);
        info_table_model.setValueAt("  "+daty.get(3), 1, 2);
        info_table_model.setValueAt("  "+daty.get(4), 1, 3);
        info_table_model.setValueAt("  "+daty.get(5), 1, 4);
        info_table_model.setValueAt("  "+daty.get(6), 1, 5);
        info_table_model.setValueAt("  "+daty.get(7), 1, 6);
        info_table_model.setValueAt("  "+czas.get(0), 2, 0);
        info_table_model.setValueAt("  "+czas.get(1), 2, 1);
        info_table_model.setValueAt("  "+czas.get(2), 2, 2);
        info_table_model.setValueAt("  "+czas.get(3), 2, 3);
        info_table_model.setValueAt("  "+czas.get(4), 2, 4);
        info_table_model.setValueAt("  "+czas.get(5), 2, 5);
        info_table_model.setValueAt("  "+czas.get(6), 2, 6);
        info_table_model.setValueAt("  "+nfz.get(0), 3, 0);
        info_table_model.setValueAt("  "+nfz.get(1), 3, 1);
        info_table_model.setValueAt("  "+nfz.get(2), 3, 2);
        info_table_model.setValueAt("  "+nfz.get(3), 3, 3);
        info_table_model.setValueAt("  "+nfz.get(4), 3, 4);
        info_table_model.setValueAt("  "+nfz.get(5), 3, 5);
        info_table_model.setValueAt("  "+nfz.get(6), 3, 6);
    }   
    public void dodaj_wiersz_kolejka(String id, String nazwisko, String imie, String pesel, String telefon){       
        kolejka_table_model.addRow(new String[]{id,nazwisko + " " + imie,pesel,telefon});
    }  
    public void wyczysc_kalendarz(){
        pon_table_model.setRowCount(0);
        wto_table_model.setRowCount(0);
        sro_table_model.setRowCount(0);
        czw_table_model.setRowCount(0);
        pia_table_model.setRowCount(0);
        sob_table_model.setRowCount(0);
        nie_table_model.setRowCount(0);
        info_table_model.setValueAt("", 2, 0);
        info_table_model.setValueAt("", 2, 1);
        info_table_model.setValueAt("", 2, 2);
        info_table_model.setValueAt("", 2, 3);
        info_table_model.setValueAt("", 2, 4);
        info_table_model.setValueAt("", 2, 5);
        info_table_model.setValueAt("", 2, 6);
        info_table_model.setValueAt("", 3, 0);
        info_table_model.setValueAt("", 3, 1);
        info_table_model.setValueAt("", 3, 2);
        info_table_model.setValueAt("", 3, 3);
        info_table_model.setValueAt("", 3, 4);
        info_table_model.setValueAt("", 3, 5);
        info_table_model.setValueAt("", 3, 6);
        int[] color = new int[7]; 
        setTableInfoColor(color);
    }
    public void wyczysc_kolejka(){
        kolejka_table_model.setRowCount(0);
    }
     
    private void table_kolejka_init(){
        scroll_kolejka.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        kolejka_table_model = new DefaultTableModel(){
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
        kolejka_table_model.addColumn("idpacjenci");
        kolejka_table_model.addColumn("Nazwisko i Imie");
        kolejka_table_model.addColumn("Pesel");
        kolejka_table_model.addColumn("Telefon");
        table_kolejka.setModel(kolejka_table_model);
        table_kolejka.getColumn("idpacjenci").setWidth(0);
        table_kolejka.getColumn("idpacjenci").setMaxWidth(0);
        table_kolejka.getColumn("idpacjenci").setMinWidth(0);
        table_kolejka.getColumn("idpacjenci").setPreferredWidth(0);
	table_kolejka.setFont(font);
	table_kolejka.setDefaultRenderer(String.class, centerRenderer);
        table_kolejka.setRowHeight(25);
    }    
    private void table_kalendarz_init(){
        
        ArrayList<String> daty = new ArrayList<>();
        Date data = calendar.getDate();
        cal.setTime(data);      
        cal.set(cal.DAY_OF_WEEK, Calendar.MONDAY);
        daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.TUESDAY);
        daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.WEDNESDAY);
        daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.THURSDAY);
        daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.FRIDAY);
        daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.SATURDAY);
        daty.add(sdf.format(cal.getTime()));
        cal.set(cal.DAY_OF_WEEK, Calendar.SUNDAY);
        daty.add(sdf.format(cal.getTime()));
        
        scroll_kalendarz.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        
        pon_table_model = new DefaultTableModel(){
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
        wto_table_model = new DefaultTableModel(){
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
        sro_table_model = new DefaultTableModel(){
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
        czw_table_model = new DefaultTableModel(){
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
        pia_table_model = new DefaultTableModel(){
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
        sob_table_model = new DefaultTableModel(){
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
        nie_table_model = new DefaultTableModel(){
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
        info_table_model = new DefaultTableModel(){
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

        pon_table_model.addColumn("idpon");
        pon_table_model.addColumn("pon"); 
        wto_table_model.addColumn("idwto");
        wto_table_model.addColumn("wto");
        sro_table_model.addColumn("idsro");
        sro_table_model.addColumn("sro");
        czw_table_model.addColumn("idczw");
        czw_table_model.addColumn("czw");
        pia_table_model.addColumn("idpia");
        pia_table_model.addColumn("pia");
        sob_table_model.addColumn("idsob");
        sob_table_model.addColumn("sob");
        nie_table_model.addColumn("idnie");
        nie_table_model.addColumn("nie");
        
        info_table_model.addColumn("pon");
        info_table_model.addColumn("wto");
        info_table_model.addColumn("sro");
        info_table_model.addColumn("czw");
        info_table_model.addColumn("pia");
        info_table_model.addColumn("sob");
        info_table_model.addColumn("nie");
        
        info_table_model.addRow(new String[]{"  Poniedziałek","  Wtorek","  Środa","  Czwartek","  Piątek","  Sobota","  Niedziela"});
        info_table_model.addRow(new String[]{"  "+daty.get(0),"  "+daty.get(1),"  "+daty.get(2),"  "+daty.get(3),"  "+daty.get(4),"  "+daty.get(5),"  "+daty.get(6)});
        info_table_model.addRow(new String[]{"","","","","","",""});
        info_table_model.addRow(new String[]{"","","","","","",""});
        
        table_pon.setModel(pon_table_model);
        table_wto.setModel(wto_table_model);
        table_sro.setModel(sro_table_model);
        table_czw.setModel(czw_table_model);
        table_pia.setModel(pia_table_model);
        table_sob.setModel(sob_table_model);
        table_nie.setModel(nie_table_model);
        table_info.setModel(info_table_model);
        
        
        
        int[] color = new int[7]; 
        setTableInfoColor(color);
        
        //table_info.getColumnModel().getColumn(1).setCellRenderer(new InfoRenderer(1));
        //table_info.getColumnModel().getColumn(2).setCellRenderer(new InfoRenderer(2));
        //table_info.getColumnModel().getColumn(3).setCellRenderer(new InfoRenderer(3));
        
        //table_info.getColumnModel().getColumn(4).setCellRenderer(new InfoRenderer(1));
        //table_info.getColumnModel().getColumn(5).setCellRenderer(new InfoRenderer(2));
        //table_info.getColumnModel().getColumn(6).setCellRenderer(new InfoRenderer(3));
        
        table_pon.getColumn("idpon").setWidth(0);
        table_pon.getColumn("idpon").setMaxWidth(0);
        table_pon.getColumn("idpon").setMinWidth(0);
        table_pon.getColumn("idpon").setPreferredWidth(0);
        table_wto.getColumn("idwto").setWidth(0);
        table_wto.getColumn("idwto").setMaxWidth(0);
        table_wto.getColumn("idwto").setMinWidth(0);
        table_wto.getColumn("idwto").setPreferredWidth(0);
        table_sro.getColumn("idsro").setWidth(0);
        table_sro.getColumn("idsro").setMaxWidth(0);
        table_sro.getColumn("idsro").setMinWidth(0);
        table_sro.getColumn("idsro").setPreferredWidth(0);
        table_czw.getColumn("idczw").setWidth(0);
        table_czw.getColumn("idczw").setMaxWidth(0);
        table_czw.getColumn("idczw").setMinWidth(0);
        table_czw.getColumn("idczw").setPreferredWidth(0);
        table_pia.getColumn("idpia").setWidth(0);
        table_pia.getColumn("idpia").setMaxWidth(0);
        table_pia.getColumn("idpia").setMinWidth(0);
        table_pia.getColumn("idpia").setPreferredWidth(0);
        table_sob.getColumn("idsob").setWidth(0);
        table_sob.getColumn("idsob").setMaxWidth(0);
        table_sob.getColumn("idsob").setMinWidth(0);
        table_sob.getColumn("idsob").setPreferredWidth(0);
        table_nie.getColumn("idnie").setWidth(0);
        table_nie.getColumn("idnie").setMaxWidth(0);
        table_nie.getColumn("idnie").setMinWidth(0);
        table_nie.getColumn("idnie").setPreferredWidth(0);

        table_pon.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_pon.getModel());    
        //kalendarz_row_sorter.setSortable(1, false);
        table_pon.setRowSelectionAllowed(true);
        table_pon.setRowSorter(kalendarz_row_sorter); 
        table_pon.setDefaultRenderer(String.class, new VisitorRenderer());
        table_wto.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_wto.getModel());
        //kalendarz_row_sorter.setSortable(1, false); 
        table_wto.setRowSelectionAllowed(true);
        table_wto.setRowSorter(kalendarz_row_sorter); 
        table_wto.setDefaultRenderer(String.class, new VisitorRenderer());
        table_sro.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_sro.getModel());
        //kalendarz_row_sorter.setSortable(1, false); 
        table_sro.setRowSelectionAllowed(true);
        table_sro.setRowSorter(kalendarz_row_sorter); 
        table_sro.setDefaultRenderer(String.class, new VisitorRenderer());
        table_czw.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_czw.getModel());
        //kalendarz_row_sorter.setSortable(1, false); 
        table_czw.setRowSelectionAllowed(true);
        table_czw.setRowSorter(kalendarz_row_sorter); 
        table_czw.setDefaultRenderer(String.class, new VisitorRenderer());
        table_pia.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_pia.getModel());
        //kalendarz_row_sorter.setSortable(1, false); 
        table_pia.setRowSelectionAllowed(true);
        table_pia.setRowSorter(kalendarz_row_sorter); 
        table_pia.setDefaultRenderer(String.class, new VisitorRenderer());
        table_sob.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_sob.getModel());
        //kalendarz_row_sorter.setSortable(1, false); 
        table_sob.setRowSelectionAllowed(true);
        table_sob.setRowSorter(kalendarz_row_sorter); 
        table_sob.setDefaultRenderer(String.class, new VisitorRenderer());
        table_nie.setAutoCreateRowSorter(true);    
        kalendarz_row_sorter = new TableRowSorter<>(table_nie.getModel());
        //kalendarz_row_sorter.setSortable(1, false); 
        table_nie.setRowSelectionAllowed(true);
        table_nie.setRowSorter(kalendarz_row_sorter); 
        table_nie.setDefaultRenderer(String.class, new VisitorRenderer());

        table_pon.setFont(font);
        table_wto.setFont(font);
        table_sro.setFont(font);
        table_czw.setFont(font);
        table_pia.setFont(font);
        table_sob.setFont(font);
        table_nie.setFont(font);
        table_info.setFont(font_bold);
        
        table_pon.setDefaultRenderer(String.class, centerRenderer);
        table_wto.setDefaultRenderer(String.class, centerRenderer);
        table_sro.setDefaultRenderer(String.class, centerRenderer);
        table_czw.setDefaultRenderer(String.class, centerRenderer);
        table_pia.setDefaultRenderer(String.class, centerRenderer);
        table_sob.setDefaultRenderer(String.class, centerRenderer);
        table_nie.setDefaultRenderer(String.class, centerRenderer);
        
        table_pon.setRowHeight(25);
        table_wto.setRowHeight(25);
        table_sro.setRowHeight(25);
        table_czw.setRowHeight(25);
        table_pia.setRowHeight(25);
        table_sob.setRowHeight(25);
        table_nie.setRowHeight(25);
        table_info.setRowHeight(25);
       
        //table_pon.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer(0, 0));
    }
    private void table_control_init(){
        control_table_model = new DefaultTableModel(){
            @Override
            
            
            public boolean isCellEditable(int row, int column)
            {
                //if (column == 1 && row == 11)
                //{
                //    return true;
                //}
                //else
                //{
                    return false;
                //}
                //return super.isCellEditable(row, column); // or maybe simply "true"
            }
        };
        control_table_model.addColumn("0");
        control_table_model.addColumn("1");
        //z komórki
        control_table_model.addRow(new Object[]{"  Komórka",""});
        //control_table_model.addRow(new Object[]{"  Adres",""});
        control_table_model.addRow(new Object[]{"  Punkty",""});
        //z personelu
        control_table_model.addRow(new Object[]{"  Personel",""});
        control_table_model.addRow(new Object[]{"  Punkty/M",""});
        control_table_model.addRow(new Object[]{"  Czas/T",""});
        //z pacjenta
        control_table_model.addRow(new Object[]{"  Pacjent",""});
        control_table_model.addRow(new Object[]{"  Pesel",""});
        control_table_model.addRow(new Object[]{"  Telefon",""});
        control_table_model.addRow(new Object[]{"  Adres",""});
        //control_table_model.addRow(new Object[]{" Lokalizacja",""});
        //z wizyty
        control_table_model.addRow(new Object[]{"  Wizyta",""});
        control_table_model.addRow(new Object[]{"  Punkty/Czas",""});
        //z kalendarza
        control_table_model.addRow(new Object[]{"  Data",""});
        control_table_model.addRow(new Object[]{"  Godzina",""});
        control_table_model.addRow(new Object[]{"  Uwagi",""});
        //control_table_model.addRow(new Object[]{"  Odwolana/Kolejka",""});
       // control_table_model.addRow(new Object[]{"  Pierwsza?",""});
        
        table_control.setModel(control_table_model);
        table_control.setFont(font);
        table_control.setRowHeight(23);
        //table_control.setIntercellSpacing(new Dimension(5,5));
        
        table_control.getColumn("0").setWidth(80);
        table_control.getColumn("0").setMaxWidth(80);
        table_control.getColumn("0").setMinWidth(80);
        table_control.getColumn("0").setPreferredWidth(80);
        
        int [] color = new int[3];
        setTableControlColor(color);
    }
     
    private void combobox_godzina_init(){
        deleteAllGodzina();
        /*ArrayList <String> hours = new ArrayList<>(Arrays.asList(
                              "06:00","06:30","07:00","07:30",
                              "08:00","08:30","09:00","09:30",
                              "10:00","10:30","11:00","11:30",
                              "12:00","12:30","13:00","13:30",
                              "14:00","14:30","15:00","15:30",
                              "16:00","16:30","17:00","17:30",
                              "18:00","18:30","19:00","19:30",
                              "20:00","20:30","21:00","21:30"));
        setComboboxModel(hours);*/
    }   
    private void combobox_init(){
        //AutoCompletion.enable(combobox_personel);
        AutoCompletion.enable(combobox_pacjent);
        //AutoCompletion.enable(combobox_komorka);
        //AutoCompletion.enable(combobox_wizyta);  
    }
    private void sorter_init(){
 
    sorter_pon = new TableRowSorter<>(table_pon.getModel());
    sorter_wto = new TableRowSorter<>(table_wto.getModel());
    sorter_sro = new TableRowSorter<>(table_sro.getModel());
    sorter_czw = new TableRowSorter<>(table_czw.getModel());
    sorter_pia = new TableRowSorter<>(table_pia.getModel());
    sorter_sob = new TableRowSorter<>(table_sob.getModel());
    sorter_nie = new TableRowSorter<>(table_nie.getModel());

    table_pon.setRowSorter(sorter_pon);
    table_wto.setRowSorter(sorter_wto);
    table_sro.setRowSorter(sorter_sro);
    table_czw.setRowSorter(sorter_czw);
    table_pia.setRowSorter(sorter_pia);
    table_sob.setRowSorter(sorter_sob);
    table_nie.setRowSorter(sorter_nie);
    
    List<RowSorter.SortKey> sortKeys = new ArrayList<>();
    int columnIndexToSort = 1;
    sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
    
    sorter_pon.setSortKeys(sortKeys);
    sorter_wto.setSortKeys(sortKeys);
    sorter_sro.setSortKeys(sortKeys);
    sorter_czw.setSortKeys(sortKeys);
    sorter_pia.setSortKeys(sortKeys);
    sorter_sob.setSortKeys(sortKeys);
    sorter_nie.setSortKeys(sortKeys);
    
    sorter_pon.sort();
    sorter_wto.sort();
    sorter_sro.sort();
    sorter_czw.sort();
    sorter_pia.sort();
    sorter_sob.sort();
    sorter_nie.sort();
    
    }

    
   @SuppressWarnings("deprecation")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_glowny = new javax.swing.JPanel();
        panel_kalendarz = new javax.swing.JPanel();
        table_info = new javax.swing.JTable();
        scroll_kalendarz = new javax.swing.JScrollPane();
        pane_tab = new javax.swing.JPanel();
        table_pon = new javax.swing.JTable();
        table_wto = new javax.swing.JTable();
        table_sro = new javax.swing.JTable();
        table_czw = new javax.swing.JTable();
        table_pia = new javax.swing.JTable();
        table_sob = new javax.swing.JTable();
        table_nie = new javax.swing.JTable();
        panel_kolejka = new javax.swing.JPanel();
        scroll_kolejka = new javax.swing.JScrollPane();
        pane_kol = new javax.swing.JPanel();
        table_kolejka = new javax.swing.JTable();
        panel_control = new javax.swing.JPanel();
        table_control = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtarea_uwagi = new javax.swing.JTextArea();
        panel_buttons = new javax.swing.JPanel();
        button_dodaj = new javax.swing.JButton();
        button_usun = new javax.swing.JButton();
        button_zmien = new javax.swing.JButton();
        button_wyczysc = new javax.swing.JButton();
        panel_combo = new javax.swing.JPanel();
        label_komorka = new javax.swing.JLabel();
        label_personel = new javax.swing.JLabel();
        label_pacjent = new javax.swing.JLabel();
        label_wizyta = new javax.swing.JLabel();
        combobox_komorka = new javax.swing.JComboBox();
        combobox_personel = new javax.swing.JComboBox();
        combobox_pacjent = new javax.swing.JComboBox();
        combobox_wizyta = new javax.swing.JComboBox();
        checkbox_kolejka = new javax.swing.JCheckBox();
        label_godzina = new javax.swing.JLabel();
        combobox_godzina = new javax.swing.JComboBox();
        checkbox_pierwsza = new javax.swing.JCheckBox();
        panel_jcalendar = new javax.swing.JPanel();
        calendar = new com.toedter.calendar.JCalendar();
        panel_clock = new javax.swing.JPanel();
        javax.swing.JLabel zegar = new ClockLabel();
        status_bar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        panel_kalendarz.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));
        panel_kalendarz.setMaximumSize(new java.awt.Dimension(668, 512));
        panel_kalendarz.setMinimumSize(new java.awt.Dimension(668, 512));

        table_info.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_info.setEnabled(false);

        scroll_kalendarz.setBorder(null);

        table_pon.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_pon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        table_wto.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_wto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        table_sro.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_sro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        table_czw.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_czw.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        table_pia.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_pia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        table_sob.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_sob.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        table_nie.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        table_nie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        javax.swing.GroupLayout pane_tabLayout = new javax.swing.GroupLayout(pane_tab);
        pane_tab.setLayout(pane_tabLayout);
        pane_tabLayout.setHorizontalGroup(
            pane_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_tabLayout.createSequentialGroup()
                .addComponent(table_pon, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(table_wto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(table_sro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(table_czw, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(table_pia, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(table_sob, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(table_nie, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pane_tabLayout.setVerticalGroup(
            pane_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_pon, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
            .addComponent(table_wto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(table_sro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(table_czw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(table_pia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(table_sob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(table_nie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scroll_kalendarz.setViewportView(pane_tab);

        javax.swing.GroupLayout panel_kalendarzLayout = new javax.swing.GroupLayout(panel_kalendarz);
        panel_kalendarz.setLayout(panel_kalendarzLayout);
        panel_kalendarzLayout.setHorizontalGroup(
            panel_kalendarzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kalendarzLayout.createSequentialGroup()
                .addGroup(panel_kalendarzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroll_kalendarz)
                    .addComponent(table_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_kalendarzLayout.setVerticalGroup(
            panel_kalendarzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_kalendarzLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(table_info, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll_kalendarz, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_kolejka.setPreferredSize(new java.awt.Dimension(655, 210));

        scroll_kolejka.setBorder(null);

        table_kolejka.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));
        table_kolejka.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        javax.swing.GroupLayout pane_kolLayout = new javax.swing.GroupLayout(pane_kol);
        pane_kol.setLayout(pane_kolLayout);
        pane_kolLayout.setHorizontalGroup(
            pane_kolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_kolejka, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
        pane_kolLayout.setVerticalGroup(
            pane_kolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_kolejka, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );

        scroll_kolejka.setViewportView(pane_kol);

        javax.swing.GroupLayout panel_kolejkaLayout = new javax.swing.GroupLayout(panel_kolejka);
        panel_kolejka.setLayout(panel_kolejkaLayout);
        panel_kolejkaLayout.setHorizontalGroup(
            panel_kolejkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_kolejka)
        );
        panel_kolejkaLayout.setVerticalGroup(
            panel_kolejkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_kolejka)
        );

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        table_control.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_control.setFocusable(false);
        table_control.setIntercellSpacing(new java.awt.Dimension(2, 2));
        table_control.setSelectionForeground(new java.awt.Color(0, 0, 0));

        txtarea_uwagi.setColumns(20);
        txtarea_uwagi.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtarea_uwagi.setRows(5);
        jScrollPane2.setViewportView(txtarea_uwagi);

        javax.swing.GroupLayout panel_controlLayout = new javax.swing.GroupLayout(panel_control);
        panel_control.setLayout(panel_controlLayout);
        panel_controlLayout.setHorizontalGroup(
            panel_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
            .addComponent(table_control, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_controlLayout.setVerticalGroup(
            panel_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_controlLayout.createSequentialGroup()
                .addComponent(table_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_buttons.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        button_dodaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_dodaj.setText("DODAJ");
        button_dodaj.setEnabled(false);

        button_usun.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_usun.setText("USUŃ");
        button_usun.setEnabled(false);

        button_zmien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_zmien.setText("ZMIEŃ");
        button_zmien.setEnabled(false);

        button_wyczysc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_wyczysc.setText("WYCZYŚĆ");
        button_wyczysc.setEnabled(false);

        javax.swing.GroupLayout panel_buttonsLayout = new javax.swing.GroupLayout(panel_buttons);
        panel_buttons.setLayout(panel_buttonsLayout);
        panel_buttonsLayout.setHorizontalGroup(
            panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_buttonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_zmien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_wyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_buttonsLayout.setVerticalGroup(
            panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_buttonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_zmien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_wyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_combo.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        label_komorka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_komorka.setText("Komórka:");

        label_personel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_personel.setText("Personel:");

        label_pacjent.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_pacjent.setText("Pacjent:");

        label_wizyta.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_wizyta.setText("Wizyta:");

        combobox_komorka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_komorka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        combobox_personel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_personel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_personel.setEnabled(false);

        combobox_pacjent.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_pacjent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_pacjent.setEnabled(false);

        combobox_wizyta.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_wizyta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_wizyta.setEnabled(false);

        checkbox_kolejka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_kolejka.setText("Kolejka");
        checkbox_kolejka.setEnabled(false);

        label_godzina.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_godzina.setText("Godzina:");

        combobox_godzina.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_godzina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_godzina.setEnabled(false);

        checkbox_pierwsza.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_pierwsza.setText("Pierwsza");
        checkbox_pierwsza.setEnabled(false);

        javax.swing.GroupLayout panel_comboLayout = new javax.swing.GroupLayout(panel_combo);
        panel_combo.setLayout(panel_comboLayout);
        panel_comboLayout.setHorizontalGroup(
            panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_comboLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_pacjent)
                    .addComponent(label_komorka)
                    .addComponent(label_wizyta)
                    .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(label_godzina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_personel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_comboLayout.createSequentialGroup()
                        .addComponent(combobox_godzina, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                        .addComponent(checkbox_kolejka)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkbox_pierwsza))
                    .addComponent(combobox_personel, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_pacjent, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_wizyta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_komorka, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_comboLayout.setVerticalGroup(
            panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_comboLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobox_komorka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_komorka))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobox_personel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_personel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobox_pacjent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_pacjent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobox_wizyta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_wizyta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_kolejka)
                    .addComponent(label_godzina)
                    .addComponent(combobox_godzina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_pierwsza))
                .addGap(11, 11, 11))
        );

        panel_jcalendar.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        calendar.setDecorationBackgroundVisible(false);
        calendar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        calendar.setWeekOfYearVisible(false);

        javax.swing.GroupLayout panel_jcalendarLayout = new javax.swing.GroupLayout(panel_jcalendar);
        panel_jcalendar.setLayout(panel_jcalendarLayout);
        panel_jcalendarLayout.setHorizontalGroup(
            panel_jcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        panel_jcalendarLayout.setVerticalGroup(
            panel_jcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_jcalendarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panel_clock.setBackground(new java.awt.Color(255, 255, 229));
        panel_clock.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        zegar.setBackground(new java.awt.Color(255, 255, 229));
        zegar.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N

        javax.swing.GroupLayout panel_clockLayout = new javax.swing.GroupLayout(panel_clock);
        panel_clock.setLayout(panel_clockLayout);
        panel_clockLayout.setHorizontalGroup(
            panel_clockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_clockLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(zegar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        panel_clockLayout.setVerticalGroup(
            panel_clockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zegar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_glownyLayout = new javax.swing.GroupLayout(panel_glowny);
        panel_glowny.setLayout(panel_glownyLayout);
        panel_glownyLayout.setHorizontalGroup(
            panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_glownyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_glownyLayout.createSequentialGroup()
                        .addComponent(panel_jcalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_kalendarz, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_kolejka, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_buttons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_clock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_glownyLayout.setVerticalGroup(
            panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_glownyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_jcalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_combo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_clock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_kalendarz, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_glownyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_buttons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_kolejka, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );

        status_bar.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(status_bar)
            .addComponent(panel_glowny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_glowny, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(status_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        erp_view.setEnabled(true);      // TODO add your handling code here:
        erp_view.setVisible(true);
    }//GEN-LAST:event_formWindowClosed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_dodaj;
    private javax.swing.JButton button_usun;
    private javax.swing.JButton button_wyczysc;
    private javax.swing.JButton button_zmien;
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JCheckBox checkbox_kolejka;
    private javax.swing.JCheckBox checkbox_pierwsza;
    private javax.swing.JComboBox combobox_godzina;
    private javax.swing.JComboBox combobox_komorka;
    private javax.swing.JComboBox combobox_pacjent;
    private javax.swing.JComboBox combobox_personel;
    private javax.swing.JComboBox combobox_wizyta;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_godzina;
    private javax.swing.JLabel label_komorka;
    private javax.swing.JLabel label_pacjent;
    private javax.swing.JLabel label_personel;
    private javax.swing.JLabel label_wizyta;
    private javax.swing.JPanel pane_kol;
    private javax.swing.JPanel pane_tab;
    private javax.swing.JPanel panel_buttons;
    private javax.swing.JPanel panel_clock;
    private javax.swing.JPanel panel_combo;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_glowny;
    private javax.swing.JPanel panel_jcalendar;
    private javax.swing.JPanel panel_kalendarz;
    private javax.swing.JPanel panel_kolejka;
    private javax.swing.JScrollPane scroll_kalendarz;
    private javax.swing.JScrollPane scroll_kolejka;
    private javax.swing.JTextField status_bar;
    private javax.swing.JTable table_control;
    private javax.swing.JTable table_czw;
    private javax.swing.JTable table_info;
    private javax.swing.JTable table_kolejka;
    private javax.swing.JTable table_nie;
    private javax.swing.JTable table_pia;
    private javax.swing.JTable table_pon;
    private javax.swing.JTable table_sob;
    private javax.swing.JTable table_sro;
    private javax.swing.JTable table_wto;
    private javax.swing.JTextArea txtarea_uwagi;
    // End of variables declaration//GEN-END:variables
}

class ClockLabel extends JLabel implements ActionListener {
    
    DateFormat time;
    DateFormat day;
    DateFormat date;
    
    
  public ClockLabel() {
    //super("" + new Date());
    time = new SimpleDateFormat("HH:mm:ss");
    day = new SimpleDateFormat("EEEE");
    date = new SimpleDateFormat("dd / MM / yyyy");
    Timer t = new Timer(1000, this);
    t.start();
  }

  public void actionPerformed(ActionEvent ae) {
    setText("<html><div align=\"center\">"+day.format(new Date())+"<br>"+date.format(new Date())+"<br>"+time.format(new Date())+"</div></html>");
  }

}
