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

public class Flashcards2 extends AppCompatActivity implements View.OnClickListener {

    private TextView word, wCount, definition;
    private Button revealButton, changeButton;
    private List<String> wordsList;
    private List<String> definitions;
    private int wordCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards2);
        definitions = new ArrayList<>();
        wordsList = new ArrayList<>();

        word = findViewById(R.id.word);
        wCount = findViewById(R.id.word_num);
        definition = findViewById(R.id.definition);

        revealButton = findViewById(R.id.revealButton);
        revealButton.setOnClickListener(this);
        changeButton = findViewById(R.id.changeButton);
        changeButton.setOnClickListener(this);
        wordsList.add("sofa");
        wordsList.add("desk");

        definitions.add("It is used for sitting");
        definitions.add("It can be used for sitting,too");
        wordCount = 0;
        word.setText(wordsList.get(0));

    }

    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.revealButton:
                //word.setText(wordsList.get(wordCount));
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
                    Intent intent = new Intent(Flashcards2.this, LearningActivity.class);
                    startActivity(intent);
                    Flashcards2.this.finish();

                }
        }

    }
}