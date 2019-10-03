/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.pomoc;

import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Arkita
 */
public class PomocController {
     @SuppressWarnings("unchecked")
    private PomocView theView;
        String IV;
        String encryptionKey;
        int weekofmonth,weekofyear;
        SimpleDateFormat sdf, yeardf, monthdf;
        
        public PomocController(ERP_View erp_view){
            theView = new PomocView(erp_view);
            this.theView.button_aktywuj(new Aktywuj());
            this.theView.button_utworz(new Utworz());
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            yeardf = new SimpleDateFormat("yyyy");
            monthdf = new SimpleDateFormat("MM");
            theView.setData(sdf.format(new Date()));
        }
    
    class Aktywuj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String plaintext = "Mamusia74!@#$%^&";
            String decrypted = null;
            String klucz;
            getData();
                    
            try {
                klucz = sha256(sha256(theView.getNazwa() + String.valueOf(weekofmonth) + String.valueOf(weekofyear)));
                IV=klucz.substring(0, 16); //init input vector
                encryptionKey = klucz.substring(16, 32); //init encKey
                System.out.println("IV:   " + IV);
                System.out.println("KEY:   " + encryptionKey);
                byte[] cipher = encrypt(plaintext);
                System.out.print("cipher:  ");
                String haslo = theView.getHaslo();
                if(haslo.length()<32){
                    IV="0000000000000000";
                    encryptionKey="0000000000000000";
                }
                else{
                    IV=(haslo.substring(0,1) + haslo.substring(2,3) + haslo.substring(4,5) + haslo.substring(6,7) +
                        haslo.substring(8,9) + haslo.substring(10,11) + haslo.substring(12,13) + haslo.substring(14,15) +
                        haslo.substring(16,17) + haslo.substring(18,19) + haslo.substring(20,21) + haslo.substring(22,23) +
                        haslo.substring(24,25) + haslo.substring(26,27) + haslo.substring(28,29) + haslo.substring(30,31));
                    encryptionKey =(
                        haslo.substring(1,2) + haslo.substring(3,4) + haslo.substring(5,6) + haslo.substring(7,8) +
                        haslo.substring(9,10) + haslo.substring(11,12) + haslo.substring(13,14) + haslo.substring(15,16) +
                        haslo.substring(17,18) + haslo.substring(19,20) + haslo.substring(21,22) + haslo.substring(23,24) +
                        haslo.substring(25,26) + haslo.substring(27,28) + haslo.substring(29,30) + haslo.substring(31,32));
                }
                decrypted = decrypt(cipher);
            } catch (Exception ex) {
                ex.printStackTrace();
            }           
        if(decrypted.equals(plaintext)){
            theView.setKlucz("SUKCES - PROGRAM ZAAKTYWOWANO POMYŚLNIE");
            Random random=new Random();
            byte[] kod = new byte[16];
            random.nextBytes(kod);
            int[] kol = {random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16),
                         random.nextInt(16),random.nextInt(16),random.nextInt(16),random.nextInt(16)};
            String kod_for_hash = new String();
            for(int i=0;i<32;i++){
                kod_for_hash += String.valueOf(kod[kol[i]]);
            }
            //System.out.println(kod_for_hash);
            String hash_of_kod = sha256(kod_for_hash);
            IV=hash_of_kod.substring(0, 16); //init input vector
            encryptionKey = hash_of_kod.substring(16, 32); //init encKey

            Calendar cal = Calendar.getInstance();
            Date data = cal.getTime();
            int year = Integer.valueOf(yeardf.format(data));
            int month = Integer.valueOf(monthdf.format(data));
            month += 2;
            if(month == 13)
                month=1;
            year+=1;
            String mon;
            if(month < 10)
                 mon = "0" + String.valueOf(month);
            else
                mon = String.valueOf(month);
            String license_data = "01/"+mon+"/"+String.valueOf(year) + "sample";
            byte[] enc_lic_data = null;
                
            try {
                enc_lic_data = encrypt(license_data);
            } catch (Exception ex) {
                Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            OutputStream output = null;
            try {
                output = new FileOutputStream("rejestrator.lic");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i=0;i<16;i++){
            try {
                    output.write(enc_lic_data[i]);
                } catch (IOException ex) {
                    Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(int i=0;i<16;i++){
            try {
                    output.write(kod[i]);
                } catch (IOException ex) {
                    Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(int i=0;i<32;i++){
            try {
                    output.write(kol[i]);
                } catch (IOException ex) {
                    Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
           
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String text = new String();
            for(int i=0;i<16;i++){
                text += String.valueOf(enc_lic_data[i]) + " ";
            }
            for(int i=0;i<16;i++){
                text += String.valueOf(kod[i]) + " ";
            }
            for(int i=0;i<32;i++){
                text += String.valueOf(kol[i]) + " ";
            }
        }
        else{
            theView.setKlucz("BŁĄD - ZŁE HASŁO");
        }   
        }  
    }       
    class Utworz implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            getData();
            theView.setKlucz(sha256(theView.getNazwa() + String.valueOf(weekofmonth) + String.valueOf(weekofyear)));
        }  
    }
    
    private static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

        return hexString.toString();
        } catch(Exception ex){
        throw new RuntimeException(ex);
        }
    }
        
    public byte[] encrypt(String plainText) throws Exception {
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
    }
    
    public void getData(){

        Calendar now = Calendar.getInstance();
        weekofmonth=now.get(Calendar.WEEK_OF_MONTH);
        System.out.println("Current week of month is : " + weekofmonth);

        weekofyear=now.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Current week of year is : " + weekofyear);
 
    }
    
    public String byteToString(byte[] _bytes)
    {
        String file_string = "";

        for(int i = 0; i < _bytes.length; i++)
        {
        file_string += (char)_bytes[i];
        }

        return file_string;    
    }
    
}
