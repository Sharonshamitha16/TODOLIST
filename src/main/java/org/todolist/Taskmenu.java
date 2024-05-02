package org.todolist;

import java.util.Scanner;

public class Taskmenu {
    public static Scanner sc = new Scanner(System.in);
    public static String tasknew_action;
    static int tasknewno;
    static String tasknewwork;
//    User user = new User(getUserId(), todousername,
//            passwd, email, gender );//tasknewno
   // private Timestamp timerecorded;

//    private String Tasknewwork() {
//        return  tasknewwork;
//    }
//    private int Tasknewno(){return tasknewno;}

    public static String task_action() {

        Scanner sc = new Scanner(System.in);

        System.out.println("1.Create a task");
        System.out.println("2.Update a task");

        System.out.println("3.Delete a task");
        System.out.println("4.Display a task");
        System.out.println("5.Exit");
        return "Enter the choice to select the task..";

    }


}



