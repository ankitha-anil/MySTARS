package Entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Index {
    private int indexNumber;
    private int vacancy;
    private Session lesson[];
    private Queue<Index> waitingList;
    private int courseId;

    public Index(int courseId, boolean hasLab, boolean hasTut){
        this.courseId = courseId;

    private int academicUnits;
    private String courseCode;
    private ArrayList<Session> lesson;
    private Queue<Student> waitingList;
    private ArrayList<Student> studentsRegistered;

    public Index(int indexNumber, int vacancy, int academicUnits, String courseCode) {
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
        this.academicUnits = academicUnits;
        this.courseCode = courseCode;
        lesson = new ArrayList<>();
        waitingList = new LinkedList<>();
        studentsRegistered = new ArrayList<>();
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

    public ArrayList<Session> getLesson() {
        return lesson;
    }

    public void setLesson(ArrayList<Session> lesson) {
        this.lesson = lesson;
    }

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        Session session = new Session(day, venue, lessonType, startTime, endTime);
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Error... Start time must be earlier than end time");
            return;
        }
        if (checkLessonValidity(session))
            this.lesson.add(session);
    }

    public void addLesson(int day, String venue, String lessonType, String labWeek, LocalTime startTime,
            LocalTime endTime) {
        Session session = new Session(day, venue, lessonType, labWeek, startTime, endTime);
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Error... Start time must be earlier than end time");
            return;
        }
        if (checkLessonValidity(session))
            this.lesson.add(session);
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
        for (Session s : lesson) {
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

    public void addStudent(Student student) {
        this.studentsRegistered.add(student);
    }

    public boolean isFilled() {
        return this.vacancy == 0;
    }

    public void print() {
        System.out.println(this.getIndexNumber());
        System.out.println(this.getVacancy());

        for (Session session : lesson) {
            session.print();
        }
    }
}
