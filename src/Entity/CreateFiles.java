package Entity;

import Controller.FileMgr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class CreateFiles {
    final static String SALT = "oops_project";

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (
                NoSuchAlgorithmException e) {
            // handle error here.
        }
        return hash.toString();
    }
    public static void main(String[] args) throws IOException {
       File stu_file = new File("student.txt");

       //String salt= SALT +"54Harmony!";
      //  String hashedPassword = generateHash(salt);

        ArrayList<Student> student_list = new ArrayList<Student>();
        student_list.add(new Student("Parthan","Parthan0123","U18221347C","54Harmony!","partha001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Ankitha","Ankitha0123","U19231515C","54Harmony!","ankitha001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Nisha","Nisha0123","U1922137C","54Harmony!","nisha001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));
        student_list.add(new Student("Atul","Atul0123","U1922187C","54Harmony!","atul001@e.ntu.edu.sg", "M", "Indian","SCSE",2 ));
        student_list.add(new Student("Srishti","Srishti0123","U1923637C","54Harmony!","srishti001@e.ntu.edu.sg", "F", "Indian","SCSE",2 ));

        FileOutputStream studFile = new FileOutputStream(stu_file);
        ObjectOutputStream studObject = new ObjectOutputStream(studFile);

        for (Object s : student_list){
            studObject.writeObject(s);
        }
        studFile.close();
        studObject.close();
    }
}
