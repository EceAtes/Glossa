package com.example.glossa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAchievementActivity extends AppCompatActivity {
    private static final String TAG = "MultipleQActivity";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    public int totalTests;
    public int completedTests;
    User user;
    private TextView solvedTest;
    private TextView percentage;
    private TextView totalinLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_achievement);
        user = register.getUser();
        System.out.println(user);
        user.setCompletedTests(3);
        solvedTest = findViewById(R.id.solvedTest);
        percentage = findViewById(R.id.percentage);
        totalinLevel = findViewById(R.id.totalinLevel);
        completedTests = user.getCompletedTests();
        setStars();

    }

    private void setStars() {

        mDatabase.child(user.getLevel()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.getKey().equals("testCount")) {
                        totalTests = Integer.parseInt(child.getValue().toString());
                        totalinLevel.setText("Total tests in the level: " + totalTests);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         solvedTest.setText("The test solved so far: " + completedTests);
         percentage.setText("The completition percentage: %" + ((100*completedTests)/(double)totalTests));
        System.out.println(user.getLevel().toString());

        }
}