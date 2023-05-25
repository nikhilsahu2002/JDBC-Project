import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataFrame extends JFrame {
    private JTextField idField, nameField, ageField, salaryField;
    private JButton insertButton;
    private JLabel idLabel, nameLabel, ageLabel, salaryLabel;

    public InsertDataFrame() {
        // Set JFrame properties
        setTitle("Insert Data Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the ID field and label
        idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 50, 30);
        idField = new JTextField();
        idField.setBounds(70, 20, 150, 30);

        // Create the name field and label
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 50, 30);
        nameField = new JTextField();
        nameField.setBounds(70, 60, 150, 30);

        // Create the age field and label
        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 100, 50, 30);
        ageField = new JTextField();
        ageField.setBounds(70, 100, 150, 30);

        // Create the salary field and label
        salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(20, 140, 50, 30);
        salaryField = new JTextField();
        salaryField.setBounds(70, 140, 150, 30);

        // Create the insert button
        insertButton = new JButton("Insert");
        insertButton.setBounds(100, 180, 80, 30);
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });

        // Add the components to the JFrame
        add(idField);
        add(nameField);
        add(ageField);
        add(salaryField);
        add(insertButton);
        add(idLabel);
        add(nameLabel);
        add(ageLabel);
        add(salaryLabel);

        // Set the layout to null to manually position the components
        setLayout(null);

        // Display the JFrame
        setVisible(true);
    }

    private void insertData() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        double salary = Double.parseDouble(salaryField.getText());

        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "1234";

        String insertSQL = "INSERT INTO employees (id, name, age, salary) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            // Set the parameter values for the prepared statement
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setDouble(4, salary);

            // Execute the statement to insert the data
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data inserted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inserting data: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InsertDataFrame();
        });
    }
}
