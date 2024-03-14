package org.todolist;

import java.io.Serializable;
import java.sql.*;
import java.util.*;


import static org.todolist.Login.todousername;
import static org.todolist.Register.userid;
import static org.todolist.Register.passwd;
//import static org.todolist.mainmenu.loggedIn;



public class Taskmenu {
    static Scanner sc = new Scanner(System.in);
    static String task_action;
    static int taskno;
    static String taskwork;
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

    public String gettaskwork() {
        return this.gettaskwork();
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



