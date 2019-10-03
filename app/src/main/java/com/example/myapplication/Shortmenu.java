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
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Shortmenu extends AppCompatActivity {

    Button btnback;
    Button btnmypage;
    Button btnall;
    Button btnoneday;
    Button btnoneweek;
    Button btntwoweek;
    Button btnonemonth;
    Button btnmenu;
    TextView tvtotal;
    ListView simpleList;
    ListView simpleList2;
    ListView simpleList3;
    ListView simpleList4;
    ListView simpleList5;
    String select="";
    int totalcount=0,count=0,count2=0,count3=0,count4=0,count5=0;

    String[] shorttotal=new String[1000] ;
    String[] xml_array=new String[1000] ;
    String[] xml_array4=new String[1000] ;
    String[] xml_array5=new String[1000] ;
    String[] xml_array6=new String[1000] ;
    String[] xml_array7=new String[1000] ;
    int pop=0;
    int pop3=0;
    int pop4=0;
    int pop5=0;
    int pop6=0;
    int pop7=0;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items4 = new ArrayList<String>();
    ArrayList<String> items5 = new ArrayList<String>();
    ArrayList<String> items6 = new ArrayList<String>();
    ArrayList<String> items7 = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shortmenu );

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnmenu = (Button)findViewById(R.id.btnmenu);
        btnback = (Button)findViewById( R.id.btnback );
        btnmypage = (Button)findViewById( R.id.btnmypage );
        btnall = (Button)findViewById( R.id.btnall );
        btnoneday = (Button)findViewById( R.id.btnoneday );
        btnoneweek = (Button)findViewById( R.id.btnoneweek );
        btntwoweek = (Button)findViewById( R.id.btntwoweek );
        btnonemonth = (Button)findViewById( R.id.btnonemonth );
        tvtotal = (TextView)findViewById(R.id.tvtotal);
        simpleList = findViewById(R.id.Listview);
        simpleList2 = findViewById(R.id.Listview2);
        simpleList3 = findViewById(R.id.Listview3);
        simpleList4 = findViewById(R.id.Listview4);
        simpleList5 = findViewById(R.id.Listview5);

        simpleList.setVisibility(View.INVISIBLE);
        simpleList2.setVisibility(View.INVISIBLE);
        simpleList3.setVisibility(View.INVISIBLE);
        simpleList4.setVisibility(View.INVISIBLE);
        simpleList5.setVisibility(View.INVISIBLE);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items4) ;
        simpleList2.setAdapter(adapter4);
        simpleList2.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items5) ;
        simpleList3.setAdapter(adapter5);
        simpleList3.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items6) ;
        simpleList4.setAdapter(adapter6);
        simpleList4.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter7 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items7) ;
        simpleList5.setAdapter(adapter7);
        simpleList5.setBackgroundColor( Color.BLACK );


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Shortmenu.this , Writeframe.class);
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

        simpleList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Shortmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",xml_array4[to]);
                intent.putExtra("title",xml_array4[to+1]);
                intent.putExtra("cat",xml_array4[to+2]);
                intent.putExtra("dolbom",xml_array4[to+3]);
                intent.putExtra("money",xml_array4[to+4]);
                intent.putExtra("term",xml_array4[to+5]);
                intent.putExtra("yoil",xml_array4[to+6]);
                intent.putExtra("time",xml_array4[to+7]);
                intent.putExtra("gender",xml_array4[to+8]);
                intent.putExtra("age",xml_array4[to+9]);
                intent.putExtra("hak",xml_array4[to+10]);
                intent.putExtra("detail",xml_array4[to+11]);
                intent.putExtra("local",xml_array4[to+12]);
                intent.putExtra("date",xml_array4[to+13]);
                intent.putExtra("id",xml_array4[to+14]);
                startActivity(intent);

            }
        });

        simpleList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Shortmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",xml_array5[to]);
                intent.putExtra("title",xml_array5[to+1]);
                intent.putExtra("cat",xml_array5[to+2]);
                intent.putExtra("dolbom",xml_array5[to+3]);
                intent.putExtra("money",xml_array5[to+4]);
                intent.putExtra("term",xml_array5[to+5]);
                intent.putExtra("yoil",xml_array5[to+6]);
                intent.putExtra("time",xml_array5[to+7]);
                intent.putExtra("gender",xml_array5[to+8]);
                intent.putExtra("age",xml_array5[to+9]);
                intent.putExtra("hak",xml_array5[to+10]);
                intent.putExtra("detail",xml_array5[to+11]);
                intent.putExtra("local",xml_array5[to+12]);
                intent.putExtra("date",xml_array5[to+13]);
                intent.putExtra("id",xml_array5[to+14]);
                startActivity(intent);

            }
        });

        simpleList4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Shortmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",xml_array6[to]);
                intent.putExtra("title",xml_array6[to+1]);
                intent.putExtra("cat",xml_array6[to+2]);
                intent.putExtra("dolbom",xml_array6[to+3]);
                intent.putExtra("money",xml_array6[to+4]);
                intent.putExtra("term",xml_array6[to+5]);
                intent.putExtra("yoil",xml_array6[to+6]);
                intent.putExtra("time",xml_array6[to+7]);
                intent.putExtra("gender",xml_array6[to+8]);
                intent.putExtra("age",xml_array6[to+9]);
                intent.putExtra("hak",xml_array6[to+10]);
                intent.putExtra("detail",xml_array6[to+11]);
                intent.putExtra("local",xml_array6[to+12]);
                intent.putExtra("date",xml_array6[to+13]);
                intent.putExtra("id",xml_array6[to+14]);
                startActivity(intent);

            }
        });

        simpleList5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Shortmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",xml_array7[to]);
                intent.putExtra("title",xml_array7[to+1]);
                intent.putExtra("cat",xml_array7[to+2]);
                intent.putExtra("dolbom",xml_array7[to+3]);
                intent.putExtra("money",xml_array7[to+4]);
                intent.putExtra("term",xml_array7[to+5]);
                intent.putExtra("yoil",xml_array7[to+6]);
                intent.putExtra("time",xml_array7[to+7]);
                intent.putExtra("gender",xml_array7[to+8]);
                intent.putExtra("age",xml_array7[to+9]);
                intent.putExtra("hak",xml_array7[to+10]);
                intent.putExtra("detail",xml_array7[to+11]);
                intent.putExtra("local",xml_array7[to+12]);
                intent.putExtra("date",xml_array7[to+13]);
                intent.putExtra("id",xml_array7[to+14]);
                startActivity(intent);

            }
        });

        btnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList.setVisibility(View.VISIBLE);
                simpleList2.setVisibility(View.INVISIBLE);
                simpleList3.setVisibility(View.INVISIBLE);
                simpleList4.setVisibility(View.INVISIBLE);
                simpleList5.setVisibility(View.INVISIBLE);

                btnall.setBackgroundResource(R.drawable.button_color);
                btnoneday.setBackgroundResource(R.drawable.edge);
                btnoneweek.setBackgroundResource(R.drawable.edge);
                btntwoweek.setBackgroundResource(R.drawable.edge);
                btnonemonth.setBackgroundResource(R.drawable.edge);

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
                try{
                    URL url = new URL(SERVER_ADDRESS + "/search_list.php?"
                    );
                    url.openStream();
                    getXmlData2("search_list.xml");
                } catch(Exception e) {

                    Toast.makeText(Shortmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int a=0;
                while(xml_array[a]!=null)
                {
                    a++;
                }
                //////////////////////////////////////////////////////////글 보여주기
                for(int t=1; t<a; t+=15)
                {
                    if(count<1){
                        items.add(xml_array[t]);
                    }
                    adapter.notifyDataSetChanged();
                }
                //////////////////////////////////////////////////////////글 보여주기 End

                //////////////////////////////////////////////////////////////////////total1 가져오기
                try {
                    URL url = new URL(SERVER_ADDRESS + "/term_all_list.php?"
                    );
                    url.openStream();
                    getXmlData3("term_all_list.xml");
                } catch (Exception e) {
                    //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    //Log.e("Error", e.getMessage());
                }
                while (shorttotal[totalcount] != null) {
                    tvtotal.setText(shorttotal[totalcount]);
                    totalcount++;
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기 End

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End

                count++;
            }
        });

        btnoneday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList.setVisibility(View.INVISIBLE);
                simpleList2.setVisibility(View.VISIBLE);
                simpleList3.setVisibility(View.INVISIBLE);
                simpleList4.setVisibility(View.INVISIBLE);
                simpleList5.setVisibility(View.INVISIBLE);

                select = "1일";

                btnall.setBackgroundResource(R.drawable.edge);
                btnoneday.setBackgroundResource(R.drawable.button_color);
                btnoneweek.setBackgroundResource(R.drawable.edge);
                btntwoweek.setBackgroundResource(R.drawable.edge);
                btnonemonth.setBackgroundResource(R.drawable.edge);

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
                try{
                    URL url = new URL(SERVER_ADDRESS + "/short_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData4("short_list.xml");
                } catch(Exception e) {

                    Toast.makeText(Shortmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int a=0;
                while(xml_array4[a]!=null)
                {
                    a++;
                }
                //////////////////////////////////////////////////////////글 보여주기
                for(int t=1; t<a; t+=15)
                {
                    if(count2<1){
                        items4.add(xml_array4[t]);
                    }
                    adapter4.notifyDataSetChanged();
                }
                //////////////////////////////////////////////////////////글 보여주기 End

                //////////////////////////////////////////////////////////////////////total1 가져오기
                try {
                    URL url = new URL(SERVER_ADDRESS + "/term_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData3("term_list.xml");
                } catch (Exception e) {
                    //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    //Log.e("Error", e.getMessage());
                }
                while (shorttotal[totalcount] != null) {
                    tvtotal.setText(shorttotal[totalcount]);
                    totalcount++;
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기 End

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End
                count2++;
            }
        });

        btnoneweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList.setVisibility(View.INVISIBLE);
                simpleList2.setVisibility(View.INVISIBLE);
                simpleList3.setVisibility(View.VISIBLE);
                simpleList4.setVisibility(View.INVISIBLE);
                simpleList5.setVisibility(View.INVISIBLE);

                select = "1주일이하";

                btnall.setBackgroundResource(R.drawable.edge);
                btnoneday.setBackgroundResource(R.drawable.edge);
                btnoneweek.setBackgroundResource(R.drawable.button_color);
                btntwoweek.setBackgroundResource(R.drawable.edge);
                btnonemonth.setBackgroundResource(R.drawable.edge);

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
                try{
                    URL url = new URL(SERVER_ADDRESS + "/short_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData5("short_list.xml");
                } catch(Exception e) {

                    Toast.makeText(Shortmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int a=0;
                while(xml_array5[a]!=null)
                {
                    a++;
                }
                //////////////////////////////////////////////////////////글 보여주기
                for(int t=1; t<a; t+=15)
                {
                    if(count3<1){
                        items5.add(xml_array5[t]);
                    }
                    adapter5.notifyDataSetChanged();
                }
                //////////////////////////////////////////////////////////글 보여주기 End

                //////////////////////////////////////////////////////////////////////total1 가져오기
                try {
                    URL url = new URL(SERVER_ADDRESS + "/term_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData3("term_list.xml");
                } catch (Exception e) {
                    //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    //Log.e("Error", e.getMessage());
                }
                while (shorttotal[totalcount] != null) {
                    tvtotal.setText(shorttotal[totalcount]);
                    totalcount++;
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기 End

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End
                count3++;
            }
        });

        btntwoweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList.setVisibility(View.INVISIBLE);
                simpleList2.setVisibility(View.INVISIBLE);
                simpleList3.setVisibility(View.INVISIBLE);
                simpleList4.setVisibility(View.VISIBLE);
                simpleList5.setVisibility(View.INVISIBLE);

                select = "1주일~2주일";

                btnall.setBackgroundResource(R.drawable.edge);
                btnoneday.setBackgroundResource(R.drawable.edge);
                btnoneweek.setBackgroundResource(R.drawable.edge);
                btntwoweek.setBackgroundResource(R.drawable.button_color);
                btnonemonth.setBackgroundResource(R.drawable.edge);

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
                try{
                    URL url = new URL(SERVER_ADDRESS + "/short_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData6("short_list.xml");
                } catch(Exception e) {

                    Toast.makeText(Shortmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int a=0;
                while(xml_array6[a]!=null)
                {
                    a++;
                }
                //////////////////////////////////////////////////////////글 보여주기
                for(int t=1; t<a; t+=15)
                {
                    if(count4<1){
                        items6.add(xml_array6[t]);
                    }
                    adapter6.notifyDataSetChanged();
                }
                //////////////////////////////////////////////////////////글 보여주기 End

                //////////////////////////////////////////////////////////////////////total1 가져오기
                try {
                    URL url = new URL(SERVER_ADDRESS + "/term_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData3("term_list.xml");
                } catch (Exception e) {
                    //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    //Log.e("Error", e.getMessage());
                }
                while (shorttotal[totalcount] != null) {
                    tvtotal.setText(shorttotal[totalcount]);
                    totalcount++;
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기 End

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End
                count4++;
            }
        });

       btnonemonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleList.setVisibility(View.INVISIBLE);
                simpleList2.setVisibility(View.INVISIBLE);
                simpleList3.setVisibility(View.INVISIBLE);
                simpleList4.setVisibility(View.INVISIBLE);
                simpleList5.setVisibility(View.VISIBLE);

                select = "1달";

                btnall.setBackgroundResource(R.drawable.edge);
                btnoneday.setBackgroundResource(R.drawable.edge);
                btnoneweek.setBackgroundResource(R.drawable.edge);
                btntwoweek.setBackgroundResource(R.drawable.edge);
                btnonemonth.setBackgroundResource(R.drawable.button_color);

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
                try{
                    URL url = new URL(SERVER_ADDRESS + "/short_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData7("short_list.xml");
                } catch(Exception e) {

                    Toast.makeText(Shortmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int a=0;
                while(xml_array7[a]!=null)
                {
                    a++;
                }
                //////////////////////////////////////////////////////////글 보여주기
                for(int t=1; t<a; t+=15)
                {
                    if(count5<1){
                        items7.add(xml_array7[t]);
                    }
                    adapter7.notifyDataSetChanged();
                }
                //////////////////////////////////////////////////////////글 보여주기 End

                //////////////////////////////////////////////////////////////////////total1 가져오기
                try {
                    URL url = new URL(SERVER_ADDRESS + "/term_list.php?"
                            + "term=" + URLEncoder.encode(select, "UTF-8")
                    );
                    url.openStream();
                    getXmlData3("term_list.xml");
                } catch (Exception e) {
                    //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    //Log.e("Error", e.getMessage());
                }
                while (shorttotal[totalcount] != null) {
                    tvtotal.setText(shorttotal[totalcount]);
                    totalcount++;
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기 End

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End
                count5++;
            }
        });



        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(intent, 1000);
            }
        } );

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



    }//////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End






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
    }








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
    }









    private String getXmlData5(String filename){
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
                        xml_array5[pop5]= ret;
                        pop5++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }








    private String getXmlData6(String filename){
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
                        xml_array6[pop6]= ret;
                        pop6++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }









    private String getXmlData7(String filename){
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
                        xml_array7[pop7]= ret;
                        pop7++;
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
