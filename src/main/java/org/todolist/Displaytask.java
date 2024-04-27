package org.todolist;

import java.io.Serializable;
import java.sql.*;

import static org.todolist.Register.todousername;


public class Displaytask {
    public static Serializable displaytask() throws SQLException {
        System.out.println("Enter the task number which you wanna display");
       // String query4 = "select tasknewaction from tasknew where todousername =? and userid =  ?";

        String query4 = "select taskaction from task where todousername =? and userid =  ?";
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        try (PreparedStatement statement = con.prepareStatement(query4)) {
            statement.setString(1, todousername);
            statement.setInt(2, Register.getUserId());

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
