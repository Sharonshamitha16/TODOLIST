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
            String query = "SELECT passwd , userid FROM UserRegister WHERE todousername = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, username);
                String storedPassword = null;
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        storedPassword = rs.getString("passwd");
                        String insertLoginQuery = "INSERT INTO UserLogin (userid, datenow) VALUES (?, CURRENT_TIMESTAMP)";
                        try (PreparedStatement insertPst = con.prepareStatement(insertLoginQuery)) {
                            insertPst.setInt(1, rs.getInt("userid"));
                            int rowsInserted = insertPst.executeUpdate();
                            System.out.println(rowsInserted + " row(s) inserted into UserLogin.");
                        }
                    } else {
                        // User does not exist or invalid credentials
                        System.out.println("Invalid credentials. Login failed.");
                    }
                }
                return password.equals(storedPassword);
            }
                }
            }
        }

