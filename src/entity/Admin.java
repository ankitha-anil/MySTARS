package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author group
 */
public class Admin extends User implements Serializable {
/**
 * Admin Id is the the identification number of the the admin
 */
    private int adminID;
/**
 * This is the constructor of objects of admin class
 * @param name is the name of the admin
 * @param networkName is the unique identification name for each admin of the system
 * @param gender is the gender of the admin
 * @param nationality is the nationality of the admin
 * @param emailID is the emailId of the admin
 * @param adminID is the identification number of the admin
 */
    public Admin(String name, String networkName, String gender, String nationality, String emailID, int adminID) {
        super(name, networkName, gender, nationality, emailID);
        this.adminID = adminID;
    }
/**
 * This is a constructor for the objects of admin class
 * @param networkName is the unique identification name for each admin of the system
 */
    public Admin(String networkName) {
        super(networkName);
    }
/**
 * This functions prints the Name, Username and Email id of the admin object
 */
    public void print() {
        System.out.println("Name: " + name + ", Username: " + networkName + " , Email: " + emailID);
    }


}
