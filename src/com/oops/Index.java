package com.oops;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
    Must implement the waitingList as well as studentsRegistered

 */

public class Index {
    private int indexNumber;
    private int vacancy;
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

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        Session session = new Session(day, venue, lessonType, startTime, endTime);
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Error... Start time must be earlier than end time");
            return;
        }
        if (checkLessonValidity(session))
            this.lesson.add(session);
    }

    private boolean checkLessonValidity(Session session) {
        String lessonType = session.getLessonType();
        if (lessonType == "Lecture")
            return true;
        boolean isTutorial = false;
        boolean isLab = false;
        if (lessonType == "Tutorial")
            isTutorial = true;
        else if (lessonType == "Lab")
            isLab = true;
        boolean hasLecture = false;
        boolean hasTutorial = false;
        for (Session s : lesson
        ) {
            String availableLesson = s.getLessonType();
            if (availableLesson == "Lecture")
                hasLecture = true;
            else if (availableLesson == "Tutorial")
                hasTutorial = true;
        }
        return (hasLecture && isTutorial) || (hasLecture && hasTutorial && isLab);
    }

    public ArrayList<Session> getLesson() {
        return lesson;
    }

    public void setLesson(ArrayList<Session> lesson) {
        this.lesson = lesson;
    }


    public void addToWaitingList(Student student) {
        waitingList.add(student);
        student.addIndexOnWaitList(this);

    }

    public void addStudent(Student student) {
        this.studentsRegistered.add(student);
    }

    public boolean isFilled() {
        if (this.vacancy == 0)
            return true;
        return false;
    }


    public void print() {
        System.out.println(this.getIndexNumber());
        System.out.println(this.getVacancy());

        for (int i = 0; i < this.lesson.size(); ++i) {
            lesson.get(i).print();
        }
    }

}
