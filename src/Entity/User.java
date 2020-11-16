package Entity;

import javax.swing.text.Style;
import java.io.Serializable;

public abstract class User implements Serializable {
    protected String name;
    protected String networkName;
    protected String emailID;
    private String gender;
    private String nationality;

    // dummy Constructor for checking if Student/Admin exist in database
    public User(String networkName) {
        this.networkName = networkName;
        System.out.println("LLLL");
    }

    public User(String name, String networkName, String gender, String nationality, String emailID) {
        this.name = name;
        this.networkName = networkName;
        this.gender = gender;
        this.nationality = nationality;
        this.emailID = emailID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
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
