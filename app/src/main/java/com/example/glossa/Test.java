package com.example.glossa;

import java.util.ArrayList;

public class Test {
    ArrayList<Question> questionList;

    public Test(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public Test() {

        this.questionList = new ArrayList<>();
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public Question get(int i){
        return questionList.get(i);
    }

    public void addQuestion (Question q) {
        this.questionList.add(q);
    }

    public int getSize(){
        return questionList.size();
    }
}
