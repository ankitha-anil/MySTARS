package com.oops;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private String gender;
    private String nationality;
    private ArrayList<Index> indexRegistered;
    private ArrayList<Index> indexOnWaitList;
    private int registeredAU = 0;

    public Student(String name, String userID, String password, String emailID, String gender, String nationality) {
        super(name, userID, password, emailID);
        this.gender = gender;
        this.nationality = nationality;
        this.indexRegistered = new ArrayList<>();
        this.indexOnWaitList = new ArrayList<>();
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
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

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addIndexRegistered(Index index) {
        this.indexRegistered.add(index);
        index.addStudent(this);
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
}
