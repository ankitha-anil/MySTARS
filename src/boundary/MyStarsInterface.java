package boundary;

import controller.*;
import entity.*;

import javax.swing.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class MyStarsInterface {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN_BACKGROUND = "\033[4;36m";
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static void main(String[] args) throws IOException {
        System.out.println(CYAN_BACKGROUND + "                                                                                               " +RESET);
        System.out.println(CYAN +"|"+ RESET+"   ___  ___         _____  _                     ______  _                                   "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"   |  \\/  |        /  ___|| |                    | ___ \\| |                                  "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"   | .  . | _   _  \\ `--. | |_  __ _  _ __  ___  | |_/ /| |  __ _  _ __   _ __    ___  _ __  "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"   | |\\/| || | | |  `--. \\| __|/ _` || '__|/ __| |  __/ | | / _` || '_ \\ | '_ \\  / _ \\| '__| "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"   | |  | || |_| | /\\__/ /| |_| (_| || |   \\__ \\ | |    | || (_| || | | || | | ||  __/| |    "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"   \\_|  |_/ \\__, | \\____/  \\__|\\__,_||_|   |___/ \\_|    |_| \\__,_||_| |_||_| |_| \\___||_|    "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"             __/ |                                                                           "+CYAN +"|"+ RESET+"\n" +
                CYAN +"|"+ RESET+"            |___/                                                                            "+CYAN +"|"+ RESET);
        System.out.println(CYAN +"|"+ RESET+CYAN_BACKGROUND + "                                                                                             " +RESET+CYAN +"|"+ RESET);
        System.out.println(CYAN +"|"+ "                                                                                             " +CYAN +"|"+ RESET);
        System.out.println(CYAN +"|"+ RESET+"                      Welcome to NTU's Course Registration Application                       "+RESET+CYAN +"|"+ RESET);
        System.out.println(CYAN +"|"+ RESET+CYAN_BACKGROUND + "                                                                                             " +RESET+CYAN +"|"+ RESET+"\n");

        //LoginInterface.main(null);

        ArrayList<Lesson> lessons1 = new ArrayList<>();
        Lesson lesson = new Lesson(1, "LKC", "Lecture", LocalTime.of(10, 30), LocalTime.of(12, 30));
        lessons1.add(lesson);
        Index index = new Index(10002, 1, 3, "CZ2001");
        index.setLessons(lessons1);


        ArrayList<Lesson> lessons2 = new ArrayList<>();
        Lesson lesson2 = new Lesson(1, "LKC", "Lecture", LocalTime.of(13, 30), LocalTime.of(14, 30));
        lessons2.add(lesson2);

        Index index1 = new Index(10003, 1, 3, "CZ2001");
        index1.setLessons(lessons2);

        ArrayList<Object> indexes = new ArrayList<>();
        indexes.add(index);
        indexes.add(index1);
        System.out.println(indexes.size());
        Course course = new Course("CZ2001", "XXX", 3, "XXX");
        course.setIndexNumberList(indexes);

        System.out.println(course.getIndexNumberList().size());
        for (Object il : course.getIndexNumberList()
        ) {

            for (Lesson l : ((Index) il).getLessons()
            ) {
                l.print();
            }
        }

        ArrayList<Object> courses = new ArrayList<>();

        Course course1 = new Course("CZ2002", "OODP", 3, "SCSE");
        ArrayList<Lesson> lessons3 = new ArrayList<>();
        Lesson lesson3 = new Lesson(1, "LKC", "Lecture", LocalTime.of(10, 30), LocalTime.of(12, 30));
        lessons3.add(lesson);
        Index index2 = new Index(10002, 1, 3, "CZ2002");
        index2.setLessons(lessons3);


        ArrayList<Lesson> lessons4 = new ArrayList<>();
        Lesson lesson4 = new Lesson(1, "LKC", "Lecture", LocalTime.of(13, 30), LocalTime.of(14, 30));
        lessons4.add(lesson4);

        Index index3 = new Index(10003, 1, 3, "CZ2002");
        index3.setLessons(lessons4);

        ArrayList<Object> indexesnew = new ArrayList<>();
        indexesnew.add(index2);
        indexesnew.add(index3);
        System.out.println(indexes.size());

        course1.setIndexNumberList(indexesnew);

        FileMgr fileMgr = new FileMgr();

        courses.add(course);
        courses.add(course1);
        fileMgr.saveObjects(courses, "course.dat");


        /*ArrayList<Object> students = new ArrayList<>();

        Object s1 = new Student("T", "Tiana", "#$%", "Gmail", "M", "SG", "SCSE", 2);
        Object s2 = new Student("Dan", "D1", "#$%", "Gmail", "F", "SG", "SCSE", 2);
        students.add(s1);
        students.add(s2);

        fileMgr.saveObjects(students, "student.dat");

        LoginMgr.createUser("Tiana", "passwordx", "student");
        LoginMgr.createUser("D1", "password", "student");*/
        LoginInterface.main(null);
    }
}



