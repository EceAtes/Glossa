package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TestingMenuActivity extends AppCompatActivity implements View.OnClickListener{

    String testCode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_menu);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(TestingMenuActivity.this, MultipleQuestionActivity.class);
        switch(v.getId()){
            case(R.id.test1) :
                testCode = "Test1";
                intent.putExtra("testNo", testCode);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test2) :
                testCode = "Test2";
                intent.putExtra("testNo", testCode);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test3) :
                intent = new Intent(TestingMenuActivity.this,FillInActivity.class);
                testCode = "Test3";
                intent.putExtra("testNo", testCode);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test4) :
                intent = new Intent(TestingMenuActivity.this,FillInActivity.class);
                testCode = "Test4";
                intent.putExtra("testNo", testCode);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
        }
    }
}