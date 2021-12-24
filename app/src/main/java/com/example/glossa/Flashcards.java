package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Flashcards extends AppCompatActivity implements View.OnClickListener {

    private TextView word, wCount, definition;
    private Button revealButton, changeButton;
    private List<String> wordsList;
    private List<String> definitions;
    private int wordCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);
        definitions = new ArrayList<>();
        wordsList = new ArrayList<>();

        word = findViewById(R.id.word);
        wCount = findViewById(R.id.word_num);
        definition = findViewById(R.id.definition);

        revealButton = findViewById(R.id.revealButton);
        revealButton.setOnClickListener(this);
        changeButton = findViewById(R.id.changeButton);
        changeButton.setOnClickListener(this);
        wordsList.add("Potato");
        wordsList.add("apple");

        definitions.add("vegetable");
        definitions.add("fruit");
        wordCount = 0;
        word.setText(wordsList.get(0));


    }

    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.revealButton:
                definition.setText(definitions.get(wordCount));
                definition.setVisibility(View.VISIBLE);

                break;
            case R.id.changeButton:
                wordCount++;
                if (wordCount < wordsList.size()) {
                    word.setText(wordsList.get(wordCount));
                    definition.setVisibility(View.INVISIBLE);
                    wCount.setText(String.valueOf(wordCount+1) + "/" + String.valueOf(wordsList.size()));

                }
                else if( wordCount == wordsList.size()){
                    wordCount = 0;
                    Intent intent = new Intent(Flashcards.this, LearningActivity.class);
                    startActivity(intent);
                    Flashcards.this.finish();

                }
        }
    }
}