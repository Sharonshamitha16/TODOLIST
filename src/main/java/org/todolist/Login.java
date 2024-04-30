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


            if ( authenticateUser(todousername, passwd)) {
                System.out.println("Login successful. Welcome, " + todousername + "!");
                //System.out.println("task number is:"+createdTaskNo);
                if (hasTasks(todousername)) {
                    int taskNumber = getTaskNumber(todousername);
                    System.out.println("Your task number is: " + taskNumber);
                } else {
                    System.out.println("New user! To get a task number, create a task.");
                }

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

    static boolean authenticateUser(String todousername, String passwd) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        String query = "SELECT passwd , userid FROM UserRegisternew WHERE todousername = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, todousername);
        String storedPassword = null;
        ResultSet rs = pst.executeQuery();
        String query1 = " select UserRegisternew.userid, UserRegisternew.todousername,UserRegisternew.passwd, UserLoginnew.datenow \n" +
                "from UserRegisternew Left JOIN UserLoginnew on \n" +
                "UserRegisternew.userid=UserLoginnew.userid " +
                "  WHERE  UserRegisternew.passwd = ?;\n";
        // String query1 = "SELECT userid FROM UserRegisternew WHERE todousername = ? AND passwd = ?";

        PreparedStatement pst1 = con.prepareStatement(query1);
       pst1.setString(1, passwd);


        ResultSet rs1 = pst1.executeQuery();

        if (rs.next()) {
            storedPassword = rs.getString("passwd");
            String insertLoginQuery = "INSERT INTO UserLoginnew (userid, datenow) VALUES (?, CURRENT_TIMESTAMP)";
            PreparedStatement insertPst = con.prepareStatement(insertLoginQuery);
            insertPst.setInt(1, rs.getInt("userid"));
            int rowsInserted = insertPst.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted into UserLoginnew.");

        } else {
            // User does not exist or invalid credentials
            System.out.println("Invalid credentials. Login failed.");
        }

        return passwd.equals(storedPassword);
    }




static boolean hasTasks(String todousername) throws SQLException {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
    String query = "SELECT tasknewno  FROM tasknew WHERE todousername = ?";
    PreparedStatement pst = con.prepareStatement(query);
    pst.setString(1, todousername);
  //  pst.setString(2, String.valueOf(tasknewno));
    ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        int tasknewno = rs.getInt("tasknewno");
        return tasknewno > 0;
    }

    return false;
}
    static int getTaskNumber(String todousername) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        String query = "SELECT tasknewno  FROM tasknew WHERE todousername = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, todousername);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt("tasknewno");
        }

        return 0; // Return 0 if no task number found
    }

}