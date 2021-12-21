package com.example.exampractice;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TopicsAdapter extends BaseAdapter {

    private int noOfTopics;

    public TopicsAdapter(int noOfTopics) {
        this.noOfTopics = noOfTopics;
    }


    @Override
    public int getCount() {
        return noOfTopics;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if(convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item_layout, parent, false);
        }
        else {
            view = convertView;
        }

        ((TextView) view.findViewById(R.id.topicNo_tv)).setText(String.valueOf(position+1));

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
        view.setBackgroundColor(color);

        return view;
    }
}
