package Entity;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    // Create some attribute

    Admin(String name, String networkName, String gender, String nationality, String emailID) {
        super(name, networkName, gender, nationality, emailID);
    }
    public Admin(String networkName){
        super(networkName);
    }

}

