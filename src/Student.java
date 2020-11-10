package com.oops;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private String matriculationNumber;
    private String school;
    private int maxAU = 21;
    private int studyYear;
    private ArrayList<Index> indexRegistered;
    private ArrayList<Index> indexOnWaitList;
    private int registeredAU = 0;

    public Student(String name, String userID, String password, String emailID, String gender, String nationality, String school, int studyYear) {
        super(name, userID, password, emailID, gender, nationality);
        this.school = school;
        this.studyYear = studyYear;
        this.indexRegistered = new ArrayList<>();
        this.indexOnWaitList = new ArrayList<>();
    }

    public int getRegisteredAU() {
        return registeredAU;
    }

    public void setRegisteredAU(int registeredAU) {
        this.registeredAU = registeredAU;
    }

    public ArrayList<Index> getIndexRegistered() {
        return indexRegistered;
    }

    public void setIndexRegistered(ArrayList<Index> indexRegistered) {
        this.indexRegistered = indexRegistered;
    }

    public void addIndexRegistered(Index index) {
        this.indexRegistered.add(index);
        this.increaseRegisteredAU(index.getAcademicUnits());
        index.addStudent(this);
    }

    public void removeIndex(Index index) {
        // Remove the particular index number from the registered courses
    }

    public void addIndexOnWaitList(Index index) {
        this.indexOnWaitList.add(index);
    }

    public ArrayList<Index> getIndexOnWaitList() {
        return indexOnWaitList;
    }

    public void setIndexOnWaitList(ArrayList<Index> indexOnWaitList) {
        this.indexOnWaitList = indexOnWaitList;
    }

    public void increaseRegisteredAU(int academicUnits) {
        this.registeredAU += academicUnits;
    }
}
