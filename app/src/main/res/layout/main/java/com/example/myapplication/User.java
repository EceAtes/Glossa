package com.example.myapplication;

public class User {



    private String name,email,username,password;

    public User(){

    }
    public User(String name1, String email1, String username1, String password1){
        name = name1;
        email = email1;
        username = username1;
        password = password1;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName(){
        return name;
    }
}
