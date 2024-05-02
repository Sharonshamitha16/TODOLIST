package org.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.todolist.Taskmenu.sc;

public class Deletetask {

    public static String tasknew_action;

    public static Object deletetask() throws SQLException {
        System.out.println("Enter the task which you need to perform");
        tasknew_action = sc.nextLine();
        System.out.println("Enter the task number which you want to delete");
        int tasknewno = sc.nextInt();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        ) {
//            String query3 = "SELECT * FROM tasknew WHERE todousername = ? AND tasknewno = ?";
//            try {
//                PreparedStatement pst1 = con.prepareStatement(query3);
//                pst1.setString(1, todousername);
//                pst1.setInt(2, tasknewno);
//
//                ResultSet rs = pst1.executeQuery();
//                if (rs.next()) {
                   // tasknewno = rs.getInt("tasknewno");
                    String deleteQuery = "UPDATE tasknew SET " +
                            " tasknewwork = NULL, tasknew_action=null, WHERE tasknewno = ? ";
                    PreparedStatement pst2 = con.prepareStatement(deleteQuery);
                    pst2.setInt(1, tasknewno);
                    //pst2.setInt(2, userid);

                    int rowsUpdated = pst2.executeUpdate();
                    if (rowsUpdated > 0) {
                        return "Task has been deleted successfully";
                    } else {
                        return "Task not found or could not be deleted";
                    }
//                } else {
//                    return "User not found or incorrect password";
//                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "An error occurred while deleting the task";
            }
        }

    }



//    public static Object deletetask() throws SQLException {
//        System.out.println("Enter the task number which you wanna delete");
//        tasknewno = sc.nextInt();
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
//
////        String query3 = "SELECT UserRegisternew.userid, tasknew.tasknew_action, tasknew.tasknewwork, tasknew.timerecorded " +
////                "FROM tasknew " +
////                "INNER JOIN UserRegisternew ON tasknew.userid = UserRegisternew.userid";
////
//        String query3 = " select UserRegisternew.userid, UserRegisternew.todousername, tasknew.tasknew_action ,tasknew.tasknewwork ,tasknew.timerecorded from tasknew\n" +
//                "  right join   UserRegisternew on  UserRegisternew.userid = tasknew.userid and UserRegisternew.todousername =tasknew.todousername\n" +
//                "  WHERE UserRegisternew.todousername = ? AND UserRegisternew.passwd = ?;\n";
//
//        try {
//            PreparedStatement pst1 = con.prepareStatement(query3);
//            pst1.setString(1, todousername);
//            pst1.setString(2, passwd);
//
//            ResultSet rs = pst1.executeQuery();
//            if (rs.next()) {
//                Timestamp timerecorded = rs.getTimestamp("timerecorded");
//                //UPDATE tasknew SET column_name = NULL WHERE tasknewno = ? AND userid = ?"
//                String deleteQuery = "UPDATE tasknew  SET   tasknew  tasknew_action=null,  tasknewwork=null where tasknewno= ? and todousername=?";
//                PreparedStatement pst2 = con.prepareStatement(deleteQuery);
//                pst2.setString(1, tasknew_action); // Assuming tasknewno is a variable declared somewhere
//                pst2.setString(2,tasknewwork); // Assuming "userid" is the column name in the result set
//               pst2.setInt(3,tasknewno);
//               pst2.setString(4,todousername);
//                int rowsDeleted = pst2.executeUpdate();
//
//                if (rowsDeleted > 0) {
//                    return "Task has been deleted successfully";
//                } else {
//                    return "Task not found or could not be deleted";
//                }
//            } else {
//                return "User not found";
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "An error occurred while deleting the task";
//        }
//
//
//    }
//}