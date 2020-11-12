package Controller;

import Entity.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.String;
import java.util.ArrayList;

public class FileMgr {
    public ArrayList<Course> loadCourses(String courseFileName) {
        ArrayList<Course> c = new ArrayList<Course>();
        FileInputStream fi = new FileInputStream(new File(courseFileName));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        try {
            while (true) {
                c.add((Course) oi.readObject());
            }
        } catch (EOFException e) {
            // do nothing
        }

        oi.close();
        fi.close();
        return c;

    }

    public void saveCourses(ArrayList<Course> c, String courseFileName) {

        FileOutputStream f = new FileOutputStream(new File(courseFileName));
        ObjectOutputStream o = new ObjectOutputStream(f);

        // Write objects to file

        while (true) {
            for (int i = 0; i < c.size(); i++)
                o.writeObject(c[i]);
        }

        o.close();
        f.close();
    }

    public void saveUserList(ArrayList<User> u, String userFileName) {

        FileOutputStream f = new FileOutputStream(new File(userFileName));
        ObjectOutputStream o = new ObjectOutputStream(f);

        // Write objects to file

        while (true) {
            for (int i = 0; i < u.size(); i++)
                o.writeObject(u[i]);
        }

        o.close();
        f.close();
    }

    public ArrayList<User> loadUSerList(String userFileName) {
        ArrayList<User> u = new ArrayList<User>();
        FileInputStream fi = new FileInputStream(new File(userFileName));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        try {
            while (true) {
                u.add((User) oi.readObject());
            }
        } catch (EOFException e) {
            // do nothing
        }

        oi.close();
        fi.close();
        return u;
    }

    public void SaveAdminList(ArrayList<Admin> a, String courseFileName) {
        FileOutputStream f = new FileOutputStream(new File(courseFileName));
        ObjectOutputStream o = new ObjectOutputStream(f);

        // Write objects to file

        while (true) {
            for (int i = 0; i < a.size(); i++)
                o.writeObject(a[i]);
        }

        o.close();
        f.close();
    }

    public ArrayList<Admin> loadAdminList(String adminFileName)
    {
        ArrayList<Course> a = new ArrayList<Admin>();
        FileInputStream fi = new FileInputStream(new File(adminFileName));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        try{
            while(true)
            {
                a.add( (Admin) oi.readObject());
            }}
        catch(EOFException e){
            //do nothing
        }
        
        

        oi.close();
        fi.close();
        return c;
    }
