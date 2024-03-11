package org.todolist;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

import static org.todolist.Login.passwd;
import static org.todolist.Login.todousername;
import static org.todolist.Register.userid;

public class Taskmenu {
    private static Scanner sc = new Scanner(System.in);
    private static String task_action;
    private static int taskno;
    private static String taskwork;
    private Timestamp timerecorded;

    public void setTask_action() {
        this.task_action = task_action();
    }

    public void settaskno() {
        this.taskno = taskno;
    }

    public void settaskwork() {
        this.taskwork = taskwork;
    }

    public void timerecorded() {
        this.timerecorded = timerecorded;
    }

    public String gettask_action() {
        return this.task_action();
    }

    public String gettaskwork() {
        return this.gettaskwork();
    }


    public static String task_action() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the task which you need to proceed");
        task_action = sc.nextLine();
        System.out.println("Enter the choice to select the task..");
        System.out.println("1.Create a task");
        System.out.println("2.Update a task");

        System.out.println("4.Delete a task");
        System.out.println("5.Display a task");
        return null;
    }

    public static boolean addtask() throws SQLException {
        System.out.println("enter the no of task");
        taskno = sc.nextInt();
        System.out.println("enter the task which u wanna add:");
        task_action = sc.nextLine();
        boolean taskno = false;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query1 = " select UserRegister.userid, UserRegister.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
                "  right join   UserRegister on  UserRegister.userid = task.userid and UserRegister.todousername =task.todousername\n" +
                "  WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?;\n";

        PreparedStatement pst1 = con.prepareStatement(query1, Integer.parseInt(String.valueOf(PreparedStatement.RETURN_GENERATED_KEYS)));
        pst1.setString(1, todousername);
        pst1.setString(2, passwd);
        int rows1 = pst1.executeUpdate();


        ResultSet rs = pst1.executeQuery();
        rs = pst1.getGeneratedKeys();
        if (rows1 > 0) {
            if (rs.next()) {

                Timestamp timerecorded = rs.getTimestamp("timerecorded");
                String query = "insert from the task(task_action, taskwork,timerecorded) values" +
                        "(?,?,?,?);";
                PreparedStatement pst2 = con.prepareStatement(query);
                pst2.setInt(1, rs.getInt(userid));
                pst2.setString(2, rs.getString(todousername));
                pst2.setString(3, task_action);
                pst2.setString(4, taskwork);
                pst2.setTimestamp(5, timerecorded);
                //pst2.setInt(6,taskno);


                pst2 = con.prepareStatement(query);
                int rows = pst2.executeUpdate(query);


                if (rs.next()) {
                    int createdTaskNumber = rs.getInt(1);
                    System.out.println("Task created successfully with number: " + taskno);

                }
            }

        }
        return taskno;
    }

    public Object updatetask(String task_action, String taskwork, Object taskno) throws SQLException {

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

    public Object deletetask(String task_action, String taskwork, Object taskno) throws SQLException {
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

        public Serializable displaytask(Connection con) {
            System.out.println("Enter the task number which you wanna display");
            String query4 = "select taskaction from task where todousername =? and userid =  ?";
            con = null;
            try (PreparedStatement statement = con.prepareStatement(query4)) {
                statement.setString(1, todousername);
                statement.setInt(2, userid);

                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {

                        return rs.getString("taskaction");
                    } else {
                        System.out.println("No task action found for the given todousername and userid.");
                        return false;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




}
