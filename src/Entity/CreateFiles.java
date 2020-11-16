package Entity;

import Controller.FileMgr;
import Controller.LoginMgr;

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
            for(int idx = 0; idx < hashedBytes.length;idx++ ) {
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
        String filename = "username_list.txt" ;
        try {
            // read file containing student records.
            ArrayList student_list = LoginMgr.readUser(filename) ;//

            student_list.add(new User("Parthan0123",generateHash(SALT +"53Harmony!" )));
            student_list.add(new User("Ankitha0123",generateHash(SALT +"54Harmony!" ) ));
            student_list.add(new User("Nisha0123",generateHash(SALT +"55Harmony!" ) ));
            student_list.add(new User("Atul0123",generateHash(SALT +"56Harmony!" )  ));
            student_list.add(new User("Srishti0123",generateHash(SALT +"57Harmony!" ) ));

            // write student record/s to file.
            LoginMgr.saveUser(filename, student_list);

        /*    ArrayList admin_list = FileMgr.readUser(filename) ;

            admin_list.add(new User("Parthan0123",generateHash(SALT +"53Harmony!" )));
            admin_list.add(new User("Ankitha0123",generateHash(SALT +"54Harmony!" ) ));
            admin_list.add(new User("Nisha0123",generateHash(SALT +"55Harmony!" ) ));
            admin_list.add(new User("Atul0123",generateHash(SALT +"56Harmony!" )  ));
            admin_list.add(new User("Srishti0123",generateHash(SALT +"57Harmony!" ) ));

            // write student record/s to file.
            FileMgr.saveUser(filename, admin_list); */

        }catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }


    }
}
