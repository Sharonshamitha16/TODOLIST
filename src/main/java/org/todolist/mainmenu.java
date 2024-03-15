package org.todolist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.todolist.Deletetask.deletetask;
import static org.todolist.Displaytask.displaytask;
import static org.todolist.Register.*;
import static org.todolist.Register.passwd;
import static org.todolist.Register.todousername;
import static org.todolist.Taskmenu.*;
import static org.todolist.Updatetask.updatetask;
//import static sun.security.jgss.GSSUtil.login;

public class mainmenu {
    static boolean loggedIn = false;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the to do list command line app");

        while (!exit) {
            if (!loggedIn) {
                System.out.println("Enter 1 to register ");
                System.out.println("Enter 2 to login");
                System.out.println("Enter 3 to exit");
                System.out.println("Enter the following numbers to login and register");

                int choice1 = sc.nextInt();
                sc.nextLine();

                switch (choice1) {
                    case 1:
                        String regmsg = register();
                        if (regmsg.contains("successfully")) {
                            if (dbConnection()) {
                                System.out.println(regmsg);
                                break;
                            } else {
                                System.out.println("Failed to register db connection lost...");
                            }
                        } else {
                            System.out.println("Failed to reg..");
                        }
                        break;

                    case 2:


                        // Perform database connection and authentication
                        if (Login.login()) {
                            loggedIn = true;
                            System.out.println("User " + todousername + " logged in successfully.");
                        } else {
                            System.out.println("Invalid credentials. Login failed.");
                        }
                        break;

                    case 3:
                        exit = true;
                        System.out.println("Visit again ...");
                        break;

                    default:
                        throw new IllegalStateException("Invalid choice. Please try again: " + choice1);
                }
            }

            if (loggedIn) {
                System.out.println(task_action());
                int choice2 = sc.nextInt();
                sc.nextLine();

                switch (choice2) {
                    case 1:
                        System.out.println(Addtask.addtask());
                        break;

                    case 2:
                        System.out.println(updatetask());
                        System.out.println(deletetask());
                        System.out.println(displaytask());
                        break;

                    case 3:
                        System.out.println(deletetask());
                        System.out.println(displaytask());
                        break;

                    case 4:
                        System.out.println(displaytask());
                        break;

                    case 5:
                        loggedIn = false;
                        System.out.println("Logged out successfully.");
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + choice2);
                }
            }
        }
    }


}
