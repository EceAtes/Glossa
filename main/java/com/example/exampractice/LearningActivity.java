package com.example.exampractice;

import static com.example.exampractice.SplashActivity.catList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class LearningActivity extends AppCompatActivity {

    private GridView catGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Topics");

        catGrid = findViewById(R.id.catGridView);



        CatGridAdapter adapter = new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);

    }

}