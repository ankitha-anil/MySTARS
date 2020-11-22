package controller;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static boundary.MyStarsInterface.*;

/**
 * Class that handles the functionality for logging in
 *
 * @author Anon
 */
public class LoginMgr {

    /**
     * string containing the separator for the usernames and passwords in the files having them
     * string containing the name of the file in which usernames and passwords of students are stored
     * string containing the name of the file in which usernames and passwords of admins are stored
     * string containing the salt for the password hashing
     * list of student login details where each a item is a string containing a username and the assoociated password
     * list of admin login details where each a item is a string containing a username and the assoociated password
     */
    private static final String SEPARATOR = "|";
    private static final String studentFile = "student_details.txt";
    private static final String adminFile = "admin_details.txt";
    final static String SALT = "oops_project";
    private static ArrayList<String> studentLoginDetails;

    static {
        try {
            studentLoginDetails = (ArrayList<String>) readCredentials(studentFile);
        } catch (IOException e) {
            System.out.println(RED + "Could not retrieve data" + RESET);
        }
    }

    private static ArrayList<String> adminLoginDetails;

    static {
        try {
            adminLoginDetails = (ArrayList<String>) readCredentials(adminFile);
        } catch (IOException e) {
            System.out.println(RED + "Could not retrieve data" + RESET);
        }
    }

    /**
     * Checks if the correct username and password have been entered for login
     *
     * @param userName username of the student/admin attempting to login
     * @param password password entered by the student/admin
     * @param userType type of user ( student/admin)
     * @return boolean value to indicate if the correct username and password have been entered
     * @throws IOException
     */
    public static boolean loginCheck(String userName, String password, String userType) throws IOException {
        ArrayList<String> loginDetails;
        if (userType.toLowerCase().equals("student")) {
            studentLoginDetails = (ArrayList<String>) readCredentials(studentFile);
            loginDetails = studentLoginDetails;
        } else if (userType.toLowerCase().equals("admin")) {
            adminLoginDetails = (ArrayList<String>) readCredentials(adminFile);
            loginDetails = adminLoginDetails;
        } else {
            return false;
        }
        if (loginDetails.size() == 0) {
            System.out.println(RED + "System error: " + userType + "s are not stored in the system" + RESET);
        }
        for (int i = 0; i < loginDetails.size(); i++) {
            String record = loginDetails.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(record, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String userNameFile = star.nextToken().trim();    // first token
            String passwordFile = star.nextToken().trim();    // second token
            String saltedPassword = SALT + password;
            String hashedPassword = generateHash(saltedPassword);

            if (userName.equals(userNameFile) && hashedPassword.equals(passwordFile)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Reads the contents of the given file.
     *
     * @param fileName name of the file to be read
     */
    public static List readCredentials(String fileName) throws IOException {
        List data = new ArrayList();
        try {
            Scanner scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(RED + "System error: file " + fileName + " is not present" + RESET);

        }
        return data;
    }

    /**
     * Saves the list of users to a file
     *
     * @param fileName name of the file in which the list of the users need to be saved
     * @throws IOException
     */
    public static void saveUser(String fileName) throws IOException {
        ArrayList<String> loginDetails;
        if (fileName.equals(studentFile))
            loginDetails = studentLoginDetails;
        else if (fileName.equals(adminFile))
            loginDetails = adminLoginDetails;
        else {
            System.out.println(RED + "File doesn't exist" + RESET);
            return;
        }
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < loginDetails.size(); i++) {
                printWriter.println(loginDetails.get(i));
            }
        } catch (NullPointerException e) {
            System.out.println(RED + "Records are empty" + RESET);
        } finally {
            printWriter.close();
        }
    }


    /**
     * Stores a new username and password pair in the corresponding file.
     * Checks if the username does not already exist before appending it to the file
     *
     * @param networkName username of the new user being created
     * @param password    password of the new user being created
     * @param userType    type of the user (student/admin)
     * @throws IOException
     */
    public static void createUser(String networkName, String password, String userType) throws IOException {
        ArrayList<String> loginDetails;
        String fileName;
        if (userType.equals("student")) {
            studentLoginDetails = (ArrayList<String>) readCredentials(studentFile);
            loginDetails = studentLoginDetails;
            fileName = studentFile;
        } else if (userType.equals("admin")) {
            adminLoginDetails = (ArrayList<String>) readCredentials(adminFile);
            loginDetails = adminLoginDetails;
            fileName = adminFile;
        } else {
            System.out.println(RED + "Invalid type: Cannot create" + RESET);
            return;
        }

        boolean toWrite = true;

        String newRecord = networkName + SEPARATOR + generateHash(SALT + password);

        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        for (int i = 0; i < loginDetails.size(); i++) {
            printWriter.println(loginDetails.get(i));
            StringTokenizer star = new StringTokenizer(loginDetails.get(i), SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String userNameFile = star.nextToken().trim();
            if (userNameFile.equals(networkName)) {
                toWrite = false;
            }
        }
        if (toWrite)
            printWriter.print(newRecord);
        else System.out.println(RED + "User " + networkName + " already exists" + RESET);
        printWriter.close();
    }

    /**
     * Removes a username and its associated password from the corresponding file
     *
     * @param userName username that needs to be removed
     * @param userType type of the user (student/admin)
     * @throws IOException
     */
    public static void removeUser(String userName, String userType) throws IOException {
        ArrayList<String> loginDetails;
        String fileName;
        if (userType.toLowerCase().equals("student")) {
            loginDetails = studentLoginDetails;
            fileName = studentFile;
        } else if (userType.toLowerCase().equals("admin")) {
            loginDetails = adminLoginDetails;
            fileName = adminFile;
        } else {
            System.out.println(RED + "Invalid type... cannot create" + RESET);
            return;
        }
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < loginDetails.size(); i++) {
                String record = loginDetails.get(i);
                StringTokenizer star = new StringTokenizer(record, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
                String userNameFile = star.nextToken().trim();
                if (!userNameFile.equals(userName))
                    printWriter.println(loginDetails.get(i));
                else {
                    System.out.println(RED + "Deleting: " + userName + " from system" + RESET);
                }
            }
        } finally {
            printWriter.close();
        }

    }

    /**
     * Generates the hash of the password
     *
     * @param input the password that needs to be hashed
     * @return
     */
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
            System.out.println(RED + "System error" + RESET);
        }
        return hash.toString();
    }

}
