package com.hfad.focusread;
/**
 * this is the user class for the database collection users
 * **/
public class User {
    private String name;
    private String email;

    public User (String name,String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

