package com.oops;

public class Student extends User{

    private String school;
    private String timetable;

    public Student(String name,String id,String password,char gender,String nationality,String school,String timetable) {
        super(name,id,password,gender,nationality);
        this.school=school;
        this.timetable=timetable; // need to change to type timetable
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getTimetable() {
        return timetable;
    }
    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }


}

