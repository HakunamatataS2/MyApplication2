package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Community extends AppCompatActivity {

    Button btnmenu;
    Button btnnewwrite;
    ListView simpleList1;
    ListView simpleList2;
    ListView simpleList3;

    String[] xml_array=new String[1000] ;
    String[] xml_array3=new String[1000] ;
    String[] xml_array4=new String[1000] ;
    int pop=0;
    int pop3=0;
    int pop4=0;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items3 = new ArrayList<String>();
    ArrayList<String> items4 = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnmenu = (Button) findViewById(R.id.btnmenu);
        btnnewwrite = (Button)findViewById(R.id.btnnewwrite);
        simpleList1 = (ListView)findViewById(R.id.Listview1);
        simpleList2 = (ListView)findViewById(R.id.Listview2);
        simpleList3 = (ListView)findViewById(R.id.Listview3);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList1.setAdapter(adapter);
        simpleList1.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items3) ;
        simpleList2.setAdapter(adapter3);
        simpleList2.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items4) ;
        simpleList3.setAdapter(adapter4);
        simpleList3.setBackgroundColor( Color.BLACK );





        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/experience_list.php?"
            );
            url.openStream();
            getXmlData2("experience_list.xml");
        } catch(Exception e) {
            Toast.makeText(Community.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
            //Toast.makeText(Community.this, "xml_array["+"t"+"]="+xml_array[t], Toast.LENGTH_SHORT).show();
        }
        //////////////////////////////////////////////////////////////////글 보여주기 End
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End





        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/review_list.php?"
            );
            url.openStream();
            getXmlData3("review_list.xml");
        } catch(Exception e) {
            Toast.makeText(Community.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a3=0;
        while(xml_array3[a3]!=null)
        {
            a3++;
        }
        //////////////////////////////////////////////////////////////////글 보여주기
        for(int t=3; t<a3; t+=5)
        {
            items3.add(xml_array3[t]);
            adapter3.notifyDataSetChanged();
        }
        //////////////////////////////////////////////////////////////////글 보여주기 End
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End





        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/story_list.php?"
            );
            url.openStream();
            getXmlData4("story_list.xml");
        } catch(Exception e) {
            Toast.makeText(Community.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a4=0;
        while(xml_array4[a4]!=null)
        {
            a4++;
        }
        //////////////////////////////////////////////////////////////////글 보여주기
        for(int t=3; t<a4; t+=5)
        {
            items4.add(xml_array4[t]);
            adapter4.notifyDataSetChanged();
        }
        //////////////////////////////////////////////////////////////////글 보여주기 End
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End



        simpleList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Community.this , Newwriteframe.class);
                int to = position*5;
                intent.putExtra("number",xml_array[to]);
                intent.putExtra("id",xml_array[to+1]);
                intent.putExtra("cat",xml_array[to+2]);
                intent.putExtra("title",xml_array[to+3]);
                intent.putExtra("content",xml_array[to+4]);
                startActivity(intent);
            }
        });

        simpleList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Community.this , Newwriteframe.class);
                int to = position*5;
                intent.putExtra("number",xml_array3[to]);
                intent.putExtra("id",xml_array3[to+1]);
                intent.putExtra("cat",xml_array3[to+2]);
                intent.putExtra("title",xml_array3[to+3]);
                intent.putExtra("content",xml_array3[to+4]);
                startActivity(intent);
            }
        });

        simpleList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Community.this , Newwriteframe.class);
                int to = position*5;
                intent.putExtra("number",xml_array4[to]);
                intent.putExtra("id",xml_array4[to+1]);
                intent.putExtra("cat",xml_array4[to+2]);
                intent.putExtra("title",xml_array4[to+3]);
                intent.putExtra("content",xml_array4[to+4]);
                startActivity(intent);
            }
        });


        btnnewwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Community.this , Newwrite.class);
                startActivity(intent);
            }
        });



        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){

                            case R.id.m0:
                                Toast.makeText(getApplication(),"Home",Toast.LENGTH_SHORT).show();
                                Intent mainintent = new Intent(getApplicationContext(), Main2Activity.class);
                                startActivityForResult(mainintent, 1000);
                                break;


                            case R.id.m1:
                                Toast.makeText(getApplication(),"맞춤돌봄이",Toast.LENGTH_SHORT).show();
                                Intent matchintent = new Intent(getApplicationContext(), Matchmenu.class);
                                startActivityForResult(matchintent, 1000);
                                break;


                            case R.id.m2:
                                Toast.makeText(getApplication(),"지역돌봄이",Toast.LENGTH_SHORT).show();
                                Intent localintent = new Intent(getApplicationContext(), Localmenu.class);
                                startActivityForResult(localintent, 1000);
                                break;


                            case R.id.m3:
                                Toast.makeText(getApplication(),"단기돌봄이",Toast.LENGTH_SHORT).show();
                                Intent shortintent = new Intent(getApplicationContext(), Shortmenu.class);
                                startActivityForResult(shortintent, 1000);
                                break;


                            case R.id.m4:
                                Toast.makeText(getApplication(),"급구돌봄이",Toast.LENGTH_SHORT).show();
                                Intent quickintent = new Intent(getApplicationContext(), Quickmenu.class);
                                startActivityForResult(quickintent, 1000);
                                break;


                            case R.id.m5:
                                Toast.makeText(getApplication(),"돌봄이신청하기",Toast.LENGTH_SHORT).show();
                                Intent writeintent = new Intent(getApplicationContext(), write.class);
                                startActivityForResult(writeintent, 1000);
                                break;


                            case R.id.m6:
                                Toast.makeText(getApplication(),"커뮤니티",Toast.LENGTH_SHORT).show();
                                Intent comunityintent = new Intent(getApplicationContext(), Community.class);
                                startActivityForResult(comunityintent, 1000);
                                break;

                            case R.id.m7:
                                Toast.makeText(getApplication(),"마이페이지",Toast.LENGTH_SHORT).show();
                                Intent mypageintent = new Intent(getApplicationContext(), mypage.class);
                                startActivityForResult(mypageintent, 1000);
                                break;


                            default:
                                break;


                        }
                        return false;
                    }
                });

                popup.show(); //Popup Menu 보이기


            }
        });



    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate_End






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
    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData2 End







    private String getXmlData4(String filename){
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
    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData2 End





}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////class End
