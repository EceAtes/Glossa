package com.example.glossa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseTest extends AppCompatActivity {
    private static final String TAG = "FirebaseTest";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://glossa-4dc57-default-rtdb.europe-west1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        mDatabase.child("A1-1").child("Test1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = "", A = "", B = "", C = "", D = "", answer = "";
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : children.getChildren()) {
                        if (child.getKey().equals("Question")) {
                            question = child.getValue().toString();
                        }
                        else if (child.getKey().equals("answer")) {
                            answer = child.getValue().toString();
                        }
                        else if (child.getKey().equals("A")) {
                            A = child.getValue().toString();
                        }
                        else if (child.getKey().equals("B")) {
                            B = child.getValue().toString();
                        }
                        else if (child.getKey().equals("C")) {
                            C = child.getValue().toString();
                        }
                        else if (child.getKey().equals("D")) {
                            D = child.getValue().toString();
                        }
                    }
                    System.out.println(new MultipleChoiceQuestion(question, A, B, C,D,answer));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}



