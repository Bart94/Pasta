package com.example.bcoccaro.pasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Add_Pasta extends AppCompatActivity{
    Database db = new Database(this);
    PastaObject pasta;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cooking, name;

                final EditText pasta_name = findViewById(R.id.pastaText);
                name = pasta_name.getText().toString();
                final EditText cooking_time = findViewById(R.id.cookingText);
                cooking = cooking_time.getText().toString();

                pasta = new PastaObject(name, cooking);
                db.insertPasta(getApplicationContext(), pasta);
                pasta_name.setText("");
                cooking_time.setText("");
            }
        });

        Button button1 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<PastaObject> list = db.getAllValues();

                String[] strings = new String[list.size()];

                int j = 0;
                for (PastaObject pO : list) {
                    strings[j] = pO.toString();
                    j++;
                }

                Bundle b = new Bundle();
                b.putStringArray("list", strings);
                Intent intent = new Intent(Add_Pasta.this, ActivityView.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
