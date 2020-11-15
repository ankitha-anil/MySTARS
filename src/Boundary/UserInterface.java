package Boundary;

import Controller.FileMgr;
import Controller.UserController;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    public static String userName,passWord;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

       login();
        if(UserController.loginCheck(userName, passWord)){
            System.out.println("SUCCESS");
        }
        else
        System.out.println("The username or password you entered is incorrect, PLease try again");
        // }
    }


    public static void login(){
        System.out.println("Enter username: ");
        userName = sc.next();

        System.out.println("Enter password: ");
        passWord = sc.next();

        /*System.out.println("1: Staff ");
        System.out.println("2: Student ");
        user = sc.nextInt();*/

    }

}


