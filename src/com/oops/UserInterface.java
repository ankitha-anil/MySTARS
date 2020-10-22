package com.oops;

import java.util.Scanner;

public class UserInterface {

    String userName, passWord; //for login
    int user; //for login
    UserController U;
    Scanner sc= new Scanner(System.in);

    //4 files required: 1. Students, 2. Admin, 3. Courses, 4. School **yet to be finalized

    private final static String student = "./source/Student.dat";
    private final static String admin = "./source/Admin.dat";
    private final static String course = "./source/Course.dat";
    private final static String school = "./source/School.dat";

    public void main(String[] args) {
       // while(1) {
            login();
            if ((U.loginCheck(userName, passWord, user))) {


               // break;
            }
            else {
                System.out.println("The username or password you entered is incorrect, PLease try again");
            }
       // }

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
