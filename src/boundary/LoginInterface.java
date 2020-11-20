package boundary;

import controller.*;
import actor.Actor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginInterface {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        String domain = "";
        do {

            System.out.println("Select your domain\n");
            System.out.println("1: Admin ");
            System.out.println("2: Student ");
            System.out.println("0: Exit");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e);
                sc.nextLine();
                choice = -1;
            }

            switch (choice) {
                case 0:
                    break;
                case 1:
                    domain = "admin";
                    login(domain);
                    break;
                case 2:
                    domain = "student";
                    login(domain);
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        } while (choice != 0);

    }

    private static void login(String domain) throws IOException {
        Scanner sc = new Scanner(System.in);
        SystemMgr systemMgr = new SystemMgr();
        String userName, password;
        System.out.println("Enter your user name");
        userName = sc.next();
        System.out.println("Enter your password");
        password = sc.next();

        if (!LoginMgr.loginCheck(userName, password, domain)) {
            System.out.println("The username or password is incorrect");
        } else {
            Actor actor = new Actor(userName);
            if (actor != null) {
                if (domain.equals("admin")) {
                    BoundaryController.callAdminFunctionInterface(actor);
                } else if (domain.equals("student")) {
                    if (systemMgr.isAccessible())
                        BoundaryController.callStudentFunctionInterface(actor);
                    else {
                        System.out.println("You are not allowed to register for course now");
                    }
                } else {
                    System.out.println("Invalid Domain");
                }
            } else {
                System.out.println("System Error... Sorry for any inconvenience");
            }
        }
    }
}
