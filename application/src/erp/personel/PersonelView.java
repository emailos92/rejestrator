/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.personel;

import erp.ERP_View;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;
import library.MyOwnClasses;

/**
 *
 * @author Arkita
 */
public final class PersonelView extends javax.swing.JFrame {

    /**
     * Creates new form PersonelView
     */
    DefaultTableModel personel_table_model;
    TableRowSorter<TableModel> personel_row_sorter;    
    ERP_View erp_view;
            
    public PersonelView(ERP_View erp_view) {
        this.erp_view=erp_view;
        initComponents();
        table_model_init();
        txtfields_init();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public String getNazwiskoImie() {
        return txtfield_nazwisko_imie.getText();
    }
    public String getAdres() {
        return txtfield_adres.getText();
    }
    public String getTelefon() {
        return txtfield_telefon.getText();
    }
    public int getId(int row){
        return Integer.valueOf((String)table_personel.getValueAt(row, 0));
    }
    public int getTableSelectedRow(){
        return table_personel.getSelectedRow();
    }      
    public String getSzukaj(){	         
	return txtfield_szukaj.getText();	         
    } 
    public ArrayList<String> getValues(){
        ArrayList<String> values = new ArrayList<>(Arrays.asList(getNazwiskoImie(), getAdres(), getTelefon()));
        return values;
    }

    public void setNazwiskoImie(String nazwisko_imie) {
        this.txtfield_nazwisko_imie.setText(nazwisko_imie);
    }
    public void setAdres(String adres) {
        this.txtfield_adres.setText(adres);
    }
    public void setTelefon(String telefon) {
        this.txtfield_telefon.setText(telefon);
    }   
    public void setRowSorterer(String text){
        personel_row_sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
    }  
    public void buttonsState(boolean status){
        button_usun.setEnabled(status);
        button_zmien.setEnabled(status);
        button_dodaj.setEnabled(!status);
    }

    void dodaj_personel(ActionListener listenForDodajPersonel){
        button_dodaj.addActionListener(listenForDodajPersonel);
    }  
    void zmien_dane(ActionListener listenForZmienDane){
        button_zmien.addActionListener(listenForZmienDane);
    }        
    void usun_personel(ActionListener listenForUsunPersonel){
        button_usun.addActionListener(listenForUsunPersonel);
    }   
    void wyczysc_pola(ActionListener listenForWyczyscPola){
        button_wyczysc.addActionListener(listenForWyczyscPola);
    }
    void zaznaczono_wiersz(ListSelectionListener listenForZaznaczonoWiersz){
        table_personel.getSelectionModel().addListSelectionListener(listenForZaznaczonoWiersz);
    }
    void txtfield_szukaj(DocumentListener listenForSzukaj){
        txtfield_szukaj.getDocument().addDocumentListener(listenForSzukaj);
    }
        
    public void dodaj_wiersz(String id, String nazwisko_imie, String adres, String telefon){       
        personel_table_model.addRow(new String[]{id,nazwisko_imie,adres,telefon});
    }    
    public void wyczysc_tabele(){
       personel_table_model.setRowCount(0);
    }    
    void table_model_init(){     
        personel_table_model = new DefaultTableModel(){
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
        personel_table_model.addColumn("Id");
        personel_table_model.addColumn("Nazwisko i imię");
        personel_table_model.addColumn("Adres");
        personel_table_model.addColumn("Telefon");                            
        table_personel.setModel(personel_table_model);
        table_personel.getColumn("Id").setWidth(0);
        table_personel.getColumn("Id").setMaxWidth(0);
        table_personel.getColumn("Id").setMinWidth(0);
        table_personel.getColumn("Id").setPreferredWidth(0);
        table_personel.getColumn("Telefon").setMinWidth(80);
        table_personel.getColumn("Telefon").setMaxWidth(80);
        table_personel.getColumn("Nazwisko i imię").setMinWidth(220);
        table_personel.getColumn("Nazwisko i imię").setMaxWidth(220);
        table_personel.setAutoCreateRowSorter(true); 
        personel_row_sorter = new TableRowSorter<>(table_personel.getModel());
        table_personel.setRowSorter(personel_row_sorter);
        //table_pacjenci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
        table_personel.setRowSelectionAllowed(true);
        table_personel.setDefaultRenderer(String.class, new MyOwnClasses.VisitorRenderer());
        table_personel.setFont(new Font("Calibri", Font.PLAIN, 14));
        table_personel.getTableHeader().setFont( new Font( "Calibri", Font.BOLD, 14));
    }    
    void txtfields_init(){
        
    ((AbstractDocument) txtfield_nazwisko_imie.getDocument()).setDocumentFilter(new MyOwnClasses.NazwiskoDocumentFilter());
    ((AbstractDocument) txtfield_telefon.getDocument()).setDocumentFilter(new MyOwnClasses.TelefonDocumentFilter());    
    ((AbstractDocument) txtfield_adres.getDocument()).setDocumentFilter(new MyOwnClasses.AdresDocumentFilter());   
    ((AbstractDocument) txtfield_szukaj.getDocument()).setDocumentFilter(new MyOwnClasses.UppercaseDocumentFilter());
    
    }
    void clear_selection(){
        this.table_personel.clearSelection();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label_nazwisko_imie = new javax.swing.JLabel();
        txtfield_nazwisko_imie = new javax.swing.JTextField();
        label_adres = new javax.swing.JLabel();
        label_telefon = new javax.swing.JLabel();
        txtfield_telefon = new javax.swing.JTextField();
        button_dodaj = new javax.swing.JButton();
        button_usun = new javax.swing.JButton();
        button_wyczysc = new javax.swing.JButton();
        txtfield_adres = new javax.swing.JTextField();
        button_zmien = new javax.swing.JButton();
        txtfield_szukaj = new javax.swing.JTextField();
        label_szukaj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_personel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        label_nazwisko_imie.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_nazwisko_imie.setText("Nazwisko i imię:");

        txtfield_nazwisko_imie.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_adres.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_adres.setText("Adres:");

        label_telefon.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_telefon.setText("Telefon:");

        txtfield_telefon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        button_dodaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_dodaj.setText("Dodaj personel");

        button_usun.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_usun.setText("Usuń personel");

        button_wyczysc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_wyczysc.setText("Wyczyść pola");

        txtfield_adres.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        button_zmien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_zmien.setText("Zmień personel");

        txtfield_szukaj.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_szukaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_szukaj.setText("Szukaj:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_nazwisko_imie)
                            .addComponent(label_adres))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtfield_nazwisko_imie)
                                .addGap(18, 18, 18)
                                .addComponent(label_telefon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfield_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtfield_adres)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_usun, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_zmien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_wyczysc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label_szukaj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtfield_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_nazwisko_imie)
                    .addComponent(txtfield_nazwisko_imie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_telefon)
                    .addComponent(txtfield_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_adres)
                    .addComponent(txtfield_adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_usun, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_wyczysc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_zmien, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfield_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_szukaj))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        table_personel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_personel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_dodaj;
    private javax.swing.JButton button_usun;
    private javax.swing.JButton button_wyczysc;
    private javax.swing.JButton button_zmien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_adres;
    private javax.swing.JLabel label_nazwisko_imie;
    private javax.swing.JLabel label_szukaj;
    private javax.swing.JLabel label_telefon;
    private javax.swing.JTable table_personel;
    private javax.swing.JTextField txtfield_adres;
    private javax.swing.JTextField txtfield_nazwisko_imie;
    private javax.swing.JTextField txtfield_szukaj;
    private javax.swing.JTextField txtfield_telefon;
    // End of variables declaration//GEN-END:variables
}
