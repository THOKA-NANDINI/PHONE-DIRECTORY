package projectpackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchContacts extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea;

    // Database connection details
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Update to your DB URL
    private final String DB_USER = "System"; // Update to your DB user
    private final String DB_PASSWORD = "nandini"; // Update to your DB password

    public SearchContacts() {
        setTitle("Search Contacts");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Using null layout to set bounds manually

        // Create components
        JLabel searchLabel = new JLabel("Search by Name: ");
        searchField = new JTextField(20);
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea); // Scroll pane for result area

        JButton searchButton = new JButton("Search");

        // Set bounds for components (x, y, width, height)
        searchLabel.setBounds(30, 20, 150, 30);
        searchField.setBounds(170, 20, 150, 30);
        searchButton.setBounds(200, 60, 100, 30);
        scrollPane.setBounds(30, 110, 430, 200); // Use scroll pane for the text area

        // Add components to the frame
        add(searchLabel);
        add(searchField);
        add(searchButton);
        add(scrollPane);

        // Add button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                resultArea.setText("Searching for " + searchTerm + " in person_name...");
                searchByName(searchTerm); // Call method to search database by name
            }
        });

        setVisible(true);
    }

    // Method to search contacts by name
    private void searchByName(String searchTerm) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to search by person_name in the person_details table
            String query = "SELECT * FROM person_details WHERE person_name = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, searchTerm); // Use the search term for exact match

            // Execute the query
            rs = pstmt.executeQuery();

            // Clear previous results
            resultArea.setText("");

            // Check if any results were returned
            if (!rs.isBeforeFirst()) { // No data returned
                resultArea.setText("No match found for " + searchTerm + " in person_name");
                return;
            }

            // Iterate through the result set and display the matched result
            while (rs.next()) {
                String name = rs.getString("person_name");
                String phone = rs.getString("mobile_number");
                String city = rs.getString("city_name");

                // Append the result to the JTextArea
                resultArea.append("Name: " + name + ", Phone: " + phone + ", City: " + city + "\n");
            }

        } catch (SQLException ex) {
            resultArea.setText("Error: " + ex.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                resultArea.setText("Error closing resources: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new SearchContacts();
    }
}
