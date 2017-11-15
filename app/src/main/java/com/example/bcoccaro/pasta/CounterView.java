package com.example.bcoccaro.pasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CounterView extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_view);

        TextView text = findViewById(R.id.textView4);

        Intent i = getIntent();
        PastaObject pasta = i.getParcelableExtra("pasta");

        text.setText(pasta.toString());
    }
}
