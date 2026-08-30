package com.akil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.akil.properties.LoadProperties;

public class SqlServerDeleteDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the actor id to delete: ");
        String actorIdInput = scanner.nextLine();

        int actorId;
        try {
            actorId = Integer.parseInt(actorIdInput);
        } catch (NumberFormatException e) {
            System.err.println("Actor id must be a number.");
            return;
        }

        System.out.print("Are you sure you want to delete actor " + actorId + "? (yes/no): ");
        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        String sql = "DELETE FROM actor WHERE actor_id = ?";

        String dbUrl = LoadProperties.getProperty("db.url");
        String dbUsername = LoadProperties.getProperty("db.username");
        String dbPassword = LoadProperties.getProperty("db.password");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, actorId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted == 1) {
                System.out.println("Actor deleted successfully.");
            } else {
                System.out.println("No actor found with id " + actorId + ".");
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}