/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import erp.bazadanych.BazaDanychView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import erp.grafik.*;
import erp.kalendarz.*;
import erp.komorka.*;
import erp.konta.KontaController;
import erp.loginform.LoginDialog;
import erp.pacjenci.*;
import erp.personel.*;
import erp.pomoc.PomocController;
import erp.terminarz.TerminarzController;
import erp.wizyty.*;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JDialog;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;*/


/**
 *
 * @author Arkita
 */

public class ERP_Controller {
    @SuppressWarnings("unchecked")
    private final ERP_View theView;
    private final ERP_Model theModel;

    private int status = 0;
    JDialog dialog;   
    
    public ERP_Controller() {

        this.theView = new ERP_View();
        this.theModel = new ERP_Model();
        this.theView.button_kalendarz(new Kalendarz());
        this.theView.button_terminarz(new Terminarz());
        this.theView.button_pacjenci(new Pacjenci());
        this.theView.button_wizyty(new Wizyty());
        this.theView.button_komorka(new Komorka());
        this.theView.button_personel(new Personel());
        this.theView.button_bazadanych(new BazaDanych());
        this.theView.button_grafik(new Grafik());
        this.theView.button_konta(new Konta());
        this.theView.button_pomoc(new Pomoc());
        this.theView.button_logowanie(new Logowanie());
        theView.setVisible(true);
        theView.setLabelLicencja("Licencja bezterminowa! :)");            
        status = 0;  //hardcode admin priviledges
        active(status);
    }
    
    public void active(int status){
            theView.setEnabledLogowanie(true);
            if(status == 3){
                theView.setEnabledBazaDanych(true);
                theView.setEnabledGrafik(true);
                theView.setEnabledKalendarz(true);
                theView.setEnabledKomorka(true);
                theView.setEnabledKonta(true);
                theView.setEnabledPacjenci(true);
                theView.setEnabledPersonel(true);
                theView.setEnabledTerminarz(true);
                theView.setEnabledWizyty(true);    

            }else if(status == 2){
                theView.setEnabledBazaDanych(false);
                theView.setEnabledGrafik(true);
                theView.setEnabledKalendarz(true);
                theView.setEnabledKomorka(true);
                theView.setEnabledKonta(false);
                theView.setEnabledPacjenci(true);
                theView.setEnabledPersonel(true);
                theView.setEnabledTerminarz(true);
                theView.setEnabledWizyty(true); 

            }else if(status == 1){
                theView.setEnabledBazaDanych(false);
                theView.setEnabledGrafik(false);
                theView.setEnabledKalendarz(true);
                theView.setEnabledKomorka(false);
                theView.setEnabledKonta(false);
                theView.setEnabledPacjenci(true);
                theView.setEnabledPersonel(false);
                theView.setEnabledTerminarz(true);
                theView.setEnabledWizyty(false); 

            }else{
                theView.setEnabledBazaDanych(false);
                theView.setEnabledGrafik(false);
                theView.setEnabledKalendarz(false);
                theView.setEnabledKomorka(false);
                theView.setEnabledKonta(false);
                theView.setEnabledPacjenci(false);
                theView.setEnabledPersonel(false);
                theView.setEnabledTerminarz(false);
                theView.setEnabledWizyty(false);
            }
    }
    
    /*private String sha256(String base) {
        sha_calc = false;
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        sha_calc = true;
        return hexString.toString();
       
        } catch(Exception ex){
            sha_calc = false;
            throw new RuntimeException(ex);
        }
    }*/
        
    /*public byte[] encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }
 
    public String decrypt(byte[] cipherText) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText),"UTF-8");
    }*/
    
    class Logowanie implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginDialog loginDlg = new LoginDialog(theView);
            loginDlg.setVisible(true);
            
            if(loginDlg.isSucceeded() != 0){
                status = loginDlg.isSucceeded();
                if(status == -1){
                    theView.setTextLog("Logowanie");
                }else if(status ==3){
                    theView.setTextLog("Administrator");
                }else if(status ==2){
                    theView.setTextLog("Moderator");
                }else if(status ==1){
                    theView.setTextLog("Użytkownik");
                }
            }
            
            active(status);
        }  
    } 

    
    class Konta implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            KontaController theController = new KontaController(theView,status);
        }  
    } 

    class Pomoc implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            PomocController theController = new PomocController(theView);
        }  
    } 

    class Kalendarz implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            KalendarzController theController = new KalendarzController(theView);                  
        }  
    } 

    class Terminarz implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            TerminarzController theController = new TerminarzController(theView);
        }  
    } 

    class BazaDanych implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            BazaDanychView BDView = new BazaDanychView(theView,status);
        }  
    } 

    class Pacjenci implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            theView.setEnabled(false);
            theView.setVisible(false);
            PacjenciController theController = new PacjenciController(theView);                            
        }  
    } 

    class Personel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            PersonelController theController = new PersonelController(theView);                  
        }  
    } 

    class Komorka implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            KomorkaController theController = new KomorkaController(theView);        
        }  
    } 

    class Grafik implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            GrafikController theController = new GrafikController(theView);       
        }  
    } 

    class Wizyty implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setEnabled(false);
            theView.setVisible(false);
            WizytyController theController = new WizytyController(theView);       
        }  
    } 

    
}