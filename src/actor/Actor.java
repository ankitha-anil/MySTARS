package actor;

/**
 * This is the current user of the system
 * The actor objects are not stored in the database
 *
 * @author Anon
 */
public class Actor {
    /**
     * user name of the current user of the system
     */
    private String userName;

    /**
     * Default Constructor for Actor class
     */
    public Actor() {
    }

    /**
     * Constructor that initialises the userName of the current user
     *
     * @param userName user name to be set
     */

    public Actor(String userName) {
        this.userName = userName;
    }

    /**
     * Accessor of userName
     *
     * @return returns the user name of the current user of the system
     */
    public String getUserName() {
        return userName;
    }
}
