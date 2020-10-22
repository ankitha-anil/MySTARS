package com.oops;

public class Student extends User{

    private String gender;
    private String nationality;
    private Timetable timeTable;

    public String getName(User U){
        return U.name;
    }

    public String getId(User U){
        return U.id;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public Timetable getTimeTable() {
        return timeTable;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setTimeTable(Timetable timeTable) {
        this.timeTable = timeTable;
    }
}
