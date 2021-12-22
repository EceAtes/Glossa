package com.example.glossa;

public class UserAchievement {
    int totalTests, compTest;
    double compPercentage;
    User user;

    public UserAchievement(int totalTests, int compTest, double compPercentage) {
        this.totalTests = totalTests;
        this.compTest = compTest;
        this.compPercentage = compPercentage;
    }

    public UserAchievement(User user){
        this.user = user;
        compTest = 3;
    }


    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getCompTest() {
        return compTest;
    }

    public void setCompTest(int compTest) {
        this.compTest = compTest;
    }

    public double getCompPercentage() {
        return compPercentage;
    }

    public void setCompPercentage(double compPercentage) {
        this.compPercentage = compPercentage;
    }
}
