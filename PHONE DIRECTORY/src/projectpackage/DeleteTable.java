package projectpackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTable {

    // Method to delete the data from the database
    public void deleteData(String name, String number, String city) {
        String sql = "DELETE FROM person_details WHERE person_name = ? AND mobile_number = ? AND city_name = ?";

        try (Connection conn = OracleDBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, number);
            pstmt.setString(3, city);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Contact deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No contact found with the given details.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while deleting contact.");
            e.printStackTrace();
        }
    }

    // Method to create the GUI
    public void createAndShowGUI() {
        // Create a JFrame
        JFrame frame = new JFrame("Delete Contact");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); // No layout manager to allow manual positioning

        // Create labels and text fields with setBounds for positioning
        JLabel nameLabel = new JLabel("Person Name:");
        nameLabel.setBounds(50, 30, 100, 25);  // x, y, width, height
        JTextField nameField = new JTextField();
        nameField.setBounds(160, 30, 150, 25);

        JLabel numberLabel = new JLabel("Mobile Number:");
        numberLabel.setBounds(50, 70, 100, 25);
        JTextField numberField = new JTextField();
        numberField.setBounds(160, 70, 150, 25);

        JLabel cityLabel = new JLabel("City Name:");
        cityLabel.setBounds(50, 110, 100, 25);
        JTextField cityField = new JTextField();
        cityField.setBounds(160, 110, 150, 25);

        // Create a button to delete the contact
        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.setBounds(120, 160, 150, 30);

        // Add action listener to the button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String number = numberField.getText();
                String city = cityField.getText();

                if (name.isEmpty() || number.isEmpty() || city.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                } else {
                    deleteData(name, number, city);  // Call the delete method
                }
            }
        });

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(numberLabel);
        frame.add(numberField);
        frame.add(cityLabel);
        frame.add(cityField);
        frame.add(deleteButton);

        // Set the frame to be visible
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        // Run the GUI creation in the Event Dispatch Thread (EDT)
//        SwingUtilities.invokeLater(() -> {
//            DeleteTable deleteTable = new DeleteTable();
//            deleteTable.createAndShowGUI();
//        });
//    }
} 