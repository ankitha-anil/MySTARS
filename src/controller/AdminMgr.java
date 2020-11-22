package controller;

import entity.Admin;

import java.util.ArrayList;

/**
 * Class that manages the list of existing admins
 *
 * @author Anon
 */
public class AdminMgr extends ObjectEntityController {

    /**
     * constructor for AdminMgr class
     */
    private static final String adminFile = "admin.dat";
    private static ArrayList<Object> admins;

    /**
     * constructor for AdminMgr class
     */
    public AdminMgr() {
        super();
        admins = fileMgr.loadObjects(adminFile);
    }

    /**
     * Displays the list of existing admins
     */
    public void printObjects() {
        loadAdminObjects();
        System.out.println("List of admins");
        for (Object admin : admins
        ) {

            if (admin instanceof Admin) {
                ((Admin) admin).print();
            }
        }
    }

    /**
     * Checks if an admin with a particular username exists
     *
     * @param userName username that needs to be verified
     * @return boolean value corresponding to whether admin exists or not
     */
    public boolean checkObjectExists(String userName) {
        loadAdminObjects();
        Object dummyAdmin = new Admin(userName);
        return systemMgr.findObject(admins, dummyAdmin) != null;
    }

    /**
     * Loads the list of admins from the file
     */
    public void loadAdminObjects() {
        admins = fileMgr.loadObjects(adminFile);
    }

    /**
     * Retrieve the admin object having a particular username
     *
     * @param userName username of the admin object that needs to be retrieved
     * @return required admin object
     */
    public Object getObjectFromList(String userName) {
        Object admin = new Admin(userName);
        admin = systemMgr.findObject(admins, admin);
        if (admin instanceof Admin)
            return admin;
        return null;
    }
}
