package org.todolist;

import java.sql.*;

//import static org.todolist.Login.todousername;
import static org.todolist.Register.*;
import static org.todolist.Taskmenu.sc;
import static org.todolist.Taskmenu.task_action;
public class Addtask {
    public static int addtask() throws SQLException {
        System.out.println("Enter the task which you want to add:");
        String task_work = sc.nextLine();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        //String query1 = " select UserRegister.userid, task.userid task.task_action ,task.taskwork, task.timerecorded  from task natural join UserRegister WHERE UserRegister.Userid =  UserRegister.task.Userid";
        String query1 = "SELECT passwd FROM UserRegister WHERE todousername = ? AND userid = ?";

        PreparedStatement pst1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
        pst1.setString(1, todousername);

        //pst1.setString(2, passwd);
        ResultSet generatedUserKeys = pst1.getGeneratedKeys();
        if (generatedUserKeys.next()) {
           int userId = generatedUserKeys.getInt(1); // Retrieve the generated userid
            System.out.println(userId);
        }
        int userId = generatedUserKeys.getInt(1);
        pst1.setInt(2,userId);
        //pst1.setInt(2, generatedUserKeys.getInt(1));
        ResultSet rs = pst1.executeQuery(query1);


//        String queryfromreg = "insert into UserRegister (todousername, passwd, email, gender)values(?,?,?,?);";
//
//        try (PreparedStatement pstfromreg = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
//        }

        generatedUserKeys = pst1.getGeneratedKeys();
         userId = -1;
        if (generatedUserKeys.next()) {
            userId = generatedUserKeys.getInt(1); // Retrieve the generated userid
            System.out.println(userId);
        }

        //int userId = Integer.parseInt(Register.register());
        if (userId != 1) {
            String query = "INSERT INTO task ( userid ,task_action, taskwork, timerecorded) VALUES ( ?, ?, ?, NOW())";
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, userId);
            pst.setString(2, task_action);
            pst.setString(3, task_work);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int createdTaskNo = generatedKeys.getInt(1);

                    System.out.println("Task created successfully with number: " + createdTaskNo);
                    System.out.println("Generated user ID: " + userId);
                    return userId;
                }
            }
            System.out.println("Failed to create task.");
            return -1;

        } else {
            System.out.println("User registration failed.");
            return -1;
        }
    }
}

