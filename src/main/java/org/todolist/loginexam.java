package org.todolist;
import java.sql.*;
import java.util.Scanner;



public class loginexam {
    private String todousername;
    private String passwd;
    private Timestamp datenow;
    public void login() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name to login:");
        todousername = scanner.nextLine();

        System.out.println("Enter your Password to login:");
        passwd = scanner.nextLine();



//        if (passwd.equals(confirmpasswd)) {
//            System.out.println("password matched..");
//
//        } else {
//            //System.out.println("provide same password..");
//
//        }

    }


    public void dbConnection(String todousername, String passwd) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/TodoUserdetails";
        String DBusername = "root";
        String DBpassword = "Sharon@1602";

        try (Connection con = DriverManager.getConnection(url, DBusername, DBpassword)) {
            // Check if the login credentials exist in UserRegisternew
            String query = "SELECT UserRegisternew.userid, UserLoginnew.datenow " +
                    "FROM UserRegisternew LEFT JOIN UserLoginnew ON UserRegisternew.userid = UserLoginnew.userid " +
                    "WHERE UserRegisternew.todousername = ? AND UserRegisternew.passwd = ?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, todousername);
                pst.setString(2, passwd);

                try (ResultSet resultSet = pst.executeQuery()) {
                    if (resultSet.next()) {
                        // User exists in UserRegisternew, login successful
                        System.out.println("Logged in successfully!");
                        Timestamp datenow = resultSet.getTimestamp("datenow");
                        // You can retrieve datenow from the ResultSet and use it as needed

                        // Optionally, you can insert a record into UserLoginnew with login timestamp
                        String insertLoginQuery = "INSERT INTO UserLoginnew (userid, datenow) VALUES (?, CURRENT_TIMESTAMP)";
                        try (PreparedStatement insertPst = con.prepareStatement(insertLoginQuery)) {
                            insertPst.setInt(1, resultSet.getInt("userid"));
                            int rowsInserted = insertPst.executeUpdate();
                            System.out.println(rowsInserted + " row(s) inserted into UserLoginnew.");
                        }
                    } else {
                        // User does not exist or invalid credentials
                        System.out.println("Invalid credentials. Login failed.");
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        try {
            loginexam login = new loginexam();
            login.login();
            login.dbConnection(login.todousername, login.passwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
