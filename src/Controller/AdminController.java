package Controller;

import Entity.Course;
import Entity.Index;
import Entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;



/*

    Not Complete yet
    Need suggestion about updates and certain functionalities

 */


public class AdminController {

    private final String studentFile = "student.dat";
    private final String courseFile = "course.dat";
    private ArrayList<Object> courses;
    private ArrayList<Object> students;
    private FileMgr fileMgr;
    private SystemMgr systemMgr;

    public AdminController() {
        fileMgr = new FileMgr();
        systemMgr = new SystemMgr();
        students = fileMgr.loadObjects(studentFile);
        courses = fileMgr.loadObjects(courseFile);
    }



    public void editAccessPeriod(LocalTime startTime, LocalTime endTime, LocalDate startDay, LocalDate endDate) {
        Student.setAccessPeriod(startTime, endTime, startDay, endDate);
    }

    public void addStudent(Object student) {
        // Check should be done in UserInterface ???
        students.add(student);
        fileMgr.saveObjects(students, studentFile);
    }

    public void removeStudent(Object student) {
        // Check should be done in UserInterface ???
        students.remove(student);
        fileMgr.saveObjects(students, studentFile);
    }

    public void updateStudent() {

                    System.out.println("Select the option to update");
                    System.out.println();
                    System.out.println("1. Name");
                    System.out.println("2. Nationality");
                    System.out.println("3. School");
                    System.out.println("4. Max AUs allowed");
                    Scanner sc = new Scanner(System.in);
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1: System.out.println("Enter updated name");
                                String updatedName = sc.next();
                            break;
                        case 2: System.out.println("Enter updated nationality");
                                String updatedNationality = sc.next();
                            break;
                        case 3: System.out.println("Enter updated school");
                                String updatedSchool = sc.next();
                            break;
                        case 4: System.out.println("Enter new limit for AUs");
                                int updatedAU = sc.nextInt();
                            break;
                        default: System.out.println("Wrong input");
                            break;
                        }
    }

    public void addCourse(Object course) {
        courses.add(course);
        fileMgr.saveObjects(courses, courseFile);
    }

    public void removeCourse(Object course) {
        courses.remove(course);
        fileMgr.saveObjects(courses, courseFile);
    }

    public void updateCourse(Course course) {
        System.out.println("1. Edit Course code");
        System.out.println("2. Edit Course name");
        System.out.println("3. Add a new index");
        System.out.println("4. Update an existing index");
        System.out.println("5. Remove an index");
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            choice = sc.nextInt();
        } while (choice >= 1 && choice <= 5);

        switch (choice) {
            case 1:
                System.out.println("Enter the new Course Code");
                course.setCourseCode(sc.next());
                // Set the course code of all index numbers also
                break;
            case 2:
                System.out.println("Enter the new Course Name");
                String newCourseName = sc.next();
                break;
            case 3:
                int indexNumber, vacancy;
                System.out.println("Enter the index number");
                indexNumber = sc.nextInt();
                System.out.println("Enter the vacancy");
                vacancy = sc.nextInt();
                course.addIndexNumber(indexNumber, vacancy);
                break;
            case 4:
                int indexNum;
                System.out.println("Enter the index number to update");
                indexNum = sc.nextInt();
                Object index = new Index(indexNum);
                SystemMgr systemMgr = new SystemMgr();
                index = systemMgr.findObject(course.getIndexNumberList(), index);
                if (index != null)
                    updateIndex(index);
                else
                    System.out.println("No such index number exists");
        }

    }

    public void checkAvailability(Index index) {
        System.out.println("Vacancies remaining for Index " + index.getIndexNumber() + " : " + index.getVacancy());
    }

    public void printStudentLstByIndex(Index index) {
        for (Student student : index.getStudentsRegistered()
        ) {
            student.print();
        }
    }

    public void printStudentListByCourse(Course course) {
        for (Object index : course.getIndexNumberList()
        ) {
            for (Student student : ((Index) index).getStudentsRegistered()
            ) {
                student.print();
            }
        }
    }

    public void addIndex(Course course) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the index number");
        int indexNumber = sc.nextInt();
        System.out.println("Enter the vacancy");
        int vacancy = sc.nextInt();
        course.addIndexNumber(indexNumber, vacancy);

        System.out.println("Add Sessions");

        Object dummyIndex = new Index(indexNumber);
        addSession((Index) systemMgr.findObject(course.getIndexNumberList(), dummyIndex));

    }

    public void removeIndex() {
    }

    public void updateIndex(Object index) {
        System.out.println("1. Edit index number");
        System.out.println("2. Edit vacancy");
        System.out.println("Press any other key to exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice < 1 || choice > 2)
            return;
        else {
            switch (choice) {
                case 1:
                    int indexNumber;
                    System.out.println("Enter the new index number");
                    indexNumber = sc.nextInt();
                    ((Index) index).setIndexNumber(indexNumber);
                    break;
                case 2:
                    int totalVacancy;
                    System.out.println("Enter the new vacancy number");
                    totalVacancy = sc.nextInt();
                    int currentSize = ((Index) index).getStudentsRegistered().size();
                    if (totalVacancy < currentSize) {
                        System.out.println("Too many students have registered already.");
                        System.out.println("Decreasing the total vacancy will be inconsiderate to the students");
                        System.out.println("Consider deleting this index number and creating it again");
                    } else {
                        ((Index) index).setVacancy(totalVacancy - currentSize);
                    }

            }
        }
    }

    private void addSession(Index index) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Add session?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            choice = sc.nextInt();
            if (choice == 2) {
                break;
            } else if (choice < 1 || choice > 2) {
                continue;
            } else {
                String labWeek = null;
                System.out.println("Enter the type of class (Lecture, Tutorial, Lab");
                String type = sc.next();
                if (type.toLowerCase().equals("lab")) {
                    System.out.println("Enter the Lab week ( Even, Odd, Both )");
                    labWeek = sc.next();
                }
                System.out.println("Enter the day (0 to 7) for Monday to Sunday");
                int day = sc.nextInt();
                System.out.println("Enter the venue");
                String venue = sc.next();
                System.out.println("Enter the start time (Separate the hour and minute by space");
                int startHour = sc.nextInt();
                int startMinute = sc.nextInt();
                System.out.println("Enter the start time (Separate the hour and minute by space");
                int endHour = sc.nextInt();
                int endMinute = sc.nextInt();

                if (labWeek == null) {
                    index.addLesson(day, venue, type, LocalTime.of(startHour, startMinute), LocalTime.of(endHour, endMinute));
                } else {
                    index.addLesson(day, venue, type, LocalTime.of(startHour, startMinute), LocalTime.of(endHour, endMinute), labWeek);
                }
            }
        }
    }

    public ArrayList<Object> getCourses() {
        return courses;
    }

    public ArrayList<Object> getStudents() {
        return students;
    }
}
