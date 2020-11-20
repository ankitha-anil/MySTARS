package controller;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class FileMgr {

    public ArrayList<Object> loadObjects(String fileName) {
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
            System.out.println("System error");
        } catch (FileNotFoundException e) {
            System.out.println("System error");
        } catch (IOException e) {
            System.out.println("System error");
        } catch (ClassNotFoundException e) {
            System.out.println("System error");
        }
        return objects;
    }

    public void saveObjects(ArrayList<Object> objects, String fileName) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        // Write objects to file
        try {
            fileOutputStream = new FileOutputStream(new File(fileName));
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objects);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (EOFException e) {
            System.out.println("System error");
        } catch (FileNotFoundException e) {
            System.out.println("System error");
        } catch (IOException e) {
            System.out.println("System error");
        }
    }

}