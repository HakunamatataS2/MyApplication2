package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Othersintroduce extends AppCompatActivity {

    Button btnchat;
    Button btnback;
    Button btncall;
    Button btnsms;
    TextView tvid;
    TextView tvidprofile;
    TextView tvemail;
    TextView tvintroduce;
    ListView simpleList;
    ListView simpleList2;
    String id="",email="",introduce="",phone="";

    String[] xml_array=new String[1000] ;
    int pop=0;
    String[] xml_array3=new String[1000] ;
    int pop3=0;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items3 = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othersintroduce);


        Intent intent = getIntent();////////////////////////////////////////////////////////////getintent
        id = intent.getStringExtra("id");
        email = intent.getStringExtra("email");
        introduce = intent.getStringExtra("introduce");
        phone = intent.getStringExtra("phone");
        ////////////////////////////////////////////////////////////////////////////////////////getintent End


        btnchat = (Button) findViewById(R.id.btnchat);
        btnback = (Button) findViewById(R.id.btnback);
        btncall = (Button) findViewById(R.id.btncall);
        btnsms = (Button) findViewById(R.id.btnsms);
        tvid = (TextView) findViewById(R.id.tvid);
        tvidprofile = (TextView) findViewById(R.id.tvidprofile);
        tvemail = (TextView) findViewById(R.id.tvemail);
        tvintroduce = (TextView) findViewById(R.id.tvintroduce);
        simpleList = findViewById(R.id.Listview);
        simpleList2 = findViewById(R.id.Listview2);


        tvidprofile.setText(id+"님의 프로필");
        tvid.setText(id);
        tvemail.setText(email);
        tvintroduce.setText(introduce);


        /////////////////////////////////////////////////////////////////////////////////////////////Tabhost
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("등록한 게시물") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("등록한 게시물") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("등록한 댓글") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("등록한 댓글") ;
        tabHost1.addTab(ts2) ;
        /////////////////////////////////////////////////////////////////////////////////////////////Tabhost End


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items3) ;
        simpleList2.setAdapter(adapter3);
        simpleList2.setBackgroundColor( Color.BLACK );





            try {

                URL url = new URL(SERVER_ADDRESS + "/other_list.php?"
                        + "id=" + URLEncoder.encode(id, "UTF-8")
                );
                url.openStream();
                getXmlData2("other_list.xml");

            } catch (Exception e) {
                Toast.makeText(Othersintroduce.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                Log.e("Error", e.getMessage());
            }
            int b = 0;
            while (xml_array[b] != null) {
                b++;
            }
            for (int t = 1; t < b; t += 15) {
                    items.add(xml_array[t]);
                    adapter.notifyDataSetChanged();
            }






        try {

            URL url = new URL(SERVER_ADDRESS + "/other_comment_list.php?"
                    + "id=" + URLEncoder.encode(id, "UTF-8")
            );
            url.openStream();
            getXmlData3("other_comment_list.xml");

        } catch (Exception e) {
            Toast.makeText(Othersintroduce.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int bb = 0;
        while (xml_array3[bb] != null) {
            bb++;
        }
        for (int t = 1; t < bb; t += 6) {
            items3.add(xml_array3[t+4]);
            adapter3.notifyDataSetChanged();
        }




        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Othersintroduce.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",xml_array[to]);
                intent.putExtra("title",xml_array[to+1]);
                intent.putExtra("cat",xml_array[to+2]);
                intent.putExtra("dolbom",xml_array[to+3]);
                intent.putExtra("money",xml_array[to+4]);
                intent.putExtra("term",xml_array[to+5]);
                intent.putExtra("yoil",xml_array[to+6]);
                intent.putExtra("time",xml_array[to+7]);
                intent.putExtra("gender",xml_array[to+8]);
                intent.putExtra("age",xml_array[to+9]);
                intent.putExtra("hak",xml_array[to+10]);
                intent.putExtra("detail",xml_array[to+11]);
                intent.putExtra("local",xml_array[to+12]);
                intent.putExtra("date",xml_array[to+13]);
                intent.putExtra("id",xml_array[to+14]);
                startActivity(intent);

            }
        });



        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chatmain.class);
                startActivityForResult(intent, 1000);
            }
        });

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(phone));
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                        Toast.makeText(getApplicationContext(), "X", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(email));
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(), "X", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End






    private String getXmlData2(String filename){
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
                        xml_array[pop]= ret;
                        pop++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }













    private String getXmlData3(String filename){
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
                        xml_array3[pop3]= ret;
                        pop3++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }














}
