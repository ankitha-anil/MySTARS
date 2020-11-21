package boundary;

import controller.*;
import actor.Actor;

import java.io.IOException;
import java.util.Scanner;
import java.io.Console;

public class StudentFunctionsInterface {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";

    public static void main(String[] args, Actor actor) throws IOException {
        RegistrationManager registrationManager = new RegistrationManager(new StudentRecordsMgr(), new CourseMgr());
        ObjectEntityController courseMgr = new CourseMgr();
        ObjectEntityController studentMgr = new StudentRecordsMgr();
        Console console = System.console();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("+--------------------------------------------+");
            System.out.println("|              Select your task              |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1. Add a Course                            |");
            System.out.println("| 2. Drop a Course                           |");
            System.out.println("| 3. Print courses registered                |");
            System.out.println("| 4. Check vacancies available               |");
            System.out.println("| 5. Change index number of a course         |");
            System.out.println("| 6. Swap index numbers with another student |");
            System.out.println("| 7. Print Time Table                        |");
            System.out.println("| 8. View Course list                        |");
            System.out.println("| 9. View Index list of a course             |");
            System.out.println("| 10. Request for Overloading                |");
            System.out.println("|--------------------------------------------|");
            System.out.println("|             Press 0 to go back             |");
            System.out.println("+--------------------------------------------+");
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e);
                sc.nextLine();
                choice = -1;
            }

            String courseCode;
            String indexNumber;
            String friendName;
            String newIndexNumber;
            String password;

            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.print("Enter the course code of the course to add: ");
                    courseCode = sc.next();
                    System.out.print("Enter the index number of the course to add: ");
                    indexNumber = sc.next();
                    registrationManager.registerCourse(actor.getUserName(), courseCode, indexNumber);
                    break;
                case 2:
                    ((StudentRecordsMgr) studentMgr).printCoursesRegistered(actor.getUserName());
                    System.out.print("Enter the course code to remove: ");
                    courseCode = sc.next();
                    System.out.print("Enter the index to remove: ");
                    indexNumber = sc.next();
                    registrationManager.dropCourse(actor.getUserName(), courseCode, indexNumber, false);
                    break;
                case 3:
                    ((StudentRecordsMgr) studentMgr).printCoursesRegistered(actor.getUserName());
                    break;
                case 4:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.print("Enter the index number: ");
                    indexNumber = sc.next();
                    ((CourseMgr) courseMgr).checkAvailabilityIndex(courseCode, indexNumber);
                    break;
                case 5:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.print("Enter the current index number of that course: ");
                    indexNumber = sc.next();
                    System.out.print("Enter the new index number of that course: ");
                    newIndexNumber = sc.next();
                    registrationManager.changeIndex(actor.getUserName(), courseCode, indexNumber, newIndexNumber);
                    break;
                case 6:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.print("Enter the current index: ");
                    indexNumber = sc.next();
                    System.out.print("Enter the other student's user name: ");
                    friendName = sc.next();
                    try {
                        password = String.valueOf(console.readPassword("Enter the other student's password: "));
                    } catch (NullPointerException e) {
                        System.out.println(RED + "Could not mask password" + RESET);
                        System.out.print("Enter the other student's password: ");
                        password = sc.next();
                    }
                    System.out.print("Enter the other student's index number: ");
                    newIndexNumber = sc.next();
                    registrationManager.swapIndex(actor.getUserName(), friendName, password, courseCode, indexNumber, newIndexNumber);
                    break;

                case 7:
                    ((StudentRecordsMgr) studentMgr).printTimeTable(actor.getUserName());
                    break;
                case 8:
                    System.out.println("+-------------List of courses----------------+"); //HERE
                    courseMgr.printObjects();
                    break;
                case 9:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.println("Index numbers with their vacancy and number of registered students"); //HERE
                    ((CourseMgr) courseMgr).printIndexes(courseCode);
                    break;

                case 10:
                    BoundaryController.callEmailAdminInterface(actor);
                    break;
                default:
                    System.out.println(RED + "Invalid choice" + RESET);
                    break;
            }

        } while (choice != 0);
    }
}
