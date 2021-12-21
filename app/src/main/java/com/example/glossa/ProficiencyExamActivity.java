package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProficiencyExamActivity extends AppCompatActivity implements View.OnClickListener{
    public String answer;
    private Button option1,
            option2,
            option3,
            option4,
            checkerButton;
    private EditText userIn;
    private int currQues,
                score;
    private TextView question,
                     qNum,
                     checkerText;
    private List<Question> questionList;
    private Question current;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiency_exam);

        question = findViewById(R.id.question);
        qNum = findViewById(R.id.quesCount);
        userIn = findViewById(R.id.FillInAnswer);
        checkerText = findViewById(R.id.rightWrongText);
        checkerButton = findViewById(R.id.checkAnswer);
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
        //user =

        score = 0;


    }

//    public int getUserScore(){
//        return score;
//    }
//
//    public int getQuestionCount(){
//        return questionList.;
//    }

    private void getQuestionsList(){
        questionList  = new ArrayList<>();
        System.out.println("EnteredFillIn");
        questionList.add(new MultipleChoiceQuestion("Question 1", "w", "e", "n","x","4"));
        questionList.add(new FillInQuestion("Question 2, answer is a", "a"));
        questionList.add(new FillInQuestion("Question 3, answer is ss", "ss"));
        questionList.add(new MultipleChoiceQuestion("Question 4", "r", "t", "b","v","4"));
        questionList.add(new FillInQuestion("Question 5, answer is f", "f"));
        questionList.add(new MultipleChoiceQuestion("Question 6", "y", "n", "k","s","4"));
        questionList.add(new FillInQuestion("Question 7, answer is g", "g"));
        questionList.add(new MultipleChoiceQuestion("Question 8", "A", "B", "C","D","4"));
        questionList.add(new FillInQuestion("Question 9, answer is d d", "d d"));
        questionList.add(new MultipleChoiceQuestion("Question 10", "b", "c", "a","d","4"));

        qNum.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));

        setFirstQuestion();
    }

    private void setFirstQuestion() {
        current = questionList.get(0);
        question.setText(current.getQuestion());
        if(current instanceof MultipleChoiceQuestion){

            option1.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption1());
            option2.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption2());
            option3.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption3());
            option4.setText(((MultipleChoiceQuestion)questionList.get(0)).getOption4());
            System.out.println("Entered mq setFQ");
            enableMultipleQuestionParts();
        } else{
            enableFillInQuestionParts();
            System.out.println("Entered fi setFQ");
        }

        qNum.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));


    }

    private void enableMultipleQuestionParts() {
        System.out.println("Entered enable mqp");
        findViewById(R.id.option1).setVisibility(View.VISIBLE);
        findViewById(R.id.option2).setVisibility(View.VISIBLE);
        findViewById(R.id.option3).setVisibility(View.VISIBLE);
        findViewById(R.id.option4).setVisibility(View.VISIBLE);
        findViewById(R.id.FillInAnswer).setVisibility(View.INVISIBLE);
        findViewById(R.id.rightWrongText).setVisibility(View.INVISIBLE);
        findViewById(R.id.checkAnswer).setVisibility(View.INVISIBLE);
    }

    private void enableFillInQuestionParts() {
        System.out.println("Entered enable fip");
        findViewById(R.id.option1).setVisibility(View.INVISIBLE);
        findViewById(R.id.option2).setVisibility(View.INVISIBLE);
        findViewById(R.id.option3).setVisibility(View.INVISIBLE);
        findViewById(R.id.option4).setVisibility(View.INVISIBLE);
        findViewById(R.id.FillInAnswer).setVisibility(View.VISIBLE);
        findViewById(R.id.rightWrongText).setVisibility(View.VISIBLE);
        findViewById(R.id.checkAnswer).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if(current instanceof MultipleChoiceQuestion){
            System.out.println("Entered mq onClick");
            switch (v.getId()){
                case R.id.option1 :
                    answer = "1";
                    break;
                case R.id.option2 :
                    answer = "2";
                    break;
                case R.id.option3 :
                    answer = "3";
                    break;
                case R.id.option4 :
                    answer = "4";
                    break;
                default:
            }
            checkMultipleQuestionAnswer(v,answer);
        } else {

            System.out.println("entered fi onclick");
            answer = "" + userIn.getText();
            checkFillInAnswer(v,answer);
        }

    }

    private void checkFillInAnswer(View v, String answer) {
        System.out.println("Entered checkAns fi");
        if (questionList.get(currQues).checkAnswer(answer)) {
              System.out.println("Entered fi correct");
//            userIn.setTextColor(Color.parseColor("#58eb34"));
//            checkerText.setText("Correct!");
//            checkerText.setTextColor(Color.parseColor("#58eb34"));
            score++;
            checkerText.setVisibility(View.VISIBLE);
        } else {
            System.out.println("Entered fi wrong");
//            userIn.setTextColor(Color.parseColor("#f5260f"));
//            checkerText.setText("Oops! Wrong Answer");
//            checkerText.setTextColor(Color.parseColor("#f5260f"));
//            checkerText.setVisibility(View.VISIBLE);
        }

        fillInNextQuestion(v,answer);
        //nextQuestion(view, answer);
    }

    private void fillInNextQuestion(View v, String answer) {
        System.out.println("Entered fi nextQ");

        if(currQues == questionList.size()-1) {
            //display score - activity
            new CountDownTimer(500, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Intent intent = new Intent(ProficiencyExamActivity.this, ScoreActivity.class);
                    intent.putExtra("Score", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
                    startActivity(intent);
                    ProficiencyExamActivity.this.finish();
                }

            }.start();

        } else{
            currQues++;
            current = questionList.get(currQues);
            playAnimation(question,0,0);

            if(current instanceof MultipleChoiceQuestion){
                System.out.println("Entered fi next mq");
                enableMultipleQuestionParts();
                playAnimation(option1,0,1);
                playAnimation(option2,0,2);
                playAnimation(option3,0,3);
                playAnimation(option4,0,4);

            } else{
                System.out.println("Entered fi next fi");
                enableFillInQuestionParts();
                playAnimation(userIn, 0, 5);
                playAnimation(checkerButton,0,6);
                playAnimation(checkerText, 0,7);

            }

            qNum.setText(String.valueOf(currQues+1) + "/" + String.valueOf(questionList.size()));

        }
    }

    private void checkMultipleQuestionAnswer(View v, String answer) {
        //might be buggy
        //Toast.makeText(this, answer + "", Toast.LENGTH_SHORT).show();
//        boolean b = questionList.get(currQues).checkAnswer(Integer.parseInt(answer));
//        Toast.makeText(this, "" + b, Toast.LENGTH_SHORT).show();
        if (questionList.get(currQues).checkAnswer(Integer.parseInt(answer))) {
            System.out.println("Entered mq correct");
            //((Button) v).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;


        } else {
            System.out.println("Entered mq false");
//            ((Button) v).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
//
//            if (questionList.get(currQues).getAnswer().equals("1")) {
//                option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            } else if (questionList.get(currQues).getAnswer().equals("2")) {
//                option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            } else if (questionList.get(currQues).getAnswer().equals("3")) {
//                option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            } else if (questionList.get(currQues).getAnswer().equals("4")) {
//                option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            }


        }
        multipleNextQuestion(v, answer);
    }

    private void multipleNextQuestion( View v, String answer){
        System.out.println("Entered mq nextQ");
        if(currQues == questionList.size()-1){
            Intent intent = new Intent(ProficiencyExamActivity.this, ProficiencyScoreActivity.class);
            intent.putExtra("Score",score );
            intent.putExtra("List size", questionList.size());
            //display score - activity
//            Intent intent = new Intent(ProficiencyExamActivity.this, ScoreActivity.class);
//            intent.putExtra("Score",String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            startActivity(intent);
            ProficiencyExamActivity.this.finish();


        } else {
            currQues++;
            current = questionList.get(currQues);
            playAnimation(question,0,0);

            if(current instanceof MultipleChoiceQuestion){
                System.out.println("Entered mq next mq");
                enableMultipleQuestionParts();
                playAnimation(option1,0,1);
                playAnimation(option2,0,2);
                playAnimation(option3,0,3);
                playAnimation(option4,0,4);

            } else{
                System.out.println("Entered mq next fi");
                enableFillInQuestionParts();
                playAnimation(userIn, 0, 5);
                playAnimation(checkerButton,0,6);
                playAnimation(checkerText, 0,7);

            }

            /*  playAnimation(question, 0, 0);
            playAnimation(userIn, 0, 5);
            playAnimation(checkerButton,0,6);
            playAnimation(checkerText, 0,7);
*/
            qNum.setText(String.valueOf(currQues+1) + "/" + String.valueOf(questionList.size()));

        }
    }

    private void playAnimation(View view, int value, int component) {

                view.animate().alpha(value).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(150);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (value == 0) {
                    switch (component) {
                        case 0:
                            System.out.println("Entered setQ");
                            ((TextView) view).setText(questionList.get(currQues).getQuestion());
                            break;
                        case 1:
                            System.out.println("Entered set O1");
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption1());
                            break;
                        case 2:
                            System.out.println("Entered set O2");
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption2());
                            break;
                        case 3:
                            System.out.println("Entered set O3");
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption3());
                            break;
                        case 4:
                            System.out.println("Entered set O4");
                            ((Button) view).setText(((MultipleChoiceQuestion)questionList.get(currQues)).getOption4());
                            break;
                        case 5:
                            System.out.println("Entered set editT");
                            ((EditText) view).setText(((FillInQuestion) questionList.get(currQues)).getOption1());
                            ((EditText) view).setTextColor(Color.parseColor("#000000"));
                            break;
                        case 6:
                            System.out.println("Entered set button");
                            ((Button) view).setText("Check Answer");
                            break;
                        case 7:
                            System.out.println("Entered set checkT");
                            ((TextView) view).setVisibility(View.INVISIBLE);
                            break;
                    }

                    playAnimation(view, 1, component);

                    if(component != 0 && component != 5 && component != 7){
                        ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3700B3")));
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        }); {

        }
    }
}