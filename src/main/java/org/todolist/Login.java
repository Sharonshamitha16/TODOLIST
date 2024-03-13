package org.todolist;

import java.sql.*;
import java.util.*;

import static org.todolist.Register.confirmpasswd;

public class Login {
    static String todousername;
    static String passwd;


    public static String login() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your Name to login:");
            todousername = scanner.nextLine();
            if (todousername.isEmpty()) {
                System.out.println("Please provide username, don't leave it blank.");
                return login(); // Prompt again for username
            }


            while (true) {
                System.out.println("Enter your Password to login:");
                passwd = scanner.nextLine();
                if (passwd.isEmpty()) {
                    System.out.println("Please provide password, don't leave it blank.");
                } else {
                    break; // Break the loop if the password is provided
                }
            }

            while (true) {
                System.out.println("Retype your password");
                confirmpasswd = scanner.nextLine();
                if (confirmpasswd.isEmpty()) {
                    System.out.println("Please provide password, don't leave it blank.");
                } else {
                    break; // Break the loop if the password is provided
                }
            }

            if (Login.passwd.equals(Register.confirmpasswd)) {
                System.out.println("password matched..");


            } else {
                System.out.println("Invalid credentials...");

            }
            //return passwd.equals(confirmpasswd) ? "User " + todousername + " logged in successfully." : "log in failed..";
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            e.printStackTrace();
        }
        return passwd.equals(confirmpasswd) ? "User " + todousername + " logged in successfully." : "log in failed..";

    }

    public static boolean dbConnection() throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

            String query = "SELECT UserRegister.userid, UserLogin.datenow " +
                    "FROM UserRegister " +
                    "LEFT JOIN UserLogin ON UserRegister.userid = UserLogin.userid " +
                    "WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?";

            pst = con.prepareStatement(query);
            pst.setString(1, todousername);
            pst.setString(2, passwd);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Timestamp datenow = rs.getTimestamp("datenow");

                String insertQuery = "INSERT INTO UserLogin (userid, datenow) VALUES (?, NOW())  ";
                pst1 = con.prepareStatement(insertQuery);
                pst1.setInt(1, rs.getInt("userid"));

                if (Login.passwd.equals(Register.confirmpasswd)) {
                    int rowsInserted = pst1.executeUpdate();
                    System.out.println(rowsInserted + " row(s) inserted into userlogin");
                } else {
                    System.out.println("no rows inserted as passwd is wrong");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (pst1 != null) {
                pst1.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }
}




    


