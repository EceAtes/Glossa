package com.example.glossa;

import android.widget.Toast;

public class MultipleChoiceQuestion extends Question{
    //String[] options;

    public MultipleChoiceQuestion(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
        super(question, option1, option2, option3, option4, correctAnswer);
//        options[0] = option1;
//        options[1] = option2;
//        options[2] = option3;
//        options[3] = option4;
    }

    @Override
    public boolean checkAnswer(Object obj) {

        String selected;
        if( obj instanceof Integer){
            System.out.println("Entered2");
            selected = "" + obj;
            System.out.println(selected + " " + correctAnswer);
            if(selected.equalsIgnoreCase(correctAnswer) ){
                return true;
            }
            return false;
        }
        //throw exception?
        return false;
    }

//    public String[] getOptions() {
//        return options;
//    }
//
//    public void setOptions(String[] options) {
//        this.options = options;
//    }
}
