package entity;

import java.io.Serializable;

/**
 * Abstract Class User that contains general details of a person
 *
 * @author Anon
 */
public abstract class User implements Serializable {
    /**
     * name is the name of the user
     * networkName is th unique identification name of the user
     * emailId is the user's email Id
     * gender is the gender of the user
     * nationality od the nationality of the user
     */
    protected String name;
    protected String networkName;
    protected String emailID;
    private String gender;
    private String nationality;

    // dummy Constructor for checking if Student/Admin exist in database

    /**
     * This is a constructor for the objects of user class
     *
     * @param networkName
     */
    public User(String networkName) {
        this.networkName = networkName;
    }

    /**
     * This is a constructor of the objects og user class
     *
     * @param name        name of the user
     * @param networkName is the unique identification name for the user
     * @param gender      is the gender if the user
     * @param nationality nationality of the user
     * @param emailID     email Id of the user
     */
    public User(String name, String networkName, String gender, String nationality, String emailID) {
        this.name = name;
        this.networkName = networkName;
        this.gender = gender;
        this.nationality = nationality;
        this.emailID = emailID;
    }

    /**
     * Accessor for name
     *
     * @return returns the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator for name
     *
     * @param name name of the person to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor for network name
     *
     * @return returns the network name of the user
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Mutator for Network name
     *
     * @param networkName network name of the user to be set
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * Accessor for email id
     *
     * @return returns the email id of the user
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * Mutator for Email ID
     *
     * @param emailID email id of the user to be set
     */
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    /**
     * Accessor for gender
     *
     * @return returns the gender of the user
     */
    public String getGender() {
        return gender;
    }

    /**
     * Mutator for gender
     *
     * @param gender gender of the user to be set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Accesor for nationality
     *
     * @return returns the nationality of the user
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Mutator for nationality
     *
     * @param nationality nationality of the user to be set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Abstract method that needs to be implemented by the sub-classes
     */
    public abstract void print();

}