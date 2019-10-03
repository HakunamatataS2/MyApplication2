package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.myapplication.MainActivity.loginid;

public class write extends AppCompatActivity {

    TextView tvid;
    Spinner spin0;
    Spinner spin1; //dolbom
    Spinner spin2; //term
    Spinner spin3;
    Spinner spin4;
    EditText edittitle;
    EditText edittime;
    EditText editdetail;
    EditText editaddress;
    Button btnback;
    Button btnok;
    RadioGroup rdggender;
    RadioGroup rdgage;
    RadioGroup rdghak;
    RadioButton rdbm, rdbw, rdbgno;
    RadioButton rdb20, rdb30, rdb40, rdb50, rdbano;
    RadioButton rdbhi, rdbcol, rdbuni, rdbup, rdbhno;
    String title="",cat="",dolbom="",money="",term="",yoil="",time="",gender="",age="",hak="",detail="",local="",date="",write_result="",count="0";

    ArrayList<String> spinnerlist0 = new ArrayList<String>();
    ArrayList<String> spinnerlist1 = new ArrayList<String>();
    ArrayList<String> spinnerlist2 = new ArrayList<String>();
    ArrayList<String> spinnerlist3 = new ArrayList<String>();
    ArrayList<String> spinnerlist4 = new ArrayList<String>();

    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_write );



        data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tvid = (TextView) findViewById(R.id.tvid);
        spin0 = (Spinner)findViewById( R.id.spin0 );
        spin1 = (Spinner)findViewById( R.id.spin1 );
        spin2 = (Spinner)findViewById( R.id.spin2 );
        spin3 = (Spinner)findViewById( R.id.spin3 );
        spin4 = (Spinner)findViewById( R.id.spin4 );
        edittitle = (EditText)findViewById( R.id.edittitle );
        edittime = (EditText)findViewById( R.id.edittime );
        editdetail = (EditText)findViewById( R.id.editdetail );
        editaddress = (EditText)findViewById( R.id.editaddress );
        btnback = (Button)findViewById( R.id.btnback );
        btnok = (Button)findViewById( R.id.btnok );
        rdggender = (RadioGroup)findViewById( R.id.rdggender );
        rdgage = (RadioGroup)findViewById( R.id.rdgage );
        rdghak = (RadioGroup)findViewById( R.id.rdghak );
        rdbm = (RadioButton)findViewById( R.id.rdbm );
        rdbw = (RadioButton)findViewById( R.id.rdbw );
        rdbgno = (RadioButton)findViewById( R.id.rdbgno );
        rdb20 = (RadioButton)findViewById( R.id.rdb20 );
        rdb30 = (RadioButton)findViewById( R.id.rdb30 );
        rdb40 = (RadioButton)findViewById( R.id.rdb40 );
        rdb50 = (RadioButton)findViewById( R.id.rdb50 );
        rdbano = (RadioButton)findViewById( R.id.rdbano );
        rdbhi = (RadioButton)findViewById( R.id.rdbhi );
        rdbcol = (RadioButton)findViewById( R.id.rdbcol );
        rdbuni = (RadioButton)findViewById( R.id.rdbuni );
        rdbup = (RadioButton)findViewById( R.id.rdbup );
        rdbhno = (RadioButton)findViewById( R.id.rdbhno );

        spinnerlist0.add( "선택하세요" );
        spinnerlist0.add("요청");
        spinnerlist0.add("신청");

        spinnerlist1.add( "선택하세요" );
        spinnerlist1.add( "함께외출돌봄" );
        spinnerlist1.add( "일상가사돌봄" );
        spinnerlist1.add( "간병간호돌봄" );
        spinnerlist1.add( "목욕단정돌봄" );
        spinnerlist1.add( "산책말벗돌봄" );
        spinnerlist1.add( "24시간돌봄" );
        spinnerlist1.add( "bath" );

        spinnerlist2.add( "선택하세요" );
        spinnerlist2.add( "1일" );
        spinnerlist2.add( "1주일이하" );
        spinnerlist2.add( "1주일~2주일" );
        spinnerlist2.add( "1달" );
        spinnerlist2.add( "1달~3달" );
        spinnerlist2.add( "6달" );
        spinnerlist2.add( "6달~1년" );

        spinnerlist3.add( "선택하세요" );
        spinnerlist3.add("일요일");
        spinnerlist3.add("월요일");
        spinnerlist3.add("화요일");
        spinnerlist3.add("수요일");
        spinnerlist3.add("목요일");
        spinnerlist3.add("금요일");
        spinnerlist3.add("토요일");

        spinnerlist4.add( "선택하세요" );
        spinnerlist4.add("1시간");
        spinnerlist4.add("2시간");
        spinnerlist4.add("3시간");
        spinnerlist4.add("4시간");
        spinnerlist4.add("5시간");
        spinnerlist4.add("6시간");
        spinnerlist4.add("7시간");
        spinnerlist4.add("8시간");
        spinnerlist4.add("9시간");
        spinnerlist4.add("10시간");
        spinnerlist4.add("11시간");
        spinnerlist4.add("12시간");

        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist0 );
        spin0.setAdapter( adapter0 );

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist1 );
        spin1.setAdapter( adapter1 );

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist2 );
        spin2.setAdapter( adapter2 );

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist3 );
        spin3.setAdapter( adapter3 );

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist4 );
        spin4.setAdapter( adapter4 );


        tvid.setText(loginid);


        spin0.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = spin0.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        spin1.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //tvdol1.setText("돌봄 : " + parent.getItemAtPosition(position));
                dolbom = spin1.getSelectedItem().toString();
                //dolbom = spinnerlist1.get( position );
                /*
                if (spinnerlist1.get( position ).equals( "선택하세요" ))
                    dolbom = "0";
                else if(spinnerlist1.get( position ).equals( "함께외출돌봄" ))
                    dolbom = "1";
                else if(spinnerlist1.get( position ).equals( "일상가사돌봄" ))
                    dolbom = "2";
                else if(spinnerlist1.get( position ).equals( "간병간호돌봄" ))
                    dolbom = "3";
                else if(spinnerlist1.get( position ).equals( "목욕단정돌봄" ))
                    dolbom = "4";
                else if(spinnerlist1.get( position ).equals( "산책말벗돌봄" ))
                    dolbom = "5";
                 else if(spinnerlist1.get( position ).equals( "24시간돌봄" ))
                    dolbom = "6";
                */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        spin2.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //tvdol2.setText("기간 : " + parent.getItemAtPosition(position));
                term = spin2.getSelectedItem().toString();
                //term = spinnerlist2.get( position );
                /*if (spinnerlist2.get( position ).equals( "선택하세요" ))
                    term = "0";
                else if(spinnerlist2.get( position ).equals( "1일" ))
                    term = "1";
                else if(spinnerlist2.get( position ).equals( "1주일이하" ))
                    term = "2";
                else if(spinnerlist2.get( position ).equals( "1주일~2주일" ))
                    term = "3";
                else if(spinnerlist2.get( position ).equals( "1달" ))
                    term = "4";
                else if(spinnerlist2.get( position ).equals( "1달~3달" ))
                    term = "5";
                else if(spinnerlist2.get( position ).equals( "6달" ))
                    term = "6";
                else if(spinnerlist2.get( position ).equals( "6달~1년" ))
                    term = "7";*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yoil = spin3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = spin4.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rdggender.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.rdbm){
                    //Toast.makeText(write.this, "남자", Toast.LENGTH_SHORT).show();
                    gender = rdbm.getText().toString();
                    //gender = "남자"
                    }
                else if(i == R.id.rdbw){
                    //Toast.makeText(write.this, "여자", Toast.LENGTH_SHORT).show();
                    gender = rdbw.getText().toString();
                    //gender = "여자"
                    }
                else if(i == R.id.rdbgno){
                    gender = rdbgno.getText().toString();
                }
            }
        } );

        rdgage.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.rdb20){
                    //Toast.makeText(write.this, "20대", Toast.LENGTH_SHORT).show();
                    age = rdb20.getText().toString();
                    //age = "0";
                }
                else if(i == R.id.rdb30){
                    //Toast.makeText(write.this, "30대", Toast.LENGTH_SHORT).show();
                    age = rdb30.getText().toString();
                    //age = "1";
                }
                else if(i == R.id.rdb40){
                    //Toast.makeText(write.this, "40대", Toast.LENGTH_SHORT).show();
                    age = rdb40.getText().toString();
                    //age = "2";
                }
                else if(i == R.id.rdb50){
                    //Toast.makeText(write.this, "50대", Toast.LENGTH_SHORT).show();
                    age = rdb50.getText().toString();
                    //age = "3";
                }
                else if(i == R.id.rdbano){
                    age = rdbano.getText().toString();
                }
            }
        } );

        rdghak.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.rdbhi){
                    //Toast.makeText(write.this, "고졸", Toast.LENGTH_SHORT).show();
                    hak = rdbhi.getText().toString();
                    //hak = "0";
                }
                else if(i == R.id.rdbcol){
                    //Toast.makeText(write.this, "전문대졸", Toast.LENGTH_SHORT).show();
                    hak = rdbcol.getText().toString();
                    //hak = "1";
                }
                else if(i == R.id.rdbuni){
                    //Toast.makeText(write.this, "대졸", Toast.LENGTH_SHORT).show();
                    hak = rdbuni.getText().toString();
                    //hak = "2";
                }
                else if(i == R.id.rdbup){
                    //Toast.makeText(write.this, "대학원졸", Toast.LENGTH_SHORT).show();
                    hak = rdbup.getText().toString();
                    //hak = "3";
                }
                else if(i == R.id.rdbhno){
                    hak = rdbhno.getText().toString();
                }
            }
        } );

        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(intent, 1000);
                Toast.makeText( write.this, "닫기" ,Toast.LENGTH_SHORT ).show();*/
                finish();
            }
        } );

        btnok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    public void run() {

                        title = edittitle.getText().toString();
                        money = edittime.getText().toString();
                        detail = editdetail.getText().toString();
                        local = editaddress.getText().toString();
                        date = getTime();

                        try{//////////////////////////////예약 신청 / 취소

                            URL url = new URL(SERVER_ADDRESS + "/title_create_check.php?"
                                    + "title=" + URLEncoder.encode( title,"UTF-8")
                            );

                            url.openStream();

                            String result = getXmlData("title_create_check.xml", "result");
                            write_result = result;
                            //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////////////

                        } catch(Exception e) {

                            Toast.makeText(write.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                            Log.e("Error", e.getMessage());
                        }

                        if(write_result.equals("0"))
                        {


                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/application_create.php?"
                                        + "title=" + URLEncoder.encode(title,"UTF-8")
                                        + "&cat=" + URLEncoder.encode(cat,"UTF-8")
                                        + "&dolbom=" + URLEncoder.encode(dolbom,"UTF-8")
                                        + "&money=" + URLEncoder.encode(money,"UTF-8")
                                        + "&term=" + URLEncoder.encode(term,"UTF-8")
                                        + "&yoil=" + URLEncoder.encode(yoil,"UTF-8")
                                        + "&time=" + URLEncoder.encode(time,"UTF-8")
                                        + "&gender=" + URLEncoder.encode(gender,"UTF-8")
                                        + "&age=" + URLEncoder.encode(age,"UTF-8")
                                        + "&hak=" + URLEncoder.encode(hak,"UTF-8")
                                        + "&detail=" + URLEncoder.encode(detail,"UTF-8")
                                        + "&local=" + URLEncoder.encode(local,"UTF-8")
                                        + "&date=" + URLEncoder.encode(date,"UTF-8")
                                        + "&id=" + URLEncoder.encode(loginid,"UTF-8")
                                        + "&count=" + URLEncoder.encode(count,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("application_create.xml", "result");
                                write_result = result;
                                //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////

                            } catch(Exception e) {
                                Toast.makeText(write.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/application_insert_check.php?"
                                        + "title=" + URLEncoder.encode(title,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("application_insert_check.xml", "result");
                                write_result = result;
                                //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();//////////////////////////////////////
                            } catch(Exception e) {
                                Toast.makeText(write.this, "인터넷 연결을 확인하세요.3", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            if(write_result.equals("1"))
                            {
                                tvid.setText("");
                                edittitle.setText("");
                                edittime.setText("");
                                editdetail.setText("");
                                editaddress.setText("");
                                spin0.setSelection( 0 );
                                spin1.setSelection( 0 ); //dolbom
                                spin2.setSelection( 0 ); //term
                                spin3.setSelection( 0 );
                                spin4.setSelection( 0 );
                                rdggender.clearCheck();
                                rdgage.clearCheck();
                                rdghak.clearCheck();

                                Toast.makeText(write.this, "신청완료.", Toast.LENGTH_SHORT).show();

                                Intent result = new Intent();
                                result.putExtra("title", edittitle.getText().toString());///////////////////////////////////////////intent

                                // 자신을 호출한 Activity로 데이터를 보낸다.
                                setResult(RESULT_OK, result);
                                finish();
                            }
                            else
                                Toast.makeText(write.this, "신청실패.", Toast.LENGTH_SHORT).show();



                        }
                        else if(write_result.equals("1"))
                        {
                            Toast.makeText(write.this, "다시 신청해주세요.", Toast.LENGTH_SHORT).show();


                        }


                    }
                });
            }
        } );

    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }/////////////////////////////////////////////////////////////////////////////////////getTime End





    private String getXmlData(String filename, String str){
        String rss = SERVER_ADDRESS + "/";
        String ret = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            URL server = new URL(rss + filename);
            InputStream is = server.openStream();
            xpp.setInput(is, "UTF-8");

            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_TAG) {
                    if(xpp.getName().equals(str)) {
                        ret = xpp.nextText();
                    }
                }
                eventType = xpp.next();
            }
        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }//////////////////////////////////////////////////////////////////////////////////////////getXmlData End



}
