package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Match1 extends AppCompatActivity {

    final Context context = this;
    EditText edittitle;
    RadioGroup rdggender;
    RadioButton rdbm, rdbw, rdbgno;
    EditText editage;
    Spinner spindolbom;
    Spinner spinhak;
    Spinner spinyoil;
    Button btnresult;
    Button btnback;
    Spinner spin1;
    Spinner spin2;
    Spinner spin3;
    TextView tvlocal;
    RadioGroup rdgage;
    RadioButton rdb20, rdb30, rdb40, rdb50, rdbano;

    String dong="",kun="", dolbom="",hak="",yoil="",title="";
    static String match1gender="",match1si="",match1age="";


    ArrayList<String> spinnerlist1 = new ArrayList<String>();
    ArrayList<String> spinnerlist2 = new ArrayList<String>();
    ArrayList<String> spinnerlist3 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match1);


        edittitle = (EditText)findViewById(R.id.edittitle);
        rdggender = (RadioGroup)findViewById(R.id.rdggender);
        rdbm = (RadioButton)findViewById(R.id.rdbm);
        rdbw = (RadioButton)findViewById(R.id.rdbw);
        rdbgno = (RadioButton)findViewById(R.id.rdbgno);
        spindolbom = (Spinner)findViewById(R.id.spindolbom);
        spinhak = (Spinner)findViewById(R.id.spinhak);
        spinyoil = (Spinner)findViewById(R.id.spinyoil);
        btnresult = (Button)findViewById(R.id.btnresult);
        tvlocal = (TextView)findViewById(R.id.tvlocal);
        btnback = (Button)findViewById(R.id.btnback);
        spin1 = (Spinner)findViewById(R.id.spin1);
        spin2 = (Spinner)findViewById(R.id.spin2);
        spin3 = (Spinner)findViewById(R.id.spin3);
        rdgage = (RadioGroup)findViewById( R.id.rdgage );
        rdb20 = (RadioButton)findViewById( R.id.rdb20 );
        rdb30 = (RadioButton)findViewById( R.id.rdb30 );
        rdb40 = (RadioButton)findViewById( R.id.rdb40 );
        rdb50 = (RadioButton)findViewById( R.id.rdb50 );
        rdbano = (RadioButton)findViewById( R.id.rdbano );



        rdgage.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.rdb20){
                    //Toast.makeText(write.this, "20대", Toast.LENGTH_SHORT).show();
                    match1age = rdb20.getText().toString();
                    //age = "0";
                }
                else if(i == R.id.rdb30){
                    //Toast.makeText(write.this, "30대", Toast.LENGTH_SHORT).show();
                    match1age = rdb30.getText().toString();
                    //age = "1";
                }
                else if(i == R.id.rdb40){
                    //Toast.makeText(write.this, "40대", Toast.LENGTH_SHORT).show();
                    match1age = rdb40.getText().toString();
                    //age = "2";
                }
                else if(i == R.id.rdb50){
                    //Toast.makeText(write.this, "50대", Toast.LENGTH_SHORT).show();
                    match1age = rdb50.getText().toString();
                    //age = "3";
                }
                else if(i == R.id.rdbano){
                    match1age = rdbano.getText().toString();
                }
            }
        } );



        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tv.setText("position : " + position + parent.getItemAtPosition(position));

                match1si = spin1.getSelectedItem().toString();
                //Toast.makeText(Match1.this, si, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tv.setText("position : " + position + parent.getItemAtPosition(position));

                kun = spin2.getSelectedItem().toString();
                //Toast.makeText(Match1.this, kun, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tv.setText("position : " + position + parent.getItemAtPosition(position));

                dong = spin3.getSelectedItem().toString();
                //Toast.makeText(Match1.this, dong, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

        title = edittitle.getText().toString() ;

        spinnerlist1.add( "선택하세요" );
        spinnerlist1.add( "함께외출돌봄" );
        spinnerlist1.add( "일상가사돌봄" );
        spinnerlist1.add( "간병간호돌봄" );
        spinnerlist1.add( "목욕단정돌봄" );
        spinnerlist1.add( "산책말벗돌봄" );
        spinnerlist1.add( "24시간돌봄" );

        spinnerlist2.add( "선택하세요" );
        spinnerlist2.add( "고졸" );
        spinnerlist2.add( "전문대졸" );
        spinnerlist2.add( "대졸" );
        spinnerlist2.add( "대학원졸" );

        spinnerlist3.add( "선택하세요" );
        spinnerlist3.add( "일요일" );
        spinnerlist3.add( "월요일" );
        spinnerlist3.add( "화요일" );
        spinnerlist3.add( "수요일" );
        spinnerlist3.add( "목요일" );
        spinnerlist3.add( "금요일" );
        spinnerlist3.add( "토요일" );





        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist1 );
        spindolbom.setAdapter( adapter1 );

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist2 );
        spinhak.setAdapter( adapter2 );

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist3 );
        spinyoil.setAdapter( adapter3 );


        spindolbom.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dolbom = spindolbom.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );//////////////////////////////////////////////////////////////////////////////////////////////spinner dolbom

        spinhak.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hak = spinhak.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );//////////////////////////////////////////////////////////////////////////////////////////////spinner hak

        spinyoil.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yoil = spinyoil.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );//////////////////////////////////////////////////////////////////////////////////////////////spinner yoil

        rdggender.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.rdbm){
                    match1gender = rdbm.getText().toString();
                }
                else if(i == R.id.rdbw){
                    match1gender = rdbw.getText().toString();
                }
                else if(i == R.id.rdbgno){
                    match1gender = rdbgno.getText().toString();
                }
            }
        } );//////////////////////////////////////////////////////////////////////////////////////////////radiogroup gender


        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Match1.this, Matchmenu.class);
                startActivityForResult(intent, 1000);//////////////////////////////////////////////////////////////////intent local gender age

            }
        });



        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Matchmenu.class);
                startActivityForResult(intent, 1000);
            }
        } );


    }//////////////////////////////////////////////////////////////////////////////////////onCreate End
}
