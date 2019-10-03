package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity.loginid;

public class Writeframe extends AppCompatActivity {

    Button btnid;
    TextView tvdate;
    TextView tvid;
    TextView tvtitle;
    TextView tvmoney;
    TextView tvterm;
    TextView tvyoil;
    TextView tvtime;
    TextView tvgender;
    TextView tvage;
    TextView tvhak;
    TextView tvlocal;
    TextView tvjmoney;
    TextView tvjterm;
    TextView tvjtime;
    TextView tvjdolbom;
    TextView tvdetail;
    ImageButton imgonline, imgtel, imgmesg;
    TextView tvtel, tvemail;
    Button btnback;
    RatingBar ratbar;
    int count=0;
    String count1="",count_result="";

    String number="",title="",cat="",dolbom="",money="",term="",yoil="",time="",gender="",age="",hak="",detail="",local="",date="",login="",write_result="",writenumber="",star="";
    String id="";

    String[] idlist=new String[1000];
    String[] xml_array4=new String[1000];
    int pop4=0;

    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";
    private static final String SERVER_ADDRESS4 = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_writeframe );

        data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try{//////////////////////////////////////////////////////////////////////////////////////////

            URL url = new URL(SERVER_ADDRESS4 + "/id_list.php?"
            );
            url.openStream();
            getXmlData4("id_list.xml");
        } catch(Exception e) {
            Toast.makeText(Writeframe.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a4=0;
        while(xml_array4[a4]!=null)
        {
            idlist[a4] = xml_array4[a4];
            //Toast.makeText(Main2Activity.this,"xml_array4["+a4+"]="+ xml_array4[a4], Toast.LENGTH_SHORT).show();
            a4++;
        }//////////////////////////////////////////////////////////////////////////////////////////


        Intent intent = getIntent();////////////////////////////////////////////////////////////getintent
        //login = intent.getExtras().getString("id");
        number = intent.getStringExtra("number");
        title = intent.getStringExtra("title");
        cat = intent.getStringExtra("cat");
        dolbom = intent.getStringExtra("dolbom");
        money = intent.getStringExtra("money");
        term = intent.getStringExtra("term");
        yoil = intent.getStringExtra("yoil");
        time = intent.getStringExtra("time");
        gender = intent.getStringExtra("gender");
        age = intent.getStringExtra("age");
        hak = intent.getStringExtra("hak");
        detail = intent.getStringExtra("detail");
        local = intent.getStringExtra("local");
        date = intent.getStringExtra("date");
        id = intent.getStringExtra("id");
        //////////////////////////////////////////////////////////////////////////////////////////////getintent End


        tvdate =  findViewById( R.id.tvdate );
        tvid =  findViewById( R.id.tvid );
        tvtitle =  findViewById( R.id.tvtitle );
        tvmoney =  findViewById( R.id.tvmoney );
        tvterm =  findViewById( R.id.tvterm );
        tvyoil =  findViewById( R.id.tvyoil );
        tvtime =  findViewById( R.id.tvtime );
        tvgender =  findViewById( R.id.tvgender );
        tvage =  findViewById( R.id.tvage );
        tvhak =  findViewById( R.id.tvhak );
        tvlocal =  findViewById( R.id.tvlocal );
        tvjmoney =  findViewById( R.id.tvjmoney );
        tvjterm =  findViewById( R.id.tvjterm );
        tvjtime =  findViewById( R.id.tvjtime );
        tvjdolbom =  findViewById( R.id.tvjdolbom );
        tvdetail =  findViewById( R.id.tvdetail );
        imgonline =  findViewById( R.id.imgonline );
        imgtel =  findViewById( R.id.imgtel );
        imgmesg =  findViewById( R.id.imgmesg );
        tvtel =  findViewById( R.id.tvtel );
        tvemail =  findViewById( R.id.tvemail );
        btnback = findViewById(R.id.btnback);
        btnid = findViewById(R.id.btnid);
        ratbar = findViewById(R.id.ratbar);


        /////////////////////////////////////////////////////////////////////////////////////////////////////값넣기
        tvdate.setText(date);
        tvid.setText(id);
        tvtitle.setText(title);
        tvmoney.setText(money);
        tvterm.setText(term);
        tvyoil.setText(yoil);
        tvtime.setText(time);
        tvgender.setText(gender);
        tvage.setText(age);
        tvhak.setText(hak);
        tvlocal.setText(local);
        tvjmoney.setText(money);
        tvjterm.setText(term);
        tvjtime.setText(time);
        tvjdolbom.setText(dolbom);
        tvdetail.setText(detail);
        /////////////////////////////////////////////////////////////////////////////////////////////////////값넣기 End



        count++;
        count1 = Integer.toString(count);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////count 저장
        try{
            URL url = new URL(SERVER_ADDRESS + "/count_create.php?"
                    + "id=" + URLEncoder.encode(loginid,"UTF-8")
                    + "&count=" + URLEncoder.encode(count1,"UTF-8")
                    + "&number=" + URLEncoder.encode(number,"UTF-8")
            );
            url.openStream();
            String result = getXmlData("count_create.xml", "result");
            count_result = result;
            //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////
        } catch(Exception e) {
            Toast.makeText(Writeframe.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        if(count_result.equals("1"))
        {
            //Toast.makeText(Writeframe.this, "완료.", Toast.LENGTH_SHORT).show();
        }
        else
            //Toast.makeText(Writeframe.this, "실패.", Toast.LENGTH_SHORT).show();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////count 저장 End


            Toast.makeText(Writeframe.this, "loginid="+loginid, Toast.LENGTH_SHORT).show();


        btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginid.equals("1"))
                {
                    Intent intent = new Intent(getApplicationContext(), Othersintroduce.class);
                    intent.putExtra("id",idlist[0]);
                    intent.putExtra("email",idlist[1]);
                    intent.putExtra("introduce",idlist[2]);
                    intent.putExtra("phone",idlist[3]);
                    startActivityForResult(intent, 1000);
                }
                else if(loginid.equals("2"))
                {
                    Intent intent = new Intent(getApplicationContext(), Othersintroduce.class);
                    intent.putExtra("id",idlist[4]);
                    intent.putExtra("email",idlist[5]);
                    intent.putExtra("introduce",idlist[6]);
                    intent.putExtra("phone",idlist[7]);
                    startActivityForResult(intent, 1000);
                }
                else if(loginid.equals("3"))
                {
                    Intent intent = new Intent(getApplicationContext(), Othersintroduce.class);
                    intent.putExtra("id",idlist[8]);
                    intent.putExtra("email",idlist[9]);
                    intent.putExtra("introduce",idlist[10]);
                    intent.putExtra("phone",idlist[11]);
                    startActivityForResult(intent, 1000);
                }

            }
        });

        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );/////////////////////////////////////////////////////////////////////////////////////////////////////btnClick End


        ratbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star = ""+rating;


                runOnUiThread(new Runnable() {
                    public void run() {

                        writenumber = number;

                        Toast.makeText( Writeframe.this, writenumber ,Toast.LENGTH_SHORT ).show();
                        Toast.makeText( Writeframe.this, star ,Toast.LENGTH_SHORT ).show();

                        try{//////////////////////////////예약 신청 / 취소

                            URL url = new URL(SERVER_ADDRESS + "/star_create_check.php?"
                                    + "writenumber=" + URLEncoder.encode( writenumber,"UTF-8")
                            );

                            url.openStream();

                            String result = getXmlData("star_create_check.xml", "result");
                            write_result = result;
                            //Toast.makeText( Writeframe.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////////////

                        } catch(Exception e) {

                            Toast.makeText(Writeframe.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                            Log.e("Error", e.getMessage());
                        }

                        if(write_result.equals("0"))
                        {

                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/star_create.php?"
                                        + "writenumber=" + URLEncoder.encode(writenumber,"UTF-8")
                                        + "&star=" + URLEncoder.encode(star,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("star_create.xml", "result");
                                write_result = result;
                                //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////

                            } catch(Exception e) {
                                Toast.makeText(Writeframe.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/star_insert_check.php?"
                                        + "writenumber=" + URLEncoder.encode(writenumber,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("star_insert_check.xml", "result");
                                write_result = result;
                                //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();//////////////////////////////////////
                            } catch(Exception e) {
                                Toast.makeText(Writeframe.this, "인터넷 연결을 확인하세요.3", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            if(write_result.equals("1"))
                            {
                                star = "0";
                                /*
                                Toast.makeText(Writeframe.this, "신청완료.", Toast.LENGTH_SHORT).show();

                                Intent result = new Intent();
                                result.putExtra("writenumber", number);///////////////////////////////////////////intent

                                // 자신을 호출한 Activity로 데이터를 보낸다.
                                setResult(RESULT_OK, result);
                                finish();*/
                            }
                            else
                                Toast.makeText(Writeframe.this, "신청실패.", Toast.LENGTH_SHORT).show();



                        }
                        else if(write_result.equals("1"))
                        {
                            Toast.makeText(Writeframe.this, "다시 신청해주세요.", Toast.LENGTH_SHORT).show();


                        }


                    }
                });

            }
        });/////////////////////////////////////////////////////////////////////////////////////////////////////ratbar End




    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End


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





    private String getXmlData4(String filename){
        String rss = SERVER_ADDRESS4 + "/";
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
                    eventType = xpp.next();

                }
                if(eventType == XmlPullParser.TEXT) {

                    ret = xpp.getText();
                    if(ret.equals("\n"))
                    {
                        //	Toast.makeText(Personal_login.this,"ret=한줄띄움", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        //		Toast.makeText(Personal_login.this,"ret="+ret, Toast.LENGTH_SHORT).show();
                        xml_array4[pop4]= ret;
                        pop4++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData4 End



}/////////////////////////////////////////////////////////////////////////////////////////////////////////////class End