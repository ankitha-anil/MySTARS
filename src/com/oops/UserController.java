package com.oops;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.FileHandler;

public class UserController{

    User U;
    FileHandler fileHandler;




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

    protected static boolean loginCheck(String userName, String passWord)
    {

        Boolean isAuthenticated = false;
        final String SALT = "oodp_project";

        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = SALT+passWord;
        String hashedPassword = generateHash(saltedPassword);

       /* storedPasswordHash = the stored password

        if(hashedPassword.equals(storedPasswordHash)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }*/
        return isAuthenticated;
    }

}
