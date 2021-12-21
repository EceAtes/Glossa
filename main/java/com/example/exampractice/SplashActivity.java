package com.example.exampractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static List<Topic> catList = new ArrayList<>();
    private TextView welcomeMessage;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        welcomeMessage = findViewById(R.id.welcome_message);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.pacifico);
        welcomeMessage.setTypeface(typeface);

        Animation messageAnimation = AnimationUtils.loadAnimation(this,R.anim.myanimation);
        welcomeMessage.setAnimation(messageAnimation);

        firestore = FirebaseFirestore.getInstance();

        new Thread() {

            @Override
            public void run() {

                //sleep(3000);

                loadData();



            }

        }.start();
    }

    private void loadData() {
        catList.clear();

        firestore.collection("LEARNING").document("CATEGORIES")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    if (doc.exists()) {
                        long count = (long) doc.get("COUNT");

                        for (int i = 1; i <= count; i++) {
                            String catName = doc.getString("CATEGORY" + String.valueOf(i));
                            catList.add(new Topic(catName));
                        }
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                    else  {
                        Toast.makeText(SplashActivity.this,"No Category Document Exists",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                else {
                    Toast.makeText(SplashActivity.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}