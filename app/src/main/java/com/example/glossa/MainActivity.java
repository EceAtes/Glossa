package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }

        }.start();
    }
}