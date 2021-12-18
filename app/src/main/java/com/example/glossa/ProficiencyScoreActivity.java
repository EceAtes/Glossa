package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProficiencyScoreActivity extends AppCompatActivity implements View.OnClickListener{
    int score,
        qNum;
    String level,
           explanation = "You will skip the test stages tat are lower than your level!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiency_score);

//        score = getIntent().getIntExtra("Score",0);
//        qNum = getIntent().getIntExtra("List size", 0);

        score = 0;
        qNum = 10;

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
           explanation = "You will start the test from the beginning!";
        }
        else if( percentage <= 50){
            level = "A1.2";
        }
        else if(percentage <=75){
            level = "A2.1";
        } else{
          level = "A2.2";
        }
    }

    @Override
    public void onClick(View v) {
        Button button = findViewById(R.id.test1);
        Intent intent = new Intent(ProficiencyScoreActivity.this, TestingMenuActivity.class);
        startActivity(intent);
    }
}