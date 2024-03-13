package org.todolist;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.todolist.Login.todousername;
import static org.todolist.Register.userid;

public class Displaytask {
    public static Serializable displaytask() {
        System.out.println("Enter the task number which you wanna display");
        String query4 = "select taskaction from task where todousername =? and userid =  ?";
        Connection con = null;
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
