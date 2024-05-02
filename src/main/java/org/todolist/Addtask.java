package org.todolist;
import java.sql.*;
import java.util.Scanner;

public class Addtask {
    public static String tasknew_action;
    public static String addtask() throws SQLException {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the task which you perform");
         tasknew_action = sc.nextLine();
        System.out.println("Enter the task which you want to add:");
        String tasknew_work = sc.nextLine();
        int userId = Register.getUserId();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        String query = "INSERT INTO tasknew (userid,todousername, tasknew_action, tasknewwork, timerecorded) VALUES (?,?, ?, ?, NOW())";
        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            pst.setInt(1, Register.getUserId());
            pst.setString(2,Register.todousername);
            pst.setString(3, Taskmenu.tasknew_action);
            pst.setString(4 , tasknew_work);
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int createdTaskNo = generatedKeys.getInt(1);
                    System.out.println("Task created successfully with number: " + createdTaskNo);
                }
            } else {
                System.out.println("No rows were inserted.");
                System.out.println("Failed to create task.");

            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return "Task created successfully";
    }
}