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

        /*//For course CZ2002
        ArrayList<Lesson> lesson_list_2002_1 = new ArrayList<Lesson>();
        lesson_list_2002_1.add(new Lesson(0,"LT2A","Lecture", LocalTime.of(10, 30), LocalTime.of(11, 30)));
        lesson_list_2002_1.add(new Lesson(3,"TR+15","Tutorial", LocalTime.of(11, 30 ), LocalTime.of(12, 30 )));
        lesson_list_2002_1.add(new Lesson(2,"SPL","Lab","Even", LocalTime.of(12, 30 ), LocalTime.of(14, 30 )));

        Index index_2002_1 =new Index(10034,0,3,"CZ2002");
        index_2002_1.setLessons(lesson_list_2002_1);

        ArrayList<Lesson> lesson_list_2002_2 = new ArrayList<Lesson>();
        lesson_list_2002_2.add(new Lesson(0,"LT2A","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2002_2.add(new Lesson(4,"TR+19","Tutorial", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2002_2.add(new Lesson(3,"SPL","Lab","Odd", LocalTime.of(13, 30 ), LocalTime.of(15, 30 )));

        Index index_2002_2 =new Index(10219,30,3,"CZ2002");
        index_2002_2.setLessons(lesson_list_2002_2);

        ArrayList<Lesson> lesson_list_2002_3 = new ArrayList<Lesson>();
        lesson_list_2002_3.add(new Lesson(0,"LT2A","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2002_3.add(new Lesson(2,"TR+15","Tutorial", LocalTime.of(13, 30 ), LocalTime.of(14, 30 )));
        lesson_list_2002_3.add(new Lesson(0,"SPL","Lab","Even", LocalTime.of(8, 30 ), LocalTime.of(10, 30 )));

        Index index_2002_3 =new Index(10829,19,3,"CZ2002");
        index_2002_3.setLessons(lesson_list_2002_3);

        ArrayList<Object> index_list_2002 = new ArrayList<>();
        index_list_2002.add(index_2002_1);
        index_list_2002.add(index_2002_2);
        index_list_2002.add(index_2002_3);

        Course course_2002 = new Course("CZ2002","Object Oriented Programming", 3,"SCSE");
        course_2002.setIndexNumberList(index_list_2002);

        //For course CZ2005
        ArrayList<Lesson> lesson_list_2005_1 = new ArrayList<Lesson>();
        lesson_list_2005_1.add(new Lesson(3,"LT-4","Lecture", LocalTime.of(9, 30 ), LocalTime.of(10, 30 )));
        lesson_list_2005_1.add(new Lesson(2,"LT-4","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2005_1.add(new Lesson(2,"TR+13","Tutorial", LocalTime.of(12, 30 ), LocalTime.of(13, 30 )));

        Index index_2005_1 =new Index(11983,3,3,"CZ2005");
        index_2002_1.setLessons(lesson_list_2005_1);

        ArrayList<Lesson> lesson_list_2005_2 = new ArrayList<Lesson>();
        lesson_list_2005_2.add(new Lesson(3,"LT-4","Lecture", LocalTime.of(9, 30 ), LocalTime.of(10, 30 )));
        lesson_list_2005_2.add(new Lesson(2,"LT-4","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2005_2.add(new Lesson(3,"TR+15","Tutorial", LocalTime.of(13, 30 ), LocalTime.of(14, 30 )));

        Index index_2005_2 =new Index(11980,14,3,"CZ2005");
        index_2002_2.setLessons(lesson_list_2005_2);

        ArrayList<Lesson> lesson_list_2005_3 = new ArrayList<Lesson>();
        lesson_list_2005_3.add(new Lesson(3,"LT-4","Lecture", LocalTime.of(9, 30 ), LocalTime.of(10, 30 )));
        lesson_list_2005_3.add(new Lesson(2,"LT-4","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2005_3.add(new Lesson(0,"TR+14","Tutorial", LocalTime.of(15, 30 ), LocalTime.of(16, 30 )));

        Index index_2005_3 =new Index(11928,9,3,"CZ2005");
        index_2002_3.setLessons(lesson_list_2005_3);

        ArrayList<Object> index_list_2005 = new ArrayList<>();
        index_list_2005.add(index_2005_1);
        index_list_2005.add(index_2005_2);
        index_list_2005.add(index_2005_3);

        Course course_2005 = new Course("CZ2005","Human Computer Interaction", 3,"SCSE");
        course_2005.setIndexNumberList(index_list_2005);

        //For course CZ2006
        ArrayList<Lesson> lesson_list_2006_1 = new ArrayList<Lesson>();
        lesson_list_2006_1.add(new Lesson(0,"LT1A","Lecture", LocalTime.of(12, 30 ), LocalTime.of(13, 30 )));
        lesson_list_2006_1.add(new Lesson(4,"TR+24","Tutorial", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2006_1.add(new Lesson(2,"HWLAB3","Lab","Odd", LocalTime.of(12, 30 ), LocalTime.of(14, 30 )));

        Index index_2006_1 = new Index(10135,0,3,"CZ2006");
        index_2006_1.setLessons(lesson_list_2006_1);

        ArrayList<Lesson> lesson_list_2006_2 = new ArrayList<Lesson>();
        lesson_list_2006_2.add(new Lesson(0,"LT1A","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2006_2.add(new Lesson(2,"TR+31","Tutorial", LocalTime.of(11, 30 ), LocalTime.of(12, 30 )));
        lesson_list_2006_2.add(new Lesson(3,"HWLAB3","Lab","Odd", LocalTime.of(8, 30 ), LocalTime.of(10, 30 )));

        Index index_2006_2 =new Index(110129,30,3,"CZ2006");
        index_2006_2.setLessons(lesson_list_2006_2);

        ArrayList<Lesson> lesson_list_2006_3 = new ArrayList<Lesson>();
        lesson_list_2006_3.add(new Lesson(0,"LT1A","Lecture", LocalTime.of(12, 30 ), LocalTime.of(13, 30 )));
        lesson_list_2006_3.add(new Lesson(4,"TR+19","Tutorial", LocalTime.of(8, 30 ), LocalTime.of(9, 30 )));
        lesson_list_2006_3.add(new Lesson(3,"HWLAB3","Lab","Even", LocalTime.of(9, 30 ), LocalTime.of(11, 30 )));

        Index index_2006_3 =new Index(10829,19,3,"CZ2006");
        index_2006_3.setLessons(lesson_list_2006_3);

        ArrayList<Object> index_list_2006 = new ArrayList<>();
        index_list_2006.add(index_2006_1);
        index_list_2006.add(index_2006_2);
        index_list_2006.add(index_2006_3);

        Course course_2006 = new Course("CZ2006","Software Engineering", 3,"SCSE");
        course_2006.setIndexNumberList(index_list_2006);

        //For course BU8625
        ArrayList<Lesson> lesson_list_8625_1 = new ArrayList<Lesson>();
        lesson_list_8625_1.add(new Lesson(4,"SR23","Lecture", LocalTime.of(13, 30 ), LocalTime.of(15, 30 )));
        lesson_list_8625_1.add(new Lesson(3,"Hive TR+34","Tutorial", LocalTime.of(8, 30 ), LocalTime.of(9, 30 )));

        Index index_8625_1 =new Index(10953,2,3,"BU8625");
        index_8625_1.setLessons(lesson_list_8625_1);

        ArrayList<Lesson> lesson_list_8625_2 = new ArrayList<Lesson>();
        lesson_list_8625_2.add(new Lesson(4,"SR23","Lecture", LocalTime.of(10, 30 ), LocalTime.of(12, 30 )));
        lesson_list_8625_2.add(new Lesson(2,"Hive TR+34","Tutorial", LocalTime.of(8, 30), LocalTime.of(9, 30 )));

        Index index_8625_2 =new Index(10742,17,3,"BU8625");
        index_8625_2.setLessons(lesson_list_8625_2);

        ArrayList<Object> index_list_8625 = new ArrayList<>();
        index_list_8625.add(index_8625_1);
        index_list_8625.add(index_8625_2);

        Course course_8625 = new Course("BU8625","Marketing in 21st Century", 3,"NBS");
        course_8625.setIndexNumberList(index_list_8625);

        //For course HP8006
        ArrayList<Lesson> lesson_list_8006_1 = new ArrayList<>();
        lesson_list_8006_1.add(new Lesson(3,"LT-1","Lecture", LocalTime.of(9, 30 ), LocalTime.of(12, 30 )));

        Index index_8006_1 =new Index(17893,34,3,"HP8006");
        index_8006_1.setLessons(lesson_list_8006_1);

        ArrayList<Lesson> lesson_list_8006_2 = new ArrayList<>();
        lesson_list_8006_2.add(new Lesson(4,"LT-1","Lecture", LocalTime.of(13, 30 ), LocalTime.of(16, 30 )));

        Index index_8006_2 =new Index(17854,9,3,"HP8006");
        index_8006_2.setLessons(lesson_list_8006_2);

        ArrayList<Lesson> lesson_list_8006_3 = new ArrayList<>();
        lesson_list_8006_3.add(new Lesson(0,"LT-1","Lecture", LocalTime.of(8, 30 ), LocalTime.of(11, 30 )));

        Index index_8006_3 =new Index(17835,15,3,"HP8006");
        index_8006_3.setLessons(lesson_list_8006_3);

        ArrayList<Object> index_list_8006 = new ArrayList<>();
        index_list_8006.add(index_8006_1);
        index_list_8006.add(index_8006_2);
        index_list_8006.add(index_8006_3);

        Course course_8006 = new Course("HP8006","Psychology of Stress Management", 3,"SSS");
        course_8006.setIndexNumberList(index_list_8006);

        ArrayList<Object> course_list = new ArrayList<>();
        course_list.add(course_2002);
        course_list.add(course_2005);
        course_list.add(course_2006);
        course_list.add(course_8625);
        course_list.add(course_8006);

        FileMgr fileMgr = new FileMgr();
        fileMgr.saveObjects(course_list, "course.dat");*/

        LoginInterface.main(null);
    }
}



