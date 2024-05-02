package org.todolist;
import java.sql.*;
import static org.todolist.Taskmenu.*;
public class Updatetask {
    public static String tasknew_action;
    public static String updatetask() {
        String resultMessage = "Task update failed."; // Default message
        System.out.println("Enter the task which you need perform");
        tasknew_action = sc.nextLine();
        System.out.println("Enter the task number which you wanna update");
        tasknewno = Integer.parseInt(sc.nextLine());
        System.out.println("Enter task u need to update");
        tasknewwork = sc.nextLine();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");) {
            String updateQuery = "UPDATE tasknew SET tasknew_action =?, tasknewwork = ?, timerecorded = ? WHERE tasknewno = ? AND todousername = ? ";
            try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                updateStmt.setString(1, tasknew_action);
                updateStmt.setString(2, tasknewwork);
                updateStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                updateStmt.setInt(4, tasknewno);
                updateStmt.setString(5, Login.todousername);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    resultMessage = "Task has been updated successfully.";
                    return "Task has been updated successfully. ";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }
}


