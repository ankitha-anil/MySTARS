package Controller;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import Entity.*;

public class UserController {

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

    public static boolean loginCheck(String userName, String passWord) throws IOException {
        Boolean isAuthenticated = false;
        File student = new File("student_list.txt");
        FileInputStream come = new FileInputStream(student);
        ObjectInputStream ios = new ObjectInputStream(come);
        ArrayList<Student> stuobj = new ArrayList<Student>();

        try{
            while(true){
                Student s = (Student)ios.readObject();
                stuobj.add(s);

            }
        }catch (EOFException | ClassNotFoundException ex){

        }

        for (Student s : stuobj) {

            System.out.println(s.getNetworkName());
            System.out.println(s.getPassword());

            String saltedPassword = SALT + passWord;
            String hashedPassword = generateHash(saltedPassword);

            if(userName.equals(s.getNetworkName())&& hashedPassword.equals(s.getPassword()))
                isAuthenticated = true;

            //isAuthenticated = hashedPassword.equals(s.getPassword()) && userName.equals(s.getUsername()) && user == 2;

        }
        return isAuthenticated;
    }

   /* public void getCourseFromIndex(int indexNo){
        for (int course = 0; course<courseList.length; course++)
        {
            for (int i = 0; i<courseList[course].getIndexList().length); i++)
        }
        }
    }*/

}
