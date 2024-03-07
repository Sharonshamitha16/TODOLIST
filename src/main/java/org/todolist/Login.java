package org.todolist;

import java.sql.*;
import java.util.*;

public class Login {
    static int userid;
    static String todousername;
    static String passwd;

    public static String login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name:");
        todousername = scanner.nextLine();

        System.out.println("Enter your Password:");
        passwd = scanner.nextLine();


        return "logged in successfully";
    }

    public static boolean dbConnection() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        String query = "SELECT * FROM UserRegister WHERE todousername = ? AND passwd = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, todousername);
        pst.setString(2, passwd);

        ResultSet rs = pst.executeQuery();

//        while (true) {
//            if (rs.next()) {
//                System.out.println("Logged in successfully...");
//            } else {
//                System.out.println("Invalid credentials login failed...");
//            }
//

            con.close();
            return true;
        //}
    }
}




    


