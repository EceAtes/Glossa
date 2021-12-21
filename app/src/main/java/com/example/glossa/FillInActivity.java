package com.example.glossa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class FillInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MultipleQActivity";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    private String answer;
    private TextView question,
            qNum,
            checkerText;
    private EditText userIn;
    private List<FillInQuestion> questionList;
    private int currQues,
            score;
    private Button checkerButton;
    private FirebaseFirestore firestore;
    private DocumentReference documentReference;
    private int category_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in);

        question = findViewById(R.id.question);
        qNum = findViewById(R.id.quesCount);
        userIn = findViewById(R.id.FillInAnswer);
        checkerText = findViewById(R.id.rightWrongText);
        checkerButton = findViewById(R.id.checkAnswer);

        //category_id = getIntent().........

        currQues = 0;
        questionList = new ArrayList<>();
        //questionList.add(new FillInQuestion("Question 1", "A"));
//
//        firestore = FirebaseFirestore.getInstance();

        getQuestionList();

        score = 0;
    }

    private void getQuestionList(){

        mDatabase.child("A1-1").child("Test3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = "", answer = "";
                int counter = 0;
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : children.getChildren()) {

                        if (child.getKey().equals("Question")) {
                            question = child.getValue().toString();
                            System.out.println(question + " " +  counter);
                        }
                        else if (child.getKey().equals("answer")) {
                            answer = child.getValue() + "";
                            System.out.println(answer + " " +  counter);
                        }

                    }
                    counter++;
                    questionList.add(new FillInQuestion(question,answer));
                    System.out.println(questionList.get(counter-1));
                }
                setFirstQuestion();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        questionList  = new ArrayList<>();
//        documentReference = firestore.collection("Testing/Level1/Test1").document("Question" + (currQues+1));
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//            }
//        });
//        //firestore.collection("Testing").document("Level1" + );
//        questionList.add(new FillInQuestion("Question 1, answer is a", "a"));
//        questionList.add(new FillInQuestion("Question 2, answer is ss", "ss"));
//        questionList.add(new FillInQuestion("Question 3, answer is d d", "d d"));
//        questionList.add(new FillInQuestion("Question 4, answer is f", "f"));
//        questionList.add(new FillInQuestion("Question 5, answer is g", "g"));
//
//        qNum.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));
//        question.setText(questionList.get(0).getQuestion());
    }

    public void setFirstQuestion(){
        question.setText(questionList.get(0).getQuestion());
        qNum.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));
    }

    @Override
    public void onClick(View v) {
        System.out.println("enteredonclick");
        answer = ("" + userIn.getText()).trim();

        checkAnswer(v,answer);
    }



    private void checkAnswer(View v, String answer) {
        System.out.println("enteredcheckanswer");
        if (questionList.get(currQues).checkAnswer(answer)) {

            userIn.setTextColor(Color.parseColor("#58eb34"));
            checkerText.setText("Correct!");
            checkerText.setTextColor(Color.parseColor("#58eb34"));
            score++;
            checkerText.setVisibility(View.VISIBLE);
        } else {
            userIn.setTextColor(Color.parseColor("#f5260f"));
            checkerText.setText("Oops! Wrong Answer");
            checkerText.setTextColor(Color.parseColor("#f5260f"));
            checkerText.setVisibility(View.VISIBLE);
        }



        nextQuestion(v,answer);
        //nextQuestion(view, answer);
    }

    public void nextQuestion(View v, String answer){
        System.out.println("entered next question");
        if(currQues == questionList.size()-1) {
            //display score - activity
            new CountDownTimer(500, 500) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Intent intent = new Intent(FillInActivity.this, ScoreActivity.class);
                    intent.putExtra("Score", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
                    startActivity(intent);
                    FillInActivity.this.finish();
                }

            }.start();

        } else{
            currQues++;
            playAnimation(question, 0, 0);
            playAnimation(userIn, 0, 1);
            playAnimation(checkerButton,0,2);
            playAnimation(checkerText, 0,3);

            qNum.setText(String.valueOf(currQues+1) + "/" + String.valueOf(questionList.size()));
        }

    }

    private void playAnimation(View view, int value, int component) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(350).setStartDelay(650)
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
//                            System.out.println();
                            break;
                        case 1:
                            ((EditText) view).setText(((FillInQuestion) questionList.get(currQues)).getOption1());
                            ((EditText) view).setTextColor(Color.parseColor("#000000"));
                            break;
                        case 2:
                            ((Button) view).setText("Check Answer");
                            break;
                        case 3:
                            ((TextView) view).setVisibility(View.INVISIBLE);
                            break;
                    }

//                    if (component == 3 ) {
//                        checkerText.setVisibility(View.INVISIBLE);
//                    }

                    playAnimation(view, 1, component);

                    if(component == 2){
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

        });

    }


}









