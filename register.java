package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {
    private Button registerEnter;
    private EditText name;
    private EditText surname;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.registerName);
        surname=findViewById(R.id.registerSurname);
        username=findViewById(R.id.registerUsername);
        password= findViewById(R.id.registerPassword);
        registerEnter = findViewById(R.id.button2);

        registerEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent2 = new Intent(register.this,proficiencyExam.class);
               startActivity(intent2);
            }
        });

    }
}