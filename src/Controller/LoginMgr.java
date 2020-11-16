package Controller;

import Entity.User;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginMgr {
    public static final String SEPARATOR = "|";


    //Reading User file
    public static ArrayList readUser(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList alr = new ArrayList() ;// to store Users data

        for (int i = 0 ; i < stringArray.size(); i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

            String  username = star.nextToken().trim();	// first token
            String  password = star.nextToken().trim();	// second token

            // create User object from file data
            User user = new User(username,password);
            // add to User list
            alr.add(user) ;
        }
        return alr ;
    }


    /** Read the contents of the given file. */
    public static List read(String fileName) throws IOException {
        List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }
        finally{
            scanner.close();
        }
        return data;
    }


    //  Saving the User class for username file
    public static void saveUser(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store Users data

        for (int i = 0 ; i < al.size() ; i++) {
            User u = (User)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(u.getNetworkName().trim());
            st.append(SEPARATOR);
            st.append(u.getPassword());
            alw.add(st.toString()) ;
        }
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        try {
            for (int i =0; i < alw.size() ; i++) {
                out.println((String)alw.get(i));
            }
        }
        finally {
            out.close();
        }
    }
}
