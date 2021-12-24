package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WHExample extends AppCompatActivity implements View.OnClickListener{

    private String explanation;
    private TextView explanationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whexample);

        explanationView = findViewById(R.id.explanation);
        explanation = "- Who are you?";
        explanationView.setText(explanation);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.changeButton) {
            Intent intent = new Intent(WHExample.this, ShortNotesActivity.class);
            startActivity(intent);
        }
    }
}