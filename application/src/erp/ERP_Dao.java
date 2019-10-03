/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import erp.bazadanych.BazaDanychView;
import erp.pomoc.PomocController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Arkita Emil
 */

public class ERP_Dao {
     @SuppressWarnings("unchecked")
    private final Connection conn;

    String IV;
    String encryptionKey;
    
    public ERP_Dao(){
        
        String host;
        String nazwa;
        String uzytkownik;
        String haslo;
        
        int host_lenght = 0, nazwa_lenght = 0, uzytkownik_lenght = 0, haslo_lenght = 0;
        int host_lenght_local = 0, nazwa_lenght_local = 0, uzytkownik_lenght_local = 0, haslo_lenght_local = 0;
        
        String sha_dbconf = sha256("Mamusia74!@#");
        IV = sha_dbconf.substring(0, 16);
        encryptionKey = sha_dbconf.substring(16, 32); //init encKey
        
         InputStream input = null;
            try {
                input = new FileInputStream("dbconf.rej");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Brak pliku \"dbconf.rej\" konfigurującego  dostęp do bazy danych!", "Informacja", JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {host_lenght = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            try {nazwa_lenght = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            try {uzytkownik_lenght = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            try {haslo_lenght = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
                        
            int byte_lenght=host_lenght+nazwa_lenght+uzytkownik_lenght+haslo_lenght;
            byte_lenght = byte_lenght + (16 - (byte_lenght % 16));
            byte[] enc = new byte[byte_lenght];

            for(int i=0;i<byte_lenght;i++)
            {
                try {enc[i] = (byte) input.read();}
                catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            }
            
            try {host_lenght_local = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            try {nazwa_lenght_local = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            try {uzytkownik_lenght_local = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            try {haslo_lenght_local = (byte) input.read();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            
            int byte_lenght_local=host_lenght_local+nazwa_lenght_local+uzytkownik_lenght_local+haslo_lenght_local;
            byte_lenght_local = byte_lenght_local + (16 - (byte_lenght_local % 16));
            byte[] enc_local = new byte[byte_lenght_local];

            for(int i=0;i<byte_lenght_local;i++)
            {
                try {enc_local[i] = (byte) input.read();}
                catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Zły plik \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
            }
            
            try {input.close();} 
            catch (IOException ex) {Logger.getLogger(PomocController.class.getName()).log(Level.SEVERE, null, ex);JOptionPane.showMessageDialog(null, "Błąd - nie można zamkąć pliku \"dbconf.rej\".", "Informacja", JOptionPane.WARNING_MESSAGE);}
        
        String dec = null;
        try {
            dec = decrypt(enc);
        } catch (Exception ex) {
            Logger.getLogger(BazaDanychView.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        String dec_local = null;
        try {
            dec_local = decrypt(enc_local);
        } catch (Exception ex) {
            Logger.getLogger(BazaDanychView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(dec);
        //System.out.println(dec_local);
        
        host=dec.substring(0,host_lenght);
        System.out.println(host);
        nazwa=dec.substring(host_lenght,host_lenght+nazwa_lenght);
        System.out.println(nazwa);
        uzytkownik=dec.substring(host_lenght+nazwa_lenght, host_lenght+nazwa_lenght+uzytkownik_lenght);
        System.out.println(uzytkownik);
        haslo=dec.substring(host_lenght+nazwa_lenght+uzytkownik_lenght,host_lenght+nazwa_lenght+uzytkownik_lenght+haslo_lenght);
        System.out.println(haslo);
        
        /*host_local=dec_local.substring(0,host_lenght_local);
        System.out.println(host_local);
        nazwa_local=dec_local.substring(host_lenght_local,host_lenght_local+nazwa_lenght_local);
        System.out.println(nazwa_local);
        uzytkownik_local = dec_local.substring(host_lenght_local+nazwa_lenght_local, host_lenght_local+nazwa_lenght_local+uzytkownik_lenght_local);
        System.out.println(uzytkownik_local);
        haslo_local = dec_local.substring(host_lenght_local+nazwa_lenght_local+uzytkownik_lenght_local,host_lenght_local+nazwa_lenght_local+uzytkownik_lenght_local+haslo_lenght_local);
        System.out.println(haslo_local);*/
        
        this.conn =  DbConnection.getConnection(host,nazwa,uzytkownik,haslo);     
        System.out.println("host: " + String.valueOf(this.conn));
    }
    
        
    public void Insert(String table, ArrayList<String> column, ArrayList value){
        
        
        
        //prepare sql columns list and values
        String columns = new String();
        String values = new String();
        
        int i, lenght = column.size();
        
        for(i=0;i<lenght;i++){
            if(i==lenght - 1){
            columns = columns + column.get(i);
            values = values + "'" + value.get(i) + "'";
            }else{
            columns = columns + column.get(i) + ", ";
            values = values + "'" + value.get(i) + "'" + ", ";
            }
        }
        //System.out.println(values.charAt(1));
        
        //System.out.format("%H ", values.charAt(1));
        
        try {
            //System.out.println("Now set read only - insert");
            conn.setReadOnly(false);
            //System.out.println("Now prepare statement - insert");
            PreparedStatement preparedStatement = conn.prepareStatement("insert into " + table + "(" + columns + ") values (" + values + ")");
            //System.out.println("Now execute update - insert");
            preparedStatement.executeUpdate(); 
        } 
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    public void Update(String table, ArrayList<String> column, ArrayList<String> value, int id){
       
        String update = new String();
        
        int i, lenght = column.size();
        
        for(i=0;i<lenght;i++){             
            if(i == lenght - 1){               
                update = update + column.get(i) + "=" + "'" + value.get(i) + "'";         
            }else{
                update = update + column.get(i) + "=" + "'" + value.get(i) + "'" + ", ";
            }                    
        }

        try {
                conn.setReadOnly(false);
                PreparedStatement preparedStatement = conn.prepareStatement("update " + table + " set " + update + " where id" + table + "=" + id);
                preparedStatement.executeUpdate();
        } 
        catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
    public void Delete(String table, int id){
        
        try{
            conn.setReadOnly(false);
            PreparedStatement preparedStatement = conn.prepareStatement("delete from " + table + " where id" + table + "=" + String.valueOf(id));
            preparedStatement.executeUpdate();
        } 
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    public ArrayList<ArrayList<String>> ReadAll(String table, ArrayList<String> column){
        
        ArrayList<ArrayList<String>> dane = new ArrayList<>();   
        String columns = new String();   
        int i, lenght = column.size();  
        
        for(i=0;i<lenght;i++){
            if(i==lenght - 1){
            columns = columns + column.get(i);           
            }else{
            columns = columns + column.get(i) + ", ";
            }
        }
        
        try{
            conn.setReadOnly(true);
            PreparedStatement preparedStatement = conn.prepareStatement("select " + columns + " from " + table);
            ResultSet rs = preparedStatement.executeQuery();  
            while (rs.next()) {  
                ArrayList<String> dana = new ArrayList<>();
                for(i=0;i<lenght;i++){                  
                    dana.add(rs.getString(column.get(i)));
                    
                }
                dane.add(dana);                
            }
        }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE); 
        }  
        //System.out.format("%H ", dane.get(0).get(1).charAt(0));
        return dane;
    }
    
    public ArrayList<String> ReadAllWhere(String table, String column, String item, String data)
    {
        ArrayList<String> dane = new ArrayList<>();
        try{
            conn.setReadOnly(true);
            PreparedStatement preparedStatement = conn.prepareStatement("select "+column+" from "+table+" where "+item+"='"+data+"'");
            ResultSet rs = preparedStatement.executeQuery();  
            while (rs.next()) {  
                dane.add(rs.getString(1));      
            }
        }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE); 
        }  
        return dane;
    }
    public ArrayList<ArrayList<String>> ReadAllWhere(String table, ArrayList<String> column, ArrayList<String> item, ArrayList<String> data){
        
        ArrayList<ArrayList<String>> dane = new ArrayList<>();   
        String columns = new String();
        String wheres = new String();
        
        int i, lenght = item.size();
        for(i=0;i<lenght;i++){
            if(i==lenght - 1){
                if(i==0){
                    wheres =  " where " + item.get(i) + "=" + "'" + data.get(i) + "'";   
                }else{
                    wheres = wheres + item.get(i) + "=" + "'" + data.get(i) + "'";       
                }
            }else if(i==0){
                wheres = " where " + item.get(i) + "=" + "'" + data.get(i) + "'" + " and ";
            }else{
                wheres = wheres + item.get(i) + "=" + "'" + data.get(i) + "'" + " and ";
            }
        }
        
        lenght = column.size();  
        for(i=0;i<lenght;i++){
            if(i==lenght - 1){
            columns = columns + column.get(i);           
            }else{
            columns = columns + column.get(i) + ", ";
            }
        }
        
        try{
            conn.setReadOnly(true);
            PreparedStatement preparedStatement = conn.prepareStatement("select " + columns + " from " + table + wheres);
            ResultSet rs = preparedStatement.executeQuery();  
            while (rs.next()) {  
                ArrayList<String> dana = new ArrayList<>();
                for(i=0;i<lenght;i++){                  
                    dana.add(rs.getString(column.get(i)));
                }
                dane.add(dana);                
            }
        }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE); 
        }  
        return dane;
    }   
    public ArrayList<String> ReadAllWhere(String table, String column, ArrayList<String> item, ArrayList<String> data){
        
        ArrayList<String> dane = new ArrayList<>();   
        String wheres = new String();
        
        int i, lenght = item.size();
        for(i=0;i<lenght;i++){
            if(i==lenght - 1){
            wheres = wheres + item.get(i) + "=" + "'" + data.get(i) + "'";           
            }else{
            wheres = wheres + item.get(i) + "=" + "'" + data.get(i) + "'" + " and ";
            }
        }

        try{
            conn.setReadOnly(true);
            PreparedStatement preparedStatement = conn.prepareStatement("select " + column + " from " + table + " where " + wheres);
            ResultSet rs = preparedStatement.executeQuery();  
            while (rs.next()) {   
                dane.add(rs.getString(1));
            }
        }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE); 
        } 

        return dane;
    }
  
    public ArrayList<String> Read(String table, ArrayList<String> column, int id){
        
        ArrayList <String> dana = new ArrayList<>();
        String columns = new String(); 
        int i, lenght = column.size();
        
        for(i=0;i<lenght;i++){
            if(i==lenght - 1){
            columns = columns + column.get(i);           
            }else{
            columns = columns + column.get(i) + ", ";
            }
        }
        
        try{
            conn.setReadOnly(true);
            PreparedStatement preparedStatement = conn.            
            prepareStatement("select " + columns + " from " + table + " where id" + table + "=" + String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();

            
            if(rs.next())
            {
                for(i=0;i<lenght;i++){ 
                dana.add(rs.getString(column.get(i)));
                }
                System.out.println("1 " + dana);
            }
            else
            {
                System.out.println("2");
                for(i=0;i<lenght;i++){ 
                dana.add("-1");
                }  
            }
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
        //System.out.println(dana);
        return dana;
    }
    
    public String Read(String table, String column, int id){
        int len = 0;
        String dana = null;
        try{
            conn.setReadOnly(true);
            PreparedStatement preparedStatement = conn.
            prepareStatement("select " + column + " from " + table + " where id" + table + "=" + String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();
            len = 0;
            while (rs.next()) {  
                  dana = rs.getString(1);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return dana;
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
    
}
