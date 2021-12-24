package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Adjectives extends AppCompatActivity implements View.OnClickListener{

    private String explanation;
    private TextView explanationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjectives);

        explanationView = findViewById(R.id.explanation);
        explanation = "They are used before nouns";
        explanationView.setText(explanation);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.changeButton) {
            Intent intent = new Intent(Adjectives.this, ShortNotesActivity.class);
            startActivity(intent);
        }
    }
}