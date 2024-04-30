package org.todolist.dao;

import java.sql.SQLException;

public interface UserDAO {
    int register(String todousername, String passwd, String email, String gender) throws SQLException;

}
