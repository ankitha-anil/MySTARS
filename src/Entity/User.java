package Entity;

import java.io.Serializable;

public class User implements Serializable {
    protected String name;
    protected String userID;
    private String password;
    protected String emailID;
    private String gender;
    private String nationality;

    public User(String name, String userID, String password, String emailID, String gender, String nationality) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.emailID = emailID;
        this.gender = gender;
        this.nationality = nationality;
    }

    public User() {
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

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}