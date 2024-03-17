package org.todolist.dao;

import java.sql.SQLException;

public interface UserDAO {
    int registerUser(String todousername, String passwd, String email, String gender) throws SQLException;

    int register(String todousername, String passwd, String email, String gender) throws SQLException;

    //int register(String username, String password, String email, String gender) throws SQLException;
}
