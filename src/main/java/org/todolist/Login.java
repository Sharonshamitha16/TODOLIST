package org.todolist;

import java.sql.*;
import java.util.*;

import static org.todolist.Register.confirmpasswd;

public class Login {
    //static int userid;
    protected static String todousername;
   protected static String passwd;
      //static Timestamp datenow;


    public static String login() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name to login:");
        todousername = scanner.nextLine();

        System.out.println("Enter your Password to login:");
        passwd = scanner.nextLine();



        if (passwd.equals(confirmpasswd)) {
            System.out.println("password matched..");

        } else {
            //System.out.println("provide same password..");

        }

        return "logged in... ";
    }
    public static boolean dbConnection(String todousername,String passwd) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoUserdetails", "root", "Sharon@1602");

        //String query = "SELECT * FROM UserRegister WHERE todousername = ? AND passwd = ?";
        String query ="SELECT UserRegister.userid, UserLogin.datenow \n" +
                "FROM UserRegister \n" +
                "LEFT JOIN UserLogin ON UserRegister.userid = UserLogin.userid \n" +
                "WHERE UserRegister.todousername = ? AND UserRegister.passwd = ?\n";

        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, todousername);
        pst.setString(2, passwd);

        ResultSet rs = pst.executeQuery();
        if(rs.next())
        {

            System.out.println("logged in successfully....");
           Timestamp datenow = rs.getTimestamp("datenow");


            String query1 = "String query1 = \"INSERT INTO UserLogin (userid, datenow) VALUES (1, CURRENT_TIMESTAMP)\";\n";

            PreparedStatement pst1 =con.prepareStatement(query1);
            pst1.setInt(1,rs.getInt("userid"));
            pst1.setTimestamp(2, datenow);

            try {
                int rowsInserted1 = pst1.executeUpdate();
                System.out.println(rowsInserted1 + " row(s) inserted into userlogin");
            } catch (SQLException e) {
                System.out.println("Error executing SQL statement: " + e.getMessage());
                e.printStackTrace();
            }

        }
        else{

            System.out.println("Invalid credentials. Login failed......");
        }
        con.close();
        return true;

    }


}




    


