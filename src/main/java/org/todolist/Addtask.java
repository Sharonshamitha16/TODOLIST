package org.todolist;

import java.sql.*;

import static org.todolist.Login.todousername;
import static org.todolist.Register.passwd;
import static org.todolist.Register.userid;
import static org.todolist.Taskmenu.sc;

public class Addtask {
    public static int addtask() throws SQLException {
        System.out.println("Enter the task which you want to add:");
        String task_action = sc.nextLine();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query = "INSERT INTO task ( userid ,task_action, taskwork, timerecorded) VALUES (?, ?, ?, NOW())";
        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, userid);
        pst.setString(2, task_action);
        pst.setString(3, ""); // Assuming taskwork is empty initially

        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                int createdTaskNo = generatedKeys.getInt(1);
                System.out.println("Task created successfully with number: " + createdTaskNo);
                return createdTaskNo;
            }
        }
        System.out.println("Failed to create task.");
        return -1; // Return -1 indicating failure
    }

   }
