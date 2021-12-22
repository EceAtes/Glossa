package com.example.glossa;

public class User {

    public String name,email,username,password, level;
    int completedTests;

    public User(){

    }
    public User(String name1, String email1, String username1, String password1){
        name = name1;
        email = email1;
        username = username1;
        password = password1;
        level = "";
        completedTests = 0;
    }

    public User(String name1, String email1, String username1, String password1, String level){
        name = name1;
        email = email1;
        username = username1;
        password = password1;
        this.level = level;
        completedTests = 0;

    }
    public void updateCompletedTest(){
        completedTests++;
    }
    public int getCompletedTests() {
        return completedTests;
    }

    public void setCompletedTests(int completedTests) {
        this.completedTests = completedTests;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}