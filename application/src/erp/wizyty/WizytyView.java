/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.wizyty;

import erp.ERP_View;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
public final class WizytyView extends javax.swing.JFrame {
    
    DefaultTableModel wizyta_table_model;
    TableRowSorter<TableModel> wizyta_row_sorter; 
    ERP_View erp_view;
    /**
     * Creates new form WizytaView
     */
    public WizytyView(ERP_View erp_view) {
        this.erp_view = erp_view;
        initComponents();
        table_model_init();
        txtfields_init();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public String getCzas() {
        return txtfield_czas.getText();
    }
    public String getNazwa() {
        return txtfield_nazwa.getText();
    }
    public String getPunkty() {
        return txtfield_punkty.getText();
    }
    public int getTableSelectedRow(){
        return table_wizyta.getSelectedRow();
    }
    public int getId(int row){
        return Integer.valueOf((String)table_wizyta.getValueAt(row, 0));
    }
    public int getTyp(){
        return combobox_komtyp.getSelectedIndex();
    }
    
    
    public ArrayList<String> getValues(){
        ArrayList<String> values = new ArrayList<>(Arrays.asList(getNazwa(), getCzas(), getPunkty(), String.valueOf(getTyp())));
        return values;
    }
    
    public void setEnabledDodaj(boolean enabled){
        button_dodaj.setEnabled(enabled);
    }
    
    public void setCzas(String czas) {
        this.txtfield_czas.setText(czas);
    }
    public void setNazwa(String  nazwa) {
        this.txtfield_nazwa.setText(nazwa);
    }
    public void setPunkty(String  punkty) {
        this.txtfield_punkty.setText(punkty);
    }
    public void setValues(ArrayList<String> values){
        this.txtfield_nazwa.setText(values.get(0));
        this.txtfield_czas.setText(values.get(1));
        this.txtfield_punkty.setText(values.get(2));
    }
    public void buttonsState(boolean status){
        button_usun.setEnabled(status);
        button_zmien.setEnabled(status);
        button_dodaj.setEnabled(!status);
    }
    
    void dodaj_wizyta(ActionListener listenForDodajWizyta){
        button_dodaj.addActionListener(listenForDodajWizyta);
    }  
    void zmien_dane(ActionListener listenForZmienDane){
        button_zmien.addActionListener(listenForZmienDane);
    }
    void usun_wizyta(ActionListener listenForUsunWizyta){
        button_usun.addActionListener(listenForUsunWizyta);
    }  
    void wyczysc_pola(ActionListener listenForWyczyscPola){
        button_wyczysc.addActionListener(listenForWyczyscPola);
    }
    void zaznaczono_wiersz(ListSelectionListener listenForZaznaczonoWiersz){
        table_wizyta.getSelectionModel().addListSelectionListener(listenForZaznaczonoWiersz);
    }
    
    public void dodaj_wiersz(String id, String nazwa, String czas, String punkty){       
        wizyta_table_model.addRow(new String[]{id,nazwa,czas,punkty});
    }
    public void wyczysc_tabele(){
       wizyta_table_model.setRowCount(0);
    }
    
    void combobox_komtyp_action(ActionListener listen){
        combobox_komtyp.addActionListener(listen);
    }
    
    void table_model_init(){     
        wizyta_table_model = new DefaultTableModel(){
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
        wizyta_table_model.addColumn("Id");
        wizyta_table_model.addColumn("Nazwa");
        wizyta_table_model.addColumn("Czas");
        wizyta_table_model.addColumn("Punkty");
        table_wizyta.setModel(wizyta_table_model);
        table_wizyta.getColumn("Id").setWidth(0);
        table_wizyta.getColumn("Id").setMaxWidth(0);
        table_wizyta.getColumn("Id").setMinWidth(0);
        table_wizyta.getColumn("Id").setPreferredWidth(0);   
        table_wizyta.getColumn("Czas").setMinWidth(50);
        table_wizyta.getColumn("Czas").setMaxWidth(50);
        table_wizyta.getColumn("Punkty").setMinWidth(60);
        table_wizyta.getColumn("Punkty").setMaxWidth(60);
        table_wizyta.setAutoCreateRowSorter(true); 
        wizyta_row_sorter = new TableRowSorter<>(table_wizyta.getModel());
        table_wizyta.setRowSorter(wizyta_row_sorter);
        //table_pacjenci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
        table_wizyta.setRowSelectionAllowed(true);
        table_wizyta.setDefaultRenderer(String.class, new MyOwnClasses.VisitorRenderer());
        table_wizyta.setFont(new Font("Calibri", Font.PLAIN, 14));
        table_wizyta.getTableHeader().setFont( new Font( "Calibri", Font.BOLD, 14));
    }   
    void txtfields_init(){
        
    ((AbstractDocument) txtfield_nazwa.getDocument()).setDocumentFilter(new MyOwnClasses.NazwaDocumentFilter());
    ((AbstractDocument) txtfield_czas.getDocument()).setDocumentFilter(new MyOwnClasses.CzasDocumentFilter());
    ((AbstractDocument) txtfield_punkty.getDocument()).setDocumentFilter(new MyOwnClasses.CzasDocumentFilter());    

    }
    void clear_selection(){
        this.table_wizyta.clearSelection();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_wizyta = new javax.swing.JPanel();
        label_nazwa = new javax.swing.JLabel();
        txtfield_nazwa = new javax.swing.JTextField();
        label_czas = new javax.swing.JLabel();
        txtfield_czas = new javax.swing.JTextField();
        label_punkty = new javax.swing.JLabel();
        txtfield_punkty = new javax.swing.JTextField();
        button_usun = new javax.swing.JButton();
        button_zmien = new javax.swing.JButton();
        button_dodaj = new javax.swing.JButton();
        button_wyczysc = new javax.swing.JButton();
        combobox_komtyp = new javax.swing.JComboBox();
        label_punkty1 = new javax.swing.JLabel();
        scrollpane_wizyta = new javax.swing.JScrollPane();
        table_wizyta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        label_nazwa.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_nazwa.setText("Nazwa:");

        txtfield_nazwa.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_czas.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_czas.setText("Czas:");

        txtfield_czas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        label_punkty.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_punkty.setText("Punkty:");

        txtfield_punkty.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        button_usun.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_usun.setText("Usuń");

        button_zmien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_zmien.setText("Zmień");

        button_dodaj.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        button_dodaj.setText("Dodaj");

        button_wyczysc.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        button_wyczysc.setText("Wyczyść");

        combobox_komtyp.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        combobox_komtyp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Wybierz", "Poradnia psychiatryczna", "Poradnia psychiatryczna DZiM","Poradnia psychologiczna", "Zespół leczenia środowiskowego"}));

        label_punkty1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        label_punkty1.setText("Typ:");

        javax.swing.GroupLayout panel_wizytaLayout = new javax.swing.GroupLayout(panel_wizyta);
        panel_wizyta.setLayout(panel_wizytaLayout);
        panel_wizytaLayout.setHorizontalGroup(
            panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wizytaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_nazwa, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(label_punkty1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_wizytaLayout.createSequentialGroup()
                        .addComponent(combobox_komtyp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(label_czas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfield_czas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_punkty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfield_punkty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtfield_nazwa))
                .addGap(18, 18, 18)
                .addGroup(panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_dodaj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_zmien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_wyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_wizytaLayout.setVerticalGroup(
            panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wizytaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_nazwa)
                    .addComponent(txtfield_nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_usun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_dodaj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_wizytaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_punkty1)
                    .addComponent(combobox_komtyp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_czas)
                    .addComponent(txtfield_czas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_punkty)
                    .addComponent(txtfield_punkty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_zmien)
                    .addComponent(button_wyczysc))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        table_wizyta.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        table_wizyta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollpane_wizyta.setViewportView(table_wizyta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_wizyta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpane_wizyta, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_wizyta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane_wizyta, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
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
    private javax.swing.JComboBox combobox_komtyp;
    private javax.swing.JLabel label_czas;
    private javax.swing.JLabel label_nazwa;
    private javax.swing.JLabel label_punkty;
    private javax.swing.JLabel label_punkty1;
    private javax.swing.JPanel panel_wizyta;
    private javax.swing.JScrollPane scrollpane_wizyta;
    private javax.swing.JTable table_wizyta;
    private javax.swing.JTextField txtfield_czas;
    private javax.swing.JTextField txtfield_nazwa;
    private javax.swing.JTextField txtfield_punkty;
    // End of variables declaration//GEN-END:variables
}
