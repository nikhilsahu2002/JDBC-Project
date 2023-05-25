
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseExample {
    public static void main(String[] args) {
        // Call the Create_table method or perform other database operations
        // Create_table();
        FillData();
        ShowData();
    }

    public static void ShowData() {
        String showSql = "SELECT * FROM employees";
    
        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "1234";
    
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(showSql)) {
            // Process the ResultSet and display the data
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double salary = resultSet.getDouble("salary");
    
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Salary: " + salary);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }
    }
    

    public static void FillData() {
        // Assume you have the necessary variables for data
        int id = 3;
        String name = "TT";
        int age = 20;
        double salary = 500000.00;

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
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }
}
