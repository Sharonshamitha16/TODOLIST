package org.todolist;

import java.sql.SQLException;
import java.util.Scanner;

import static org.todolist.Login.login;
import static org.todolist.Taskmenu.*;

public class mainmenu {
    private static boolean loggedIn = false;

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to the to do list command line app");
        System.out.println("Enter 1 to register ");
        System.out.println("Enter 2 to login");
        System.out.println("Enter 3 to exit");
        System.out.println("Enter the following numbers to login and register");
        Scanner sc = new Scanner(System.in);
        int choice1 = sc.nextInt();
        sc.nextLine();
        boolean exit = false;
        while (!exit) {
            switch (choice1) {
                case 1:
                    String regmsg = Register.register();
                    if (regmsg.contains("successfully")) {
                        if (Register.dbConnection()) {
                            System.out.println(regmsg);
                        } else {
                            System.out.println("Failed to register  user.");
                        }
                    }
                    break;

                case 2:
                    String logmsg = login();
                    if (Login.dbConnection()) {
                        if (logmsg.contains("successful")) {
                            System.out.println(logmsg);
                            loggedIn = true;
                        } else {
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
                    System.out.println(addtask());
                    
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



//  if(loggedIn){
//                                if (choice2 == 1) {
//                                    System.out.println(addtask());
//                                } else if (choice2 == 2) {
//                                    System.out.println(updatetask());
//
//                                    System.out.println(deletetask());
//
//                                    System.out.println(displaytask());
//                                } else if (choice2 == 3) {
//                                    System.out.println(deletetask());
//
//                                    System.out.println(displaytask());
//                                } else if (choice2 == 4) {
//                                    System.out.println(displaytask());
//                                } else {
//                                    throw new IllegalStateException("Unexpected value: " + choice2);
//                                }
//
//                            }
//
//                    }
