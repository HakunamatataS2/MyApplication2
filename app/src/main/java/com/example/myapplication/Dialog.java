package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Dialog extends AppCompatActivity {

    Spinner spin1;
    Spinner spin2;
    Spinner spin3;
    static String diasi="";
    String kun="",dong="";
    Button btnok, btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        spin1 = (Spinner)findViewById(R.id.spin1);
        spin2 = (Spinner)findViewById(R.id.spin2);
        spin3 = (Spinner)findViewById(R.id.spin3);
        btnok = (Button)findViewById(R.id.btnok);
        btncancel = (Button)findViewById(R.id.btncancel);


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tv.setText("position : " + position + parent.getItemAtPosition(position));

                diasi = spin1.getSelectedItem().toString();
                //Toast.makeText(Dialog.this, si, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tv.setText("position : " + position + parent.getItemAtPosition(position));

                kun = spin2.getSelectedItem().toString();
                //Toast.makeText(Dialog.this, kun, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tv.setText("position : " + position + parent.getItemAtPosition(position));

                dong = spin3.getSelectedItem().toString();
                //Toast.makeText(Dialog.this, dong, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

        //sum = si + " " + kun + " " + dong;

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dialog.this, Localmenu.class);
                startActivityForResult(intent, 1000);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dialog.this, Localmenu.class);
                startActivityForResult(intent, 1000);
            }
        });



    }///////////////////////////////////////////////////////////////////////////////////////////onCreate End
}
