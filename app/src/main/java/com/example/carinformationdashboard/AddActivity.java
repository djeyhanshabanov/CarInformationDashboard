package com.example.carinformationdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText marka_input, modl_input, regnom_input, danak_input, zastrahovka_input, pregled_input, vinetka_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        marka_input = findViewById(R.id.marka_input);
        modl_input = findViewById(R.id.modl_input);
        regnom_input = findViewById(R.id.regnom_input);
        danak_input = findViewById(R.id.danak_input);
        zastrahovka_input = findViewById(R.id.zastrahovka_input);
        pregled_input = findViewById(R.id.pregled_input);
        vinetka_input = findViewById(R.id.vinetka_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addCar(marka_input.getText().toString().trim(),
                        modl_input.getText().toString().trim(),
                        regnom_input.getText().toString().trim(),
                        Integer.parseInt(danak_input.getText().toString().trim()),
                        Integer.parseInt(zastrahovka_input.getText().toString().trim()),
                        Integer.parseInt(pregled_input.getText().toString().trim()),
                        Integer.parseInt(vinetka_input.getText().toString().trim()));
            }
        });
    }
}