package com.example.exampractice;

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

        List<Topic> catList = new ArrayList<>();

        catList.add(new Topic("History"));
        catList.add(new Topic("Jobs"));
        catList.add(new Topic("School"));
        catList.add(new Topic("Movies"));

        System.out.println("entered learning");

        CatGridAdapter adapter = new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);

    }

}