package com.example.glossa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    private static User user ;
    private Button registerEnter;
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText password;
    private ProgressBar p1;
    private FirebaseAuth mAuth;
    private TextView textView;

    FirebaseDatabase database;
    DatabaseReference myRef;
    private static final String TAG = register.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.registerName);
        email=  findViewById(R.id.emailAddress);
        username= findViewById(R.id.registerUsername);
        password= findViewById(R.id.registerPassword);
        registerEnter = findViewById(R.id.button2);
        p1 = findViewById(R.id.progressBar2);
        textView = findViewById(R.id.Account);
        mAuth = FirebaseAuth.getInstance();

        registerEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                if(registerUser()){
                    Intent intent2 = new Intent(register.this,RegisterSuccessful.class);
                    String testCode = "Proficiency";
                    intent2.putExtra("testNo", testCode);
                    startActivity(intent2);
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(register.this, Login.class);
                startActivity(intent3);

            }
        });

    }

    public static User getUserFromRegister(){
        return user;
    }

    public boolean validatePassword( String value){

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
    public boolean validateName( String nameString){
        if(nameString.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validateEmail( String emailString) {
        if (emailString.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("Please provide a valid email address");
            email.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validateUserName( String usernameString){
        if(usernameString.isEmpty()){
            username.setError("User name is required");
            username.requestFocus();
            return false;
        }
        return true;
    }

    public boolean registerUser() {

        String nameString = name.getText().toString();
        String emailString = email.getText().toString();
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        // if the username is already used give an error

        if(!validateEmail(emailString))
            return false;
        if(!validatePassword(passwordString))
            return false;
        if(!validateName(nameString))
            return false;
        if(!validateUserName( usernameString))
            return false;

       user = new User(nameString,emailString,usernameString, passwordString);

        FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference("user")
                .child(usernameString)
                .setValue(user);


        if(FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference("user")
                                .child(usernameString) == null){ }
        return true;
    }
}