package Entity;

import Controller.TimeTableMgr;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Index implements Serializable {
    private int indexNumber;
    private int vacancy;
    private int academicUnits;
    private String courseCode;
    private ArrayList<Session> lessons;
    private Queue<Student> waitingList;
    private ArrayList<Student> studentsRegistered;

    public Index(int indexNumber, int vacancy, int academicUnits, String courseCode) {
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
        this.academicUnits = academicUnits;
        this.courseCode = courseCode;
        lessons = new ArrayList<>();
        waitingList = new LinkedList<>();
        studentsRegistered = new ArrayList<>();
    }

    public Index(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getAcademicUnits() {
        return academicUnits;
    }

    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Queue<Student> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Queue<Student> waitingList) {
        this.waitingList = waitingList;
    }

    public ArrayList<Student> getStudentsRegistered() {
        return studentsRegistered;
    }

    public void setStudentsRegistered(ArrayList<Student> studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public ArrayList<Session> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Session> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Error... Start time must be earlier than end time");
            System.out.println("System will not add this lesson");
            return;
        }
        // Check for clash with existing sessions
        Session session = new Session(day, venue, lessonType, startTime, endTime);
        for (Session lesson : lessons
        ) {
            if (session.checkCLash(lesson)) {
                System.out.println("Lesson clashes with an existing leeson in this index number");
                System.out.println("System will not add this lesson");
                return;
            }
        }
        if (checkLessonValidity(session))
            this.lessons.add(session);
    }

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime,
                          LocalTime endTime, String labWeek) {
        if (startTime.isAfter(endTime)) {
            System.out.println("Error... Start time must be earlier than end time");
            System.out.println("System will not add this lesson");
            return;
        }
        // Check for clash with existing sessions
        Session session = new Session(day, venue, lessonType, labWeek, startTime, endTime);
        for (Session lesson : lessons
        ) {
            if (session.checkCLash(lesson)) {
                System.out.println("Lesson clashes with an existing leeson in this index number");
                System.out.println("System will not add this lesson");
                return;
            }
        }
        if (checkLessonValidity(session))
            this.lessons.add(session);
    }

    private boolean checkLessonValidity(Session session) {
        String lessonType = session.getLessonType();
        if (lessonType.equals("lecture"))
            return true;
        boolean isTutorial = false;
        boolean isLab = false;
        if (lessonType.equals("tutorial"))
            isTutorial = true;
        else if (lessonType.equals("lab"))
            isLab = true;
        boolean hasLecture = false;
        boolean hasTutorial = false;
        for (Session s : lessons) {
            String availableLesson = s.getLessonType();
            if (availableLesson.equals("lecture"))
                hasLecture = true;
            else if (availableLesson.equals("tutorial"))
                hasTutorial = true;
        }
        return (hasLecture && isTutorial) || (hasLecture && hasTutorial && isLab);
    }

    public void addToWaitingList(Student student) {
        waitingList.add(student);
        student.addIndexOnWaitList(this);
    }

    public void removeFromWaitingList(Student student) {
        waitingList.remove(student);
    }

    public void addStudent(Student student) {
        this.studentsRegistered.add(student);
    }

    public void removeStudent(Student student) {
        this.studentsRegistered.remove(student);
    }

    public boolean isFilled() {
        return this.vacancy == 0;
    }

    public void print() {
        System.out.println(this.getIndexNumber());
        System.out.println(this.getVacancy());

        for (Session session : lessons) {
            session.print();
        }
    }

    public boolean equals(Object object) {
        return (indexNumber == ((Index) object).getIndexNumber());
    }

}

