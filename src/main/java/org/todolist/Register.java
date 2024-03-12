package org.todolist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class Register {
   static int userid;
   static String todousername;
   static String passwd;
   static String confirmpasswd;
   private static String email, email1;
   private static String gender;
public Register(int userid, String confirmpasswd, String email, String email1, String gender) {
    Register.userid = userid;
    Register.confirmpasswd = confirmpasswd;
    Register.email = email;
    Register.email1 = email1;
    Register.gender = gender;
}
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        Register.userid = userid;
    }

    public String getConfirmpasswd() {
        return confirmpasswd;
    }

    public void setConfirmpasswd(String confirmpasswd) {
        Register.confirmpasswd = confirmpasswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Register.email = email;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        Register.email1 = email1;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        Register.gender = gender;
    }
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
//        while(true){
//            System.out.println("Enter valid Email:");
//            email1 = scanner.nextLine();
//
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
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");
            String query = "insert into UserRegister (todousername, passwd, email, gender)values(?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, todousername);
            pst.setString(2, passwd);
            pst.setString(3, email);
            pst.setString(4, gender);
            int rows = pst.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}