package projectpackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RetrieveTable {

    private DefaultTableModel tableModel;

    public void retrieveData() {
        String sql = "SELECT * FROM person_details";
        String[] columnNames = {"Person Name", "Mobile Number", "City Name"};

        if (tableModel == null) {
            tableModel = new DefaultTableModel(columnNames, 0);
        } else {
            tableModel.setRowCount(0);
        }

        try (Connection conn = OracleDBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("person_name");
                String mobile = rs.getString("mobile_number");
                String city = rs.getString("city_name");

                tableModel.addRow(new Object[]{name, mobile, city});
            }

        } catch (SQLException e) {
            System.out.println("Error while retrieving data.");
            e.printStackTrace();
        }

        displayTable();
    }

    private void displayTable() {
        JFrame frame = new JFrame("Person Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteButton = new JButton("Delete Selected Contact");
        deleteButton.addActionListener(e -> deleteSelectedContact(table));

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(deleteButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void deleteSelectedContact(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String personName = (String) table.getValueAt(selectedRow, 0);
            deleteContactFromDatabase(personName);
            retrieveData();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a contact to delete.");
        }
    }

    private void deleteContactFromDatabase(String personName) {
        String deleteSQL = "DELETE FROM person_details WHERE person_name = ?";

        try (Connection conn = OracleDBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, personName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("No contact found with name: " + personName);
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting contact.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RetrieveTable retrieveTable = new RetrieveTable();
            retrieveTable.retrieveData();
        });
    }
}
