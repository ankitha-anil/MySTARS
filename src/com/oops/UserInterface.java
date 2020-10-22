package com.oops;

import java.util.Scanner;

public class UserInterface {

    String userName, passWord;
    int user;
    UserController U;
    Scanner sc= new Scanner(System.in);

    //4 files required: 1. Students, 2. Admin, 3. Courses, 4. School **yet to be finalized

    public void main(String[] args) {
       // while(1) {
            login();
            if ((U.loginCheck(userName, passWord))) {


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
