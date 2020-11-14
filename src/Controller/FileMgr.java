package Controller;

import Entity.*;

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
            // do nothing
        } catch (FileNotFoundException e) {
            // do nothing
        } catch (IOException e) {
            // do nothing
        } catch (ClassNotFoundException e) {
            // do nothing
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
            // do nothing
        } catch (FileNotFoundException e) {
            // do nothing
        } catch (IOException e) {
            // do nothing
        }
    }
}