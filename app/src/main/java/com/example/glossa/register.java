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
    private static User user;
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
                    Intent intent2 = new Intent(register.this,MultipleQuestionActivity.class);
                    String testCode = "Proficiency";
                    intent2.putExtra("testNo", testCode);
                    startActivity(intent2);
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(register.this, MultipleQuestionActivity.class);
                User user = new User(name.getText().toString(),email.getText().toString(),username.getText().toString(),password.getText().toString());
                String testCode = "Proficiency";

                intent3.putExtra("testNo", testCode);
                intent3.putExtra("email", email.getText().toString());
                intent3.putExtra("username", username.getText().toString());
                intent3.putExtra("password", password.getText().toString());

                startActivity(intent3);
            }
        });
        user = new User(name.getText().toString(),email.getText().toString(),username.getText().toString(),password.getText().toString());
    }

    public static User getUser(){
        return user;
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
    public boolean validateName(){
        String nameString = name.getText().toString();
        if(nameString.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validateEmail() {
        String emailString = email.getText().toString();
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

    public boolean validateUserName(){
        String usernameString = username.getText().toString();
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
        /*User user1 = new User(nameString,emailString,usernameString,passwordString);
        database = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app");
        myRef = database.getReference("users");
        myRef.child(usernameString).push().setValue(user1);
         */


        if(!validateEmail())
            return false;
        if(!validatePassword())
            return false;
        if(!validateName())
            return false;
        if(!validateUserName())
            return false;




        /* Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
*/
        p1.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailString,passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(nameString,emailString,usernameString, passwordString);
                            FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference("user")
                                    .child(usernameString)//FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user);
                            Toast.makeText(register.this, "Register SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                        }
                        else if(FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference("user")
                                .child(usernameString) == null){
                            Toast.makeText(register.this, "Register is not completed!", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.GONE);
                        }
                        /*.addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(register.this,"The user has not registered!",Toast.LENGTH_LONG).show();
                                    }
                                    p1.setVisibility(View.GONE);
                                }
                            });
                        }
                        */
                        else{
                            Toast.makeText(register.this,"Register is not completed!",Toast.LENGTH_LONG).show();
                            p1.setVisibility(View.GONE);
                        }
                    }
                });

        return true;
    }
}