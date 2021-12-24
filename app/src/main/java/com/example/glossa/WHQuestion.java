package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WHQuestion extends AppCompatActivity implements View.OnClickListener{

    private String explanation;
    private TextView explanationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whquestion);

        explanationView = findViewById(R.id.explanation);
        explanation = "There are five W-H questions";
        explanationView.setText(explanation);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.changeButton) {
            Intent intent = new Intent(WHQuestion.this, WHExample.class);
            startActivity(intent);
        }
    }
}