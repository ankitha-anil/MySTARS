package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends User implements Serializable {

    private int adminID;

    public Admin(String name, String networkName, String gender, String nationality, String emailID, int adminID) {
        super(name, networkName, gender, nationality, emailID);
        this.adminID = adminID;
    }

    public Admin(String networkName) {
        super(networkName);
    }

    public void print() {
        System.out.println("Name: " + name + ", Username: " + networkName + " , Email: " + emailID);
    }

    public static void printObjects(ArrayList<Object> admins) {
        for (Object admin : admins
        ) {
            if (admin instanceof Course)
                ((Admin) admin).print();
        }
    }

}
