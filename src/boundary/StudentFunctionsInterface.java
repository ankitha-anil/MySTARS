package boundary;

import controller.*;
import actor.Actor;
import entity.Course;

import java.io.IOException;
import java.util.Scanner;
import java.io.Console;

import static boundary.MyStarsInterface.RED;
import static boundary.MyStarsInterface.RESET;

/**
 * Boundary class that executes Student user functionalities
 *
 * @author anon
 */

public class StudentFunctionsInterface {

    /**
     * Main function that displays menu of student functions and input details or view details based on chosen function.
     * The operations are: Add, drop and print course, Check vacancies, Change and swap index numbers, Printing time table, course list, index list and request for overloading.
     *
     * @param args  null argument can be used to call the StudentFunction interface
     * @param actor Actor object which passes username details from LoginInterface
     * @throws IOException throws IOException
     */

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
                    if (courseMgr instanceof CourseMgr)
                        ((CourseMgr) courseMgr).checkAvailabilityIndex(courseCode, indexNumber);
                    else System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
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
                    if (studentMgr instanceof StudentRecordsMgr)
                        ((StudentRecordsMgr) studentMgr).printTimeTable(actor.getUserName());
                    else System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                    break;
                case 8:
                    courseMgr.printObjects();

                    break;
                case 9:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    if (courseMgr instanceof CourseMgr)
                        ((CourseMgr) courseMgr).printIndexes(courseCode);
                    else System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
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
