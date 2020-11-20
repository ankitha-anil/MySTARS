package boundary;

import controller.*;
import actor.Actor;

import java.io.IOException;
import java.util.Scanner;

public class StudentFunctionsInterface {
    public static void main(String[] args, Actor actor) throws IOException {
        RegistrationManager registrationManager = new RegistrationManager(new StudentRecordsMgr(), new CourseMgr());
        ObjectEntityController courseMgr = new CourseMgr();
        ObjectEntityController studentMgr = new StudentRecordsMgr();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Select your task: \n");
            System.out.println("1. Add a Course");
            System.out.println("2. Drop a Course");
            System.out.println("3. Print courses registered");
            System.out.println("4. Check vacancies available");
            System.out.println("5. Change index number of a course");
            System.out.println("6. Swap index numbers with another student");
            System.out.println("7. Print Time Table");
            System.out.println("8. View Course list");
            System.out.println("9. View Index list of a course");
            System.out.println("10. Request for Overloading");
            System.out.println("Press 0 to go back");
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
            String friendPassWord;
            String newIndexNumber;

            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Enter the course code of the course to add");
                    courseCode = sc.next();
                    System.out.println("Enter the index number of the course to add");
                    indexNumber = sc.next();
                    registrationManager.registerCourse(actor.getUserName(), courseCode, indexNumber);
                    break;
                case 2:
                    ((StudentRecordsMgr)studentMgr).printCoursesRegistered(actor.getUserName());
                    System.out.println("Enter the course code to remove");
                    courseCode = sc.next();
                    System.out.println("Enter the index to remove");
                    indexNumber = sc.next();
                    registrationManager.dropCourse(actor.getUserName(), courseCode, indexNumber, false);
                    break;
                case 3:
                    ((StudentRecordsMgr)studentMgr).printCoursesRegistered(actor.getUserName());
                    break;
                case 4:
                    System.out.println("Enter the course code");
                    courseCode = sc.next();
                    System.out.println("Enter the index number");
                    indexNumber = sc.next();
                    ((CourseMgr) courseMgr).checkAvailabilityIndex(courseCode, indexNumber);
                    break;
                case 5:
                    System.out.println("Enter the course code");
                    courseCode = sc.next();
                    System.out.println("Enter the current index number of that course");
                    indexNumber = sc.next();
                    System.out.println("Enter the new index number of that course");
                    newIndexNumber = sc.next();
                    registrationManager.changeIndex(actor.getUserName(), courseCode, indexNumber, newIndexNumber);
                    break;
                case 6:
                    System.out.println("Enter the course code");
                    courseCode = sc.next();
                    System.out.println("Enter the current index");
                    indexNumber = sc.next();
                    System.out.println("Enter the other student's user name");
                    friendName = sc.next();
                    System.out.println("Enter the other student's password");
                    friendPassWord = sc.next();
                    System.out.println("Enter the other student's index number");
                    newIndexNumber = sc.next();
                    registrationManager.swapIndex(actor.getUserName(), friendName, friendPassWord, courseCode, indexNumber, newIndexNumber);
                    break;

                case 7:
                    ((StudentRecordsMgr)studentMgr).printTimeTable(actor.getUserName());
                    break;
                case 8:
                    System.out.println("Below are the list of all courses");
                    courseMgr.printObjects();
                    break;
                case 9:
                    System.out.println("Enter the course code");
                    courseCode = sc.next();
                    System.out.println("Below are the index numbers with their vacancy anc number of registered students");
                    ((CourseMgr) courseMgr).printIndexes(courseCode);
                    break;

                case 10:
                    BoundaryController.callEmailAdminInterface(actor);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while (choice != 0);
    }
}
