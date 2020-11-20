package controller;

public abstract class ObjectEntityController {
    protected SystemMgr systemMgr;
    protected FileMgr fileMgr;

    public ObjectEntityController() {
        systemMgr = new SystemMgr();
        fileMgr = new FileMgr();
    }

    public abstract boolean checkObjectExists(String key);

    public abstract void printObjects();

    public abstract Object getObjectFromList(String key);

}
