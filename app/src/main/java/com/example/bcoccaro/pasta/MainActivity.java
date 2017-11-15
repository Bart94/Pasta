package com.example.bcoccaro.pasta;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Database db = new Database(this);
        ArrayList<PastaObject> list = db.getAllValues();

        String[] strings = new String[list.size()];

        int j = 0;
        for(PastaObject pO: list) {
            strings[j] = pO.toString();
            j++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String p = spinner.getSelectedItem().toString();
                String name = p.substring(7, p.indexOf(","));
                String cooking = p.substring(p.indexOf(","), p.indexOf(" "));
                Log.e("cooking", cooking);

                PastaObject pasta = new PastaObject(name, cooking);

                Bundle b = new Bundle();
                b.putParcelable("pasta", pasta);

                Intent counter = new Intent(MainActivity.this, CounterView.class);
                counter.putExtras(b);
                startActivity(counter);

                //Log.v("pasta", pasta.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Add_Pasta.class);
                startActivity(i);
            }
        });
    }
}
