package controller;

import entity.Course;
import entity.Index;
import entity.Lesson;
import entity.Student;

import java.util.ArrayList;

public class CourseMgr extends ObjectEntityController {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";
    public static final String GREEN = "\033[1;32m";
    public static final String YELLOW = "\033[1;33m"; // YELLOW

    private static ArrayList<Object> courses;
    private static final String courseFile = "course.dat";

    public CourseMgr() {
        super();
        courses = fileMgr.loadObjects(courseFile);
    }

    public boolean checkObjectExists(String courseCode) {
        loadCourseObjectList();
        Object course = new Course(courseCode);
        course = systemMgr.findObject(courses, course);
        if (course != null)
            return true;
        else return false;
    }


    public boolean checkIndex(String courseCode, String indexNumber) {
        loadCourseObjectList();
        if (checkObjectExists(courseCode)) {
            Object course = new Course(courseCode);
            Object existingCourse = systemMgr.findObject(courses, course);
            IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
            if (indexMgr.checkObjectExists(indexNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addCourse(String courseCode, String courseName, int academicUnits, String schoolName) {
        loadCourseObjectList();
        Object course = new Course(courseCode, courseName, academicUnits, schoolName);
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse == null) {
            courses.add(course);
            saveCourseObjectList();
            printObjects();
        } else {
            System.out.println(RED + "Course already exists: System cannot add this course" + RESET);
        }
    }

    // removeCourse is ot required for the project
    /*

    public void removeCourse(String courseCode) {
        loadCourseObjectList();
        Object course = new Course(courseCode);
        Object existingCourse = systemMgr.findObject(courses, course);
        if (existingCourse != null) {
            RegistrationManager registrationManager = new RegistrationManager(new StudentRecordsMgr(), new CourseMgr());
            courses.remove(existingCourse);
            for (Object index : ((Course) existingCourse).getIndexNumberList()) {
                for (Student student : ((Index) index).getStudentsRegistered()
                ) {
                    registrationManager.dropCourse(student.getNetworkName(), courseCode, String.valueOf(((Index) index).getIndexNumber()));
                }

                for (Student student : ((Index) index).getWaitingList()
                ) {
                    registrationManager.dropCourse(student.getNetworkName(), courseCode, String.valueOf(((Index) index).getIndexNumber()));
                }
            }
            fileMgr.saveObjects(courses, courseFile);
            printObjects();
        } else {
            System.out.println("Course doesn't exist");
            System.out.println("System cannot add this course");
        }
    }

     */

    // updateCourseCode is defined in UpdateManager
    /*
    public void updateCourseCode(String courseCode, String newCourseCode) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse != null) {
            ((Course) existingCourse).setCourseCode(newCourseCode);
            for (Object index : ((Course) existingCourse).getIndexNumberList()
            ) {
                ((Index) index).setCourseCode(courseCode);
            }
            saveCourseObjectList();

            System.out.println("Updated course");
            ((Course) existingCourse).print();
        } else {
            System.out.println("Course doesn't exist in the database");
            System.out.println();
        }
    }

     */

    public void updateCourseName(String courseCode, String newCourseName) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse != null) {
            ((Course) existingCourse).setCourseName(newCourseName);
            saveCourseObjectList();
            System.out.println(GREEN + "Updated course" + RESET);
            ((Course) existingCourse).print();
        } else {
            System.out.println(RED + "Course doesn't exist in the database" + RESET);
            saveCourseObjectList();
        }
    }

    /*
    public void updateCourseAU(String courseCode, int newAU) {
        loadCourseObjectList();
        Object course = new Course(courseCode);
        Object existingCourse = systemMgr.findObject(courses, course);
        if (existingCourse != null) {
            ((Course) existingCourse).setAcademicUnits(newAU);
            fileMgr.saveObjects(courses, courseFile);
        } else {
            System.out.println("Course doesn't exist in the database");
        }
    }
     */


    public void updateCourseSchool(String courseCode, String newCourseSchool) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse != null) {
            ((Course) existingCourse).setSchoolName(newCourseSchool);
            saveCourseObjectList();
            System.out.println(GREEN + "Updated course" + RESET);
            ((Course) existingCourse).print();
        } else {
            System.out.println(RED + "Course doesn't exist in the database" + RESET);
        }
    }

    public void addIndex(String courseCode, String indexNumber, int vacancy) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse != null) {
            String courseCode1 = ((Course) existingCourse).getCourseCode();
            int academicUnits = ((Course) existingCourse).getAcademicUnits();
            IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
            indexMgr.addIndex(indexNumber, vacancy, courseCode1, academicUnits);
            saveCourseObjectList();
            saveCourseObjectList();
        } else {
            System.out.println(RED + "Course doesn't exist in the database" + RESET);
        }
    }

    /*
    public void removeIndex(String courseCode, String indexNumber) {
        loadCourseObjectList();
        Object course = new Course(courseCode);
        Object existingCourse = systemMgr.findObject(courses, course);
        if (existingCourse != null) {
            IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
            indexMgr.removeIndex(indexNumber);
            fileMgr.saveObjects(courses, courseFile);
        } else {
            System.out.println("Course doesn't exist in the database");
        }
    }
     */

    // Defined in UpdateManager
    /*
    public void updateIndexVacancy(String courseCode, String indexNumber, int vacancy) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse != null) {
            IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
            indexMgr.updateIndexVacancy(indexNumber, vacancy);
            if (!indexMgr.checkObjectExists(indexNumber)) {
                return;
            }

            saveCourseObjectList();
            System.out.println("Updated successfully");
            saveCourseObjectList();
        } else {
            System.out.println("Course doesn't exist in the database");
        }
    }

     */

    // Defined in UpdateManager
    /*
    public void updateIndexNumber(String courseCode, String indexNumber, String newIndexNumber) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse != null) {
            IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
            indexMgr.updateIndexNumber(indexNumber, newIndexNumber);
            saveCourseObjectList();
            System.out.println("Updated successfully");
        } else {
            System.out.println("Course doesn't exist in the database");
        }
    }

     */

    public void printStudentListByCourse(String courseCode) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "This course doesn't exist" + RESET);
            System.out.println();
            return;
        } else {
            for (Object index : ((Course) existingCourse).getIndexNumberList()
            ) {
                for (Student student : ((Index) index).getStudentsRegistered()
                ) {
                    student.print();
                }
            }
        }
    }

    public void printStudentListByIndex(String courseCode, String indexNumber) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "This course doesn't exist" + RESET);
            System.out.println();
            saveCourseObjectList();
            return;
        } else {
            if (existingCourse instanceof Course) {
                IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
                indexMgr.printStudentListByIndex(indexNumber);
            } else {
                System.out.println(RED + "Unexpected object received" + RESET);
                System.out.println();
            }

        }
    }

    public void checkAvailabilityIndex(String courseCode, String indexNumber) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "This course doesn't exist" + RESET);
            System.out.println();
            return;
        } else {
            IndexMgr indexMgr;
            if (existingCourse instanceof Course) {
                indexMgr = new IndexMgr((Course) existingCourse);
                indexMgr.checkAvailabilityIndex(indexNumber);
            } else {
                System.out.println(RED + "System Error: Will resolve it soon" + RESET);
            }
        }
    }

    public void printObjects() {
        loadCourseObjectList();
        for (Object course : courses
        ) {
            if (course instanceof Course)
                ((Course) course).print();
            else
                System.out.println(RED + "Unexpected object type" + RESET);
        }
    }

    public void printIndexes(String courseCode) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "This course doesn't exist" + RESET);
            return;
        } else {
            if (existingCourse instanceof Course) {
                for (Object index : ((Course) existingCourse).getIndexNumberList()
                ) {
                    if (index instanceof Index) {
                        ((Index) index).print();
                    }
                }
            }

        }
    }

    public void printLessons(String courseCode, String indexNumber) {
        loadCourseObjectList();
        Object existingCourse = getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "No such course exists" + RESET);
            return;
        }
        IndexMgr indexMgr = new IndexMgr((Course) existingCourse);
        Object index = indexMgr.getObjectFromList(indexNumber);

        if (index == null) {
            System.out.println(RED + "No such index number exists" + RESET);
            return;
        }
        if (index instanceof Index)
            for (Lesson lesson : ((Index) index).getLessons()
            ) {
                lesson.print();
            }
    }

    public void loadCourseObjectList() {
        courses = fileMgr.loadObjects(courseFile);
        for (Object o : courses
        ) {
            System.out.println(o);
        }
    }

    public void saveCourseObjectList() {
        fileMgr.saveObjects(courses, courseFile);
    }

    public Object getObjectFromList(String courseCode) {
        Object course = new Course(courseCode);
        Object existingCourse = systemMgr.findObject(courses, course);
        return existingCourse;
    }

}
