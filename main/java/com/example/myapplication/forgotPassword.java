package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class forgotPassword extends AppCompatActivity {
    private EditText username;
    private Button resetButton;
    FirebaseAuth auth2;
    private EditText password;
    private EditText newPassword;
    private MainActivity ma;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        username = findViewById(R.id.usernameForgotPassword);
        resetButton = findViewById(R.id.forgotPasswordButton);
        password = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        auth2 = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }


    private void resetPassword(){
        String userName = username.getText().toString().trim();
        String userPassword = password.getText().toString();
        String newPasswordString = newPassword.getText().toString();

        if(userPassword.isEmpty()){
            password.setError("Password field should not be empty");
            password.requestFocus();
            return;
        }

        if(userPassword.length() < 6){
            password.setError("The password should be at least 6 characters");
            password.requestFocus();
            return;
        }
        user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(userName,userPassword);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("value","User re-authenticated");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updatePassword(newPasswordString).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgotPassword.this,"The password is successfully changed",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(forgotPassword.this,"Try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        /*auth2.sendPasswordResetEmail(emailString).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotPassword.this,"Please check your email to reset your password",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(forgotPassword.this,"Try again",Toast.LENGTH_LONG).show();
                }
            }
        });
         */
    }
}