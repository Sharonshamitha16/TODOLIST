package org.todolist;

import org.todolist.dao.UserDAO;
import org.todolist.dao.UserDAOImpl;

import java.sql.*;
import java.util.Scanner;

public class Register {

    public static String todousername;

    public static String passwd;
    public static String email;
    public static String gender;
    private static int userId;

    private static UserDAO userDAO = new UserDAOImpl();

    public static String register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name:");
        todousername = scanner.nextLine();

        System.out.println("Create your Password:");
        passwd = scanner.nextLine();

        System.out.println("Confirm your created password:");
        String confirmpasswd = scanner.nextLine();

        while (!passwd.equals(confirmpasswd)) {
            System.out.println("Passwords do not match. Please try again.");
            System.out.println("Create your Password:");
            passwd = scanner.nextLine();
            System.out.println("Confirm your created password:");
            confirmpasswd = scanner.nextLine();
        }

        System.out.println("Enter your Email:");
        email = scanner.nextLine();

        System.out.println("Enter your Gender:");
        gender = scanner.nextLine();

        System.out.println("Input values:");
        System.out.println("todousername: " + todousername);
        System.out.println("passwd: " + passwd);
        System.out.println("email: " + email);
        System.out.println("gender: " + gender);

        // Call the DAO method to register the user
        try {
            userId = userDAO.register(todousername, passwd, email, gender);
            System.out.println(" Successfully completed registration with User ID: " + userId);
            return "successfully completed registration...!!!";
        } catch (SQLIntegrityConstraintViolationException e) { /////////////////////////////////////////////
            System.out.println("Username already exists. Please choose a different username.");
            return "registration failed";
        } catch (SQLException e) {
            System.out.println("Registration failed due to an error: " + e.getMessage());
            return "registration failed";
        }
    }

    public static int getUserId() {
        return  userId;
    }


}
