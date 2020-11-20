package controller;

import entity.Admin;

import java.util.ArrayList;

public class AdminMgr extends ObjectEntityController {
    private static final String adminFile = "admin.dat";
    private static ArrayList<Object> admins;

    public AdminMgr() {
        super();
        admins = fileMgr.loadObjects(adminFile);
    }

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

    public boolean checkObjectExists(String userName) {
        loadAdminObjects();
        Object dummyAdmin = new Admin(userName);
        SystemMgr systemMgr = new SystemMgr();
        return systemMgr.findObject(admins, dummyAdmin) != null;
    }


    public void loadAdminObjects() {
        admins = fileMgr.loadObjects(adminFile);
    }

    public Object getObjectFromList(String userName) {
        Object admin = new Admin(userName);
        SystemMgr systemMgr = new SystemMgr();
        admin = systemMgr.findObject(admins, admin);
        if (admin instanceof Admin)
            return (Admin) admin;
        return null;
    }
}
