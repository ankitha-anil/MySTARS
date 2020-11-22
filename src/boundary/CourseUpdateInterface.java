package boundary;

import controller.*;
import actor.Actor;
import entity.Course;

import java.util.Scanner;

import static boundary.MyStarsInterface.RED;
import static boundary.MyStarsInterface.RESET;

/**
 * Boundary class that admin uses to update course details
 *
 * @author anon
 */

public class CourseUpdateInterface {

    /**
     * Main function where admin can update course details based on chosen attribute (Course code, course name, school, add index, update index)
     *
     * @param args  null argument can be used to call the CourseUpdate interface
     * @param actor Actor object which passes username details from AdminFunctionInterface
     */

    public static void main(String[] args, Actor actor) {
        ObjectEntityController courseMgr = new CourseMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            String courseCode;
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                    Update Course Menu                   |");
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Enter the Course code to update (Enter 0 to go back):   |  ");
            courseCode = sc.next();
            if (courseCode.equals("0"))
                return;
            boolean isCoursePresent = courseMgr.checkObjectExists(courseCode);
            if (!isCoursePresent) {
                System.out.println(RED + "The course doesn't exists in the system" + RESET);
                System.out.println("+---------------------------------------------------------+");
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
                int vacancy = 10; //default for error update
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
                        System.out.println("Course name : " + courseName);
                        if (courseMgr instanceof CourseMgr)
                            ((CourseMgr) courseMgr).updateCourseName(courseCode, courseName);
                        else
                            System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                        goBack = true;
                        break;
                    case 3:
                        System.out.print("Enter the new school of the course : ");
                        school = sc.next();
                        if (courseMgr instanceof CourseMgr)
                            ((CourseMgr) courseMgr).updateCourseSchool(courseCode, school);
                        else
                            System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                        goBack = true;
                        break;
                    case 4:
                        System.out.print("Enter the index number : ");
                        indexNumber = sc.next();
                        System.out.print("Enter the vacancy : ");
                        try {
                            vacancy = sc.nextInt();
                            if (vacancy < 0) {
                                System.out.println(RED + "Vacancy must be a positive integer" + RESET);
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "Vacancy must be a positive integer" + RESET);
                        }
                        if (courseMgr instanceof CourseMgr)
                            ((CourseMgr) courseMgr).addIndex(courseCode, indexNumber, vacancy);
                        else
                            System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                        goBack = true;
                        break;
                    case 5:
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
