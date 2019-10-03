package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity.loginid;
import static com.example.myapplication.Match1.match1age;
import static com.example.myapplication.Match1.match1gender;
import static com.example.myapplication.Match1.match1si;
import static com.example.myapplication.Match2.match2age;
import static com.example.myapplication.Match2.match2gender;
import static com.example.myapplication.Match2.match2si;
import static com.example.myapplication.Match3.match3age;
import static com.example.myapplication.Match3.match3gender;
import static com.example.myapplication.Match3.match3si;

public class Matchmenu extends AppCompatActivity{

    Button btnback;
    Button btnmenu;
    Button btnsearch;
    Button btnmatch1;
    Button btnmatch2;
    Button btnmatch3;
    Button btncancel1;
    Button btncancel2;
    Button btncancel3;
    EditText editsearch;
    TextView tvtotal;
    TextView tvtotal2;
    TextView tvtotal3;
    ListView simpleList;
    ListView simpleList2;
    ListView simpleList3;
    TextView tvid;
    TextView tvid2;
    TextView tvid3;
    int totalcount=0,totalcount2=0,totalcount3=0;
    String sum="",sum2="",sum3="";


    static final String[] match=new String[3] ;
    static final String[] total=new String[3] ;
    static final String[] total2=new String[3] ;
    static final String[] total3=new String[3] ;

    String[] match_xml_array_number=new String[1000] ;
    String[] match_xml_array_number2=new String[1000] ;
    String[] match_xml_array_number3=new String[1000] ;
    String[] match_xml_array=new String[1000] ;
    String[] match_xml_array2=new String[1000] ;
    String[] match_xml_array3=new String[1000] ;
    int pop2=0;
    int pop4=0;
    int pop5=0;
    int pop6=0;
    int pop7=0;
    int pop8=0;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items2 = new ArrayList<String>();
    ArrayList<String> items3 = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_matchmenu );

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btncancel1= findViewById(R.id.btncancel1);
        btncancel2= findViewById(R.id.btncancel2);
        btncancel3= findViewById(R.id.btncancel3);
        btnmatch1 = findViewById(R.id.btnmatch1);
        btnmatch2 = findViewById(R.id.btnmatch2);
        btnmatch3 = findViewById(R.id.btnmatch3);
        btnmenu = findViewById(R.id.btnmenu);
        btnback = findViewById( R.id.btnback );
        btnsearch = findViewById( R.id.btnsearch );
        editsearch = findViewById( R.id.editsearch );
        tvtotal = findViewById( R.id.tvtotal );
        tvtotal2 = findViewById( R.id.tvtotal2 );
        tvtotal3 = findViewById( R.id.tvtotal3 );
        tvid = findViewById(R.id.tvid);
        tvid2 = findViewById(R.id.tvid2);
        tvid3 = findViewById(R.id.tvid3);
        simpleList = findViewById(R.id.Listview);
        simpleList2 = findViewById(R.id.Listview2);
        simpleList3 = findViewById(R.id.Listview3);


        /////////////////////////////////////////////////////////////////////////////////////////////Tabhost
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("맞춤조건1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("맞춤조건1") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("맞춤조건2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("맞춤조건2") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("맞춤조건3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("맞춤조건3") ;
        tabHost1.addTab(ts3) ;
        /////////////////////////////////////////////////////////////////////////////////////////////Tabhost End







        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items2) ;
        simpleList2.setAdapter(adapter2);
        simpleList2.setBackgroundColor( Color.BLACK );

        final ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items3) ;
        simpleList3.setAdapter(adapter3);
        simpleList3.setBackgroundColor( Color.BLACK );







        sum = match1si+", "+match1gender+", "+match1age;
        sum2 = match2si+", "+match2gender+", "+match2age;
        sum3 = match3si+", "+match3gender+", "+match3age;







        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////조건 가져오기

        //////////////////////////////////////////////////////////////////////////////////////////맞춤조건1 가져오기
        if( TextUtils.isEmpty(match[0]) == false && match[0].equals("1")) {
            try {

                URL url = new URL(SERVER_ADDRESS + "/match_search.php?"
                        + "gender=" + URLEncoder.encode(match1gender, "UTF-8")
                        + "&age=" + URLEncoder.encode(match1age, "UTF-8")
                        + "&local=" + URLEncoder.encode(match1si, "UTF-8")
                );
                url.openStream();
                getXmlData3("match_search.xml");

            } catch (Exception e) {
                //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                //Log.e("Error", e.getMessage());
            }
            int b = 0;
            while (match_xml_array[b] != null) {
                match_xml_array_number[b] = match_xml_array[b * 15];
                b++;
            }
            int hi = 0;
            for (int t = 1; t < b; t += 15) {
                if (TextUtils.isEmpty(match_xml_array_number[hi]) == true) {
                    break;
                } else {
                    tvid.setText(sum);
                    items.add(match_xml_array[t]);
                    adapter.notifyDataSetChanged();
                    hi++;
                }
            }


            //////////////////////////////////////////////////////////////////////total1 가져오기
            try {
                URL url = new URL(SERVER_ADDRESS + "/total_list.php?"
                        + "gender=" + URLEncoder.encode(match1gender, "UTF-8")
                        + "&age=" + URLEncoder.encode(match1age, "UTF-8")
                        + "&local=" + URLEncoder.encode(match1si, "UTF-8")
                );
                url.openStream();
                getXmlData6("total_list.xml");
            } catch (Exception e) {
                //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                //Log.e("Error", e.getMessage());
            }
            while (total[totalcount] != null) {
                tvtotal.setText(total[totalcount]);
                totalcount++;
            }
            //////////////////////////////////////////////////////////////////////total1 가져오기 End


        }
        //////////////////////////////////////////////////////////////////////////////////////////맞춤조건1 가져오기 End

        //////////////////////////////////////////////////////////////////////////////////////////맞춤조건2 가져오기
        if( TextUtils.isEmpty(match[1]) == false && match[1].equals("2")) {
            try {
                URL url = new URL(SERVER_ADDRESS + "/match_search.php?"
                        + "gender=" + URLEncoder.encode(match2gender, "UTF-8")
                        + "&age=" + URLEncoder.encode(match2age, "UTF-8")
                        + "&local=" + URLEncoder.encode(match2si, "UTF-8")
                );

                url.openStream();
                getXmlData4("match_search.xml");

            } catch (Exception e) {
                //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                //Log.e("Error", e.getMessage());
            }

            int b2 = 0;
            while (match_xml_array2[b2] != null) {
                match_xml_array_number2[b2] = match_xml_array2[b2 * 15];
                b2++;
            }
            int hi2 = 0;
            for (int t = 1; t < b2; t += 15) {
                if (TextUtils.isEmpty(match_xml_array_number2[hi2]) == true) {
                    break;
                } else {
                    tvid2.setText(sum2);
                    items2.add(match_xml_array2[t]);
                    adapter2.notifyDataSetChanged();
                    hi2++;
                }
            }

            //////////////////////////////////////////////////////////////////////total2 가져오기
            try {
                URL url = new URL(SERVER_ADDRESS + "/total_list.php?"
                        + "gender=" + URLEncoder.encode(match2gender, "UTF-8")
                        + "&age=" + URLEncoder.encode(match2age, "UTF-8")
                        + "&local=" + URLEncoder.encode(match2si, "UTF-8")
                );
                url.openStream();
                getXmlData7("total_list.xml");
            } catch (Exception e) {
                //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                //Log.e("Error", e.getMessage());
            }
            while (total2[totalcount2] != null) {
                tvtotal2.setText(total2[totalcount2]);
                totalcount2++;
            }
            //////////////////////////////////////////////////////////////////////total2 가져오기 End


        }
        //////////////////////////////////////////////////////////////////////////////////////////맞춤조건2 가져오기 End

        //////////////////////////////////////////////////////////////////////////////////////////맞춤조건3 가져오기
        if(TextUtils.isEmpty(match[2]) == false && match[2].equals("3")) {
            try {
                URL url = new URL(SERVER_ADDRESS + "/match_search.php?"
                        + "gender=" + URLEncoder.encode(match3gender, "UTF-8")
                        + "&age=" + URLEncoder.encode(match3age, "UTF-8")
                        + "&local=" + URLEncoder.encode(match3si, "UTF-8")
                );
                url.openStream();
                getXmlData5("match_search.xml");

            } catch (Exception e) {
                //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                //Log.e("Error", e.getMessage());
            }

            int b3 = 0;
            while (match_xml_array3[b3] != null) {
                match_xml_array_number3[b3] = match_xml_array3[b3 * 15];
                b3++;
            }
            int hi3 = 0;
            for (int t = 1; t < b3; t += 15) {
                if (TextUtils.isEmpty(match_xml_array_number3[hi3]) == true) {
                    break;
                } else {
                    tvid3.setText(sum3);
                    items3.add(match_xml_array3[t]);
                    adapter3.notifyDataSetChanged();
                    hi3++;
                }
            }
            //////////////////////////////////////////////////////////////////////total3 가져오기
            try {
                URL url = new URL(SERVER_ADDRESS + "/total_list.php?"
                        + "gender=" + URLEncoder.encode(match3gender, "UTF-8")
                        + "&age=" + URLEncoder.encode(match3age, "UTF-8")
                        + "&local=" + URLEncoder.encode(match3si, "UTF-8")
                );
                url.openStream();
                getXmlData8("total_list.xml");
            } catch (Exception e) {
                //Toast.makeText(Matchmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                //Log.e("Error", e.getMessage());
            }
            while (total3[totalcount3] != null) {
                tvtotal3.setText(total3[totalcount3]);
                totalcount3++;
            }
            //////////////////////////////////////////////////////////////////////total3 가져오기 End
        }
        //////////////////////////////////////////////////////////////////////////////////////////맞춤조건3 가져오기 End

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////조건 가져오기 End



        btnmatch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match[0]="1";
                Intent intent = new Intent(getApplicationContext(), Match1.class);
                startActivityForResult(intent, 1000);
            }
        });

        btnmatch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match[0]="1";
                match[1]="2";
                if(match[0]=="0"){
                    Toast.makeText(Matchmenu.this, "맞춤조건1부터 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Match2.class);
                    startActivityForResult(intent, 1000);
                }

            }
        });
        btnmatch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match[0]="1";
                match[1]="2";
                match[2]="3";
                if(match[0]=="0"){
                    Toast.makeText(Matchmenu.this, "맞춤조건1부터 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(match[1]=="0"){
                    Toast.makeText(Matchmenu.this, "맞춤조건2부터 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Match3.class);
                    startActivityForResult(intent, 1000);
                }

            }
        });





        btncancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvid.setText("");
                match[0]="";
                sum="";
                items.clear();
                adapter.clear();
                tvtotal.setText("");
            }
        });
        btncancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvid2.setText("");
                match[1]="";
                sum2="";
                items2.clear();
                adapter2.clear();
                tvtotal2.setText("");
            }
        });
        btncancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvid3.setText("");
                match[2]="";
                sum3="";
                items3.clear();
                adapter3.clear();
                tvtotal3.setText("");
            }
        });



        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(Matchmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",match_xml_array[to]);
                intent.putExtra("title",match_xml_array[to+1]);
                intent.putExtra("cat",match_xml_array[to+2]);
                intent.putExtra("dolbom",match_xml_array[to+3]);
                intent.putExtra("money",match_xml_array[to+4]);
                intent.putExtra("term",match_xml_array[to+5]);
                intent.putExtra("yoil",match_xml_array[to+6]);
                intent.putExtra("time",match_xml_array[to+7]);
                intent.putExtra("gender",match_xml_array[to+8]);
                intent.putExtra("age",match_xml_array[to+9]);
                intent.putExtra("hak",match_xml_array[to+10]);
                intent.putExtra("detail",match_xml_array[to+11]);
                intent.putExtra("local",match_xml_array[to+12]);
                intent.putExtra("date",match_xml_array[to+13]);
                intent.putExtra("id",match_xml_array[to+14]);
                startActivity(intent);

            }
        });



        simpleList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Matchmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",match_xml_array2[to]);
                intent.putExtra("title",match_xml_array2[to+1]);
                intent.putExtra("cat",match_xml_array2[to+2]);
                intent.putExtra("dolbom",match_xml_array2[to+3]);
                intent.putExtra("money",match_xml_array2[to+4]);
                intent.putExtra("term",match_xml_array2[to+5]);
                intent.putExtra("yoil",match_xml_array2[to+6]);
                intent.putExtra("time",match_xml_array2[to+7]);
                intent.putExtra("gender",match_xml_array2[to+8]);
                intent.putExtra("age",match_xml_array2[to+9]);
                intent.putExtra("hak",match_xml_array2[to+10]);
                intent.putExtra("detail",match_xml_array2[to+11]);
                intent.putExtra("local",match_xml_array2[to+12]);
                intent.putExtra("date",match_xml_array2[to+13]);
                intent.putExtra("id",match_xml_array2[to+14]);
                startActivity(intent);

            }
        });



        simpleList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Matchmenu.this , Writeframe.class);
                int to = position*15;
                intent.putExtra("number",match_xml_array3[to]);
                intent.putExtra("title",match_xml_array3[to+1]);
                intent.putExtra("cat",match_xml_array3[to+2]);
                intent.putExtra("dolbom",match_xml_array3[to+3]);
                intent.putExtra("money",match_xml_array3[to+4]);
                intent.putExtra("term",match_xml_array3[to+5]);
                intent.putExtra("yoil",match_xml_array3[to+6]);
                intent.putExtra("time",match_xml_array3[to+7]);
                intent.putExtra("gender",match_xml_array3[to+8]);
                intent.putExtra("age",match_xml_array3[to+9]);
                intent.putExtra("hak",match_xml_array3[to+10]);
                intent.putExtra("detail",match_xml_array3[to+11]);
                intent.putExtra("local",match_xml_array3[to+12]);
                intent.putExtra("date",match_xml_array3[to+13]);
                intent.putExtra("id",match_xml_array3[to+14]);
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










        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(intent, 1000);
            }
        } );
















    }       ////////////////////////////////////////////////////////////////////////////////////////////////////////    OnCreate _ End













/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Get XML Data _ Start






    private String getXmlData8(String filename){
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
                        total3[pop8]= ret;
                        pop8++;
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
                        total2[pop7]= ret;
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
                        total[pop6]= ret;
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
                        match_xml_array3[pop5]= ret;
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
                        match_xml_array2[pop4]= ret;
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
                        match_xml_array[pop2]= ret;
                        pop2++;
                    }
                }

                eventType = xpp.next();


            }


        } catch(Exception e) {
            Log.e("Error", e.getMessage());
        }

        return ret;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Get XML Data _ End


}
