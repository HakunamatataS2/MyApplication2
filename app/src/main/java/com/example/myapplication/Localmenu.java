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

import static com.example.myapplication.Dialog.diasi;

public class Localmenu extends AppCompatActivity {

    Button btnback;
    Button btnmenu;
    Button btnlocalchk;
    Button btnmap;
    TextView tvlocal;
    TextView tvtotal;
    ListView simpleList;
    int totalcount=0;

    String[] xml_array=new String[1000] ;
    String[] shorttotal=new String[1000] ;
    int pop=0;
    int pop3=0;
    ArrayList<String> items = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_localmenu );

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnmenu=(Button)findViewById(R.id.btnmenu);
        btnmap=(Button)findViewById(R.id.btnmap);
        btnback = (Button)findViewById( R.id.btnback );
        btnlocalchk = (Button)findViewById( R.id.btnlocalchk );
        tvtotal = (TextView)findViewById( R.id.tvtotal );
        tvlocal = (TextView)findViewById(R.id.tvlocal);
        simpleList = findViewById(R.id.Listview);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );


        tvlocal.setText(diasi);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/local_list.php?"
                    + "local=" + URLEncoder.encode(diasi, "UTF-8")
            );
            url.openStream();
            getXmlData2("local_list.xml");
        } catch(Exception e) {

            Toast.makeText(Localmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
            items.add(xml_array[t]);
            adapter.notifyDataSetChanged();
        }
        //////////////////////////////////////////////////////////글 보여주기 End

        //////////////////////////////////////////////////////////////////////total1 가져오기
        try {
            URL url = new URL(SERVER_ADDRESS + "/local_total.php?"
                    + "local=" + URLEncoder.encode(diasi, "UTF-8")
            );
            url.openStream();
            getXmlData3("local_total.xml");
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








        btnlocalchk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Localmenu.this , Dialog.class);
                startActivity(intent);
            }
        });

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Localmenu.this , Writeframe.class);
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
                tvtotal.setText("");
                tvlocal.setText("");
                items.clear();
                adapter.clear();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Kakaomap.class);
                startActivityForResult(intent, 1000);
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



    }////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End




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







}
