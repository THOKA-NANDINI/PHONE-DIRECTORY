package projectpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTable {

    public void insertData(String name, String number, String city) {
        String sql = "INSERT INTO person_details (person_name, mobile_number, city_name) VALUES (?, ?, ?)";

        try (Connection conn = OracleDBConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, number);
            pstmt.setString(3, city);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into the database.");
            } else {
                System.out.println("Data insertion failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting data.");
            e.printStackTrace();
        }
    }
}
