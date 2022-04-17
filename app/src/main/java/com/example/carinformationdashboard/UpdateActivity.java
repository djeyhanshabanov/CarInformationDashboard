package com.example.carinformationdashboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText marka_input, modl_input, regnom_input, danak_input, zastrahovka_input, pregled_input, vinetka_input;
    Button update_button, delete_button;
    String id, marka, modl, regnom, danak, zastrahovka, pregled, vinetka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        marka_input = findViewById(R.id.marka_input2);
        modl_input = findViewById(R.id.modl_input2);
        regnom_input = findViewById(R.id.regnom_input2);
        danak_input = findViewById(R.id.danak_input2);
        zastrahovka_input = findViewById(R.id.zastrahovka_input2);
        pregled_input = findViewById(R.id.pregled_input2);
        vinetka_input = findViewById(R.id.vinetka_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(marka);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                marka = marka_input.getText().toString().trim();
                modl = modl_input.getText().toString().trim();
                regnom = regnom_input.getText().toString().trim();
                danak = danak_input.getText().toString().trim();
                zastrahovka = zastrahovka_input.getText().toString().trim();
                pregled = pregled_input.getText().toString().trim();
                vinetka = vinetka_input.getText().toString().trim();
                myDB.updateData(id, marka, modl, regnom, danak, zastrahovka, pregled, vinetka);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("marka") &&
                getIntent().hasExtra("modl") &&
                getIntent().hasExtra("regnom") &&
                getIntent().hasExtra("danak") &&
                getIntent().hasExtra("zastrahovka") &&
                getIntent().hasExtra("pregled") &&
                getIntent().hasExtra("vinetka")) {
            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            marka = getIntent().getStringExtra("marka");
            modl = getIntent().getStringExtra("modl");
            regnom = getIntent().getStringExtra("regnom");
            danak = getIntent().getStringExtra("danak");
            zastrahovka = getIntent().getStringExtra("zastrahovka");
            pregled = getIntent().getStringExtra("pregled");
            vinetka = getIntent().getStringExtra("vinetka");

            //Setting Intent data
            marka_input.setText(marka);
            modl_input.setText(modl);
            regnom_input.setText(regnom);
            danak_input.setText(danak);
            zastrahovka_input.setText(zastrahovka);
            pregled_input.setText(pregled);
            vinetka_input.setText(vinetka);
            Log.d("jeko", marka+" "+modl+" "+regnom+" "+danak+" "+zastrahovka+" "+pregled+" "+vinetka);
        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + marka + " " + regnom + " ?");
        builder.setMessage("Are you sure you want to delete " + marka + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}