package org.todolist;


import java.sql.SQLException;
import java.util.Scanner;

import static org.todolist.Deletetask.deletetask;
import static org.todolist.Displaytask.displaytask;
import static org.todolist.Login.login;
import static org.todolist.Register.confirmpasswd;
import static org.todolist.Register.passwd;
import static org.todolist.Taskmenu.*;
import static org.todolist.Updatetask.updatetask;

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
                        String regmsg = Register.register();
                        if (regmsg.contains("successfully")) {
                            if (Register.dbConnection()) {
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
                        String logmsg = login();
                        if (Login.dbConnection()) {
                            if (Login.passwd.equals(Register.confirmpasswd)) {
                                System.out.println(logmsg);
                                loggedIn = true;

                            } else {
                                loggedIn = false;
                                System.out.println("Login failed. Please try again.");
                            }
                        } else {
                            System.out.println("Database connection failed..");
                        }
                        break;
                    case 3:
                        exit = true;
                        System.out.println("visit again ...");
                        break;
                    default:
                        throw new IllegalStateException("Invalid choice. Please try again." + choice1);
                }
                if (loggedIn) {
                    System.out.println(task_action());
                    int choice2 = sc.nextInt();
                    sc.nextLine();
                    if (choice2 == 1) {
                        System.out.println(Addtask.addtask());

                    } else {
                        if (choice2 == 2) {
                            System.out.println(updatetask());
                            System.out.println(deletetask());
                            System.out.println(displaytask());
                        } else if (choice2 == 3) {
                            System.out.println(deletetask());
                            System.out.println(displaytask());
                        } else if (choice2 == 4) {
                            System.out.println(displaytask());
                        } else if (choice2 == 5) {
                            loggedIn = false; // Log out
                            System.out.println("Logged out successfully.");
                            break;

                        } else {
                            throw new IllegalStateException("Unexpected value: " + choice2);

                        }

                    }
                }

            }


        }

    }
}


