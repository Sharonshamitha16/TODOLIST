package org.todolist.dao;


import org.todolist.Register;

import java.sql.*;



public class UserDAOImpl implements UserDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
    }


    @Override
    public int register(String todousername, String passwd, String email, String gender) throws SQLException {
        String query = "INSERT INTO UserRegisternew (todousername, passwd, email, gender) VALUES (?, ?, ?, ?);";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, todousername);
            pst.setString(2, passwd);
            pst.setString(3, Register.email);
            pst.setString(4, Register.gender);

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }
}
