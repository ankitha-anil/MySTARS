package com.oops;

public class User {
    private String name;
    private String id;
    private String password;
    private char gender;
    private String nationality;
    private String school;


    public User(String name,String id,String password,char gender,String nationality, String school) {
        this.name=name;
        this.id=id;
        this.password=password;
        this.gender=gender;
        this.nationality=nationality;
        this.school=school;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
}


