package com.oops;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Student1 implements Serializable {
    private String Username;
    public String password;
    private String Name;
    private String Nationality;
    private String School;
    private String Matric;
    ArrayList<String> timetable = new ArrayList<String>();
    private String waiting_list;
    private String example;
    private static String SALT = "oops-project";

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for(int idx = 0; idx < hashedBytes.length; idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();

    }

    public Student1(String u_name, String p, String n, String na, String S, String m, ArrayList<String> t, String wl) {
        this.Username = u_name;
        this.password = generateHash(SALT + p);
        this.Name = n;
        this.Nationality = na;
        this.School = S;
        this.Matric = m;
        this.timetable = t;
        this.waiting_list = wl;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return Username;
    }
}
