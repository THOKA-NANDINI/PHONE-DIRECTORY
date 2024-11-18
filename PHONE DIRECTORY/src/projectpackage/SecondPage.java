package projectpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SecondPage {
    private JFrame frame;

    public SecondPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Contact Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400); 
        frame.setLayout(null);
        Dimension buttonSize = new Dimension(200, 50); 

        // Create buttons
        JButton appendButton = new JButton("Append Contact");
        JButton searchButton = new JButton("Search Contact");
        JButton retrieveButton = new JButton("Retrieve Contact");
        JButton deleteButton = new JButton("Delete Contact");
        JButton cancelButton = new JButton("Cancel");

        // Set bounds for buttons in a column format
        appendButton.setBounds(50, 30, buttonSize.width, buttonSize.height);
        searchButton.setBounds(50, 100, buttonSize.width, buttonSize.height);
        retrieveButton.setBounds(50, 170, buttonSize.width, buttonSize.height);
        deleteButton.setBounds(50, 240, buttonSize.width, buttonSize.height);
        cancelButton.setBounds(50, 310, buttonSize.width, buttonSize.height);

        // Action for Append button
        appendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AppendPage(); // Create new AppendPage instance
            }
        });

        // Action for Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the second page
            }
            
        });
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                RetrieveTable r= new RetrieveTable();
                r.retrieveData();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
              DeleteTable d= new DeleteTable();   
              d.createAndShowGUI();
              }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
              new SearchContacts();           
              }
        });
        // Adding buttons to the frame
        frame.add(appendButton);
        frame.add(searchButton);
        frame.add(retrieveButton);
        frame.add(deleteButton);
        frame.add(cancelButton);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new SecondPage();
    }
}
