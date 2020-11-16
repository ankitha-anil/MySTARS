package Controller;

import Entity.User;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginMgr {
    private static final String SEPARATOR = "|";
    private static final String fileName = "users.txt";
    final static String SALT = "oops_project";
    private static ArrayList<String> loginDetails = new ArrayList<>();

    //Reading User file
    public LoginMgr() throws IOException {
        loginDetails = (ArrayList<String>) readCredentials(fileName);
    }

    public static boolean loginCheck(String userName, String password) throws IOException {

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
     * Read the contents of the given file.
     *
     * @param fileName
     */
    public static List readCredentials(String fileName) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(LoginMgr.fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    public static void saveUser() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < loginDetails.size(); i++) {
                printWriter.println((String) loginDetails.get(i));
            }
        } finally {
            printWriter.close();
        }
    }


    //  Saving the User class for username file
    public static void createUser(User newUser, String password) throws IOException {

        for (int i = 0; i < loginDetails.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(newUser.getNetworkName().trim());
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(generateHash(password));
            loginDetails.add(stringBuilder.toString());
        }
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i = 0; i < loginDetails.size(); i++) {
                printWriter.println((String) loginDetails.get(i));
            }
        } finally {
            printWriter.close();
        }
    }

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

    public static ArrayList<String> getLoginDetails() {
        return loginDetails;
    }
}
