package com.oops;

public class User {
    protected static String name;
    protected static String id;
    private static String password; //if we are using the login function here

    public static String getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }


}
