package boundary;

import controller.StudentRecordsMgr;
import actor.Actor;
import controller.UpdateManager;

import java.util.Scanner;

public class StudentUpdateInterface {
    public static void main(String args[], Actor actor) {
        StudentRecordsMgr studentUpdateManager = new StudentRecordsMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            String userName = "";
            System.out.println("Update Student Menu");
            System.out.println("Enter the student's username (Enter 0 to go back)");
            userName = sc.next();
            if (userName.equals("0"))
                return;
            boolean isStudentPresent = studentUpdateManager.checkObjectExists(userName);
            if (!isStudentPresent) {
                System.out.println("The student doesn't exists in the system");
                break;
            } else {
                System.out.println("Enter the field to update");
                System.out.println("1: Name");
                System.out.println("2: Nationality");
                System.out.println("3: School");
                System.out.println("4: Max allowable AUs");
                System.out.println("Press 0 to go back");
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
                        System.out.println("Enter the new name of the student");
                        name = sc.next();
                        updateManager.updateStudentName(userName, name);
                        goBack = true;
                        break;
                    case 2:
                        System.out.println("Enter the new nationality of the student");
                        nationality = sc.next().toUpperCase();
                        updateManager.updateStudentNationality(userName, nationality);
                        goBack = true;
                        break;
                    case 3:
                        System.out.println("Enter the new school of the student");
                        school = sc.next();
                        updateManager.updateStudentSchool(userName, school);
                        goBack = true;
                        break;
                    case 4:
                        System.out.println("Enter the max allowable AUs");
                        maxAU = sc.nextInt();
                        updateManager.updateStudentMaxAU(userName, maxAU);
                        goBack = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;

                }
                if (goBack)
                    break;
            }
        } while (choice != 0);
    }
}
