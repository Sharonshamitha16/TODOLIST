package org.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;




public class Register {
    static int userid;
    static String todousername;
    static String passwd, confirmpasswd;
    static String email; //email1;
    static String gender;

    public static String register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name:");
        todousername = scanner.nextLine();

        System.out.println("Create your Password:");
        passwd = scanner.nextLine();

        while (true) {
            System.out.println("confirm your created password");
            confirmpasswd = scanner.nextLine();

            if (passwd.equals(confirmpasswd)) {
                //System.out.println("password matched..");
                break;
            } else {
                System.out.println("provide same password..");

            }
        }
        System.out.println("Enter your Email:");
        email = scanner.nextLine();
        //while(true){
//            System.out.println("Enter valid Email:");
//            email1 = scanner.nextLine();

//        if (email.contains("@")) {
//            // System.out.println("Valid email format.");
//            // break;
//        } else {
//            System.out.println("Invalid email format.");
//        }


        System.out.println("Enter your Gender:");
        gender = scanner.nextLine();

        System.out.println("Input values:");
        System.out.println("todousername: " + todousername);
        System.out.println("passwd: " + passwd);
        System.out.println("email: " + email);
        System.out.println("gender: " + gender);

        return "successfully completed registration...!!!";
    }

    public static boolean dbConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/TodoUserdetails";
//        String DBusername = "root";
//        String DBpassword = "Sharon@1602";
//        Connection con = DriverManager.getConnection(url, DBusername, DBpassword);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
        String query = "insert into UserRegister (todousername, passwd, email, gender)values(?,?,?,?);";
        PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, todousername);
        pst.setString(2, passwd);
        pst.setString(3, email);
        pst.setString(4, gender);
        int rows = pst.executeUpdate();
        // System.out.println("Number of rows added:" + rows);

        con.close();
        return true;
    }
}
//public static boolean dbConnection() {
//    try {
//        String url = "jdbc:mysql://localhost:3306/TodoUserdetails";
//        String DBusername = "root";
//        String DBpassword = "Sharon@1602";
//        Connection con = DriverManager.getConnection(url, DBusername, DBpassword);
//        String query = "INSERT INTO UserRegister (todousername, passwd, email, gender) VALUES (?, ?, ?, ?)";
//        PreparedStatement pst = con.prepareStatement(query);
//
//        pst.setString(1, todousername);
//        pst.setString(2, passwd);
//        pst.setString(3, email);
//        pst.setString(4, gender);
//        int rows = pst.executeUpdate();
//        System.out.println("Number of rows added: " + rows);
//
//        con.close();
//        return true;
//    } catch (SQLException e) {
//        e.printStackTrace();
//        return false;
//    }
//}
//
//
//}
//
