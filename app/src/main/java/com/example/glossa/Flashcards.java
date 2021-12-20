package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Flashcards extends AppCompatActivity {

    Button button;
    TextView definition, word;
    boolean isOdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);
        button = findViewById(R.id.button4);
        definition = findViewById(R.id.definition);
        isOdd = true;
        word = findViewById(R.id.word);

}

    public void onClick(View view) {

        if(isOdd){
            isOdd = false;
            definition.setVisibility(View.VISIBLE);
            System.out.println(isOdd);
        } else{
            isOdd = true;
            definition.setVisibility(View.INVISIBLE);
            System.out.println(isOdd);
        }



    }
}