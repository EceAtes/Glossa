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

        mDatabase.child("Questions").child("Question1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    if(children.getKey().equals("Question")){
                        String q = children.getValue().toString();
                        System.out.println(q);
                    }
                }

//                Log.v("keyResult", "   " + thumbUrl);
                // Log.v("key2","   " + thumbUrl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }
}
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                String question = dataSnapshot.getValue(String.class);
//                System.out.println(question);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//        };
//        mDatabase.addValueEventListener(postListener);


