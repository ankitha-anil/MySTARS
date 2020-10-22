package com.oops;

public class User {
    protected String id;
    private String password; //if we are using the login function here
    protected String name;
    

    public User(String name,String id,String password){
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public void setName(String name){
        this.name = name;

    public void setId(String id){
        this.id = id;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getPassword(){
        return password;
    }
    public static void main(String[] args) {
        System.out.print("Hey Guys");
    } //test
}

