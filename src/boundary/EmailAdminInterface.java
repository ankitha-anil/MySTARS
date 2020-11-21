package boundary;

import controller.AdminMgr;
import controller.CommunicationController;
import controller.EmailMgr;
import controller.Notifier;
import actor.Actor;

import java.util.Scanner;

public class EmailAdminInterface {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";

    public static void main(String[] args, Actor actor) {
        AdminMgr adminMgr = new AdminMgr();
        String adminUsername;
        Scanner sc = new Scanner(System.in);
        adminMgr.printObjects();
        System.out.println();
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("| Enter the admin username to send the email to (Press 0 to go back)  :        |  ");
        adminUsername = sc.next();
        System.out.println("+------------------------------------------------------------------------------------+");

        if (adminUsername.equals("0"))
            return;
        if (!adminMgr.checkObjectExists(adminUsername)) {
            System.out.println(RED + "No such admin exists" + RESET);
        } else {
            CommunicationController communicationController = new CommunicationController();
            int academicUnits;
            String message;
            System.out.print("How many AUs do you require? : ");
            academicUnits = sc.nextInt();
            System.out.print("State your reason : ");
            sc.nextLine();
            message = sc.nextLine();
            message = "I want to overload courses this semester. I would like to take " + academicUnits
                    + " AUs because " + message + ".";
            String subject = "Permission to Overload this semester for " + actor.getUserName();
            Notifier notifier = new EmailMgr();
            communicationController.communicateToAdmin(adminUsername, subject, message, notifier);
        }
    }
}
