package com.oops;

import java.io.*;
import java.util.Scanner;

public class UserInterface {

    public static String userName;
    public static String passWord;
    public static int user;
    Scanner sc= new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       while(true) {

           UserInterface demo= new UserInterface();
           demo.login();
            if (UserController.loginCheck(userName, passWord,user)) {
                System.out.println("Login successful!");
            }
            else if(!(UserController.loginCheck(userName, passWord,user))){
               System.out.println("BOO!");

           }
       }

    }

    public void login(){
        System.out.println("Enter username: ");
        userName= sc.next();

        System.out.println("Enter password: ");
        passWord= sc.next();

        System.out.println("1: Staff ");
        System.out.println("2: Student ");
        user= sc.nextInt();
    }

}




