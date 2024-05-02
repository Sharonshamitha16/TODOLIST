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
                    String deleteQuery = "UPDATE tasknew SET " +
                            " tasknewwork = NULL , tasknew_action=null WHERE tasknewno = ? ";
                    PreparedStatement pst2 = con.prepareStatement(deleteQuery);
                    pst2.setInt(1, tasknewno);
                    int rowsUpdated = pst2.executeUpdate();
                    if (rowsUpdated > 0) {
                        return "Task has been deleted successfully";
                    } else {
                        return "Task not found or could not be deleted";
                    }
            } catch (SQLException e) {
                e.printStackTrace();
                return "An error occurred while deleting the task";
            }
        }
    }


