package org.todolist;



import java.sql.SQLException;
import java.util.Scanner;
import static org.todolist.Login.todousername;
import static org.todolist.Taskmenu.*;

public class mainmenu {
    private static Object taskno;

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to the to do list command line app");
        System.out.println("Enter 1to register ");
        System.out.println("Enter 2 to login");
        System.out.println("Enter 3 to exit");
        System.out.println("Enter the following numbers to login and register");
        Scanner sc = new Scanner(System.in);
        int choice1 = sc.nextInt();
        int choice2 = sc.nextInt();
        switch (choice1){
            case 1:
                String regmsg = Register.register();
                if (regmsg.contains("successfully")) {
                    if (Register.dbConnection()) {
                        System.out.println(regmsg);
                    } else {
                        System.out.println("Failed to register user.");
                    }}


            case 2:

                Login lg =new Login();
                String logmsg = Login.login();
                if(logmsg.contains("successfull")){
                    if(lg.dbConnection(lg.todousername, lg.passwd)) {
                        System.out.println(lg.login());
                        System.out.println(task_action());
                        switch (choice2){
                            case 1:
                                System.out.println(addtask());
                                break;
                            case 2:
                                //System.out.println(updatetask(String task_action, String taskwork, Object taskno));
                            case 3:
                                //System.out.println(deletetask());
                            case 4:
                                //System.out.println(displaytask());
                        }
                    } else {
                        System.out.println("Database connection failed.");
                    }
                    break;

                }

            case 3:
                System.out.println("visit again ...");
                break;

            default:
                System.out.println("Invalid number choice...");


        }


        }




}

