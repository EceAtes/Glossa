package com.example.glossa;

public class UserAchievement {
    int totalTests, compTest;
    double compPercentage;

    public UserAchievement(int totalTests, int compTest, double compPercentage) {
        this.totalTests = totalTests;
        this.compTest = compTest;
        this.compPercentage = compPercentage;
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
