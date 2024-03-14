package org.todolist;

import java.sql.*;

import static org.todolist.Login.todousername;
import static org.todolist.Register.passwd;
import static org.todolist.Register.userid;
import static org.todolist.Taskmenu.sc;
import static org.todolist.Taskmenu.task_action;
public class Addtask {
    public static int addtask() throws SQLException {
        System.out.println("Enter the task which you want to add:");
        String task_work = sc.nextLine();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query1 = " select UserRegister.userid, UserRegister.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
                "  right join   UserRegister on  UserRegister.userid = task.userid and UserRegister.todousername =task.todousername\n" +
                "  WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?;\n";
        // String query1 = "SELECT userid FROM UserRegister WHERE todousername = ? AND passwd = ?";

        PreparedStatement pst1 = con.prepareStatement(query1);
        pst1.setString(1, todousername);
        pst1.setString(2, passwd);


        ResultSet rs = pst1.executeQuery();


        int userId = Integer.parseInt(Register.register());
        if (userId != 1) {
            String query = "INSERT INTO task ( userid ,task_action, taskwork, timerecorded) VALUES ( ?, ?, ?, NOW())";
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, userid);
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

        }
        else {
            System.out.println("User registration failed.");
            return -1;
        }
       // return userId;
    }
}

