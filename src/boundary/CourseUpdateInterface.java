package boundary;

import controller.BoundaryController;
import controller.CourseMgr;
import actor.Actor;
import controller.IndexMgr;
import controller.UpdateManager;

import java.util.Scanner;

public class CourseUpdateInterface {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";

    public static void main(String[] arg, Actor actor) {
        CourseMgr courseMgr = new CourseMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            String courseCode;
            courseCode = sc.next();
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                    Update Course Menu                   |");
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Enter the Course code to update (Enter 0 to go back):   |  ");
            courseCode = sc.next();
            System.out.println("+---------------------------------------------------------+");
            if (courseCode.equals("0"))
                return;
            boolean isCoursePresent = courseMgr.checkObjectExists(courseCode);
            if (!isCoursePresent) {
                System.out.println(RED + "The course doesn't exists in the system" + RESET);
                break;
            } else {
                System.out.println("+-------------------------------------------+");
                System.out.println("|         Enter the field to update         |");
                System.out.println("+-------------------------------------------+");
                System.out.println("| 1: Course code                            |");
                System.out.println("| 2: Course Name                            |");
                System.out.println("| 3: School                                 |");
                System.out.println("| 4: Add a new index                        |");
                System.out.println("| 5: Update an index                        |");
                System.out.println("|-------------------------------------------|");
                System.out.println("|            Press 0 to go back             |");
                System.out.println("+-------------------------------------------+");
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(e);
                    sc.nextLine();
                    choice = -1;
                }

                String newCourseCode = "";
                String courseName = "";
                String school = "";
                String indexNumber;
                int vacancy;
                boolean goBack = false;
                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        System.out.print("Enter the new course code : ");
                        newCourseCode = sc.next();
                        updateManager.updateCourseCode(courseCode, newCourseCode);
                        goBack = true;
                        break;
                    case 2:
                        System.out.print("Enter the new course name : ");
                        sc.nextLine();
                        courseName = sc.nextLine().trim();
                        courseMgr.updateCourseName(courseCode, courseName);
                        goBack = true;
                        break;
                    case 3:
                        System.out.print("Enter the new school of the course : ");
                        school = sc.next();
                        courseMgr.updateCourseSchool(courseCode, school);
                        goBack = true;
                        break;
                    case 4:
                        System.out.print("Enter the index number : ");
                        indexNumber = sc.next();
                        System.out.print("Enter the vacancy : ");
                        vacancy = sc.nextInt();
                        courseMgr.addIndex(courseCode, indexNumber, vacancy);
                        goBack = true;
                        break;
                    case 5:
                        // Set up index update interface
                        BoundaryController.callIndexUpdateInterface(actor, courseCode);
                        goBack = true;
                        break;

                    default:
                        System.out.println(RED + "Invalid option" + RESET);
                        break;

                }
                if (goBack)
                    break;
            }
        } while (choice != 0);
    }
}
