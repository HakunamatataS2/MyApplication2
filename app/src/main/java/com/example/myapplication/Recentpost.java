package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity.loginid;

public class Recentpost extends AppCompatActivity {

    Button btnback;
    TextView tvtotal;
    ListView simpleList;
    int totalcount=0;

    String[] shorttotal=new String[1000] ;
    String[] xml_array=new String[1000] ;
    int pop=0;
    int pop3=0;
    ArrayList<String> items = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentpost);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tvtotal = (TextView)findViewById(R.id.tvtotal);
        btnback = (Button)findViewById( R.id.btnback );
        simpleList = findViewById(R.id.Listview);


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/recent_list.php?"
                    + "id=" + URLEncoder.encode(loginid,"UTF-8")
            );
            url.openStream();
            getXmlData2("recent_list.xml");
        } catch(Exception e) {
            Toast.makeText(Recentpost.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a=0;
        while(xml_array[a]!=null)
        {
            a++;
        }
        /////////////////////////////////////////////////////////////////////글 보여주기
        for(int t=1; t<a; t+=15)
        {
            items.add(xml_array[t]);
            adapter.notifyDataSetChanged();
        }
        /////////////////////////////////////////////////////////////////////글 보여주기 End

        //////////////////////////////////////////////////////////////////////total1 가져오기
        try {
            URL url = new URL(SERVER_ADDRESS + "/recent_total_list.php?"
                    + "id=" + URLEncoder.encode(loginid,"UTF-8")
            );
            url.openStream();
            getXmlData3("recent_total_list.xml");
        } catch (Exception e) {
            Toast.makeText(Recentpost.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        while (shorttotal[totalcount] != null) {
            tvtotal.setText(shorttotal[totalcount]);
            totalcount++;
        }
        //////////////////////////////////////////////////////////////////////total1 가져오기 End

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////가져오기 End






        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Recentpost.this , Writeframe.class);
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





        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mypage.class);
                startActivityForResult(intent, 1000);
            }
        } );





    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End







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
    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData2 End





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
                        shorttotal[pop3]= ret;
                        pop3++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData3 End





}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////class End