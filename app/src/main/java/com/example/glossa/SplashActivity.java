package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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

                Intent intent = new Intent(SplashActivity.this, Login.class);
                startActivity(intent);

            }

        }.start();
    }
}