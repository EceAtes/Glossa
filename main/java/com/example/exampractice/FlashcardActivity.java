package com.example.exampractice;

import static com.example.exampractice.TopicsActivity.category_id;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView word, wCount, definition;
    private Button revealButton, changeButton;
    private List<Word> wordsList;
    private int wordCount;
    private FirebaseFirestore firestore;
    private int setNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        word = findViewById(R.id.word);
        wCount = findViewById(R.id.word_num);
        definition = findViewById(R.id.definition);

        revealButton = findViewById(R.id.revealButton);
        revealButton.setOnClickListener(this);
        changeButton = findViewById(R.id.changeButton);
        changeButton.setOnClickListener(this);

        setNo = getIntent().getIntExtra("SETNO",1);

        firestore = FirebaseFirestore.getInstance();


        getWordsList();

    }

    private void getWordsList() {
        wordsList = new ArrayList<>();

        firestore.collection("LEARNING").document(String.valueOf(category_id))
                .collection("SET" + String.valueOf(setNo))
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot words = task.getResult();

                    wordsList.add(new Word("potato","vegetable"));
                    wordsList.add(new Word("apple","fruit"));
                    wordsList.add(new Word("cucumber","vegetable"));

                    for (QueryDocumentSnapshot doc: words) {
                        System.out.println("b");
                        wordsList.add(new Word(doc.getString("NAME"),
                                doc.getString("DEFINITION")));

                    }
                    setWord();
                    setNo++;
                } else {
                    Toast.makeText(FlashcardActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setWord() {
        word.setText(wordsList.get(0).getWord());
        definition.setText(wordsList.get(0).getDefinition());
        wCount.setText(String.valueOf(1) + "/" + String.valueOf(wordsList.size()));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.revealButton:
                definition.setVisibility(View.VISIBLE);

                break;
            case R.id.changeButton:

                if (wordCount < wordsList.size() - 1) {
                    definition.setVisibility(View.INVISIBLE);
                    wordCount++;
                    playAnim(word, 0,0);
                    playAnim(changeButton, 0,1);
                    playAnim(definition,0,2);
                    wCount.setText(String.valueOf(wordCount+1) + "/" + String.valueOf(wordsList.size()));

                }
                else {
                    wordCount = 0;
                    Intent intent = new Intent(FlashcardActivity.this, TopicsActivity.class);
                    startActivity(intent);
                    FlashcardActivity.this.finish();

                }

        }
    }

    private void playAnim(View view, final int value, int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0) {

                    if (viewNum == 0) {
                        ((TextView) view).setText(wordsList.get(wordCount).getWord());
                    }
                    if (viewNum == 1 ) {

                    }
                    if (viewNum == 2) {
                        ((TextView) view).setText(wordsList.get(wordCount).getDefinition());
                    }
                    playAnim(view,1,viewNum);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}