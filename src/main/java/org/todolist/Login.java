package org.todolist;

import java.sql.*;
import java.util.Scanner;

public class Login {
    static String todousername;
    static String passwd;

    public static boolean login() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your Name to login:");
            todousername = scanner.nextLine();
            if (todousername.isEmpty()) {
                System.out.println("Please provide username, don't leave it blank.");
                return false;
            }

            System.out.println("Enter your Password to login:");
            passwd = scanner.next();
            if (passwd.isEmpty()) {
                System.out.println("Please provide password, don't leave it blank.");
                return false;
            }


            if (authenticateUser(todousername, passwd)) {
                System.out.println("Login successful. Welcome, " + todousername + "!");
                return true;
            } else {
                System.out.println("Invalid credentials. Login failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    static boolean authenticateUser(String username, String password) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602")) {
            String query = "SELECT passwd FROM UserRegister WHERE todousername = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, username);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("passwd");

                        return password.equals(storedPassword);
                    }
                }
            }
        }
        return false;
    }
}
