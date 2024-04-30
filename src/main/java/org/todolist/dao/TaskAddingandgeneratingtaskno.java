//package org.todolist.dao;
//
//import org.todolist.Register;
//import org.todolist.Taskmenu;
//
//import java.sql.*;
//
//public class TaskAddingandgeneratingtaskno {
//    private static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
//    }
//
//    // @override
//    public static int addtask() {
//        int createdTaskNo = -1; // Initialize with a default value
//
//        System.out.println("Enter the task which you want to add:");
//        String tasknew_work = Taskmenu.sc.nextLine();
//
//        int userId = Register.getUserId();
//
//        String query = "INSERT INTO tasknew (userid, todousername, tasknew_action, tasknewwork, timerecorded) VALUES (?, ?, ?, ?, NOW())";
//
//        try (Connection con = getConnection();
//             PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//
//            pst.setInt(1, userId);
//            pst.setString(2, Register.todousername);
//            pst.setString(3, Taskmenu.tasknew_action);
//            pst.setString(4, tasknew_work);
//
//            int rowsInserted = pst.executeUpdate();
//            if (rowsInserted > 0) {
//                ResultSet generatedKeys = pst.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    createdTaskNo = generatedKeys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("SQL Exception: " + e.getMessage());
//        }
//
//        return createdTaskNo;
//    }
// }
//
//package org.todolist.dao;
//
//import org.todolist.Register;
//import org.todolist.Taskmenu;
//
//import java.sql.*;
//
//public class TaskAddingandgeneratingtaskno {
//    private static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
//    }
//
//    public static int addtask() throws SQLException {
//        int createdTaskNo = -1; // Initialize with a default value
//
//        try (Connection con = getConnection();
//             PreparedStatement pst = con.prepareStatement("INSERT INTO tasknew (userid, todousername, tasknew_action, tasknewwork, timerecorded) VALUES (?, ?, ?, ?, NOW())",
//                     Statement.RETURN_GENERATED_KEYS)) {
//
//            pst.setInt(1, Register.getUserId());
//            pst.setString(2, Register.todousername);
//            pst.setString(3, Taskmenu.tasknew_action);
//            pst.setString(4, Taskmenu.sc.nextLine());
//
//            int rowsInserted = pst.executeUpdate();
//            if (rowsInserted > 0) {
//                ResultSet generatedKeys = pst.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    createdTaskNo = generatedKeys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("SQL Exception: " + e.getMessage());
//        }
//
//        return createdTaskNo;
//    }
//}
