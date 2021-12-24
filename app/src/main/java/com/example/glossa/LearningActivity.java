package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class LearningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.flashcards:
                Intent intent = new Intent(LearningActivity.this, FlashcardTopics.class);
                startActivity(intent);

                break;
            case R.id.shortnotes:
                Intent intent2 = new Intent(LearningActivity.this, ShortNotesActivity.class);
                startActivity(intent2);
        }
    }
}