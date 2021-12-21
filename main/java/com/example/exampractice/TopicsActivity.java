package com.example.exampractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class TopicsActivity extends AppCompatActivity {

    private GridView topics_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        Toolbar toolbar = findViewById(R.id.topic_toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CATEGORY");
        getSupportActionBar().setTitle(title);

        topics_grid = findViewById(R.id.topics_gridView);

        List<Topic> topicList = new ArrayList<>();



        TopicsAdapter adapter = new TopicsAdapter(6);
        topics_grid.setAdapter(adapter);

    }
}