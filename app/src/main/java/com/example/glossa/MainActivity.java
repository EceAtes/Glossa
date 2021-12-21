package com.example.glossa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView welcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button button = findViewById(R.id.button);

        welcomeMessage = findViewById(R.id.welcome_message);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.pacifico);
        welcomeMessage.setTypeface(typeface);

        Animation messageAnimation = AnimationUtils.loadAnimation(this,R.anim.myanimation);
        welcomeMessage.setAnimation(messageAnimation);

        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, ProficiencyExamActivity.class);
                startActivity(intent);
//                SplashActivity.this.finish();

            }

        }.start();
    }

//    public void buttonListener(View view){
//
//
//        EditText edtNick = findViewById(R.id.nickname);
//        EditText edtName = findViewById(R.id.name);
//        EditText edtSurname = findViewById(R.id.surname);
//        EditText edtEmail = findViewById(R.id.email);
//        Button button = findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nick = edtNick.getText().toString();
//                String name = edtName.getText().toString();
//                String surname = edtSurname.getText().toString();
//                String email = edtEmail.getText().toString();
//
//                Map<String,Object> user = new HashMap<>();
//                user.put("email",email);
//                user.put("name",name);
//                user.put("surname",surname);
//                user.put("nickname",nick);
//
//                db.collection("GlossaTest").add(user)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        System.out.println("Success!");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println("Fail :(");;
//                    }
//                });
//
//            }
//        });
//        Intent intent = new Intent(MainActivity.this, TestingMenuActivity.class);
//        startActivity(intent);
//
//    }


}