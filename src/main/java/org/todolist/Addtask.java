//package org.todolist;
//
//import java.sql.*;
//
//public class Addtask {
//    public static int addtask() throws SQLException {
//        System.out.println("Enter the task which you want to add:");
//        String tasknew_work = Taskmenu.sc.nextLine();
//
//        int userId = Register.getUserId();
//
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
//        String query = "INSERT INTO tasknew (userid,todousername, tasknew_action, tasknewwork, timerecorded) VALUES (?,?, ?, ?, NOW())";
////        String query1 = "SELECT UserRegisternew.userid, tasknew.tasknew_action, tasknew.tasknewwork, tasknew.timerecorded " +
////                "FROM tasknew " +
////                "Right JOIN UserRegisternew ON tasknew.userid = UserRegisternew.userid and UserRegisternew.todousername =tasknew.todousername";
//
//
////        String query1 = " select UserRegisternew.userid, UserRegisternew.todousername, task.task_action ,task.taskwork ,task.timerecorded from task\n" +
////                "  right join   UserRegister on  UserRegister.userid = task.userid and UserRegisternew.todousername =task.todousername\n" +
////                "  WHERE  UserRegisternew.passwd = ?;\n";
////
////
////        PreparedStatement selectpst = con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
//       // ResultSet rs1 = selectpst.executeQuery();
//        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//           //
//            pst.setInt(1, Register.getUserId());
//            pst.setString(2,Register.todousername);
//            pst.setString(3, Taskmenu.tasknew_action);
//            pst.setString(4 , tasknew_work);
//
//
//            int rowsInserted = pst.executeUpdate();
//            if (rowsInserted > 0) {
//                ResultSet generatedKeys = pst.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    int createdTaskNo = generatedKeys.getInt(1);
//                    System.out.println("Task created successfully with number: " + createdTaskNo);
//                    return userId;
//                }
//            } else {
//                System.out.println("No rows were inserted.");
//            }
//        } catch (SQLException e) {
//            // Print SQL exception details
//            e.printStackTrace();
//            System.out.println("SQL Exception: " + e.getMessage());
//        }
//        System.out.println("Failed to create task.");
//        return -1;
//    }
//
//
//
//}
package org.todolist;

import java.sql.*;

public class Addtask {
    public static int addtask() throws SQLException {
        int createdTaskNo = -1; // Initialize with a default value

        System.out.println("Enter the task which you want to add:");
        String tasknew_work = Taskmenu.sc.nextLine();

        int userId = Register.getUserId();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        String query = "INSERT INTO tasknew (userid, todousername, tasknew_action, tasknewwork, timerecorded) VALUES (?, ?, ?, ?, NOW())";

        try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, userId);
            pst.setString(2, Register.todousername);
            pst.setString(3, Taskmenu.tasknew_action);
            pst.setString(4, tasknew_work);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createdTaskNo = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return createdTaskNo;
    }
}
