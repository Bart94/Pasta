package com.example.bcoccaro.pasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityView extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent i = getIntent();
        String[] strings = i.getStringArrayExtra("list");

        ListView view = findViewById(R.id.list_item);
        ArrayAdapter<String> items = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        view.setAdapter(items);
    }
}
