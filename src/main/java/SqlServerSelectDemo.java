import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class SqlServerSelectDemo {
    
    // Update these variables with your database connection details
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "Akil@12345";

    public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);

        // 2. Read a full line of text (String)
        System.out.print("Enter your actor id: ");
        String actor_id = scanner.nextLine();

        String sqlQuery = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";

        // Try-with-resources ensures connections and st1atements close automatically
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            System.out.println("ID | First Name | Last Name");
            System.out.println("------------------------");

            // Loop through the result set
            while (rs.next()) {
                int id = rs.getInt("actor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                System.out.println(id + " | " + firstName + " | " + lastName);
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}
