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

/**
 * Main interface at the start of the program
 * @author anon
 */

public class MyStarsInterface {
    /**
     * Different colours to enhance user friendliness of program
     */
    public static final String RED = "\033[1;31m";
    public static final String GREEN = "\033[1;32m";
    public static final String YELLOW = "\033[1;33m";
    public static final String CYAN = "\033[1;36m";
    public static final String CYAN_UNDERLINED = "\033[4;36m";
    public static final String RESET = "\u001B[0m";


    /**
     * Main function that facilitates the interface and calls Login Interface
     * @param args null argument can be used to call the MyStars interface
     * @throws IOException Throws IOException
     */

    public static void main(String[] args) throws IOException {
        System.out.println(CYAN_UNDERLINED + "                                                                                               " + RESET);
        System.out.println(CYAN + "|" + RESET + "   ___  ___         _____  _                     ______  _                                   " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "   |  \\/  |        /  ___|| |                    | ___ \\| |                                  " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "   | .  . | _   _  \\ `--. | |_  __ _  _ __  ___  | |_/ /| |  __ _  _ __   _ __    ___  _ __  " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "   | |\\/| || | | |  `--. \\| __|/ _` || '__|/ __| |  __/ | | / _` || '_ \\ | '_ \\  / _ \\| '__| " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "   | |  | || |_| | /\\__/ /| |_| (_| || |   \\__ \\ | |    | || (_| || | | || | | ||  __/| |    " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "   \\_|  |_/ \\__, | \\____/  \\__|\\__,_||_|   |___/ \\_|    |_| \\__,_||_| |_||_| |_| \\___||_|    " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "             __/ |                                                                           " + CYAN + "|" + RESET + "\n" +
                CYAN + "|" + RESET + "            |___/                                                                            " + CYAN + "|" + RESET);
        System.out.println(CYAN + "|" + RESET + CYAN_UNDERLINED + "                                                                                             " + RESET + CYAN + "|" + RESET);
        System.out.println(CYAN + "|" + "                                                                                             " + CYAN + "|" + RESET);
        System.out.println(CYAN + "|" + RESET + "                      Welcome to NTU's Course Registration Application                       " + RESET + CYAN + "|" + RESET);
        System.out.println(CYAN + "|" + RESET + CYAN_UNDERLINED + "                                                                                             " + RESET + CYAN + "|" + RESET + "\n");

        //For course CZ2002
        ArrayList<Lesson> lesson_list_2002_1 = new ArrayList<Lesson>();
        lesson_list_2002_1.add(new Lesson(1,"LT2A","Lecture", LocalTime.of(10, 30), LocalTime.of(11, 30)));
        lesson_list_2002_1.add(new Lesson(3,"TR+15","Tutorial", LocalTime.of(11, 30 ), LocalTime.of(12, 30 )));
        lesson_list_2002_1.add(new Lesson(2,"SPL","Lab","Even", LocalTime.of(12, 30 ), LocalTime.of(14, 30 )));

        Index index_2002_1 =new Index(10034,10,3,"CZ2002");
        index_2002_1.setLessons(lesson_list_2002_1);

        ArrayList<Lesson> lesson_list_2002_2 = new ArrayList<Lesson>();
        lesson_list_2002_2.add(new Lesson(1,"LT2A","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2002_2.add(new Lesson(4,"TR+19","Tutorial", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2002_2.add(new Lesson(3,"SPL","Lab","Odd", LocalTime.of(13, 30 ), LocalTime.of(15, 30 )));

        Index index_2002_2 =new Index(10219,10,3,"CZ2002");
        index_2002_2.setLessons(lesson_list_2002_2);

        ArrayList<Lesson> lesson_list_2002_3 = new ArrayList<Lesson>();
        lesson_list_2002_3.add(new Lesson(1,"LT2A","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2002_3.add(new Lesson(2,"TR+15","Tutorial", LocalTime.of(13, 30 ), LocalTime.of(14, 30 )));
        lesson_list_2002_3.add(new Lesson(1,"SPL","Lab","Even", LocalTime.of(8, 30 ), LocalTime.of(10, 30 )));

        Index index_2002_3 =new Index(10829,10,3,"CZ2002");
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

        Index index_2005_1 =new Index(11983,10,3,"CZ2005");
        index_2002_1.setLessons(lesson_list_2005_1);

        ArrayList<Lesson> lesson_list_2005_2 = new ArrayList<Lesson>();
        lesson_list_2005_2.add(new Lesson(3,"LT-4","Lecture", LocalTime.of(9, 30 ), LocalTime.of(10, 30 )));
        lesson_list_2005_2.add(new Lesson(5,"LT-4","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2005_2.add(new Lesson(3,"TR+15","Tutorial", LocalTime.of(13, 30 ), LocalTime.of(14, 30 )));

        Index index_2005_2 =new Index(11980,10,3,"CZ2005");
        index_2002_2.setLessons(lesson_list_2005_2);

        ArrayList<Lesson> lesson_list_2005_3 = new ArrayList<Lesson>();
        lesson_list_2005_3.add(new Lesson(3,"LT-4","Lecture", LocalTime.of(9, 30 ), LocalTime.of(10, 30 )));
        lesson_list_2005_3.add(new Lesson(2,"LT-4","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2005_3.add(new Lesson(1,"TR+14","Tutorial", LocalTime.of(15, 30 ), LocalTime.of(16, 30 )));

        Index index_2005_3 =new Index(11928,10,3,"CZ2005");
        index_2002_3.setLessons(lesson_list_2005_3);

        ArrayList<Object> index_list_2005 = new ArrayList<>();
        index_list_2005.add(index_2005_1);
        index_list_2005.add(index_2005_2);
        index_list_2005.add(index_2005_3);

        Course course_2005 = new Course("CZ2005","Human Computer Interaction", 3,"SCSE");
        course_2005.setIndexNumberList(index_list_2005);

        //For course CZ2006
        ArrayList<Lesson> lesson_list_2006_1 = new ArrayList<Lesson>();
        lesson_list_2006_1.add(new Lesson(1,"LT1A","Lecture", LocalTime.of(12, 30 ), LocalTime.of(13, 30 )));
        lesson_list_2006_1.add(new Lesson(4,"TR+24","Tutorial", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2006_1.add(new Lesson(2,"HWLAB3","Lab","Odd", LocalTime.of(12, 30 ), LocalTime.of(14, 30 )));

        Index index_2006_1 = new Index(10135,10,3,"CZ2006");
        index_2006_1.setLessons(lesson_list_2006_1);

        ArrayList<Lesson> lesson_list_2006_2 = new ArrayList<Lesson>();
        lesson_list_2006_2.add(new Lesson(1,"LT1A","Lecture", LocalTime.of(10, 30 ), LocalTime.of(11, 30 )));
        lesson_list_2006_2.add(new Lesson(2,"TR+31","Tutorial", LocalTime.of(11, 30 ), LocalTime.of(12, 30 )));
        lesson_list_2006_2.add(new Lesson(3,"HWLAB3","Lab","Odd", LocalTime.of(8, 30 ), LocalTime.of(10, 30 )));

        Index index_2006_2 =new Index(10129,10,3,"CZ2006");
        index_2006_2.setLessons(lesson_list_2006_2);

        ArrayList<Lesson> lesson_list_2006_3 = new ArrayList<Lesson>();
        lesson_list_2006_3.add(new Lesson(1,"LT1A","Lecture", LocalTime.of(12, 30 ), LocalTime.of(13, 30 )));
        lesson_list_2006_3.add(new Lesson(4,"TR+19","Tutorial", LocalTime.of(8, 30 ), LocalTime.of(9, 30 )));
        lesson_list_2006_3.add(new Lesson(3,"HWLAB3","Lab","Even", LocalTime.of(9, 30 ), LocalTime.of(11, 30 )));

        Index index_2006_3 =new Index(10829,10,3,"CZ2006");
        index_2006_3.setLessons(lesson_list_2006_3);

        ArrayList<Object> index_list_2006 = new ArrayList<>();
        index_list_2006.add(index_2006_1);
        index_list_2006.add(index_2006_2);
        index_list_2006.add(index_2006_3);

        Course course_2006 = new Course("CZ2006","Software Engineering", 3,"SCSE");
        course_2006.setIndexNumberList(index_list_2006);

        //For course BU8625
        ArrayList<Lesson> lesson_list_8625_1 = new ArrayList<Lesson>();
        lesson_list_8625_1.add(new Lesson(5,"SR23","Lecture", LocalTime.of(13, 30 ), LocalTime.of(15, 30 )));
        lesson_list_8625_1.add(new Lesson(3,"Hive TR+34","Tutorial", LocalTime.of(8, 30 ), LocalTime.of(9, 30 )));

        Index index_8625_1 =new Index(10953,10,3,"BU8625");
        index_8625_1.setLessons(lesson_list_8625_1);

        ArrayList<Lesson> lesson_list_8625_2 = new ArrayList<Lesson>();
        lesson_list_8625_2.add(new Lesson(4,"SR23","Lecture", LocalTime.of(10, 30 ), LocalTime.of(12, 30 )));
        lesson_list_8625_2.add(new Lesson(2,"Hive TR+34","Tutorial", LocalTime.of(8, 30), LocalTime.of(9, 30 )));

        Index index_8625_2 =new Index(10742,10,3,"BU8625");
        index_8625_2.setLessons(lesson_list_8625_2);

        ArrayList<Object> index_list_8625 = new ArrayList<>();
        index_list_8625.add(index_8625_1);
        index_list_8625.add(index_8625_2);

        Course course_8625 = new Course("BU8625","Marketing in 21st Century", 3,"NBS");
        course_8625.setIndexNumberList(index_list_8625);

        //For course HP8006
        ArrayList<Lesson> lesson_list_8006_1 = new ArrayList<>();
        lesson_list_8006_1.add(new Lesson(3,"LT-1","Lecture", LocalTime.of(9, 30 ), LocalTime.of(12, 30 )));

        Index index_8006_1 =new Index(17893,10,3,"HP8006");
        index_8006_1.setLessons(lesson_list_8006_1);

        ArrayList<Lesson> lesson_list_8006_2 = new ArrayList<>();
        lesson_list_8006_2.add(new Lesson(4,"LT-1","Lecture", LocalTime.of(13, 30 ), LocalTime.of(16, 30 )));

        Index index_8006_2 =new Index(17854,10,3,"HP8006");
        index_8006_2.setLessons(lesson_list_8006_2);

        ArrayList<Lesson> lesson_list_8006_3 = new ArrayList<>();
        lesson_list_8006_3.add(new Lesson(1,"LT-1","Lecture", LocalTime.of(8, 30 ), LocalTime.of(11, 30 )));

        Index index_8006_3 =new Index(17835,10,3,"HP8006");
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
        fileMgr.saveObjects(course_list, "course.dat");

        ArrayList<Object> student_list = new ArrayList<>();
        student_list.add(new Student("Parthan","Parthan0123","U18221347C","parthan001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Ankitha","Ankitha0123","U19231515C","ankitha001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Nisha","Nisha0123","U1922137C","nisha001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Atul","Atul0123","U1922187C","atul001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Srishti","Srishti0123","U1923637C","srishti001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Aratrika","Aratrika0123","U18226347C","aratrika001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Agnesh","Agnesh0123","U19231518C","agnesh001@e.ntu.edu.sg", "M", "Indian","SCSE",3 ));
        student_list.add(new Student("Saiteja","Saiteja0123","U1912137C","saiteja001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Kartikeya","Kartikeya0123","U1925987C","kartikeya001@e.ntu.edu.sg", "M", "Indian","SCSE",3 ));
        student_list.add(new Student("Shyam","Shyam0123","U1824637C","shyam001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Aneez","Aneez0123","U18222847C","aneez001@e.ntu.edu.sg", "M", "Indian","SCSE",4 ));
        student_list.add(new Student("Kirthana","kirthana0123","U1923195C","kirthana001@e.ntu.edu.sg", "F", "Indian","SCSE",3 ));
        student_list.add(new Student("Aditya","Aditya0123","U1921837C","aditya001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Ananya","Ananya0123","U1922189C","ananya001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Madhav","Madhav0123","U1923347C","madhav001@e.ntu.edu.sg", "M", "Indian","SCSE",1 ));

        FileMgr fileMgr1 = new FileMgr();
        fileMgr1.saveObjects(student_list, "student.dat");

        ArrayList<Object> admin_list = new ArrayList<>();
        admin_list.add(new Admin("John","John0123", "M", "Indian" ,"John001@e.ntu.edu.sg",101));
        admin_list.add(new Admin("Anne","Anne0123", "F", "American","anne001@e.ntu.edu.sg", 102));
        admin_list.add(new Admin("Henry","Henry0123", "F", "Singapore","henry001@e.ntu.edu.sg",103 ));
        admin_list.add(new Admin("Sam","Sam0123", "M", "Indian","sam001@e.ntu.edu.sg",104));
        admin_list.add(new Admin("Jenna","Jenna0123", "F", "Indian" ,"jenna001@e.ntu.edu.sg",105));

        FileMgr fileMgr2 = new FileMgr();
        fileMgr2.saveObjects(admin_list, "admin.dat");

        LoginInterface.main(null);
    }
}



