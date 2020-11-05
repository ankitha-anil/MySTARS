package com.oops;

public class User {
    protected String name;
    protected String userID;
    private String password;
    private String emailID;
    //if we are using the login function here
    //public static void main(String[] args) {
    // System.out.print("Hey Guys");
    //} //test

    public User(String name, String userID, String password, String emailID) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.emailID = emailID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
