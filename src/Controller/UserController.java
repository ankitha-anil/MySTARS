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
            for (int idx = 0; idx < hashedBytes.length; idx++) {
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
        LoginMgr txtDB = new LoginMgr();
        Boolean isAuthenticated = false;
        String filename = "username_list.txt" ;
        try {
            // read file containing Professor records.
            ArrayList al = txtDB.readUser(filename) ;

            String saltedPassword = SALT + passWord;
            String hashedPassword = generateHash(saltedPassword);

            for (int i = 0 ; i < al.size() ; i++) {
                User s = (User)al.get(i);
                if(userName.equals(s.getNetworkName()) && hashedPassword.equals(s.getPassword()))
                { isAuthenticated = true;}
            }

        }catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        return isAuthenticated;
    }

   /* public void getCourseFromIndex(int indexNo){
        for (int course = 0; course<courseList.length; course++)
        {
            for (int i = 0; i<courseList[course].getIndexList().length); i++)
        }`
        }
    }*/

}
