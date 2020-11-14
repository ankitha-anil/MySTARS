package Entity;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private String admin;

    Admin(String name, String userID, String password, String emailID, String gender, String nationality,
          String admin) {
        super(name, userID, password, emailID, gender, nationality);
        this.admin = admin;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public boolean equals(Object object) {
        return (admin.equals(((Admin) object).getAdmin()));
    }
}
