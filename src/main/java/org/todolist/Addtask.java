package org.todolist;

import java.sql.*;

public class Addtask {
    public static int addtask() throws SQLException {
        System.out.println("Enter the task which you want to add:");
        String task_work = Taskmenu.sc.nextLine();

        int userId = Register.getUserId(); // Retrieve userId from Register class

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        String query = "INSERT INTO task (userid, task_action, taskwork, timerecorded) VALUES (?, ?, ?, NOW())";

        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, userId);
            pst.setString(2, Taskmenu.task_action);
            pst.setString(3, task_work);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int createdTaskNo = generatedKeys.getInt(1);
                    System.out.println("Task created successfully with number: " + createdTaskNo);
                    return userId;
                }
            } else {
                System.out.println("No rows were inserted.");
            }
        } catch (SQLException e) {
            // Print SQL exception details
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
        System.out.println("Failed to create task.");
        return -1;
    }


}