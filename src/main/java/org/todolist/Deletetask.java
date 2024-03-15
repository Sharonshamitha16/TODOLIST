package org.todolist;

import java.io.Serializable;
import java.sql.*;

//import static org.todolist.Login.todousername;
import static org.todolist.Register.*;
import static org.todolist.Taskmenu.task_action;
import static org.todolist.Taskmenu.taskwork;

public class Deletetask {
    public static Object deletetask() throws SQLException {
        System.out.println("Enter the task number which you wanna delete");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query3;
        query3 = " select UserRegister.userid, UserRegister.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
                "  right join   UserRegister on  UserRegister.userid = task.userid and UserRegister.todousername =task.todousername\n" +
                "  WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?;\n";

        try {
            PreparedStatement pst1 = con.prepareStatement(query3);
            pst1.setString(1, todousername);

            pst1.setString(2, passwd);
            int rows1 = pst1.executeUpdate(query3);

            ResultSet rs = pst1.executeQuery();
            if (rs.next()) {

                Timestamp timerecorded = rs.getTimestamp("timerecorded");
                query3 = "DELETE FROM TASK WHERE task_action = ? and taskno = ? and userid =? and todousername =?";
                PreparedStatement pst2 = con.prepareStatement(query3);
                pst2.setInt(1, rs.getInt(userid));
                pst2.setString(2, rs.getString(todousername));
                pst2.setString(3, task_action);
                pst2.setString(4, taskwork);
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