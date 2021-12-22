package com.example.glossa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TestingMenuActivity extends AppCompatActivity implements View.OnClickListener{
    Intent i_m = new Intent(TestingMenuActivity.this, MultipleQuestionActivity.class);
    Intent i_f = new Intent(TestingMenuActivity.this, FillInActivity.class);
    String testCode = "";
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
                testCode = "1";
                i_m.putExtra("testNo", testCode);
                intent = new Intent(TestingMenuActivity.this, MultipleQuestionActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test2) :
                testCode = "2";
                i_m.putExtra("testNo", testCode);
                intent = new Intent(TestingMenuActivity.this, MultipleQuestionActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test3) :
                testCode = "3";
                i_f.putExtra("testNo", testCode);
                intent = new Intent(TestingMenuActivity.this,FillInActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;
            case(R.id.test4) :
                testCode = "4";
                i_f.putExtra("testNo", testCode);
                intent = new Intent(TestingMenuActivity.this,FillInActivity.class);
                startActivity(intent);
                TestingMenuActivity.this.finish();
                break;

        }
    }
}