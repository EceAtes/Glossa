package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterSuccessful extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_successful);
    }
    public void buttonListener(View view) {

        Intent intent = new Intent(RegisterSuccessful.this, MultipleQuestionActivity.class);
        String testCode = "Proficiency";
        intent.putExtra("testNo", testCode);
        startActivity(intent);
    }
}
