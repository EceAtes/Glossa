package com.example.glossa;

public class UserAchievement {
    int totatTests, compTest;
    double compPercentage;

    public UserAchievement(int totatTests, int compTest, double compPercentage) {
        this.totatTests = totatTests;
        this.compTest = compTest;
        this.compPercentage = compPercentage;
    }

    public int getTotatTests() {
        return totatTests;
    }

    public void setTotatTests(int totatTests) {
        this.totatTests = totatTests;
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
