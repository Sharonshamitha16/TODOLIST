package org.todolist;

import java.sql.*;

import static org.todolist.Login.todousername;
import static org.todolist.Register.passwd;
import static org.todolist.Taskmenu.sc;
import static org.todolist.Taskmenu.taskno;
import static org.todolist.Taskmenu.*;

public class Addtask {
    public static Object addtask() throws SQLException {
        System.out.println("enter the no of task");
        taskno = sc.nextInt();
        sc.nextLine();
        System.out.println("enter the task which u wanna add:");
        task_action = sc.nextLine();
        boolean taskno = false;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query1 = " select UserRegister.userid, UserRegister.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
                "  right join   UserRegister on  UserRegister.userid = task.userid and UserRegister.todousername =task.todousername\n" +
                "  WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?;\n";

        PreparedStatement pst1 = con.prepareStatement(query1);
        pst1.setString(1, todousername);
        pst1.setString(2, passwd);


        ResultSet rs = pst1.executeQuery();
       // rs = pst1.getGeneratedKeys();
        //if (rows1 > 0) {
        if (rs.next()) {

            Timestamp timerecorded = rs.getTimestamp("timerecorded");
            String query = "insert from the task(task_action, taskwork,timerecorded) values" +
                    "(?,?,?,NOW());";
            PreparedStatement pst2 = con.prepareStatement(query);
            // pst2.setInt(1, rs.getInt(userid));
            //pst2.setString(2, rs.getString(todousername));
            pst2.setString(3, task_action);
            pst2.setString(4, taskwork);
            pst2.setTimestamp(5, timerecorded);
            //pst2.setInt(6,taskno);


            pst2 = con.prepareStatement(query, Integer.parseInt(String.valueOf(PreparedStatement.RETURN_GENERATED_KEYS)));
            int rows = pst2.executeUpdate();


            int createdtaskno = 0;
            if (rows > 0) {
                ResultSet generatedKeys = pst2.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createdtaskno = generatedKeys.getInt(1);
                    System.out.println("Task created successfully with number: " + taskno);
                }
            }

            //}
            return createdtaskno;
        }


        return null;
    }
}