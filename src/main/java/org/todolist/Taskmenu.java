package org.todolist;

import org.todolist.model.User;

import java.sql.Timestamp;
import java.util.Scanner;

import static org.todolist.Register.*;

//import static org.todolist.Register.userid;
//import static org.todolist.mainmenu.loggedIn;


public class Taskmenu {
    static Scanner sc = new Scanner(System.in);
    static String task_action;
    static int taskno;
    static String taskwork;
    User user = new User(getUserId(), todousername,
            passwd, email, gender);
    private Timestamp timerecorded;

//    public void settask_action() {
//        this.task_action = task_action();
//    }
//
//    public void settaskno() {
//        this.taskno = taskno;
//    }
//
//    public void settaskwork() {
//        this.taskwork = taskwork;
//    }
//
//    public void timerecorded() {
//        this.timerecorded = timerecorded;
//    }
//
//    public String gettask_action() {
//        return this.task_action();
//    }

    public String getTaskwork() {
        return taskwork();
    }
    public void setTaskwork(){
        this.taskwork= taskwork();
    }

    private String taskwork() {
        return  taskwork;
    }





    public static String task_action() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the task which you need to proceed add,modify,delete,display");
         taskwork = sc.nextLine();
        System.out.println("Enter the choice to select the task..");
        System.out.println("1.Create a task");
        System.out.println("2.Update a task");

        System.out.println("3.Delete a task");
        System.out.println("4.Display a task");
        System.out.println("5.Exit");
        return "";

    }


}



