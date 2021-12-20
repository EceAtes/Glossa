package com.example.exampractice;
import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }

    public void onClick(View view) {
        Button accountButton = findViewById(R.id.profileButton);
        Button homeButton = findViewById(R.id.homeButton);


        if (view.getId() == R.id.profileButton) {
            Intent accountIntent = new Intent(this, ProfileActivity.class);
            startActivity(accountIntent);
        }

        if (view.getId() == R.id.learningButton) {
            Intent learningIntent = new Intent(this, LearningActivity.class);
            startActivity(learningIntent);
        }

    }
}