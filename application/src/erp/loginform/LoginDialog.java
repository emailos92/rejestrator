/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.loginform;

/**
 *
 * @author Arkita
 */
import erp.ERP_Dao;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;
 
public class LoginDialog extends JDialog {
 
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnLogout;
    private JButton btnCancel;
    private int sta;
    private ERP_Dao theDao;
    
    public LoginDialog(Frame parent) {
        super(parent, "Logowanie", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Użytkownik: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Hasło: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Zaloguj");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                sta = (authenticate(getUsername(), getPassword()));
                
                if (sta != -1){
                    //JOptionPane.showMessageDialog(LoginDialog.this,
                    //        "Witaj " + getUsername() + "!",
                    //        "Logowanie",
                    //        JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Błędna nazwa użytkownika i/lub hasło.",
                            "Logowanie",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                }
            }
        });
        
        btnLogout = new JButton("Wyloguj");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sta = -1;
                dispose();
            }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                sta = 0;
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnLogout);
        bp.add(btnCancel);
        
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    public int isSucceeded() {
        return sta;
    }
    
    public int authenticate(String username, String password) {
        
        if (username.equals("root") && password.equals("qwe123")) {
            return 3;
        }
        else if(username.equals("mod") && password.equals("mod123")){
            return 2;
        }
        else if(username.equals("user") && password.equals("user123")){
            return 1;    
        }
        else{
            theDao = new ERP_Dao();
            ArrayList<ArrayList<String>> users;
            ArrayList<String> col = new ArrayList<>(Arrays.asList("nazwa", "haslo", "uprawnienia"));
            users = theDao.ReadAll("users", col);
            int lenght = users.size();
            for(int i=0;i<lenght;i++){
                if(users.get(i).get(0).equals(username)){
                    if(users.get(i).get(1).equals(password)){
                        return Integer.parseInt(users.get(i).get(2));
                    }
                }
            }   
        }
        return -1;
    }
}