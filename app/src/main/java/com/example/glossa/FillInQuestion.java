package com.example.glossa;

public class FillInQuestion extends Question{
    String correctAnswer;

    public FillInQuestion(String question, String correctAnswer) {
        super(question,correctAnswer);
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer() {
        return correctAnswer;
    }

    public void setAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "FillInQuestion{" +
                "correctAnswer='" + correctAnswer + '\'' +
                ", Question='" + Question + '\'' +
                '}';
    }

    @Override
    public boolean checkAnswer(Object obj) {
        String userAnswer;
        if( obj instanceof String){
            userAnswer = "" + obj;
            if(correctAnswer.equalsIgnoreCase(userAnswer)){
                return true;
            }
            return false;
        }
        //throw exception?
        return false;
    }
}
