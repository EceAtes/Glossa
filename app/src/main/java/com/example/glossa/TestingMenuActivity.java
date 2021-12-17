package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestingMenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_menu);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case(R.id.test1) :
                intent = new Intent(TestingMenuActivity.this,QuestionActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test2) :
                intent = new Intent(TestingMenuActivity.this,QuestionActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test3) :
                intent = new Intent(TestingMenuActivity.this,FillInActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test4) :
                intent = new Intent(TestingMenuActivity.this,FillInActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;

        }
    }
}