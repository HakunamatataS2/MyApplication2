package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
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

public class Mynewwrite extends AppCompatActivity {

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
        setContentView(R.layout.activity_mynewwrite);

        btnback = (Button)findViewById(R.id.btnback);
        tvtotal = (TextView)findViewById(R.id.tvtotal);
        simpleList = (ListView)findViewById(R.id.Listview);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/newwrite_list.php?"
            );
            url.openStream();
            getXmlData2("newwrite_list.xml");
        } catch(Exception e) {
            Toast.makeText(Mynewwrite.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a=0;
        while(xml_array[a]!=null)
        {
            a++;
        }
        //////////////////////////////////////////////////////////////////글 보여주기
        for(int t=3; t<a; t+=5)
        {
            items.add(xml_array[t]);
            adapter.notifyDataSetChanged();
        }
        //////////////////////////////////////////////////////////////////글 보여주기 End
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End



        //////////////////////////////////////////////////////////////////////total1 가져오기
        try {
            URL url = new URL(SERVER_ADDRESS + "/newwrite_total_list.php?"
                    + "id=" + URLEncoder.encode(loginid, "UTF-8")
            );
            url.openStream();
            getXmlData3("newwrite_total_list.xml");
        } catch (Exception e) {
            Toast.makeText(Mynewwrite.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        while (shorttotal[totalcount] != null) {
            tvtotal.setText(shorttotal[totalcount]);
            totalcount++;
        }
        //////////////////////////////////////////////////////////////////////total1 가져오기 End


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Mynewwrite.this , Newwriteframe.class);
                int to = position*5;
                intent.putExtra("number",xml_array[to]);
                intent.putExtra("id",xml_array[to+1]);
                intent.putExtra("cat",xml_array[to+2]);
                intent.putExtra("title",xml_array[to+3]);
                intent.putExtra("content",xml_array[to+4]);
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



    }//////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End




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





}//////////////////////////////////////////////////////////////////////////////////////////////////////////////class End
