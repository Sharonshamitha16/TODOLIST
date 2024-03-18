package org.todolist;

import org.todolist.model.User;

import java.sql.Timestamp;
import java.util.Scanner;

import static org.todolist.Register.*;

//import static org.todolist.Register.userid;
//import static org.todolist.mainmenu.loggedIn;
public class Taskmenu {
    static Scanner sc = new Scanner(System.in);
    static String tasknew_action;
    static int tasknewno;
    static String tasknewwork;
    static String task_action;
    static int taskno;
    static String taskwork;
    User user = new User(getUserId(), todousername,
            passwd, email, gender);
    private Timestamp timerecorded;

    private String tasknewwork() {
        return  tasknewwork;
    }

    public static String task_action() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the task which you need to proceed add,modify,delete,display");
         tasknew_action = sc.nextLine();
        System.out.println("1.Create a task");
        System.out.println("2.Update a task");

        System.out.println("3.Delete a task");
        System.out.println("4.Display a task");
        System.out.println("5.Exit");
        return "Enter the choice to select the task..";

    }


}



