package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShortNotesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_notes);
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.whquestions:
                Intent intent = new Intent(ShortNotesActivity.this, WHQuestion.class);
                startActivity(intent);
                break;
            case R.id.adjectives:
                Intent intent2 = new Intent(ShortNotesActivity.this, Adjectives.class);
                startActivity(intent2);
                break;
            case R.id.goBack:
                Intent intent3 = new Intent(ShortNotesActivity.this, LearningActivity.class);
                startActivity(intent3);
                break;
        }
    }
}