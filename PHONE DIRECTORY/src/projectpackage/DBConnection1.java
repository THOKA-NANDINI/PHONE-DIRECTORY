package projectpackage;
import java.sql.*;
public class DBConnection1 {
	    public static Connection connect() {
	        Connection conn = null;
	        try {
	            // Load Oracle JDBC Driver
	            Class.forName("oracle.jdbc.driver.OracleDriver");

	            // Establish the connection
	            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "nandini");

	            System.out.println("Connected to Oracle Database!");
	            Statement statement=conn.createStatement();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }

	    public static void main(String[] args) {
	        connect();  // Call the method to connect to the database
	    }
   }  
	


