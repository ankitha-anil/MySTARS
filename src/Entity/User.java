package Entity;

import java.io.Serializable;

public abstract class User implements Serializable {
      protected String name;
    protected String networkName; // Unique Value
    private String password;
    protected String emailID;
    private String gender;
    private String nationality;

    public User(String networkName,String password) {
        this.networkName=networkName;
        this.password=password;
    }


    public User(String name, String networkName, String password, String emailID,
                String gender, String nationality) {
        this.name = name;
        this.networkName = networkName;
        this.password=password;
        this.emailID=emailID;
        this.gender = gender;
        this.nationality = nationality;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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
