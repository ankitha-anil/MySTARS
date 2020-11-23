package boundary;

import controller.*;
import actor.Actor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Console;

import static boundary.MyStarsInterface.RED;
import static boundary.MyStarsInterface.RESET;

/**
 * Boundary class that facilitates the login function
 *
 * @author anon
 */

public class LoginInterface {

    /**
     * Main function that executes login interface. User inputs the domain (admin or student)
     *
     * @param args null argument can be used to call the login interface
     * @throws IOException throws IOException
     */

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        String domain = "";
        do {

            System.out.println("+----------------------------+");
            System.out.println("|  Select your domain        |");
            System.out.println("|----------------------------|");
            System.out.println("|  1: Admin                  |");
            System.out.println("|  2: Student                | ");
            System.out.println("|  0: Exit                   |");
            System.out.println("+----------------------------+");
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
                    System.out.println(RED + "Invalid Choice" + RESET);

            }
        } while (choice != 0);

    }

    /**
     * Function that retrieves login details and passes it to the domain type interface
     *
     * @param domain String used to check type of user
     * @throws IOException
     */

    private static void login(String domain) throws IOException {
        Scanner sc = new Scanner(System.in);
        SystemMgr systemMgr = new SystemMgr();
        Console console = System.console();
        String userName, password;
        System.out.println("  +-------------------------+");
        System.out.print("  |  Enter your user name : |  ");
        userName = sc.next();
        System.out.println("  +-------------------------+");
        try {
            password = String.valueOf(console.readPassword("  |  Enter your password :  |  "));
        } catch (NullPointerException e) {
            System.out.println(RED + "Cannot mask password" + RESET);
            System.out.print("  |  Enter your password :  |  ");
            password = sc.next();
        }

        System.out.println("  +-------------------------+");

        if (!LoginMgr.loginCheck(userName, password, domain)) {
            System.out.println(RED + "The username or password is incorrect" + RESET);
        } else {
            Actor actor = new Actor(userName);
            if (actor != null) {
                if (domain.equals("admin")) {
                    BoundaryController.callAdminFunctionInterface(actor);
                } else if (domain.equals("student")) {
                    if (systemMgr.isAccessible())
                        BoundaryController.callStudentFunctionInterface(actor);
                    else {
                        System.out.println(RED + "You are not allowed to register for course now" + RESET);
                        systemMgr.printSystemRegistrationTime();
                    }
                } else {
                    System.out.println(RED + "Invalid Domain" + RESET);
                }
            } else {
                System.out.println(RED + "System Error: Sorry for any inconvenience" + RESET);
            }
        }
    }
}
