package controller;

import entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistrationManager {

    private ObjectEntityController studentRecordsMgr;
    private ObjectEntityController courseMgr;
    private ObjectEntityController indexMgr;
    CommunicationController communicationController;

    private TimeTableMgr timeTableMgr;

    public RegistrationManager(ObjectEntityController studentRecordsMgr, ObjectEntityController courseMgr) {
        this.courseMgr = courseMgr;
        this.studentRecordsMgr = studentRecordsMgr;
        timeTableMgr = new TimeTableMgr();
        communicationController = new CommunicationController();
    }

    public RegistrationManager() {
        studentRecordsMgr = new StudentRecordsMgr();
        courseMgr = new CourseMgr();
        timeTableMgr = new TimeTableMgr();
    }

    public void registerCourse(String userName, String courseCode, String indexNumber) {
        User student = (User) studentRecordsMgr.getObjectFromList(userName);
        if (!courseMgr.checkObjectExists(courseCode)) {
            System.out.println("This course doesn't exist in the system... Registration failed");
            System.out.println();
            return;
        }
        Course courseToAdd = (Course) courseMgr.getObjectFromList(courseCode);
        indexMgr = new IndexMgr(courseToAdd);
        Index indexToAdd = (Index) indexMgr.getObjectFromList(indexNumber);

        if (indexToAdd == null) {
            System.out.println("Index number does not exist. Please enter a valid index number");
            System.out.println();
            return;
        }
        if (student instanceof Student) {
            if (timeTableMgr.checkExistingCourse(((Student) student).getIndexOnWaitList(), indexToAdd)) {
                System.out.println("You are already on wait list for this course... Registration failed");
                System.out.println();
                return;
            } else if (timeTableMgr.checkExistingCourse(((Student) student).getIndexRegistered(), indexToAdd)) {
                System.out.println(indexToAdd.getCourseCode());
                System.out.println("You are already registered for this course... Registration failed");
                System.out.println();
                return;
            } else if (indexToAdd.isFilled()) {
                System.out.println("No more vacancies left for this course");

                if (timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), indexToAdd)) {
                    System.out.println("Clashes with an index on wait list... Registration failed");
                    System.out.println();
                    return;
                } else if (timeTableMgr.checkClash(((Student) student).getIndexRegistered(), indexToAdd)) {
                    System.out.println("Clashes with a registered course's index... Registration failed");
                    System.out.println();
                    return;

                } else {
                    indexToAdd.addToWaitingList((Student) student);
                    System.out.println("You have been added to waiting list for: " + indexToAdd.getCourseCode() + " index: " + indexToAdd.getIndexNumber());
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
                    System.out.println("Clashes with an index on waiting list... Registration failed");
                    System.out.println();
                    return;

                } else if (timeTableMgr.checkClash(((Student) student).getIndexRegistered(), indexToAdd)) {
                    System.out.println("Clashes with a registered course's index... Registration failed");
                    System.out.println();

                } else {
                    if ((((Student) student).getMaxAU() - ((Student) student).getRegisteredAU()) < courseToAdd.getAcademicUnits()) {
                        System.out.println("Max AU limit has been reached. Drop some courses before registering or request for overloading... Registration failed");
                        return;
                    }
                    ((Student) student).addIndexRegistered(indexToAdd);
                    System.out.println("Successfully Registered... Please print your updated time table");
                    System.out.println();
                    String subject = "Registration for course";
                    String message = "You have registered for " + indexToAdd.getCourseCode() + ", Index: " + indexToAdd.getCourseCode();
                    ((CourseMgr) courseMgr).saveCourseObjectList();
                    ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                    communicationController.communicateToStudent(student.getNetworkName(), subject, message, new EmailMgr());
                    return;
                }
            }
        } else {
            System.out.println("Only students can register for courses");
            System.out.println();
        }

    }


    public void dropCourse(String userName, String courseCode, String indexToDrop, boolean ignoreWaitList) {
        Object student = studentRecordsMgr.getObjectFromList(userName);
        if (!courseMgr.checkObjectExists(courseCode)) {
            System.out.println("Course doesn't exist in the system");
        } else {
            indexMgr = new IndexMgr((Course) courseMgr.getObjectFromList(courseCode));
            Index indexDeregister = (Index) indexMgr.getObjectFromList(indexToDrop);
            if (indexDeregister == null) {
                System.out.println("This index number doesn't exist");
                System.out.println();
                return;
            }
            if (student instanceof Student) {
                for (Index index : ((Student) student).getIndexRegistered()) {
                    if (index.equals(indexDeregister) && index.getCourseCode().equals(courseCode)) {
                        ((Student) student).removeIndex(indexDeregister);
                        ((CourseMgr) courseMgr).saveCourseObjectList();
                        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                        System.out.println("Index Number " + index.getIndexNumber() + " is removed from registered list");
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
                        System.out.println("Index Number " + index.getIndexNumber() + " is removed from waiting list");
                        String subject = "Course Dropped";
                        String message = "You have dropped " + index.getCourseCode() + ", Index number: " + index.getCourseCode();
                        communicationController.communicateToStudent(((Student) student).getNetworkName(), subject, message, new EmailMgr());
                        ((CourseMgr) courseMgr).saveCourseObjectList();
                        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
                        return;
                    }
                }
                System.out.println("Index not found in waiting list and registered index list");

            } else {
                System.out.println("Only students can drop course");
            }
            System.out.println();
        }

    }

    public void changeIndex(String userName, String courseCode, String currentIndex, String newIndex) {

        if (newIndex.equals(currentIndex)) {
            System.out.println("You are already registered for this index");
            System.out.println();
            return;
        }
        User student = (User) studentRecordsMgr.getObjectFromList(userName);
        Course course = (Course) courseMgr.getObjectFromList(courseCode);
        if (course == null) {
            System.out.println("Course code does not exist");
            System.out.println();
            return;
        }

        IndexMgr indexMgr = new IndexMgr(course);
        Object originalIndex = indexMgr.getObjectFromList(currentIndex);
        if (originalIndex == null) {
            System.out.println("Current index does not exist");
            System.out.println();
            return;
        }

        Object changeToIndex = indexMgr.getObjectFromList(newIndex);

        if (changeToIndex == null) {
            System.out.println("New index does not exist");
            System.out.println();
            return;
        }
        if (((Index) changeToIndex).isFilled()) {
            System.out.println("No more vacancies in this index, cannot change");
            System.out.println();
            return;
        }
        if (student instanceof Student && originalIndex instanceof Index && changeToIndex instanceof Index) {
            for (Index index : ((Student) student).getIndexRegistered()) {
                if (index.equals(((Index) originalIndex).getIndexNumber()) && index.getCourseCode().equals(courseCode)) {
                    // need to skip iteration of checking with currentIndex
                    if (timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), (Index) changeToIndex, (Index) originalIndex) || timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), (Index) changeToIndex, (Index) originalIndex)) {
                        System.out.println("New index clashes with waiting index or registered indices of student");
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
                    System.out.println("No vacancies in new index");
                }
                return;
            }
        }
        System.out.println("Not registered for current index");
    }


    public void swapIndex(String userName, String friendUserName, String password, String courseCode, String userIndexNumber, String friendIndexNumber) throws IOException {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Object student = studentRecordsMgr.getObjectFromList(userName);
        Object friend = studentRecordsMgr.getObjectFromList(friendUserName);
        boolean isAuthenticate = LoginMgr.loginCheck(friendUserName, password, "student");
        if (friend == null || !isAuthenticate) {
            System.out.println("Invalid username or password");
            return;
        }
        if (student == null) {
            System.out.println("Invalid name");
        }
        Object course = courseMgr.getObjectFromList(courseCode);
        if (course == null) {
            System.out.println("No such course exists");
            return;
        }

        if (student instanceof Student && friend instanceof Student) {
            indexMgr = new IndexMgr((Course) course);
            Object actorIndex = indexMgr.getObjectFromList(userIndexNumber);
            Object friendIndex = indexMgr.getObjectFromList(friendIndexNumber);
            if (actorIndex == null) {
                System.out.println("No such index number exists");
                System.out.println();
                return;
            }

            if (friendIndex == null) {
                System.out.println("No such index number exists");
                System.out.println();
                return;
            }
            if (!timeTableMgr.checkExistingCourse(((Student) student).getIndexRegistered(), (Index) actorIndex)) {
                System.out.println("You have not registered for this index");
                System.out.println();
                return;
            }
            if (!timeTableMgr.checkExistingCourse(((Student) friend).getIndexRegistered(), (Index) friendIndex)) {
                System.out.println("The student has not registered for this index");
                System.out.println();
                return;
            }

            // need to skip clash checking with currently registered index

            if (!timeTableMgr.checkClash(((Student) student).getIndexRegistered(), (Index) friendIndex, (Index) actorIndex) && !timeTableMgr.checkClash(((Student) student).getIndexOnWaitList(), (Index) friendIndex, (Index) actorIndex) && !timeTableMgr.checkClash(((Student) friend).getIndexRegistered(), (Index) actorIndex, (Index) friendIndex) && !timeTableMgr.checkClash(((Student) friend).getIndexOnWaitList(), (Index) actorIndex, (Index) friendIndex)) {
                dropCourse(((Student) student).getNetworkName(), courseCode, String.valueOf(((Index) actorIndex).getIndexNumber()), true);
                dropCourse(((Student) friend).getNetworkName(), courseCode, String.valueOf(((Index) friendIndex).getIndexNumber()), true);
                registerCourse(((Student) student).getNetworkName(), courseCode, String.valueOf(((Index) friendIndex).getIndexNumber()));
                registerCourse(((Student) friend).getNetworkName(), courseCode, String.valueOf(((Index) actorIndex).getIndexNumber()));
                System.out.println("Successfully changed");
                String subject = "Swapping of course index";
                String message = "You have changed the index of " + ((Index) actorIndex).getCourseCode() + " from " + ((Index) actorIndex).getCourseCode() + " to " + ((Index) friendIndex).getIndexNumber();
                communicationController.communicateToStudent(((Student) student).getNetworkName(), subject, message, new EmailMgr());
                subject = "Swapping of course index";
                message = "You have changed the index of " + ((Index) friendIndex).getCourseCode() + " from " + ((Index) friendIndex).getCourseCode() + " to " + ((Index) actorIndex).getIndexNumber();
                communicationController.communicateToStudent(((Student) friend).getNetworkName(), subject, message, new EmailMgr());
                ((CourseMgr) courseMgr).saveCourseObjectList();
                ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            }
        } else System.out.println("Only students can register for courses");

    }
}