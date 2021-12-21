package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button enter;
    private Button register;
    private EditText username;
    private EditText password;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.button);
        register = findViewById(R.id.registerButton);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.passwordEditText);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*firebaseDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app");
                databaseReference = firebaseDatabase.getReference("users");
                databaseReference.push().setValue("change in the data");
                 */
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // compare the data with the database to find a match
                loginUser();
            }
        });
    }
    public boolean validateUserName(){
        String usernameString = username.getText().toString();
        if(usernameString.isEmpty()){
            username.setError("User name is required");
            username.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validatePassword(){
        String value = password.getText().toString();

        if(value.isEmpty()){
            password.setError("Password field should not be empty");
            password.requestFocus();
            return false;
        }

        if(value.length() < 6){
            password.setError("The password should be at least 6 characters");
            password.requestFocus();
            return false;
        }
        return true;
    }
    public void loginUser(){
        if(validateUserName() && validatePassword()){
            isUser(username.getText().toString(),password.getText().toString());
            return;
        }
        else{
            Toast.makeText(MainActivity.this,"Invalid password or username",Toast.LENGTH_LONG).show();
        }
    }

    public void isUser(String userName, String userPassword) {

        firebaseDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference("user");

        Query checkUser = databaseReference.orderByChild("username").equalTo(userName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);

                    String  passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);
                    String  usernameFromDB = snapshot.child(userName).child("username").getValue(String.class);

                    if(passwordFromDB.equals(userPassword)){
                        Intent intent = new Intent(MainActivity.this,menu.class);
                        intent.putExtra("username",usernameFromDB);
                        startActivity(intent);
                    }
                    else{
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No such user exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}