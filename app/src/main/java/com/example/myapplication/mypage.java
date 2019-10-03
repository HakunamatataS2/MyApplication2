package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.myapplication.MainActivity.loginid;

public class mypage extends AppCompatActivity {

    TextView tvid;
    TextView tvemail;
    TextView tvintroduce;
    Button btnmenu;
    Button btnlogout;
    Button btnintro;
    Button btninterest;
    Button btnrecent;
    Button btnmywrite;
    Button btnchat;
    Button btnintroupdate;
    Button btnnewwrite;

    String[] xml_array=new String[1000] ;
    int pop=0;

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        tvid = (TextView) findViewById(R.id.tvid);
        tvemail = (TextView) findViewById(R.id.tvemail);
        tvintroduce = (TextView) findViewById(R.id.tvintroduce);
        btnmenu = (Button)findViewById( R.id.btnmenu);
        btnlogout = (Button) findViewById(R.id.btnlogout);
        btnintro = (Button) findViewById(R.id.btnintro);
        btninterest = (Button) findViewById(R.id.btninterest);
        btnrecent = (Button) findViewById(R.id.btnrecent);
        btnmywrite = (Button) findViewById(R.id.btnmywrite);
        btnchat = (Button) findViewById(R.id.btnchat);
        btnintroupdate = (Button) findViewById(R.id.btnintroupdate);
        btnnewwrite = (Button)findViewById(R.id.btnnewwrite);


        tvid.setText(loginid);



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////값 가져오기
        try{
            URL url = new URL(SERVER_ADDRESS + "/dolbommember_list.php?"
                    + "id=" + URLEncoder.encode(loginid, "UTF-8")
            );
            url.openStream();
            getXmlData2("dolbommember_list.xml");
        } catch(Exception e) {
            Toast.makeText(mypage.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int a=0;
        while(xml_array[a]!=null)
        {
            a++;
        }
        //////////////////////////////////////////////////////////////////글 보여주기
        for(int t=2; t<a; t+=5)
        {
            tvemail.setText(xml_array[t]);
            tvintroduce.setText(xml_array[t+1]);
        }
        //////////////////////////////////////////////////////////////////글 보여주기 End
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////값 가져오기 End



        btnnewwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mynewwrite.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnintroupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Writeintro.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chatmain.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnrecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recentpost.class);
                startActivityForResult(intent, 1000);
            }
        });

        btninterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Interestpost.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnmywrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mywritepost.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnintro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Myintroduce.class);
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





    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End




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






}/////////////////////////////////////////////////////////////////////////////////////////////////////////////class End
