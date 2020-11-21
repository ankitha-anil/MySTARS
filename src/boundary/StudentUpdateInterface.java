package boundary;

import controller.StudentRecordsMgr;
import actor.Actor;
import controller.UpdateManager;

import java.util.Scanner;

public class StudentUpdateInterface {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";
    public static void main(String args[], Actor actor) {
        StudentRecordsMgr studentUpdateManager = new StudentRecordsMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            String userName = "";
            System.out.println("+----------------------------------------------------+");
            System.out.println("|                 Update Student Menu                |");
            System.out.println("+----------------------------------------------------+");
            System.out.print("| Enter the student's username (Enter 0 to go back): |  ");
            userName = sc.next();
            System.out.println("+----------------------------------------------------+");

            if (userName.equals("0"))
                return;
            boolean isStudentPresent = studentUpdateManager.checkObjectExists(userName);
            if (!isStudentPresent) {
                System.out.println(RED+"The student doesn't exists in the system"+RESET);
                break;
            } else {
                System.out.println("+------------------------------+");
                System.out.println("|   Enter the field to update  |");
                System.out.println("|------------------------------|");
                System.out.println("| 1: Name                      |");
                System.out.println("| 2: Nationality               |");
                System.out.println("| 3: School                    |");
                System.out.println("| 4: Max allowable AUs         |");
                System.out.println("|------------------------------|");
                System.out.println("|      Press 0 to go back      |");
                System.out.println("+------------------------------+");

                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(e);
                    sc.nextLine();
                    choice = -1;
                }

                String name = "";
                String nationality = "";
                String school = "";
                int maxAU;

                boolean goBack = false;

                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        System.out.print("Enter the new name of the student: ");
                        name = sc.next();
                        updateManager.updateStudentName(userName, name);
                        goBack = true;
                        break;
                    case 2:
                        System.out.print("Enter the new nationality of the student: ");
                        nationality = sc.next().toUpperCase();
                        updateManager.updateStudentNationality(userName, nationality);
                        goBack = true;
                        break;
                    case 3:
                        System.out.print("Enter the new school of the student: ");
                        school = sc.next();
                        updateManager.updateStudentSchool(userName, school);
                        goBack = true;
                        break;
                    case 4:
                        System.out.print("Enter the max allowable AUs: ");
                        maxAU = sc.nextInt();
                        updateManager.updateStudentMaxAU(userName, maxAU);
                        goBack = true;
                        break;
                    default:
                        System.out.println(RED+"Invalid option"+RESET);
                        break;

                }
                if (goBack)
                    break;
            }
        } while (choice != 0);
    }
}
