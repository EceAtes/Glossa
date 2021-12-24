package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProficiencyScoreActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    int score,
        qNum;
    String name;
    String level,
           explanation = "You will skip the test stages that are lower than your level!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiency_score);

        score = getIntent().getIntExtra("Score",0);
        qNum = getIntent().getIntExtra("List size", 0);

        determineLevel(score, qNum);

        TextView levelView = findViewById(R.id.userLevel);
        levelView.setText(level);

        TextView expView = findViewById(R.id.levelExplanation);
        expView.setText(explanation);
    }

    private void determineLevel(int score, int qNum) {

        int percentage = (score * 100) / qNum;
        if(percentage <= 25){
            level = "A1.1";
            explanation = "You will start the tests from the beginning!";
        }
        else if( percentage <= 50){
            level = "A1.2";
        }
        else if(percentage <=75){
            level = "A2.1";
        } else{
            level = "A2.2";
        }
        register.getUserFromRegister().setLevel(level);
        System.out.println(register.getUserFromRegister().getLevel());
        mDatabase.child("user").child(register.getUserFromRegister().getUsername()).child("level").setValue(level);
        System.out.println(register.getUserFromRegister().getEmail());
    }

    @Override
    public void onClick(View v) {
        Button button = findViewById(R.id.test1);
        Intent intent = new Intent(ProficiencyScoreActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}