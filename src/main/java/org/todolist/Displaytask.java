package org.todolist;
import java.sql.*;
import java.util.Scanner;
import static org.todolist.Register.todousername;
import static org.todolist.Taskmenu.tasknew_action;
import static org.todolist.Taskmenu.tasknewwork;
public class Displaytask {
    public static int displaytask() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the task number which you want to display:");
        int tasknewno = sc.nextInt();
        String query = "SELECT * FROM tasknew WHERE  tasknewno = ? ";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1,tasknewno);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    todousername =rs.getString("todousername");
                    tasknewno = rs.getInt("tasknewno");
                     tasknew_action = rs.getString("tasknew_action");
                     tasknewwork = rs.getString("tasknewwork");
                    Timestamp timeRecorded = rs.getTimestamp("timerecorded");
                    System.out.println("username :" + todousername);
                   System.out.println("Task Number: " + tasknewno);
                    System.out.println("Task Action: " + tasknew_action);
                    System.out.println("Task Work: " + tasknewwork);
                    System.out.println("Time Recorded: " + timeRecorded);
                } else {
                    System.out.println("No task found for the given task number and username.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return -1;
    }
}