package org.todolist;

import java.sql.*;

import static org.todolist.Register.*;
import static org.todolist.Taskmenu.*;

public class Deletetask {
    public static Object deletetask() throws SQLException {
        System.out.println("Enter the task number which you wanna delete");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

//        String query3 = "SELECT UserRegisternew.userid, tasknew.tasknew_action, tasknew.tasknewwork, tasknew.timerecorded " +
//                "FROM tasknew " +
//                "INNER JOIN UserRegisternew ON tasknew.userid = UserRegisternew.userid";
//
        String query3 = " select UserRegisternew.userid, UserRegisternew.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
                "  right join   UserRegisternew on  UserRegisternew.userid = task.userid and UserRegisternew.todousername =task.todousername\n" +
                "  WHERE UserRegisternew.todousername = ? AND UserRegisternew.passwd = ?;\n";

        try {
            PreparedStatement pst1 = con.prepareStatement(query3);
            pst1.setString(1, todousername);

            pst1.setString(2, passwd);
            int rows1 = pst1.executeUpdate(query3);

            ResultSet rs = pst1.executeQuery();
            if (rs.next()) {

                Timestamp timerecorded = rs.getTimestamp("timerecorded");
                query3 = "DELETE FROM tasknew WHERE tasknew_action = ? and tasknewno = ? and userid =? and todousername =?";
                PreparedStatement pst2 = con.prepareStatement(query3);
                pst2.setInt(1, rs.getInt(getUserId()));
                pst2.setString(2, rs.getString(todousername));
                pst2.setString(3, tasknew_action);
                pst2.setString(4, tasknewwork);
                pst2.setTimestamp(5, timerecorded);

                pst2 = con.prepareStatement(query3);
                int rows = pst2.executeUpdate(query3);

            }


            return "task has been deleted successfully";
        } catch (Exception E) {
            System.out.println("invalid task number ... ");
        }
        return "task has been deleted successfully";
    }

}