package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FlashcardTopics extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_topics);
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.flashcards1:
                Intent intent = new Intent(FlashcardTopics.this, Flashcards.class);
                startActivity(intent);
                break;
            case R.id.flashcards2:
                Intent intent2 = new Intent(FlashcardTopics.this, Flashcards2.class);
                startActivity(intent2);
                break;
            case R.id.goBack:
                Intent intent3 = new Intent(FlashcardTopics.this, LearningActivity.class);
                startActivity(intent3);
                break;
        }
    }
}