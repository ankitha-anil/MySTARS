package com.oops;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public class UserController {

    final static String SALT = "oops_project";

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length;)
            {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

    protected static boolean loginCheck (String userName, String passWord, Integer user) throws IOException {
        Boolean isAuthenticated = false;
        File student = new File("student_list.dat");
        FileInputStream come = new FileInputStream(student);
        ObjectInputStream ios = new ObjectInputStream(come);
        ArrayList<Student1> student2 = new ArrayList<Student1>();

        try{
            while(true){
                Student1 s = (Student1)ios.readObject();
                student2.add(s);
                System.out.println("User works");

            }
        }catch (EOFException | ClassNotFoundException ex){
            System.out.println("User works2");
        }

        for (Student1 s : student2) {

            System.out.println(s.getUsername());
            System.out.println(s.getPassword());

            String saltedPassword = SALT + passWord;
            String hashedPassword = generateHash(saltedPassword);

            /*if(!userName.equals(s.getName()))
            { System.out.println("Wrong username");
                isAuthenticated= false;}

            else if(!hashedPassword.equals(s.getPassword()))
            { System.out.println("Wrong password");
                isAuthenticated= false;}*/

            isAuthenticated = hashedPassword.equals(s.getPassword()) && userName.equals(s.getUsername()) && user == 2;

        }
        return isAuthenticated;
    }

}
