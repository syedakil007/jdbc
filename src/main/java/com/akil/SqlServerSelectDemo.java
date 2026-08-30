package com.akil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.akil.properties.LoadProperties;

import java.sql.SQLException;
import java.sql.PreparedStatement;

public class SqlServerSelectDemo {

    public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);

        // 2. Read a full line of text (String)
        System.out.print("Enter your actor id: ");
        String actor_id = scanner.nextLine();

        String sqlQuery = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";

        // Try-with-resources ensures connections and st1atements close automatically
        String dbUrl = LoadProperties.getProperty("db.url");
        String dbUsername = LoadProperties.getProperty("db.username");
        String dbPassword = LoadProperties.getProperty("db.password");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {

            stmt.setString(1, actor_id);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("ID | First Name | Last Name");
                System.out.println("------------------------");

                // Loop through the result set
                while (rs.next()) {
                    int id = rs.getInt("actor_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    System.out.println(id + " | " + firstName + " | " + lastName);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}
