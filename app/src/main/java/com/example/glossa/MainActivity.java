package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
    }

    public void buttonListener(View view){
//        TextView txtName = findViewById(R.id.nameTxt);
//        TextView txtSurname = findViewById(R.id.surnameTxt);
//        TextView txtEmail = findViewById(R.id.emailTxt);
//
//        EditText edtName = findViewById(R.id.name);
//        EditText edtSurname = findViewById(R.id.surname);
//        EditText edtEmail = findViewById(R.id.email);
//
//        txtName.setText("Name: " + edtName.getText().toString());
//        txtSurname.setText("Surname: " + edtSurname.getText().toString());
//        txtEmail.setText("Email: " + edtEmail.getText().toString());

        Button button = findViewById(R.id.button);
        Intent intent = new Intent(MainActivity.this, RegisterSuccessful.class);
        startActivity(intent);

    }


}