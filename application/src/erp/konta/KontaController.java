/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.konta;

import erp.ERP_Dao;
import erp.ERP_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Arkita
 */
public class KontaController {
    
    int status;
    ERP_Dao theDao;
    KontaView theView;
    int row = -1;
    
    public KontaController(ERP_View erp_view,int sta){
        status = sta;
        theDao = new ERP_Dao();
        theView = new KontaView(erp_view,status);
        theView.setEnabledUsun(false);
        this.theView.dodaj(new Dodaj());
        this.theView.usun(new Usun());
        this.theView.zaznaczono(new Zaznaczono());
        fill_table();
    }
    
    class Dodaj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            dodaj();
            fill_table();
            theView.setEnabledUsun(false);
        }  
    }   
    
    class Usun implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            if(row >= 0){  
                theDao.Delete("users", theView.getId(row));
                fill_table();
                theView.setEnabledUsun(false);
                row = -1;
            }
        }  
    }   
    
    class Zaznaczono implements ListSelectionListener{  
        @Override
        public void valueChanged(ListSelectionEvent e) {    
           if(e.getValueIsAdjusting()==false){           
                row = theView.getTableSelectedRow();
                //theView.checkboxStatus(true);
                if(row!=-1){
                    theView.setEnabledUsun(true);
                }
           }
        }
    }       

    private void dodaj(){
        
        if(theView.getNazwa().length() <8 || theView.getHaslo().length() <8){
            JOptionPane.showMessageDialog(null, "Nazwa i hasło musi mieć conajmniej 8 znaków.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }else{
        ArrayList<String> users_column = new ArrayList<>(Arrays.asList("nazwa", "haslo", "uprawnienia"));
        int level = 0;
        String upraw = theView.getCombo();
        if(upraw.equals("Użytkownik")){
            level = 1;
        }else if(upraw.equals("Moderator")){
            level = 2;
        }else if(upraw.equals("Administrator")){
            level = 3;
        }
        ArrayList<String> users_data = new ArrayList<>(Arrays.asList(theView.getNazwa(), theView.getHaslo(), String.valueOf(level)));
        theDao.Insert("users", users_column, users_data);
        }
    }
    
    private void fill_table(){
        theView.wyczysc_tabele();
        ArrayList<String> users_column = new ArrayList<>(Arrays.asList("idusers", "nazwa", "haslo", "uprawnienia"));
        ArrayList<ArrayList<String>> users = theDao.ReadAll("users", users_column);
        int i,lenght = users.size();
        for(i=0;i<lenght;i++){
            String upraw = users.get(i).get(3);
            switch (upraw) {
                case "1":
                    upraw = "Użytkownik";
                    break;
                case "2":
                    upraw = "Moderator";
                    break;
                case "3":
                    upraw = "Administrator";
                    break;
            }
            theView.dodaj_wiersz(users.get(i).get(0), users.get(i).get(1), users.get(i).get(2), upraw);
        }                 
        
        
    }
    
}
