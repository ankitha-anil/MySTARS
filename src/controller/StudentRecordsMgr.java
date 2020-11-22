package controller;

import entity.Index;
import entity.Lesson;
import entity.Student;
import entity.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static boundary.MyStarsInterface.*;


public class StudentRecordsMgr extends ObjectEntityController {

    private static ArrayList<Object> students;
    private static final String studentFile = "student.dat";

    public StudentRecordsMgr() {
        super();
        students = fileMgr.loadObjects(studentFile);
    }

    public boolean checkObjectExists(String key) {
        loadStudentObjectList();
        Student student = (Student) getObjectFromList(key);
        if (student != null)
            return true;
        else return false;
    }

    public void editAccessPeriod(LocalTime startTime, LocalTime endTime, LocalDate startDay, LocalDate endDate) {
        loadStudentObjectList();
        if (!systemMgr.checkTimeSanity(startTime, endTime)) {
            System.out.println(RED + "Start Time must be before End Time" + RESET);
            return;
        } else if (!systemMgr.checkDateSanity(startDay, endDate)) {
            System.out.println(RED + "Start Date must be before End Date" + RESET);
            return;
        } else {
            Student.setAccessPeriod(startTime, endTime, startDay, endDate);
            saveStudentObjectList();
        }
    }

    public void addStudent(String name, String networkName, String matriculationNumber, String emailID, String gender, String nationality,
                           String school, int studyYear, String password) throws IOException {
        loadStudentObjectList();
        Object student = new Student(name, networkName, matriculationNumber, emailID, gender, nationality, school, studyYear);
        if (getObjectFromList(networkName) == null) {
            students.add(student);
            saveStudentObjectList();
            printObjects();
            LoginMgr.createUser(networkName, password, "student");
        } else {
            System.out.println(RED + "System cannot add this student as student already exists" + RESET);
        }
        System.out.println();

    }

    public void printCoursesRegistered(String userName) {
        Student student = (Student) getObjectFromList(userName);
        if (student instanceof Student) {
            for (Index registeredIndex : student.getIndexRegistered()) {
                System.out.println(registeredIndex.getCourseCode() + " " + registeredIndex.getIndexNumber() + " " + registeredIndex.getAcademicUnits() + " Registered");
            }
            for (Index waitingListIndex : student.getIndexOnWaitList()) {
                System.out.println(waitingListIndex.getCourseCode() + " " + waitingListIndex.getIndexNumber() + " " + waitingListIndex.getAcademicUnits() + " Waiting List");
            }
        }
    }

    public void printTimeTable(String userName) {
        System.out.println(CYAN_UNDERLINED + "+--------------------------------------+" + RESET);
        System.out.println(CYAN + "|" + RESET + "              TIMETABLE               " + CYAN + "|" + RESET);
        System.out.println(CYAN_UNDERLINED + "|--------------------------------------|" + RESET);
        Student student = (Student) getObjectFromList(userName);
        if (student == null)
            return;

        ArrayList<Lesson>[] weeklyLessons = new ArrayList[7];
        ArrayList<String>[] weeklyCourses = new ArrayList[7];
        for (int i = 0; i < weeklyLessons.length; i++) {
            weeklyCourses[i] = new ArrayList<>();
            weeklyLessons[i] = new ArrayList<>();
        }
        for (Index index : student.getIndexRegistered()
        ) {
            for (Lesson lesson : index.getLessons()
            ) {
                weeklyLessons[lesson.getDay() - 1].add(lesson);
                weeklyCourses[lesson.getDay() - 1].add(index.getCourseCode());
            }
        }

        HashMap<Integer, String> dayMap = new HashMap<Integer, String>();
        dayMap.put(0, "Monday");
        dayMap.put(1, "Tuesday");
        dayMap.put(2, "Wednesday");
        dayMap.put(3, "Thursday");
        dayMap.put(4, "Friday");
        dayMap.put(5, "Saturday");
        dayMap.put(6, "Sunday");
        for (int i = 0; i < weeklyLessons.length; i++) {
            switch (i) {
                case 0:
                    System.out.println(CYAN + "|" + RESET + "                " + dayMap.get(0) + "                " + CYAN + "|" + RESET);
                    break;
                case 1:
                    System.out.println(CYAN + "|" + RESET + "               " + dayMap.get(1) + "                " + CYAN + "|" + RESET);
                    break;
                case 2:
                    System.out.println(CYAN + "|" + RESET + "               " + dayMap.get(2) + "              " + CYAN + "|" + RESET);
                    break;
                case 3:
                    System.out.println(CYAN + "|" + RESET + "               " + dayMap.get(3) + "               " + CYAN + "|" + RESET);
                    break;
                case 4:
                    System.out.println(CYAN + "|" + RESET + "                " + dayMap.get(4) + "                " + CYAN + "|" + RESET);
                    break;
                case 5:
                    System.out.println(CYAN + "|" + RESET + "               " + dayMap.get(5) + "               " + CYAN + "|" + RESET);
                    break;
                case 6:
                    System.out.println(CYAN + "|" + RESET + "                " + dayMap.get(6) + "                " + CYAN + "|" + RESET);
                    break;
            }
            System.out.println(CYAN + "|--------------------------------------|" + RESET);
            for (int j = 0; j < weeklyLessons[i].size(); j++) {
                System.out.println(weeklyCourses[i].get(j));
                weeklyLessons[i].get(j).print();
                System.out.println(CYAN_UNDERLINED + "|--------------------------------------|" + RESET);
            }
        }
    }

    // Defined in UpdateManager
    /*

    public void updateName(String userName, String name) {
        loadStudentObjectList();
        Object existingStudent = getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setName(name);
            saveStudentObjectList();
            System.out.println("Student updated");
            ((Student) existingStudent).print();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

     */

    // Defined in UpdateManager

    /*
    public void updateNationality(String userName, String nationality) {
        loadStudentObjectList();
        Object existingStudent = getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setNationality(nationality);
            System.out.println("Student updated");
            ((Student) existingStudent).print();
            saveStudentObjectList();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

    public void updateSchool(String userName, String school) {
        loadStudentObjectList();
        Object existingStudent = getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setSchool(school);
            saveStudentObjectList();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }


     */
    /*
    public void updateMaxAU(String userName, int maxAU) {
        loadStudentObjectList();
        Object existingStudent = getObjectFromList(userName);
        if (existingStudent != null) {
            int currentAUs = ((Student) existingStudent).getRegisteredAU();
            if (maxAU < ((Student) existingStudent).getRegisteredAU()) {
                System.out.println("Cannot decrease max AUs");
                return;
            }
            if (currentAUs > maxAU) {
                System.out.println("Student has already registered for too many AUs... Cannot decrease now");
            } else {
                ((Student) existingStudent).setMaxAU(maxAU);
                saveStudentObjectList();
            }
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

     */

    public void printObjects() {
        for (Object student : students
        ) {
            ((Student) student).print();
        }
    }

    public void loadStudentObjectList() {
        students = fileMgr.loadObjects(studentFile);
    }

    public void saveStudentObjectList() {
        fileMgr.saveObjects(students, studentFile);
    }

    public Object getObjectFromList(String key) {
        Object student = new Student(key);
        Object existingStudent = systemMgr.findObject(students, student);
        return existingStudent;
    }
}
