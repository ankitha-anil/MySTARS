package controller;


import entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Anon
 */

public class RegistrationManager {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";
    public static final String GREEN = "\033[1;32m";
    public static final String YELLOW = "\033[1;33m"; // YELLOW


    /**
     * controller for list of students
     * controller for list of all available courses
     * controller for list of indices of a particular course
     * controller for notifications
     * controller for timetable of a student
     */
    private ObjectEntityController studentRecordsMgr;
    private ObjectEntityController courseMgr;
    private ObjectEntityController indexMgr;
    CommunicationController communicationController;

    private TimeTableMgr timeTableMgr;

    /**
     * Constructor for RegistrationManager class
     *
     * @param studentRecordsMgr controller for records of the student
     * @param courseMgr         controller for courses
     */
    public RegistrationManager(ObjectEntityController studentRecordsMgr, ObjectEntityController courseMgr) {
        this.courseMgr = courseMgr;
        this.studentRecordsMgr = studentRecordsMgr;
        timeTableMgr = new TimeTableMgr();
        communicationController = new CommunicationController();
    }

    /**
     * Constructor for RegistrationManager class. Initialises studentRecordsMgr, courseMgr and timeTableMgr
     */

    public RegistrationManager() {
        studentRecordsMgr = new StudentRecordsMgr();
        courseMgr = new CourseMgr();
        timeTableMgr = new TimeTableMgr();
    }

    /**
     * To register a student for a particular index of the course
     *
     * @param userName    username of the student who wants to register
     * @param courseCode  course code of the course to be added
     * @param indexNumber index number of the index to be added
     */

    public void registerCourse(String userName, String courseCode, String indexNumber) {
        User student = (User) studentRecordsMgr.getObjectFromList(userName);
        if (!courseMgr.checkObjectExists(courseCode)) {
            System.out.println(RED + "Registration failed: This course doesn't exist in the system" + RESET);
            System.out.println();
            return;
        }
        Course courseToAdd = (Course) courseMgr.getObjectFromList(courseCode);
        indexMgr = new IndexMgr(courseToAdd);
        Index indexToAdd = (Index) indexMgr.getObjectFromList(indexNumber);

        if (indexToAdd == null) {
            System.out.println(RED + "Index number does not exist. Please enter a valid index number" + RESET);
            System.out.println();
            return;
        }
        if (student instanceof Student) {
            if (timeTableMgr.checkExistingCourse(((Student) student).getIndexOnWaitList(), indexToAdd)) {
                System.out.println(RED + "Registration failed: You are on wait list for this course" + RESET);
                System.out.println();
                return;
            } else if (timeTableMgr.checkExistingCourse(((Student) student).getIndexRegistered(), indexToAdd)) {
                System.out.println(indexToAdd.getCourseCode());
                System.out.println(RED + "Registration failed: You are already registered for this course " + RESET);
                System.out.println();
                return;
            } else if (indexToAdd.isFilled()) {
                System.out.println(RED + "No more vacancies left for this course" + RESET);

                if (timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), indexToAdd)) {
                    System.out.println(RED + "Registration failed: Clashes with an index on wait list" + RESET);
                    System.out.println();
                    return;
                } else if (timeTableMgr.checkClash(((Student) student).getIndexRegistered(), indexToAdd)) {
                    System.out.println(RED + "Registration failed: Clashes with a registered course's index" + RESET);
                    System.out.println();
                    return;

                } else {
                    indexToAdd.addToWaitingList((Student) student);
                    System.out.println(YELLOW + "You have been added to waiting list for: " + indexToAdd.getCourseCode() + "\n Index: " + indexToAdd.getIndexNumber() + RESET);
                    System.out.println();
                    ((CourseMgr) courseMgr).saveCourseObjectList();
                    ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                    String head = indexToAdd.getCourseCode() + " " + indexToAdd.getIndexNumber() + " Wait list";
                    String message = "You have been added to the waiting list for " + indexToAdd.getCourseCode() + " " + indexToAdd.getIndexNumber();
                    communicationController.communicateToStudent(student.getNetworkName(), head, message, new EmailMgr());
                    return;
                }
            } else {
                if (timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), indexToAdd)) {
                    System.out.println(RED + "Registration failed: Clashes with an index on waiting list" + RESET);
                    System.out.println();
                    return;

                } else if (timeTableMgr.checkClash(((Student) student).getIndexRegistered(), indexToAdd)) {
                    System.out.println(RED + "Registration failed: Clashes with a registered course's index" + RESET);
                    System.out.println();

                } else {
                    if ((((Student) student).getMaxAU() - ((Student) student).getRegisteredAU()) < courseToAdd.getAcademicUnits()) {
                        System.out.println(RED + "Registration failed: Max AU limit has been reached. Drop some courses before registering or request for overloading" + RESET);
                        return;
                    }
                    ((Student) student).addIndexRegistered(indexToAdd);
                    System.out.println(GREEN + "Successfully Registered: Please print your updated time table" + RESET);
                    System.out.println();
                    String subject = "Registration for course";
                    String message = "You have registered for " + indexToAdd.getCourseCode() + "\n Index: " + indexToAdd.getCourseCode();
                    ((CourseMgr) courseMgr).saveCourseObjectList();
                    ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                    communicationController.communicateToStudent(student.getNetworkName(), subject, message, new EmailMgr());
                    return;
                }
            }
        } else {
            System.out.println(RED + "Only students can register for courses" + RESET);
            System.out.println();
        }

    }

    /**
     * To drop an index of a course that a student has been registered for or is on waiting list for
     *
     * @param userName       username of the student who wants to drop an index
     * @param courseCode     course code of the index to be dropped
     * @param indexToDrop    index number of the index to be dropped
     * @param ignoreWaitList boolean variable to indicate if vacancy created should be assigned to first student in waiting list (should not be done in case of swap)
     */
    public void dropCourse(String userName, String courseCode, String indexToDrop, boolean ignoreWaitList) {
        Object student = studentRecordsMgr.getObjectFromList(userName);
        if (!courseMgr.checkObjectExists(courseCode)) {
            System.out.println(RED + "Course doesn't exist in the system" + RESET);
        } else {
            indexMgr = new IndexMgr((Course) courseMgr.getObjectFromList(courseCode));
            Index indexDeregister = (Index) indexMgr.getObjectFromList(indexToDrop);
            if (indexDeregister == null) {
                System.out.println(RED + "This index number doesn't exist" + RESET);
                System.out.println();
                return;
            }
            if (student instanceof Student) {
                for (Index index : ((Student) student).getIndexRegistered()) {
                    if (index.equals(indexDeregister) && index.getCourseCode().equals(courseCode)) {
                        ((Student) student).removeIndex(indexDeregister);
                        ((CourseMgr) courseMgr).saveCourseObjectList();
                        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                        System.out.println(GREEN + "Index Number " + index.getIndexNumber() + " is removed from registered list" + RESET);
                        String head = "Dropping of course" + indexDeregister.getCourseCode() + " " + indexDeregister.getIndexNumber();
                        String message = "You have dropped the course " + indexDeregister.getCourseCode() + " " + indexDeregister.getIndexNumber();
                        communicationController.communicateToStudent(((Student) student).getNetworkName(), head, message, new EmailMgr());
                        if (ignoreWaitList) {
                            ((CourseMgr) courseMgr).saveCourseObjectList();
                            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                            return;
                        }
                        Student headOfWaitList = indexDeregister.getWaitingList().poll();
                        if (headOfWaitList != null) {
                            headOfWaitList = (Student) studentRecordsMgr.getObjectFromList(headOfWaitList.getNetworkName());
                            headOfWaitList.addIndexRegistered(indexDeregister);
                            indexDeregister.removeFromWaitingList(headOfWaitList);
                            ((CourseMgr) courseMgr).saveCourseObjectList();
                            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                            head = "Registered for " + indexDeregister.getCourseCode() + " " + indexDeregister.getIndexNumber();
                            message = "You are removed from the waiting list and have been registered for " + indexDeregister.getCourseCode() + " " + indexDeregister.getIndexNumber();
                            communicationController.communicateToStudent(((Student) student).getNetworkName(), head, message, new EmailMgr());
                        }
                        return;
                    }
                }
                for (Index index : ((Student) student).getIndexOnWaitList()) {
                    if (index.equals(indexDeregister) && index.getCourseCode().equals(courseCode)) {
                        ((Student) student).removeIndexFromWaitList(indexDeregister);
                        System.out.println(GREEN + "Index Number " + index.getIndexNumber() + " is removed from waiting list" + RESET);
                        String subject = "Course Dropped";
                        String message = "You have dropped " + index.getCourseCode() + ", Index number: " + index.getCourseCode();
                        communicationController.communicateToStudent(((Student) student).getNetworkName(), subject, message, new EmailMgr());
                        ((CourseMgr) courseMgr).saveCourseObjectList();
                        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                        return;
                    }
                }
                System.out.println(RED + "Index not found in waiting list and registered index list" + RESET);

            } else {
                System.out.println(RED + "Only students can drop course" + RESET);
            }
            System.out.println();
        }

    }

    /**
     * To change the index of a course that a student is already registered to
     *
     * @param userName     user name of the student who wants to change index
     * @param courseCode   course code of the index to be changed
     * @param currentIndex index number of the currently registered index
     * @param newIndex     index number of the index to be changed to
     */

    public void changeIndex(String userName, String courseCode, String currentIndex, String newIndex) {

        if (newIndex.equals(currentIndex)) {
            System.out.println(RED + "You are already registered for this index" + RESET);
            System.out.println();
            return;
        }
        User student = (User) studentRecordsMgr.getObjectFromList(userName);
        Course course = (Course) courseMgr.getObjectFromList(courseCode);
        if (course == null) {
            System.out.println(RED + "Course code does not exist" + RESET);
            System.out.println();
            return;
        }

        IndexMgr indexMgr = new IndexMgr(course);
        Object originalIndex = indexMgr.getObjectFromList(currentIndex);
        if (originalIndex == null) {
            System.out.println(RED + "Current index does not exist" + RESET);
            System.out.println();
            return;
        }

        Object changeToIndex = indexMgr.getObjectFromList(newIndex);

        if (changeToIndex == null) {
            System.out.println(RED + "New index does not exist" + RESET);
            System.out.println();
            return;
        }
        if (((Index) changeToIndex).isFilled()) {
            System.out.println(RED + "No more vacancies in this index, cannot change" + RESET);
            System.out.println();
            return;
        }
        if (student instanceof Student && originalIndex instanceof Index && changeToIndex instanceof Index) {
            for (Index index : ((Student) student).getIndexRegistered()) {
                if (index.equals(((Index) originalIndex).getIndexNumber()) && index.getCourseCode().equals(courseCode)) {
                    // need to skip iteration of checking with currentIndex
                    if (timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), (Index) changeToIndex, (Index) originalIndex) || timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), (Index) changeToIndex, (Index) originalIndex)) {
                        System.out.println(RED + "New index clashes with waiting index or registered indices of student" + RESET);
                        System.out.println();
                        return;
                    } else {
                        ((Student) student).removeIndex((Index) originalIndex);
                        ((Student) student).addIndexRegistered((Index) changeToIndex);
                        ((CourseMgr) courseMgr).saveCourseObjectList();
                        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                        String subject = "Changing of course index";
                        String message = "You have changed the index of " + index.getCourseCode() + " from " + index.getCourseCode() + " to " + ((Index) changeToIndex).getIndexNumber();
                        communicationController.communicateToStudent(student.getNetworkName(), subject, message, new EmailMgr());
                    }
                } else {
                    System.out.println(RED + "No vacancies in new index" + RESET);
                }
                return;
            }
        }
        System.out.println(RED + "Not registered for current index" + RESET);
    }

    /**
     * To swap the indices of 2 students registered for a particular course
     *
     * @param userName          user name of the student who is currently logged in
     * @param friendUserName    user name of the other student with whom swap is to be done
     * @param password          password of the student with whom swap is to be done
     * @param courseCode        course code of the indices to be swapped
     * @param userIndexNumber   index number of the student who is currently logged in
     * @param friendIndexNumber index number of the other student with whom swap is to be done
     * @throws IOException
     */
    public void swapIndex(String userName, String friendUserName, String password, String courseCode, String userIndexNumber, String friendIndexNumber) throws IOException {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Object student = studentRecordsMgr.getObjectFromList(userName);
        Object friend = studentRecordsMgr.getObjectFromList(friendUserName);
        boolean isAuthenticate = LoginMgr.loginCheck(friendUserName, password, "student");
        if (friend == null || !isAuthenticate) {
            System.out.println(RED + "Invalid username or password" + RESET);
            return;
        }
        if (student == null) {
            System.out.println(RED + "Invalid name" + RESET);
        }
        Object course = courseMgr.getObjectFromList(courseCode);
        if (course == null) {
            System.out.println(RED + "No such course exists" + RESET);
            return;
        }

        if (student instanceof Student && friend instanceof Student) {
            indexMgr = new IndexMgr((Course) course);
            Object actorIndex = indexMgr.getObjectFromList(userIndexNumber);
            Object friendIndex = indexMgr.getObjectFromList(friendIndexNumber);
            if (actorIndex == null) {
                System.out.println(RED + "No such index number exists" + RESET);
                System.out.println();
                return;
            }

            if (friendIndex == null) {
                System.out.println(RED + "No such index number exists" + RESET);
                System.out.println();
                return;
            }
            if (!timeTableMgr.checkExistingCourse(((Student) student).getIndexRegistered(), (Index) actorIndex)) {
                System.out.println(RED + "You have not registered for this index" + RESET);
                System.out.println();
                return;
            }
            if (!timeTableMgr.checkExistingCourse(((Student) friend).getIndexRegistered(), (Index) friendIndex)) {
                System.out.println(RED + "The student has not registered for this index" + RESET);
                System.out.println();
                return;
            }

            // need to skip clash checking with currently registered ind
            if (!timeTableMgr.checkClash(((Student) student).getIndexRegistered(), (Index) friendIndex, (Index) actorIndex) && !timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), (Index) friendIndex, (Index) actorIndex) && !timeTableMgr.checkClash(((Student) friend).getIndexRegistered(), (Index) actorIndex, (Index) friendIndex) && !timeTableMgr.checkClash(((Student) friend).getIndexOnWaitList(), (Index) actorIndex, (Index) friendIndex)) {
                dropCourse(((Student) student).getNetworkName(), courseCode, String.valueOf(((Index) actorIndex).getIndexNumber()), true);
                dropCourse(((Student) friend).getNetworkName(), courseCode, String.valueOf(((Index) friendIndex).getIndexNumber()), true);
                registerCourse(((Student) student).getNetworkName(), courseCode, String.valueOf(((Index) friendIndex).getIndexNumber()));
                registerCourse(((Student) friend).getNetworkName(), courseCode, String.valueOf(((Index) actorIndex).getIndexNumber()));
                System.out.println(GREEN + "Successfully changed" + RESET);
                String subject = "Swapping of course index";
                String message = "You have changed the index of " + ((Index) actorIndex).getCourseCode() + " from " + ((Index) actorIndex).getCourseCode() + " to " + ((Index) friendIndex).getIndexNumber();
                communicationController.communicateToStudent(((Student) student).getNetworkName(), subject, message, new EmailMgr());
                subject = "Swapping of course index";
                message = "You have changed the index of " + ((Index) friendIndex).getCourseCode() + " from " + ((Index) friendIndex).getCourseCode() + " to " + ((Index) actorIndex).getIndexNumber();
                communicationController.communicateToStudent(((Student) friend).getNetworkName(), subject, message, new EmailMgr());
                ((CourseMgr) courseMgr).saveCourseObjectList();
                ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            }
        } else System.out.println(RED + "Only students can register for courses" + RESET);

    }
}
