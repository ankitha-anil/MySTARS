package boundary;

import controller.BoundaryController;
import controller.CourseMgr;
import actor.Actor;
import controller.IndexMgr;
import controller.UpdateManager;
import entity.Index;

import java.util.Scanner;

public class CourseUpdateInterface {

    public static void main(String[] arg, Actor actor) {
        CourseMgr courseMgr = new CourseMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            String courseCode = "";
            System.out.println("Enter the course code (Enter 0 to go back)");
            courseCode = sc.next();
            if (courseCode.equals("0"))
                return;
            boolean isCoursePresent = courseMgr.checkObjectExists(courseCode);
            if (!isCoursePresent) {
                System.out.println("The course doesn't exists in the system");
                break;
            } else {
                System.out.println("Enter the field to update");
                System.out.println("1: Course code");
                System.out.println("2: Course Name");
                System.out.println("3: School");
                System.out.println("4: Add a new index");
                System.out.println("5: Update an index");
                System.out.println("Press 0 to go back");
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
                int academicUnits = 0;
                String indexNumber;
                int vacancy;
                boolean goBack = false;
                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Enter the new course code");
                        newCourseCode = sc.next();
                        updateManager.updateCourseCode(courseCode, newCourseCode);
                        goBack = true;
                        break;
                    case 2:
                        System.out.println("Enter the new course name");
                        courseName = sc.next();
                        courseMgr.updateCourseName(courseCode, courseName);
                        goBack = true;
                        break;
                    case 3:
                        System.out.println("Enter the new school of the course");
                        school = sc.next();
                        courseMgr.updateCourseSchool(courseCode, school);
                        goBack = true;
                        break;
                    case 4:
                        System.out.println("Enter the index number");
                        indexNumber = sc.next();
                        System.out.println("Enter the vacancy");
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
                        System.out.println("Invalid option");
                        break;

                }
                if (goBack)
                    break;
            }
        } while (choice != 0);
    }
}
