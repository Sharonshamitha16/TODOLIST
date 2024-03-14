package org.todolist;

import java.sql.*;
import java.util.Scanner;

public class Register {
    static int userid;
    static String todousername;
    static String passwd;
    static String confirmpasswd;
    private static String email, email1;
    private static String gender;

    public void setUserid(){
        this.userid = userid;
    }
    public static int getuserid(String todousername){
        return 0;
    }
    public Register(int userid, String confirmpasswd, String email, String email1, String gender) {
        Register.userid = userid;
        Register.passwd = passwd;
        Register.confirmpasswd = confirmpasswd;
        Register.email = email;
        Register.email1 = email1;
        Register.gender = gender;
    }

       public static String register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name:");
        todousername = scanner.nextLine();
        if (todousername.isEmpty()) {
            System.out.println("please provide username don't leave as blank");
            return register();
        }

           while (true) {
               System.out.println("Create your Password:");
               passwd = scanner.nextLine();
               if (passwd.isEmpty()) {
                   System.out.println("Please provide password, don't leave it blank.");
               } else {
                   break;
               }
           }
        while (true) {
            System.out.println("confirm your created password");
            confirmpasswd = scanner.nextLine();
            if (passwd.isEmpty()) {
                System.out.println("Please provide password, don't leave it blank.");
            } else {
                break;
            }
            if (passwd.equals(confirmpasswd)) {
                //System.out.println("password matched..");
                break;
            } else {
                System.out.println("provide same password..");

            }
            System.out.println("Enter valid Email:");
            email1 = scanner.nextLine();

            if (email.contains("@")) {
                // System.out.println("Valid email format.");
                // break;
            } else {
                System.out.println("Invalid email format.");
            }
            }
            System.out.println("Enter your Email:");
            email = scanner.nextLine();
           // if (email1.isEmpty()) {System.out.println("please provide email don't leave as blank");}

        System.out.println("Enter your Gender:");
        gender = scanner.nextLine();

        System.out.println("Input values:");
        System.out.println("todousername: " + todousername);
        System.out.println("passwd: " + passwd);
        System.out.println("email: " + email);
        System.out.println("gender: " + gender);
        System.out.println("userid"+ userid);

        return "successfully completed registration...!!!";
    }

    public static boolean dbConnection() throws SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
            String query = "insert into UserRegister (todousername, passwd, email, gender)values(?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS );
           // pst.setInt(1, userid);

            pst.setString(1, todousername);

            pst.setString(2, passwd);
            pst.setString(3, email);
            pst.setString(4, gender);
            int rows = pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                System.out.println("Generated user ID: " + userId);
            }
            con.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}