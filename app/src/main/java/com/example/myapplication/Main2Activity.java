package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Main2Activity extends AppCompatActivity{

    Button btnmenu;
    Button btnmypage;
    Button btnsearch;
    Button btnmatch;
    Button btnlocal;
    Button btnshort;
    Button btnquick;
    Button btnrequest;
    Button btnquestion;
    Button btnadd;
    ImageButton imgbtn1;
    ImageButton imgbtn2;
    ImageButton imgbtn3;
    EditText editsearch;
    TextView tvin1;
    TextView tvin2;
    TextView tvin3;
    ViewFlipper viewflipper;
    ListView simpleList;
    String search="";//,id=""

    String[] idlist=new String[1000];

    String[] xml_array=new String[1000];
    String[] xml_array3=new String[1000];
    String[] xml_array4=new String[1000];
    int pop=0;
    int pop3=0;
    int pop4=0;
    ArrayList<String> items = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);






        try{//////////////////////////////////////////////////////////////////////////////////////////새글에 들어가는 db(Listview)

            URL url = new URL(SERVER_ADDRESS + "/search_list.php?"


            );

            url.openStream();
            getXmlData2("search_list.xml");

        } catch(Exception e) {

            Toast.makeText(Main2Activity.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }

        int a=0;
        while(xml_array[a]!=null)
        {
            //Toast.makeText(Main2Activity.this,"xml_array["+a+"]="+ xml_array[a], Toast.LENGTH_SHORT).show();
            a++;
        }//////////////////////////////////////////////////////////////////////////////////////////새글에 들어가는 db(Listview) End






        try{//////////////////////////////////////////////////////////////////////////////////////////검색어에 들어가는 db

            URL url = new URL(SERVER_ADDRESS + "/search_list.php?"
                    + "search=" + URLEncoder.encode(search,"UTF-8")

            );

            url.openStream();
            getXmlData3("search_list.xml");

        } catch(Exception e) {

            Toast.makeText(Main2Activity.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }

        int a3=0;
        while(xml_array3[a3]!=null)
        {
            //Toast.makeText(Main2Activity.this,"xml_array["+a+"]="+ xml_array[a], Toast.LENGTH_SHORT).show();
            a3++;
        }//////////////////////////////////////////////////////////////////////////////////////////검색어에 들어가는 db End





        try{//////////////////////////////////////////////////////////////////////////////////////////따끈따끈돌봄이에 들어가는 db

            URL url = new URL(SERVER_ADDRESS + "/id_list.php?"
            );
            url.openStream();
            getXmlData4("id_list.xml");
        } catch(Exception e) {
            Toast.makeText(Main2Activity.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a4=0;
        while(xml_array4[a4]!=null)
        {
            idlist[a4] = xml_array4[a4];
            //Toast.makeText(Main2Activity.this,"xml_array4["+a4+"]="+ xml_array4[a4], Toast.LENGTH_SHORT).show();
            a4++;
        }//////////////////////////////////////////////////////////////////////////////////////////따끈따끈돌봄이에 들어가는 db End





        btnadd = (Button)findViewById(R.id.btnadd);
        btnmenu = (Button)findViewById(R.id.btnmenu);
        btnmypage = (Button)findViewById(R.id.btnmypage);
        btnsearch = (Button)findViewById(R.id.btnsearch);
        btnmatch = (Button)findViewById(R.id.btnmatch);
        btnlocal = (Button)findViewById(R.id.btnlocal);
        btnshort = (Button)findViewById(R.id.btnshort);
        btnquick = (Button)findViewById(R.id.btnquick);
        btnrequest = (Button)findViewById(R.id.btnrequest);
        btnquestion = (Button)findViewById(R.id.btnquestion);
        editsearch = (EditText) findViewById(R.id.editsearch);
        imgbtn1 = (ImageButton) findViewById(R.id.imgbtn1);
        imgbtn2 = (ImageButton) findViewById(R.id.imgbtn2);
        imgbtn3 = (ImageButton) findViewById(R.id.imgbtn3);
        viewflipper = findViewById(R.id.viewflipper);
        tvin1 = (TextView) findViewById(R.id.tvin1);
        tvin2 = (TextView) findViewById(R.id.tvin2);
        tvin3 = (TextView) findViewById(R.id.tvin3);
        simpleList = (ListView)findViewById(R.id.Listview);



        for(int i=0; i<a4; i+=4){
            if(idlist[i]==NULL){
                break;
            }
            else if(i == 0){
                tvin1.setText(idlist[i]);
            }
            else if(i == 4){
                tvin2.setText(idlist[i]);
            }
            else if(i == 8){
                tvin3.setText(idlist[i]);
            }
            else{
                break;
            }
        }


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(idlist[0]) == true)
                {
                    Toast.makeText(Main2Activity.this, "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else//(idlist[0].equals("1"))
                {
                    Intent intent = new Intent(getApplicationContext(), Othersintroduce.class);
                    intent.putExtra("id",idlist[0]);
                    intent.putExtra("email",idlist[1]);
                    intent.putExtra("introduce",idlist[2]);
                    intent.putExtra("phone",idlist[3]);
                    startActivityForResult(intent, 1000);
                }

            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(idlist[4]) == true)
                {
                    Toast.makeText(Main2Activity.this, "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else//(idlist[4].equals("2"))
                {
                    Intent intent = new Intent(getApplicationContext(), Othersintroduce.class);
                    intent.putExtra("id",idlist[4]);
                    intent.putExtra("email",idlist[5]);
                    intent.putExtra("introduce",idlist[6]);
                    intent.putExtra("phone",idlist[7]);
                    startActivityForResult(intent, 1000);
                }
            }
        });


        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(idlist[8]) == true)
                {
                    Toast.makeText(Main2Activity.this, "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Othersintroduce.class);
                    intent.putExtra("id",idlist[8]);
                    intent.putExtra("email",idlist[9]);
                    intent.putExtra("introduce",idlist[10]);
                    intent.putExtra("phone",idlist[11]);
                    startActivityForResult(intent, 1000);
                }
            }
        });



        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );






        for(int t=1; t<a; t+=15)/////////////////////////////////////////////////////////////////////////새글에 들어가는 db(Listview) 글 보여주기
        {
            items.add(xml_array[t]);
            adapter.notifyDataSetChanged();
            //Toast.makeText(Matchmenu.this, xml_array[t], Toast.LENGTH_SHORT).show();
        }










        int images[] = {
                R.mipmap.mainview1,
                R.mipmap.mainview2,
                R.mipmap.mainview3
        };

        for(int image : images) {
            fllipperImages(image);
        }











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



        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search = editsearch.getText().toString();

                Intent intent = new Intent(getApplicationContext(), Searchlist.class);
                intent.putExtra("search",search);
                startActivityForResult(intent, 1000);
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainAdd.class);
                startActivityForResult(intent, 1000);
            }
        });


        btnmypage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), mypage.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btnmatch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Matchmenu.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btnlocal.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Localmenu.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btnshort.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Shortmenu.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btnquick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quickmenu.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btnrequest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), write.class);
                startActivityForResult(intent, 1000);
            }
        } );


        btnquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), help.class);
                startActivityForResult(intent, 1000);
            }
        });



        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this , Writeframe.class);
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







    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////create End







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
    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData2 End







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
    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData3 End




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
    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData4 End





    public void fllipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewflipper.addView(imageView);      // 이미지 추가
        viewflipper.setFlipInterval(4000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        viewflipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        viewflipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewflipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////fllipperImages End




}
