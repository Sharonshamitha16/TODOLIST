package org.todolist;

import java.sql.*;

import static org.todolist.Login.todousername;
import static org.todolist.Register.passwd;
import static org.todolist.Register.userid;
import static org.todolist.Taskmenu.*;

public class Updatetask {
    public static String updatetask() throws SQLException {

        System.out.println("Enter the task   which you wanna update");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query2 = " select UserRegister.userid, UserRegister.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
                "  right join   UserRegister on  UserRegister.userid = task.userid and UserRegister.todousername =task.todousername\n" +
                "  WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?;\n";

        PreparedStatement pst1 = con.prepareStatement(query2);
        pst1.setString(1, todousername);

        pst1.setString(2, passwd);
        int rows1 = pst1.executeUpdate(query2);

        try (ResultSet rs = pst1.executeQuery()) {
            if (rs.next()) {

                Timestamp timerecorded = rs.getTimestamp("timerecorded");
                String query3 = "UPDATE task SET column1 = task_action , column2 = taskwork WHERE condition_column = ?";
                PreparedStatement pst2 = con.prepareStatement(query2);
                pst2.setInt(1, rs.getInt(userid));
                pst2.setString(2, rs.getString(todousername));


                pst2.setString(3, task_action);
                pst2.setString(4, taskwork);
                pst2.setTimestamp(5, timerecorded);

                pst2 = con.prepareStatement(query3);
                int rows = pst2.executeUpdate(query2);

            }
        }


        return "Task has been updated successfully you're task number is" + taskno;
    }

}
