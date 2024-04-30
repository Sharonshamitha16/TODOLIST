package org.todolist;

import java.sql.*;

        import static org.todolist.Taskmenu.*;

public class Updatetask {
    public static String updatetask() {
        String resultMessage = "Task update failed."; // Default message
        //System.out.println("Enter the task which you want to add:");
        //String tasknew_work = Taskmenu.sc.nextLine();

        System.out.println("Enter the task number which you wanna display");
        tasknewno = Integer.parseInt(sc.nextLine());
        System.out.println("Enter task u need to update");
         tasknewwork = sc.nextLine();


        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        ) {//tasknew_action =?,
            String updateQuery = "UPDATE tasknew SET  tasknewwork = ?,tasknew_action =?, timerecorded = ? WHERE tasknewno = ? AND todousername = ? ";
            try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
               // updateStmt.setString(1,tasknew_action);
                updateStmt.setString(1, tasknewwork); // Replace with user input or method argument
                updateStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Example timestamp, adjust as necessary
                updateStmt.setInt(3, tasknewno); // Assuming tasknewno is the task number to update
                updateStmt.setString(4, Login.todousername); // Assuming todousername is the username associated with the task
               // updateStmt.setInt(5, Register.getUserId());
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    resultMessage = "Task has been updated successfully.";
                    return "Task has been updated successfully. ";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // For debugging, consider logging the error
        }

        return resultMessage;
    }
}


