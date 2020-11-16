package Boundary;

import Controller.AdminController;
import Controller.FileMgr;
import Controller.SystemMgr;
import Controller.UserController;
import Entity.Admin;
import Entity.Student;
import Entity.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {

    public static String userName,passWord;
    public static int user;
    public static Scanner sc = new Scanner(System.in);
    private static ArrayList<Object> User;
    private static FileMgr fileMgr;
    private static SystemMgr systemMgr;
    public static String fileName;

    public static void main(String[] args) throws IOException {

        while(true) {
           login();
           if(user==1)
               fileName="admin.dat";
           else if(user==2)
               fileName="student.dat";

           if (UserController.loginCheck(userName, passWord))
           {
               System.out.println("Login is successful!");
               fileMgr = new FileMgr();
               systemMgr = new SystemMgr();

               User = fileMgr.loadObjects(fileName);

               break;
           }
           else if (!(UserController.loginCheck(userName, passWord)))
               System.out.println("The username or password you entered is incorrect, PLease try again");
       }

    }


    public static void login(){
        System.out.println("1: Admin ");
        System.out.println("2: Student ");
        user = sc.nextInt();

        System.out.println("Enter username: ");
        userName = sc.next();

        System.out.println("Enter password: ");
        passWord = sc.next();



    }

}


