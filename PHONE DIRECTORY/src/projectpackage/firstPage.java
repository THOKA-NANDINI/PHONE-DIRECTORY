package projectpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstPage {
    private JFrame frame;
    private JTextField nameField;
    private JTextField phoneField;

    public firstPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Phone Directory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(null);
        // Heading
        JLabel headingLabel = new JLabel("PHONE DIRECTORY");
        headingLabel.setBounds(400, 10, 300, 40);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(headingLabel);

        // Name Label and Field
        JLabel nameLabel = new JLabel("Person Name:");
        nameLabel.setBounds(50, 50, 200, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        nameField = new JTextField(20);
        nameField.setBounds(300, 50, 250, 40); 

        // Phone Label and Field
        JLabel phoneLabel = new JLabel("Mobile Number:");
        phoneLabel.setBounds(50, 100, 200, 30);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));

        phoneField = new JTextField(20);
        phoneField.setBounds(300, 100, 250, 40); 

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(250, 200, 200, 50); 
        nextButton.setFont(new Font("Arial", Font.BOLD, 25));
        nextButton.setBackground(new Color(0, 122, 255)); 
        nextButton.setForeground(Color.GREEN);
        nextButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); 

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();

                // Validate name and phone number
                if (name.equals("Raj") && phone.equals("7674939790")) {
                    frame.dispose(); // Close the first page
                    new SecondPage(); // Open the second page
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid person name or mobile number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(nextButton); 

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new firstPage());
    }
}
