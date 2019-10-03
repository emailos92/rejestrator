/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.pacjenci;

import erp.ERP_View;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

import library.MyOwnClasses.*;

/**
 *
 * @author Arkita
 */
public final class PacjenciView extends javax.swing.JFrame {
    
    DefaultTableModel pacjenci_table_model;
    TableRowSorter<TableModel> pacjenci_row_sorter;           
    ButtonGroup lokalizacja;
    ButtonGroup plec;
    ERP_View erp_view;
    
    public PacjenciView(ERP_View erp_view) {
        this.erp_view = erp_view;
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table_model_init();
        txtfields_init();
        rbutton_init();
    }
    
    public String getNazwisko(){	         
	return txtfield_nazwisko.getText();	         
    }
    public String getImie(){	         
	return txtfield_imie.getText();	         
    }   
    public String getPesel(){	         
	return txtfield_pesel.getText();	         
    }    
    public String getTelefon(){	         
	return txtfield_telefon.getText();	         
    }    
    public String getAdres(){	         
	return txtfield_adres.getText();	         
    }     
    public String getSzukaj(){	         
	return txtfield_szukaj.getText();	         
    }  
    public int getTableSelectedRow(){
        return table_pacjenci.getSelectedRow();
    }    
    public int getId(int row){
        return Integer.valueOf((String)table_pacjenci.getValueAt(row, 0));
    }
    public String getLok(){
        if(rbutton_wies.isSelected() == true)
            return "WIEŚ";
        else if(rbutton_miasto.isSelected() == true)
            return "MIASTO";
        else
            return " ";
    }
    public String getPlec(){
        if(rbutton_man.isSelected() == true)
            return "MĘŻCZYZNA";
        else if(rbutton_woman.isSelected() == true)
            return "KOBIETA";
        else
            return " ";
    }
    
    public ArrayList<String> getValues(){
        ArrayList<String> values = new ArrayList<>(Arrays.asList(getNazwisko(), getImie(), getPesel(), getTelefon(), getAdres(), getPlec(), getLok()));
        return values;
    }
    

    public void setRowSorterer(String text){
        pacjenci_row_sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
    }
    public void setSzukaj(String szukaj){
        txtfield_szukaj.setText(szukaj);
    }
    public void setNazwisko(String nazwisko){
        txtfield_nazwisko.setText(nazwisko);
    }    
    public void setImie(String imie){
        txtfield_imie.setText(imie);
    }    
    public void setPesel(String pesel){
        txtfield_pesel.setText(pesel);
    }    
    public void setTelefon(String telefon1){
        txtfield_telefon.setText(telefon1);
    }           
    public void setAdres(String adres){
        txtfield_adres.setText(adres);
    }
    public void setLok(String lok){
        if(lok.equals("MIASTO")){
            rbutton_miasto.setSelected(true);
        }else if (lok.equals("WIEŚ")){
            rbutton_wies.setSelected(true);   
        }else{
            rbutton_miasto.setSelected(false);
            rbutton_wies.setSelected(false);
        }
    }
    public void setPlec(String plec){
        if(plec.equals("MĘŻCZYZNA")){
            rbutton_man.setSelected(true);
        }else if (plec.equals("KOBIETA")){
            rbutton_woman.setSelected(true);   
        }else{
            rbutton_man.setSelected(false);
            rbutton_woman.setSelected(false);
        }
    }
    
    public void setValues(ArrayList<String> values){
        setNazwisko(values.get(0));
        setImie(values.get(1));
        setPesel(values.get(2));
        setTelefon(values.get(3));
        setAdres(values.get(4));
        setPlec(values.get(5));
        setLok(values.get(6));
    }
    public void buttonsState(boolean status){
        button_usun.setEnabled(status);
        button_zmien.setEnabled(status);
        button_dodaj.setEnabled(!status);
    }
    public void setStatus(String tekst)
    {
        txtfield_status.setText(tekst);
    }
    
    //EVENTS
    void dodaj_pacjenta(ActionListener listen){
        button_dodaj.addActionListener(listen);
    }  
    void zmien_dane(ActionListener listen){
        button_zmien.addActionListener(listen);
    }        
    void usun_pacjenta(ActionListener listen){
        button_usun.addActionListener(listen);
    }   
    void wyczysc_pola(ActionListener listen){
        button_wyczysc.addActionListener(listen);
    }
    void odswiez_liste(ActionListener listen){
        button_refresh.addActionListener(listen);
    }
    void zaznaczono_wiersz(ListSelectionListener listen){
        table_pacjenci.getSelectionModel().addListSelectionListener(listen);
    }
    void txtfield_szukaj(DocumentListener listen){
        txtfield_szukaj.getDocument().addDocumentListener(listen);
    }   

    //FUNKCJE
    public void dodaj_wiersz(String id, String nazwisko, String imie, String pesel, String telefon, String adres, String plec, String lok){       
        pacjenci_table_model.addRow(new String[]{id,nazwisko,imie,pesel,telefon,adres,plec,lok});
    }    
    public void wyczysc_tabele(){
       pacjenci_table_model.setRowCount(0);
    }
    void rbutton_init(){
        lokalizacja = new ButtonGroup();
        rbutton_wies.setSelected(true);
        lokalizacja.add(rbutton_wies);
        lokalizacja.add(rbutton_miasto);
        
        plec = new ButtonGroup();
        rbutton_man.setSelected(true);
        plec.add(rbutton_man);
        plec.add(rbutton_woman);
    }
    void table_model_init(){     
        pacjenci_table_model = new DefaultTableModel()
        {
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
        pacjenci_table_model.addColumn("Id");
        pacjenci_table_model.addColumn("Nazwisko");
        pacjenci_table_model.addColumn("Imię");
        pacjenci_table_model.addColumn("Pesel");
        pacjenci_table_model.addColumn("Telefon");                    
        pacjenci_table_model.addColumn("Adres");
        pacjenci_table_model.addColumn("Płeć");
        pacjenci_table_model.addColumn("Lok");
        table_pacjenci.setModel(pacjenci_table_model);
        table_pacjenci.getColumn("Id").setWidth(0);
        table_pacjenci.getColumn("Id").setMaxWidth(0);
        table_pacjenci.getColumn("Id").setMinWidth(0);
        table_pacjenci.getColumn("Id").setPreferredWidth(0);
        table_pacjenci.getColumn("Lok").setMinWidth(60);
        table_pacjenci.getColumn("Lok").setMaxWidth(60);
        table_pacjenci.getColumn("Pesel").setMinWidth(90);
        table_pacjenci.getColumn("Pesel").setMaxWidth(90);
        table_pacjenci.getColumn("Telefon").setMinWidth(70);
        table_pacjenci.getColumn("Nazwisko").setMinWidth(100);
        table_pacjenci.getColumn("Imię").setMinWidth(80);
        table_pacjenci.getColumn("Adres").setMinWidth(40);
        table_pacjenci.setAutoCreateRowSorter(true);    
        pacjenci_row_sorter = new TableRowSorter<>(table_pacjenci.getModel());
        table_pacjenci.setRowSorter(pacjenci_row_sorter);     
        table_pacjenci.setRowSelectionAllowed(true);
        table_pacjenci.setDefaultRenderer(String.class, new VisitorRenderer());
        table_pacjenci.setFont(new Font("Calibri", Font.PLAIN, 14));
        table_pacjenci.getTableHeader().setFont( new Font( "Calibri", Font.BOLD, 14));
    }    
    void txtfields_init(){
        ((AbstractDocument) txtfield_nazwisko.getDocument()).setDocumentFilter(new NazwiskoDocumentFilter());
        ((AbstractDocument) txtfield_imie.getDocument()).setDocumentFilter(new ImieDocumentFilter());
        ((AbstractDocument) txtfield_pesel.getDocument()).setDocumentFilter(new PeselDocumentFilter());
        ((AbstractDocument) txtfield_telefon.getDocument()).setDocumentFilter(new TelefonDocumentFilter());    
        ((AbstractDocument) txtfield_adres.getDocument()).setDocumentFilter(new AdresDocumentFilter());   
        ((AbstractDocument) txtfield_szukaj.getDocument()).setDocumentFilter(new UppercaseDocumentFilter());
    }
    void clear_selection(){
        this.table_pacjenci.clearSelection();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane_nowypacjent = new javax.swing.JPanel();
        label_nazwisko = new javax.swing.JLabel();
        txtfield_nazwisko = new javax.swing.JTextField();
        label_imie = new javax.swing.JLabel();
        txtfield_imie = new javax.swing.JTextField();
        label_pesel = new javax.swing.JLabel();
        txtfield_pesel = new javax.swing.JTextField();
        label_telefony = new javax.swing.JLabel();
        txtfield_telefon = new javax.swing.JTextField();
        label_adres = new javax.swing.JLabel();
        txtfield_adres = new javax.swing.JTextField();
        label_plec = new javax.swing.JLabel();
        button_dodaj = new javax.swing.JButton();
        button_usun = new javax.swing.JButton();
        button_zmien = new javax.swing.JButton();
        button_wyczysc = new javax.swing.JButton();
        txtfield_szukaj = new javax.swing.JTextField();
        label_szukaj = new javax.swing.JLabel();
        rbutton_miasto = new javax.swing.JRadioButton();
        rbutton_wies = new javax.swing.JRadioButton();
        rbutton_man = new javax.swing.JRadioButton();
        rbutton_woman = new javax.swing.JRadioButton();
        label_lokalizacja = new javax.swing.JLabel();
        button_refresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        scrollpane_pacjenci = new javax.swing.JScrollPane();
        table_pacjenci = new javax.swing.JTable();
        txtfield_status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        label_nazwisko.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_nazwisko.setText("Nazwisko:");

        txtfield_nazwisko.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_imie.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_imie.setText("Imię:");

        txtfield_imie.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_pesel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_pesel.setText("Pesel:");

        txtfield_pesel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_telefony.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_telefony.setText("Telefon:");

        txtfield_telefon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_adres.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_adres.setText("Adres:");

        txtfield_adres.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_plec.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_plec.setText("Płeć:");

        button_dodaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_dodaj.setText("Dodaj pacjenta");

        button_usun.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_usun.setText("Usuń pacjenta");

        button_zmien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_zmien.setText("Zmień dane");

        button_wyczysc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_wyczysc.setText("Wyczyść pola");

        txtfield_szukaj.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtfield_szukaj.setToolTipText("");

        label_szukaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_szukaj.setText("Szukaj:");

        rbutton_miasto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rbutton_miasto.setText("Miasto");

        rbutton_wies.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rbutton_wies.setText("Wieś");

        rbutton_man.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rbutton_man.setText("Mężczyzna");

        rbutton_woman.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rbutton_woman.setText("Kobieta");

        label_lokalizacja.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_lokalizacja.setText("Lokalizacja:");

        button_refresh.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_refresh.setText("Odśwież listę");

        javax.swing.GroupLayout pane_nowypacjentLayout = new javax.swing.GroupLayout(pane_nowypacjent);
        pane_nowypacjent.setLayout(pane_nowypacjentLayout);
        pane_nowypacjentLayout.setHorizontalGroup(
            pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                        .addComponent(button_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button_usun, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(button_zmien, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_wyczysc, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_refresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                        .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_nazwisko)
                            .addComponent(label_adres)
                            .addComponent(label_szukaj))
                        .addGap(18, 18, 18)
                        .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                                .addComponent(txtfield_nazwisko)
                                .addGap(18, 18, 18)
                                .addComponent(label_imie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfield_imie, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_pesel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfield_pesel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                                .addComponent(txtfield_szukaj)
                                .addGap(18, 18, 18)
                                .addComponent(label_plec)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbutton_man)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbutton_woman)
                                .addGap(18, 18, 18)
                                .addComponent(label_lokalizacja)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbutton_wies)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbutton_miasto))
                            .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                                .addComponent(txtfield_adres)
                                .addGap(18, 18, 18)
                                .addComponent(label_telefony)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfield_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pane_nowypacjentLayout.setVerticalGroup(
            pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_nazwisko)
                    .addComponent(txtfield_nazwisko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_imie)
                    .addComponent(txtfield_imie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_pesel)
                    .addComponent(txtfield_pesel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_telefony)
                    .addComponent(txtfield_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_adres)
                    .addComponent(txtfield_adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_plec)
                    .addComponent(txtfield_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_szukaj)
                    .addComponent(rbutton_miasto)
                    .addComponent(rbutton_wies)
                    .addComponent(rbutton_man)
                    .addComponent(rbutton_woman)
                    .addComponent(label_lokalizacja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pane_nowypacjentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_nowypacjentLayout.createSequentialGroup()
                        .addComponent(button_wyczysc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_refresh))
                    .addComponent(button_zmien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        scrollpane_pacjenci.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        table_pacjenci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollpane_pacjenci.setViewportView(table_pacjenci);

        txtfield_status.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpane_pacjenci, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(txtfield_status)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollpane_pacjenci, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfield_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pane_nowypacjent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pane_nowypacjent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        erp_view.setEnabled(true);        // TODO add your handling code here:
        erp_view.setVisible(true);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_dodaj;
    private javax.swing.JButton button_refresh;
    private javax.swing.JButton button_usun;
    private javax.swing.JButton button_wyczysc;
    private javax.swing.JButton button_zmien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_adres;
    private javax.swing.JLabel label_imie;
    private javax.swing.JLabel label_lokalizacja;
    private javax.swing.JLabel label_nazwisko;
    private javax.swing.JLabel label_pesel;
    private javax.swing.JLabel label_plec;
    private javax.swing.JLabel label_szukaj;
    private javax.swing.JLabel label_telefony;
    private javax.swing.JPanel pane_nowypacjent;
    private javax.swing.JRadioButton rbutton_man;
    private javax.swing.JRadioButton rbutton_miasto;
    private javax.swing.JRadioButton rbutton_wies;
    private javax.swing.JRadioButton rbutton_woman;
    private javax.swing.JScrollPane scrollpane_pacjenci;
    private javax.swing.JTable table_pacjenci;
    private javax.swing.JTextField txtfield_adres;
    private javax.swing.JTextField txtfield_imie;
    private javax.swing.JTextField txtfield_nazwisko;
    private javax.swing.JTextField txtfield_pesel;
    private javax.swing.JTextField txtfield_status;
    private javax.swing.JTextField txtfield_szukaj;
    private javax.swing.JTextField txtfield_telefon;
    // End of variables declaration//GEN-END:variables
}
