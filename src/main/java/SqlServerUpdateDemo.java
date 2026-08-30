import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SqlServerUpdateDemo {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "Akil@12345";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the actor id to update: ");
        String actorIdInput = scanner.nextLine();

        int actorId;
        try {
            actorId = Integer.parseInt(actorIdInput);
        } catch (NumberFormatException e) {
            System.err.println("Actor id must be a number.");
            return;
        }

        System.out.print("Enter the new first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the new last name: ");
        String lastName = scanner.nextLine();

        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, actorId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 1) {
                System.out.println("Actor updated successfully.");
            } else {
                System.out.println("No actor found with id " + actorId + ".");
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}