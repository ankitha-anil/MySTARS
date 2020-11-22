package controller;

/**
 * @author Anon
 * Abstract class that can be used for managing list of objects of a particular type
 */
public abstract class ObjectEntityController {
    /**
     * SystemMgr object
     * FileMgr object
     */
    protected SystemMgr systemMgr;
    protected FileMgr fileMgr;

    /**
     * Constructor for object entity controller class
     */
    public ObjectEntityController() {
        systemMgr = new SystemMgr();
        fileMgr = new FileMgr();
    }

    /**
     * Checks if an object with a given key exists in the list of objects of the same type
     *
     * @param key value of the attribute that uniquely identifies each object in the list
     * @return boolean value indicating if the required object exists in the list
     */
    public abstract boolean checkObjectExists(String key);

    /**
     * Prints all the objects present in a list
     */
    public abstract void printObjects();

    /**
     * Retrieves an object with a given key in the list of objects of the same type
     *
     * @param key value of the attribute that uniquely identifies each object in the list
     * @return required object from the corresponding list
     */
    public abstract Object getObjectFromList(String key);

}
