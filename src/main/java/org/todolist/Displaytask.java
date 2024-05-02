//package org.todolist;
//
//import java.io.Serializable;
//import java.sql.*;
//import java.util.Scanner;
//
//import static org.todolist.Register.todousername;
//import static org.todolist.Taskmenu.tasknewno;
//
//
//public class Displaytask {
//    public static Serializable displaytask() throws SQLException {
//        Scanner sc = new  Scanner(System.in);
//        System.out.println("Enter the task number which you wanna display");
//        tasknewno=sc.nextInt();
//       // String query4 = "select tasknewaction from tasknew where todousername =? and userid =  ?";
//
//        String query4 = "select * from tasknew where todousername =? and tasknewno =  ?";
//        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
//        try (PreparedStatement statement = con.prepareStatement(query4)) {
//            statement.setString(1, todousername);
//            statement.setInt(2, Register.getUserId());
//
//
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//
//                    return rs.getString("taskaction");
//                } else {
//                    System.out.println("No task action found for the given todousername and userid.");
//                    return false;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//package org.todolist;
//
//import java.sql.*;
//import java.util.Scanner;
//
//public class Displaytask {
//    public static Task  displaytask() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the task number which you want to display:");
//        int tasknewno = sc.nextInt();
//
//        String query = "SELECT * FROM tasknew WHERE todousername = ? AND tasknewno = ?";
//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
//             PreparedStatement statement = con.prepareStatement(query)) {
//
//            statement.setString(1, Register.todousername);
//            statement.setInt(2, tasknewno);
//
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    // Display task details
//                    int taskNumber = rs.getInt("tasknewno");
//                    String taskAction = rs.getString("tasknew_action");
//                    String taskWork = rs.getString("tasknewwork");
//                    Timestamp timeRecorded = rs.getTimestamp("timerecorded");
//

//
//                    return new Task(taskNumber, taskAction, taskWork, timeRecorded);
//                } else {
//                    System.out.println("No task found for the given task number and username.");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
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

           // statement.setString(1, Register.todousername);
            statement.setInt(1,tasknewno);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Retrieve task details
                    todousername =rs.getString("todousername");
                    tasknewno = rs.getInt("tasknewno");
                    //Register.getUserId() = rs.getInt("userid");
                     tasknew_action = rs.getString("tasknew_action");
                     tasknewwork = rs.getString("tasknewwork");
                    Timestamp timeRecorded = rs.getTimestamp("timerecorded");
                    System.out.println("username :" + todousername);
                   System.out.println("Task Number: " + tasknewno);
                    System.out.println("Task Action: " + tasknew_action);
                    System.out.println("Task Work: " + tasknewwork);
                    System.out.println("Time Recorded: " + timeRecorded);

                    // Create and return a Task object
                    //return tasknewno;
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