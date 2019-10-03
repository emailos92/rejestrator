/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.terminarz;

import erp.ERP_View;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Arkita
 */
public class TerminarzView extends javax.swing.JFrame {

    DefaultTableModel terminarz_table_model;
    Font font_bold,font;
    DefaultTableCellRenderer centerRenderer;
    DateFormat sdf;
    TableRowSorter<TableModel> terminarz_row_sorter; 
    ERP_View erp_view;
    
    public TerminarzView(ERP_View erp_view) {
        this.erp_view = erp_view;
        initComponents();
        font_bold = new Font( "Calibri", Font.BOLD, 14);
        font = new Font( "Calibri", Font.PLAIN, 14);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        initCombobox();
        initCal();
        initTable();
        initTxtFields();
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public String getKomorka(){
        return combobox_komorka.getSelectedItem().toString();
    }
    public String getPersonel(){
        return combobox_personel.getSelectedItem().toString();
    }
    public String getPacjent(){
        return combobox_pacjent.getSelectedItem().toString();
    }
    public String getWizyta(){
        return combobox_wizyta.getSelectedItem().toString();
    }
    public String getPlec(){
        return combobox_plec.getSelectedItem().toString();
    }    
    public String getLok(){
        return combobox_lok.getSelectedItem().toString();
    }

    public String getDataOd(){
        return sdf.format(data_od.getDate());
    }
    public String getDataDo(){
        return sdf.format(data_do.getDate());
    }
    public String getUrodzonyOd(){
        return sdf.format(urodzony_od.getDate());
    }
    public String getUrodzonyDo(){
        return sdf.format(urodzony_do.getDate());
    }
    
    public boolean getCheckboxDataOd(){
        return checkbox_dataod.isSelected();
    } 
    public boolean getCheckboxDataDo(){
        return checkbox_datado.isSelected();
    } 
    public boolean getCheckboxUrodzonyOd(){
        return checkbox_urodzonyod.isSelected();
    } 
    public boolean getCheckboxUrodzonyDo(){
        return checkbox_urodzonydo.isSelected();
    } 
    public boolean getCheckboxKomorka(){
        return checkbox_komorka.isSelected();
    }
    public boolean getCheckboxPersonel(){
        return checkbox_personel.isSelected();
    }
    public boolean getCheckboxPacjent(){
        return checkbox_pacjent.isSelected();
    }
    public boolean getCheckboxAdres(){
        return checkbox_adres.isSelected();
    }    
    public boolean getCheckboxTelefon(){
        return checkbox_telefon.isSelected();
    }      
    public boolean getCheckboxWizyta(){
        return checkbox_wizyta.isSelected();
    } 
    public boolean getCheckboxData(){
        return checkbox_data.isSelected();
    } 
    public boolean getCheckboxGodzina(){
        return checkbox_godzina.isSelected();
    } 
    public boolean getCheckboxUwagi(){
        return checkbox_uwagi.isSelected();
    } 
    public boolean getCheckboxKolejka(){
        return checkbox_kolejka.isSelected();
    } 
    public boolean getCheckboxPierwsza(){
        return checkbox_pierwsza.isSelected();
    } 
    public boolean getEnabledComboboxKomorka(){
        return combobox_komorka.isEnabled();
    }
    public boolean getEnabledComboboxPersonel(){
        return combobox_personel.isEnabled();
    }
    public boolean getEnabledComboboxPacjent(){
        return combobox_pacjent.isEnabled();
    }
    public boolean getEnabledComboboxWizyta(){
        return combobox_wizyta.isEnabled();
    }
    public String getSzukaj(){	         
	return txtfield_szukaj.getText();	         
    }
    public void setEnabledComboboxWizyta(boolean enabled){
        combobox_wizyta.setEnabled(enabled);
    }
    public boolean isEnabledComboboxWizyta(){
        return combobox_wizyta.isEnabled();
    }
    public boolean isEnabledComboboxKomorka(){
        return combobox_komorka.isEnabled();
    }
    public boolean isEnabledComboboxPersonel(){
        return combobox_personel.isEnabled();
    }
    
    public void setEnabledUsun(boolean enabled){
        button_usun.setEnabled(enabled);
    }
    
    public int getId(int row){
        return Integer.valueOf((String)table_terminarz.getValueAt(row, 0));
    }
    void zaznaczono_wiersz(ListSelectionListener listen){
        table_terminarz.getSelectionModel().addListSelectionListener(listen);
    }
    public int getTableSelectedRow(){
        return table_terminarz.getSelectedRow();
    }  
    
    void button_usun(ActionListener listen){
        button_usun.addActionListener(listen);
    } 
    public void clear_selection(){
        table_terminarz.clearSelection();
    }
    
    void komorka_checkbox(ActionListener listen){
        checkbox_komorka.addActionListener(listen);
    } 
    
    void pacjent_checkbox(ActionListener listen){
        checkbox_pacjent.addActionListener(listen);
    }
    
    void telefon_checkbox(ActionListener listen){
        checkbox_telefon.addActionListener(listen);
    }
    
    void data_checkbox(ActionListener listen){
        checkbox_data.addActionListener(listen);
    }
    
    void uwagi_checkbox(ActionListener listen){
        checkbox_uwagi.addActionListener(listen);
    }
    
    void personel_checkbox(ActionListener listen){
        checkbox_personel.addActionListener(listen);
    }
    
    void adres_checkbox(ActionListener listen){
        checkbox_adres.addActionListener(listen);
    }
    
    void wizyta_checkbox(ActionListener listen){
        checkbox_wizyta.addActionListener(listen);
    }
    
    void godzina_checkbox(ActionListener listen){
        checkbox_godzina.addActionListener(listen);
    }
    
    public ArrayList<ArrayList<String>> getValuesAt(ArrayList<String> col){
        ArrayList<ArrayList<String>> values;
        ArrayList<String> row;
        values = new ArrayList<>();
        int i,j,lenght_row, lenght_col;
        lenght_row = table_terminarz.getRowCount();
        lenght_col = col.size();
        for(i=0;i<lenght_row;i++){
            row = new ArrayList<>();            
            for(j=0;j<lenght_col;j++){
                row.add((String) table_terminarz.getValueAt(i, Integer.parseInt(col.get(j))));
            }
            values.add(row);
        }
        return values;
    }
    
    public void setPlec(String plec){
        combobox_plec.setSelectedItem(plec);
    }
    public void setLok(String lok){
        combobox_lok.setSelectedItem(lok);
    }
    
    public void setEnabledCheckboxDataOd(boolean enabled){
        checkbox_dataod.setEnabled(enabled);
    }
    public void setEnabledCheckboxDataDo(boolean enabled){
        checkbox_datado.setEnabled(enabled);
    }
    
    public void setEnabledDataOd(boolean enabled){
        if(enabled){
            data_od.setDate(new Date());
            data_od.setEnabled(true);
        }
        else{
            data_od.setEnabled(false);
            data_od.setDate(new Date());
        }
    }
    public void setEnabledDataDo(boolean enabled){
        if(enabled){
            data_do.setDate(new Date());
            data_do.setEnabled(true);
        }
        else{
            data_do.setEnabled(false);
            data_do.setDate(new Date());
        }
    }
    public void setEnabledUrodzonyOd(boolean enabled){
        if(enabled){
            urodzony_od.setDate(new Date());
            urodzony_od.setEnabled(true);
        }
        else{
            urodzony_od.setEnabled(false);
            urodzony_od.setDate(new Date());
        }
    }
    public void setEnabledUrodzonyDo(boolean enabled){
        if(enabled){
            urodzony_do.setDate(new Date());
            urodzony_do.setEnabled(true);
        }
        else{
            urodzony_do.setEnabled(false);
            urodzony_do.setDate(new Date());
        }
    }   
    public void setEnabledPacjent(boolean enabled){
        if(enabled){
            combobox_plec.setEnabled(true);
            combobox_lok.setEnabled(true);
            checkbox_urodzonyod.setEnabled(true);
            checkbox_urodzonydo.setEnabled(true);
        }
        else{
            combobox_plec.setEnabled(false);
            combobox_lok.setEnabled(false);        
            combobox_plec.setSelectedIndex(0);
            combobox_lok.setSelectedIndex(0);
            checkbox_urodzonyod.setSelected(false);
            checkbox_urodzonydo.setSelected(false);
            checkbox_urodzonyod.setEnabled(false);
            checkbox_urodzonydo.setEnabled(false);
        }
    }
    public void setSelectedCheckboxDataOd(boolean selected){
        checkbox_dataod.setSelected(selected);
    }
    public void setSelectedCheckboxDataDo(boolean selected){
        checkbox_datado.setSelected(selected);
    }
    public void setSelectedCheckboxKolejka(boolean selected){
        checkbox_kolejka.setSelected(selected);
    }
    public void setSelectedCheckboxPierwsza(boolean selected){
        checkbox_pierwsza.setSelected(selected);
    }
    public void setSelectedCheckboxUrodzonyOd(boolean selected){
        checkbox_urodzonyod.setSelected(selected);
    }
    public void setSelectedCheckboxUrodzonyDo(boolean selected){
        checkbox_urodzonydo.setSelected(selected);
    }
    public void setSzukaj(String szukaj){
        txtfield_szukaj.setText(szukaj);
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
    public void setItemKomorka(String item){
        combobox_komorka.setEnabled(false);
        combobox_komorka.setSelectedItem(item);
        combobox_komorka.setEnabled(true);
    }
    public void setItemPersonel(String item){
        combobox_personel.setEnabled(false);
        combobox_personel.setSelectedItem(item);
        combobox_personel.setEnabled(true);
    }
    public void setItemPacjent(String item){
        combobox_pacjent.setEnabled(false);
        combobox_pacjent.setSelectedItem(item);
        combobox_pacjent.setEnabled(true);
    }
    public void setItemWizyta(String item){
        combobox_wizyta.setEnabled(false);
        combobox_wizyta.setSelectedItem(item);
        combobox_wizyta.setEnabled(true);
    }  
    public void setPunkty(String punkty){
        txtfield_punkty.setText(punkty);
    }
    public void setCzas(String czas){
        txtfield_czas.setText(czas);
    }
    public void setIloscWizyt(String iloscwizyt){
        txtfield_iloscwizyt.setText(iloscwizyt);
    }
    public void setPLN(String iloscwizyt){
        txtfield_pln.setText(iloscwizyt);
    }   

    public void setColumnWidth(String column, int widht){
        if(widht > 0)
        {
            table_terminarz.getColumn(column).setMaxWidth(2000);
            table_terminarz.getColumn(column).setMinWidth(0);
            table_terminarz.getColumn(column).setWidth(widht);
            table_terminarz.getColumn(column).setPreferredWidth(widht);
        }
        else
        {
            table_terminarz.getColumn(column).setMaxWidth(0);
            table_terminarz.getColumn(column).setMinWidth(0);
            table_terminarz.getColumn(column).setWidth(0);
            table_terminarz.getColumn(column).setPreferredWidth(0);        
        }
    }
    
    public void setColumnWidth(String column, int widht, int max, int min){
        if(widht > 0)
        {
            table_terminarz.getColumn(column).setMaxWidth(max);
            table_terminarz.getColumn(column).setMinWidth(min);
            table_terminarz.getColumn(column).setWidth(widht);
            table_terminarz.getColumn(column).setPreferredWidth(widht);
        }
        else
        {
            table_terminarz.getColumn(column).setMaxWidth(0);
            table_terminarz.getColumn(column).setMinWidth(0);
            table_terminarz.getColumn(column).setWidth(0);
            table_terminarz.getColumn(column).setPreferredWidth(0);        
        }
    }
    
    public void setRowSorterer(String text){
        terminarz_row_sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
    }  
    
    public void dodaj_wiersz(ArrayList<String> dane){
        terminarz_table_model.addRow(new String[]{dane.get(0),dane.get(1),dane.get(2),dane.get(3),dane.get(4),
                                                  dane.get(5),dane.get(6),dane.get(7),dane.get(8),dane.get(9),
                                                  dane.get(10),dane.get(11),dane.get(12),dane.get(13),dane.get(14), dane.get(15)});
    }
    public void wyczysc_terminarz(){
        terminarz_table_model.setRowCount(0);
    }
    public void toExcel(){
        try{
            TableModel model = table_terminarz.getModel();
            String file = null;
            try (FileWriter excel = new FileWriter(file)) {
                for(int i = 0; i < model.getColumnCount(); i++){
                        
                    excel.write(model.getColumnName(i) + "\t");

                }
                
                excel.write("\n");
                
                for(int i=0; i< model.getRowCount(); i++) {
                    for(int j=0; j < model.getColumnCount(); j++) {
                        
                        excel.write(model.getValueAt(i,j).toString()+"\t");
                    
                    }
                    excel.write("\n");
                }
            }

        }catch(IOException e){ System.out.println(e); }  
    }
    void button_dopliku(ActionListener listen){
        button_dopliku.addActionListener(listen);
    }  
    void button_pokaz(ActionListener listen){
        button_pokaz.addActionListener(listen);
    }  
    void button_wyczysc(ActionListener listen){
        button_wyczysc.addActionListener(listen);
    }  
    
    void checkbox_dataod(ActionListener listen){
        checkbox_dataod.addActionListener(listen);
    }  
    void checkbox_datado(ActionListener listen){
        checkbox_datado.addActionListener(listen);
    }  
    void checkbox_urodzonyod(ActionListener listen){
        checkbox_urodzonyod.addActionListener(listen);
    }  
    void checkbox_urodzonydo(ActionListener listen){
        checkbox_urodzonydo.addActionListener(listen);
    }  
    void checkbox_kolejka(ActionListener listen){
        checkbox_kolejka.addActionListener(listen);
    }  
    
    void combobox_komorka(ActionListener listen){
        combobox_komorka.addActionListener(listen);
    }
    void combobox_personel(ActionListener listen){
        combobox_personel.addActionListener(listen);
    }    
    void combobox_pacjent(ActionListener listen){
        combobox_pacjent.addActionListener(listen);
    }      
    void combobox_wizyta(ActionListener listen){
        combobox_wizyta.addActionListener(listen);
    }  
    void txtfield_szukaj(DocumentListener listen){
        txtfield_szukaj.getDocument().addDocumentListener(listen);
    } 
    
     @SuppressWarnings("deprecation")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        label_punkty = new javax.swing.JLabel();
        label_czas = new javax.swing.JLabel();
        label_wizyty = new javax.swing.JLabel();
        txtfield_iloscwizyt = new javax.swing.JTextField();
        txtfield_czas = new javax.swing.JTextField();
        txtfield_punkty = new javax.swing.JTextField();
        txtfield_pln = new javax.swing.JTextField();
        label_pln = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        checkbox_komorka = new javax.swing.JCheckBox();
        checkbox_personel = new javax.swing.JCheckBox();
        checkbox_pacjent = new javax.swing.JCheckBox();
        checkbox_adres = new javax.swing.JCheckBox();
        checkbox_telefon = new javax.swing.JCheckBox();
        checkbox_wizyta = new javax.swing.JCheckBox();
        checkbox_data = new javax.swing.JCheckBox();
        checkbox_godzina = new javax.swing.JCheckBox();
        checkbox_uwagi = new javax.swing.JCheckBox();
        button_dopliku = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        label_komorka = new javax.swing.JLabel();
        label_personel = new javax.swing.JLabel();
        label_pacjent = new javax.swing.JLabel();
        label_wizyta = new javax.swing.JLabel();
        combobox_komorka = new javax.swing.JComboBox();
        combobox_personel = new javax.swing.JComboBox();
        combobox_pacjent = new javax.swing.JComboBox();
        combobox_wizyta = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        data_do = new com.toedter.calendar.JDateChooser();
        checkbox_datado = new javax.swing.JCheckBox();
        data_od = new com.toedter.calendar.JDateChooser();
        checkbox_dataod = new javax.swing.JCheckBox();
        labe_data = new javax.swing.JLabel();
        urodzony_do = new com.toedter.calendar.JDateChooser();
        checkbox_urodzonydo = new javax.swing.JCheckBox();
        urodzony_od = new com.toedter.calendar.JDateChooser();
        checkbox_urodzonyod = new javax.swing.JCheckBox();
        combobox_lok = new javax.swing.JComboBox();
        combobox_plec = new javax.swing.JComboBox();
        label_plec_lok = new javax.swing.JLabel();
        label_urodzony = new javax.swing.JLabel();
        label_wizyta1 = new javax.swing.JLabel();
        checkbox_pierwsza = new javax.swing.JCheckBox();
        checkbox_kolejka = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_terminarz = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        button_wyczysc = new javax.swing.JButton();
        button_pokaz = new javax.swing.JButton();
        button_usun = new javax.swing.JButton();
        txtfield_szukaj = new javax.swing.JTextField();
        label_szukaj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        label_punkty.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_punkty.setText("Punkty:");

        label_czas.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_czas.setText("Czas:");

        label_wizyty.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_wizyty.setText("Wizyty:");

        txtfield_iloscwizyt.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtfield_iloscwizyt.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtfield_czas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtfield_czas.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtfield_punkty.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtfield_punkty.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtfield_pln.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtfield_pln.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfield_pln.setEnabled(false);

        label_pln.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_pln.setText("PLN:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_wizyty)
                    .addComponent(label_czas)
                    .addComponent(label_punkty)
                    .addComponent(label_pln))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfield_pln, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(txtfield_punkty)
                    .addComponent(txtfield_czas)
                    .addComponent(txtfield_iloscwizyt))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_punkty)
                    .addComponent(txtfield_punkty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_czas)
                    .addComponent(txtfield_czas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_wizyty)
                    .addComponent(txtfield_iloscwizyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfield_pln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_pln))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        checkbox_komorka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_komorka.setText("Komórka");

        checkbox_personel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_personel.setText("Personel");

        checkbox_pacjent.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_pacjent.setText("Pacjent");

        checkbox_adres.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_adres.setText("Adres");

        checkbox_telefon.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_telefon.setText("Telefon");

        checkbox_wizyta.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_wizyta.setText("Wizyta");

        checkbox_data.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_data.setText("Data");

        checkbox_godzina.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_godzina.setText("Godzina");

        checkbox_uwagi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_uwagi.setText("Uwagi");

        button_dopliku.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_dopliku.setText("Do pliku");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(checkbox_komorka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox_pacjent, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkbox_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox_data, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkbox_uwagi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(checkbox_personel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox_adres, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                        .addComponent(checkbox_wizyta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox_godzina, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_dopliku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_komorka)
                    .addComponent(checkbox_pacjent)
                    .addComponent(checkbox_telefon)
                    .addComponent(checkbox_data)
                    .addComponent(checkbox_uwagi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkbox_personel)
                        .addComponent(checkbox_adres)
                        .addComponent(checkbox_wizyta)
                        .addComponent(checkbox_godzina))
                    .addComponent(button_dopliku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

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
        combobox_komorka.setMaximumSize(new java.awt.Dimension(265, 265));

        combobox_personel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_personel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_personel.setMaximumSize(new java.awt.Dimension(265, 265));

        combobox_pacjent.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_pacjent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_pacjent.setMaximumSize(new java.awt.Dimension(265, 265));

        combobox_wizyta.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_wizyta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        combobox_wizyta.setMaximumSize(new java.awt.Dimension(265, 265));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_komorka, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(label_personel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_pacjent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_wizyta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combobox_personel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_pacjent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_wizyta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_komorka, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_komorka)
                    .addComponent(combobox_komorka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_personel)
                    .addComponent(combobox_personel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_pacjent)
                    .addComponent(combobox_pacjent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_wizyta)
                    .addComponent(combobox_wizyta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        data_do.setDateFormatString("dd-MM-yyyy");
        data_do.setEnabled(false);
        data_do.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        checkbox_datado.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_datado.setText("do:");

        data_od.setDateFormatString("dd-MM-yyyy");
        data_od.setEnabled(false);
        data_od.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        checkbox_dataod.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_dataod.setText("od:");

        labe_data.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        labe_data.setText("Data:");

        urodzony_do.setDateFormatString("dd-MM-yyyy");
        urodzony_do.setEnabled(false);
        urodzony_do.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        checkbox_urodzonydo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_urodzonydo.setText("do:");

        urodzony_od.setDateFormatString("dd-MM-yyyy");
        urodzony_od.setEnabled(false);
        urodzony_od.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        checkbox_urodzonyod.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_urodzonyod.setText("od:");

        combobox_lok.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_lok.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        combobox_plec.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_plec.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        label_plec_lok.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_plec_lok.setText("Płeć/Lok:");

        label_urodzony.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_urodzony.setText("Urodzony:");

        label_wizyta1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_wizyta1.setText("Status:");

        checkbox_pierwsza.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_pierwsza.setText("Pierwsza");

        checkbox_kolejka.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        checkbox_kolejka.setText("Kolejka");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(labe_data)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkbox_dataod))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(label_urodzony)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                                .addComponent(checkbox_urodzonyod))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(label_plec_lok)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combobox_plec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(urodzony_od, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkbox_urodzonydo))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(data_od, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkbox_datado)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(data_do, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(combobox_lok, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(urodzony_do, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(label_wizyta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_pierwsza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkbox_kolejka)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkbox_dataod)
                            .addComponent(labe_data)))
                    .addComponent(data_od, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_datado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(data_do, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(urodzony_do, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_urodzonydo)
                    .addComponent(urodzony_od, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkbox_urodzonyod)
                        .addComponent(label_urodzony)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combobox_lok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combobox_plec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_plec_lok)))
                .addGap(2, 2, 2)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_wizyta1)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkbox_kolejka)
                        .addComponent(checkbox_pierwsza)))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        jScrollPane1.setViewportView(table_terminarz);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder, 2));

        button_wyczysc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_wyczysc.setText("Wyczyść");

        button_pokaz.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_pokaz.setText("Pokaż");

        button_usun.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_usun.setText("Usuń");

        txtfield_szukaj.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtfield_szukaj.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        label_szukaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_szukaj.setText("Szukaj:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label_szukaj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfield_szukaj))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button_pokaz, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_usun, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_wyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_wyczysc)
                    .addComponent(button_pokaz)
                    .addComponent(button_usun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfield_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_szukaj))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        erp_view.setEnabled(true);        // TODO add your handling code here:
        erp_view.setVisible(true);
    }//GEN-LAST:event_formWindowClosed
    
    public void margeColumns()
    {
        
        this.setColumnWidth("idkalendarz",0);
        this.setColumnWidth("idpersonel",0);
        this.setColumnWidth("idwizyta",0);
        this.setColumnWidth("idpacjent",0);
        this.setColumnWidth("idkomorka",0);  
        this.setColumnWidth("1", 20, 20, 20);
        this.setColumnWidth("S", 20, 20, 20);
        
        if(checkbox_komorka.isSelected())
        {
            this.setColumnWidth("Komórka", 120);
        }
        else
        {
            this.setColumnWidth("Komórka", 0);
        }
        if(checkbox_personel.isSelected())
        {
            this.setColumnWidth("Personel", 120);
        }
        else
        {
            this.setColumnWidth("Personel", 0);
        }        
        if(checkbox_pacjent.isSelected())
        {
            this.setColumnWidth("Pacjent", 120);
        }
        else
        {
            this.setColumnWidth("Pacjent", 0);
        }      
        if(checkbox_adres.isSelected())
        {
            this.setColumnWidth("Adres", 120);
        }
        else
        {
            this.setColumnWidth("Adres", 0);
        }   
        if(checkbox_telefon.isSelected())
        {
            this.setColumnWidth("Telefon", 120);
        }
        else
        {
            this.setColumnWidth("Telefon", 0);
        }    
        if(checkbox_personel.isSelected())
        {
            this.setColumnWidth("Personel", 120);
        }
        else
        {
            this.setColumnWidth("Personel", 0);
        }        
        if(checkbox_wizyta.isSelected())
        {
            this.setColumnWidth("Wizyta", 120);
        }
        else
        {
            this.setColumnWidth("Wizyta", 0);
        }       
        if(checkbox_godzina.isSelected())
        {
            this.setColumnWidth("Godz.", 50, 50, 50);
        }
        else
        {
            this.setColumnWidth("Godz.", 0);
        } 
        if(checkbox_data.isSelected())
        {
            this.setColumnWidth("Data", 80, 80, 80);
        }
        else
        {
            this.setColumnWidth("Data", 0);
        } 
        setColumnWidth("1", 20, 20, 0);
        if(checkbox_uwagi.isSelected())
        {
            this.setColumnWidth("Uwagi", 120);
        }
        else
        {
            this.setColumnWidth("Uwagi", 0);
        }
        setColumnWidth("S", 20, 20, 0);
    }
        
    private void initTable(){
        terminarz_table_model = new DefaultTableModel(){
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
        terminarz_table_model.addColumn("idkalendarz");
        terminarz_table_model.addColumn("idkomorka");
        terminarz_table_model.addColumn("idpersonel");
        terminarz_table_model.addColumn("idpacjent");
        terminarz_table_model.addColumn("idwizyta");
        terminarz_table_model.addColumn("Komórka");
        terminarz_table_model.addColumn("Personel");
        terminarz_table_model.addColumn("Pacjent");
        terminarz_table_model.addColumn("Adres");
        terminarz_table_model.addColumn("Telefon");
        terminarz_table_model.addColumn("Wizyta");
        terminarz_table_model.addColumn("Data");
        terminarz_table_model.addColumn("Godz.");
        terminarz_table_model.addColumn("1");
        terminarz_table_model.addColumn("Uwagi");
        terminarz_table_model.addColumn("S");
        
        table_terminarz.setModel(terminarz_table_model);
        
        this.checkbox_komorka.setSelected(false);
        this.checkbox_personel.setSelected(true);
        this.checkbox_pacjent.setSelected(true);
        this.checkbox_adres.setSelected(true);
        this.checkbox_telefon.setSelected(true);
        this.checkbox_wizyta.setSelected(false);
        this.checkbox_godzina.setSelected(true);
        this.checkbox_data.setSelected(true);
        this.checkbox_uwagi.setSelected(true);
        
        margeColumns();

        table_terminarz.getTableHeader().setFont(font_bold);
        
        table_terminarz.setFont(font);
	table_terminarz.setDefaultRenderer(String.class, centerRenderer);
        table_terminarz.setRowHeight(25);
        terminarz_row_sorter = new TableRowSorter<>(table_terminarz.getModel());
        table_terminarz.setRowSorter(terminarz_row_sorter);
    }
    private void initCombobox(){
        combobox_plec.addItem("Wybierz płeć");
        combobox_plec.addItem("MĘŻCZYZNA");
        combobox_plec.addItem("KOBIETA");
        
        combobox_lok.addItem("Wybierz lok");
        combobox_lok.addItem("MIASTO");
        combobox_lok.addItem("WIEŚ");  
    }
    private void initCal(){
        data_od.setDate(new Date());
        data_do.setDate(new Date());
        urodzony_od.setDate(new Date());
        urodzony_do.setDate(new Date());
    }
    private void initTxtFields(){
        txtfield_punkty.setEditable(false);
        txtfield_czas.setEditable(false);
        txtfield_iloscwizyt.setEditable(false);
        //((AbstractDocument) txtfield_szukaj.getDocument()).setDocumentFilter(new MyOwnClasses.UppercaseDocumentFilter());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_dopliku;
    private javax.swing.JButton button_pokaz;
    private javax.swing.JButton button_usun;
    private javax.swing.JButton button_wyczysc;
    private javax.swing.JCheckBox checkbox_adres;
    private javax.swing.JCheckBox checkbox_data;
    private javax.swing.JCheckBox checkbox_datado;
    private javax.swing.JCheckBox checkbox_dataod;
    private javax.swing.JCheckBox checkbox_godzina;
    private javax.swing.JCheckBox checkbox_kolejka;
    private javax.swing.JCheckBox checkbox_komorka;
    private javax.swing.JCheckBox checkbox_pacjent;
    private javax.swing.JCheckBox checkbox_personel;
    private javax.swing.JCheckBox checkbox_pierwsza;
    private javax.swing.JCheckBox checkbox_telefon;
    private javax.swing.JCheckBox checkbox_urodzonydo;
    private javax.swing.JCheckBox checkbox_urodzonyod;
    private javax.swing.JCheckBox checkbox_uwagi;
    private javax.swing.JCheckBox checkbox_wizyta;
    private javax.swing.JComboBox combobox_komorka;
    private javax.swing.JComboBox combobox_lok;
    private javax.swing.JComboBox combobox_pacjent;
    private javax.swing.JComboBox combobox_personel;
    private javax.swing.JComboBox combobox_plec;
    private javax.swing.JComboBox combobox_wizyta;
    private com.toedter.calendar.JDateChooser data_do;
    private com.toedter.calendar.JDateChooser data_od;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labe_data;
    private javax.swing.JLabel label_czas;
    private javax.swing.JLabel label_komorka;
    private javax.swing.JLabel label_pacjent;
    private javax.swing.JLabel label_personel;
    private javax.swing.JLabel label_plec_lok;
    private javax.swing.JLabel label_pln;
    private javax.swing.JLabel label_punkty;
    private javax.swing.JLabel label_szukaj;
    private javax.swing.JLabel label_urodzony;
    private javax.swing.JLabel label_wizyta;
    private javax.swing.JLabel label_wizyta1;
    private javax.swing.JLabel label_wizyty;
    private javax.swing.JTable table_terminarz;
    private javax.swing.JTextField txtfield_czas;
    private javax.swing.JTextField txtfield_iloscwizyt;
    private javax.swing.JTextField txtfield_pln;
    private javax.swing.JTextField txtfield_punkty;
    private javax.swing.JTextField txtfield_szukaj;
    private com.toedter.calendar.JDateChooser urodzony_do;
    private com.toedter.calendar.JDateChooser urodzony_od;
    // End of variables declaration//GEN-END:variables
}
