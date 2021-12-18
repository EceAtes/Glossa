package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MultipleQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    boolean isUsed = false;
    private Button option1,
            option2,
            option3,
            option4,
            selected;
    private TextView question,
            qNum;
    private List<Question> questionList;
    private int currQues,
            score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multiple);
        question = findViewById(R.id.question);
        qNum = findViewById(R.id.quesCount);
        currQues = 0;

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        getQuestionsList();

        score = 0;

    }

    private void getQuestionsList() {
        questionList = new ArrayList<>();

        questionList.add(new MultipleChoiceQuestion("Question 1", "A", "B", "C","D","4"));
        questionList.add(new MultipleChoiceQuestion("Question 2", "b", "c", "a","d","4"));
        questionList.add(new MultipleChoiceQuestion("Question 3", "y", "n", "k","s","4"));
        questionList.add(new MultipleChoiceQuestion("Question 4", "r", "t", "b","v","4"));
        questionList.add(new MultipleChoiceQuestion("Question 5", "w", "e", "n","x","4"));

        setFirstQuestion();
    }

    private void setFirstQuestion() {
        question.setText(questionList.get(0).getQuestion());
        option1.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption1());
        option2.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption2());
        option3.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption3());
        option4.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption4());

        qNum.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));
    }
//
//      public void onClick(View v){
//        isUsed = true;
//        if(isUsed){
//            selected.setBackgroundColor(Color.parseColor("#ffffff"));
//        }
//
//        selected = findViewById(v.getId());
//        selected.setBackgroundColor(Color.parseColor("#03f0fc"));
//
//
//    }

    @Override
    public void onClick(View v) {
        int answer = 0;

        switch (v.getId()){
            case R.id.option1 :
                answer = 1;
                break;
            case R.id.option2 :
                answer = 2;
                break;
            case R.id.option3 :
                answer = 3;
                break;
            case R.id.option4 :
                answer = 4;
                break;
            default:
        }

        //answer = 4;
        checkAnswer(v,answer);

    }

    private void checkAnswer(View view, int answer) {
        //Toast.makeText(this, answer + "", Toast.LENGTH_SHORT).show();
//        boolean b = questionList.get(currQues).checkAnswer(answer);
//        Toast.makeText(this, "" + b , Toast.LENGTH_SHORT).show();
        if(questionList.get(currQues).checkAnswer(answer)){

            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;


        } else {
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            if(questionList.get(currQues).getAnswer().equals("1")){
                option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else if(questionList.get(currQues).getAnswer().equals("2")){
                option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else if (questionList.get(currQues).getAnswer().equals("3")){
                option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else if(questionList.get(currQues).getAnswer().equals("4")){
                option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
//            switch(questionList.get(currQues).getAnswer()){
//
//                case 1:
//
//                    break;
//                case 2:
//
//                    break;
//                case 3:
//
//                    break;
//                case 4:
//
//                    break;
//            }



        }


        nextQuestion(view, answer);

    }

    private void nextQuestion( View view, int selected) {
        //Toast.makeText(this, "Entered", Toast.LENGTH_SHORT).show();
        if(currQues == questionList.size()-1){
            //display score - activity
            Intent intent = new Intent(MultipleQuestionActivity.this, ScoreActivity.class);
            intent.putExtra("Score",String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            startActivity(intent);
            MultipleQuestionActivity.this.finish();


        } else {
            currQues++;
            playAnimation(question,0,0);
            playAnimation(option1,0,1);
            playAnimation(option2,0,2);
            playAnimation(option3,0,3);
            playAnimation(option4,0,4);

            qNum.setText(String.valueOf(currQues+1) + "/" + String.valueOf(questionList.size()));
//            currQues++;
//            ((TextView)findViewById(R.id.question)).setText(questionList.get(currQues).getQuestion());
//
//            ((Button)view).setText(questionList.get(currQues).getOption1());
//            ((Button)view).setText(questionList.get(currQues).getOption2());
//            ((Button)view).setText(questionList.get(currQues).getOption3());
//            ((Button)view).setText(questionList.get(currQues).getOption4());
//
//            qNum.setText(String.valueOf(currQues+1) + "/" + String.valueOf(questionList.size()));


//            option1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3700B3")));
//            option2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3700B3")));
//            option3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3700B3")));
//            option4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3700B3")));


        }
    }

    private void playAnimation(View view, int value, int component) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(500)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0) {
                    switch (component) {
                        case 0:
                            ((TextView) view).setText(questionList.get(currQues).getQuestion());
                            break;
                        case 1:
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption1());
                            break;
                        case 2:
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption2());
                            break;
                        case 3:
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption3());
                            break;
                        case 4:
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption4());
                            break;
                    }
                    playAnimation(view, 1, component);

                    if(component != 0){
                        ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3700B3")));
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }); {

        }
    }


}