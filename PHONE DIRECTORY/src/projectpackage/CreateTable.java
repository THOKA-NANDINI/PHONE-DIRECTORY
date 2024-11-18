package projectpackage;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateTable {

    public static void createPersonDetailsTable() {
        String createTableSQL = "CREATE TABLE person_details (person_name VARCHAR2(100), mobile_number VARCHAR2(15), city_name VARCHAR2(50))";
        try (Connection conn = OracleDBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Table 'person_details' is created successfully!");

        } catch (SQLException e) {
            System.out.println("Error while creating table.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createPersonDetailsTable();
    }
}
