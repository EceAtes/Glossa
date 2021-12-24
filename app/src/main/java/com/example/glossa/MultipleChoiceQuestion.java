package com.example.glossa;

import android.widget.Toast;

public class MultipleChoiceQuestion extends Question{

    public MultipleChoiceQuestion(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
        super(question, option1, option2, option3, option4, correctAnswer);
    }

    @Override
    public boolean checkAnswer(Object obj) {

        String selected;
        if( obj instanceof Integer){
            selected = "" + obj;
            if(selected.equalsIgnoreCase(correctAnswer) ){
                return true;
            }
            return false;
        }
        return false;
    }
}
