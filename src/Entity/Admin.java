package Entity;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    // Create some attribute

    Admin(String name, String networkName,String password, String email, String gender, String nationality) {
        super(name, networkName,password,email, gender, nationality);
    }

}
