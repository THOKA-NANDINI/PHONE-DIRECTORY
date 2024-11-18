package projectpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppendPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField nameField;
    private JTextField numberField;
    private JTextField cityField;
    private JButton addButton;

    public AppendPage() {
        setTitle("Add Page");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Absolute positioning

        // Create and position components
        JLabel nameLabel = new JLabel("Person Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setBounds(30, 30, 100, 25);
        add(nameLabel);

        nameField = new JTextField(15);
        nameField.setBounds(150, 30, 150, 25);
        add(nameField);

        JLabel numberLabel = new JLabel("Mobile Number:");
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        numberLabel.setBounds(30, 70, 100, 25);
        add(numberLabel);

        numberField = new JTextField(15);
        numberField.setBounds(150, 70, 150, 25);
        add(numberField);

        JLabel cityLabel = new JLabel("City Name:");
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cityLabel.setBounds(30, 110, 100, 25);
        add(cityLabel);

        cityField = new JTextField(15);
        cityField.setBounds(150, 110, 150, 25);
        add(cityField);

        addButton = new JButton("Add");
        addButton.setBounds(120, 160, 100, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addData();
            }
        });

        setVisible(true);
    }

    private void addData() {
        String name = nameField.getText();
        String number = numberField.getText();
        String city = cityField.getText();

        if (!name.isEmpty() && !number.isEmpty() && !city.isEmpty()) {
            // Create an instance of InsertPage and insert the data into the database
            InsertTable insertPage = new InsertTable();
            insertPage.insertData(name, number, city);

            System.out.println("Data inserted successfully!");
        } else {
            System.out.println("All fields are required!");
        }

        // Clear the text fields after adding the data
        nameField.setText("");
        numberField.setText("");
        cityField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppendPage addPage = new AppendPage();
            addPage.setVisible(true);
        });
    }
}
