package controller;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class FileMgr {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";

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
            System.out.println(RED+"System error"+RESET);
        } catch (FileNotFoundException e) {
            System.out.println(RED+"System error"+RESET);
        } catch (IOException e) {
            System.out.println(RED+"System error"+RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(RED+"System error"+RESET);
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
            System.out.println(RED+"System error"+RESET);
        } catch (FileNotFoundException e) {
            System.out.println(RED+"System error"+RESET);
        } catch (IOException e) {
            System.out.println(RED+"System error"+RESET);
        }
    }

}