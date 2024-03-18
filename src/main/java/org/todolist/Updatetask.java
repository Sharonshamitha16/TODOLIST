package org.todolist;

import java.sql.*;

//import static org.todolist.Login.todousername;
import static org.todolist.Register.*;
import static org.todolist.Taskmenu.*;

public class Updatetask {
    public static String updatetask() throws SQLException {
        String resultMessage = "Task update failed."; // Default message

        System.out.println("Enter the task   which you wanna update");

//        String query2= "SELECT UserRegisternew.userid, tasknew.tasknew_action, tasknew.tasknewwork, tasknew.timerecorded " +
//                "FROM tasknew " +
//                "INNER JOIN UserRegisternew ON tasknew.userid = UserRegisternew.userid";
//
//        String query2 = " select UserRegisternew.userid, UserRegisternew.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
//                "  right join   UserRegisternew on  UserRegisternew.userid = task.userid and UserRegisternew.todousername =task.todousername\n" +
//                "  WHERE UserRegisternew.todousername = ? AND UserRegisternew.passwd = ?;\n";
//
//        PreparedStatement pst1 = con.prepareStatement(query2);
//        pst1.setString(1, todousername);
//
//        pst1.setString(2, passwd);
//        int rows1 = pst1.executeUpdate(query2);
        String selectQuery = "SELECT userid, taskwork, timerecorded FROM task WHERE todousername = ? AND passwd = ?";
        String updateQuery = "UPDATE task SET taskwork = ?, timerecorded = ? WHERE userid = ? AND todousername = ?";

        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

                PreparedStatement selectStmt = con.prepareStatement(selectQuery);
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
        ) {
            selectStmt.setString(1, todousername);
            selectStmt.setString(2, passwd);

            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    // Assuming you are getting new task details from somewhere
                    String newTaskWork = "New Task Work"; // Example update, get this from user input or method argument
                    Timestamp newTimeRecorded = new Timestamp(System.currentTimeMillis()); // Example timestamp, adjust as necessary

                    updateStmt.setString(1, newTaskWork);
                    updateStmt.setTimestamp(2, newTimeRecorded);
                    updateStmt.setInt(3, rs.getInt("userid"));
                    updateStmt.setString(4, todousername);

                    int rowsAffected = updateStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        resultMessage = "Task has been updated successfully.";
                        return "Task has been updated successfully you're task number is" + tasknewno;
                    }
                }
            } catch (SQLException e) {

                e.printStackTrace(); // For debugging, consider logging the error
            }
        }

        return resultMessage;
    }
}
