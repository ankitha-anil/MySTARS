package Controller;

import Entity.*;

import javax.management.ObjectName;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class FileMgr {
    private static ArrayList<Object> courses;
    private static ArrayList<Object> students;
    private static ArrayList<Object> admins;
    private static final String studentFile = "student.dat";
    private static final String adminFile = "admin.dat";
    private static final String courseFile = "course.dat";

    public FileMgr() {
        courses = loadObjects(courseFile);
        students = loadObjects(studentFile);
        admins = loadObjects(adminFile);
    }

    public static ArrayList<Object> loadObjects(String fileName) {
        ArrayList<Object> objects = new ArrayList<>();
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        // Read objects
        try {
            fileInputStream = new FileInputStream(new File(fileName));
            objectInputStream = new ObjectInputStream(fileInputStream);
            objects = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return objects;
    }

    public static void saveObjects(ArrayList<Object> objects) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        String fileName;
        if (objects.get(0) instanceof Course)
            fileName = courseFile;
        else if (objects.get(0) instanceof Student)
            fileName = studentFile;
        else if (objects.get(0) instanceof Admin)
            fileName = adminFile;

        else {
            System.out.println("System Error... Wait for bug to be fixed");
            return;
        }
        // Write objects to file
        try {
            fileOutputStream = new FileOutputStream(new File(fileName));
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objects);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (EOFException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Object> getCourses() {
        return courses;
    }

    public static ArrayList<Object> getStudents() {
        return students;
    }

    public static void addObject(Object object) {
        if (object instanceof Student) {
            students.add(object);
            saveObjects(students);
        } else if (object instanceof Admin) {
            admins.add(object);
            saveObjects(admins);
        } else if (object instanceof Course) {
            courses.add(object);
            saveObjects(courses);
        }
    }

    public static void removeObject(Object object) {
        if (object instanceof Student) {
            students.remove(object);
            saveObjects(students);
        } else if (object instanceof Admin) {
            admins.remove(object);
            saveObjects(admins);
        } else if (object instanceof Course) {
            courses.remove(object);
            saveObjects(courses);
        }
    }
}
