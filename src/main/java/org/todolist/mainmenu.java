package org.todolist;

import java.sql.SQLException;
import java.util.Scanner;

public class mainmenu {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to the to do list command line app");
        System.out.println("Enter 1 to login");
        System.out.println("Enter 2 to register");
        System.out.println("Enter 3 to exit");
        System.out.println("Enter the following numbers to login and register");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                if(Login.dbConnection()) {
                    System.out.println(Login.login());
                } else {
                    System.out.println("Database connection failed.");
                }
                break;

            case 2:
                String registrationMessage = Register.register();
                if (registrationMessage.contains("successfully")) {
                if (Register.dbConnection()) {
                    System.out.println(registrationMessage);
                } else {
                    System.out.println("Failed to register user.");
                }}
//                if(Register.dbConnection()) {
//                    System.out.println(Register.register());
//                } else {
//                    System.out.println("Database connection failed.");
//                }
                break;
            case 3:
                System.out.println("visit again ...");
                break;

            default:
                System.out.println("Invalid number choice...");


        }


        }
    }

