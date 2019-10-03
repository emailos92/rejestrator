
package erp;

import java.sql.Connection;
//import java.util.Properties;
//import com.mysql.jdbc.ReplicationDriver;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Arkita
 */
public class DbConnection {
    @SuppressWarnings("unchecked")
    private static Connection conn = null;
    
    public static Connection getConnection(String host, String name, String user, String password){   //String host_local
        
        /*try {
            ReplicationDriver driver = new ReplicationDriver();
            Properties props = new Properties();
            // We want this for failover on the slaves
            props.put("autoReconnect", "true");

            // We want to load balance between the slaves
            props.put("roundRobinLoadBalance", "true");

            props.put("user", user);
            props.put("password", password);
            System.out.println("Now init JDBC...");
            conn = driver.connect("jdbc:mysql://"+host+","+host_local+"/"+name, props);
        } 
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }*/

            try {
              
                String driver = "com.mysql.jdbc.Driver";
                
                //String name = "db1974";
                
                //String host = "localhost:3306";
                
                String url = "jdbc:mysql://"+host+"/";
  
                //String user = "root";
  
                //String password = "Mamusia74!@#";
  
                Class.forName(driver);  
  
                conn = DriverManager.getConnection(url+name, user, password);  
  
            } catch (ClassNotFoundException e) {  
  
                e.printStackTrace();  
  
            } catch (SQLException e) {  
  
                JOptionPane.showMessageDialog(null, "Nie można nawiązać połączenia z bazą danych, sprawdź konfigurację.", "Informacja", JOptionPane.WARNING_MESSAGE);
  
            }
  
            return conn;  
    }  
}  
