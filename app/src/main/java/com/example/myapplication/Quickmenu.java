package com.example.myapplication;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Quickmenu extends AppCompatActivity {

    Button btnok;
    Button btnback;
    Button btnch;
    Button btnmenu;
    TextView tvday;
    TextView tvday2;
    TextView tvtotal;
    ListView simpleList;
    Spinner spin;
    String dolbom="",day="";
    int mYear, mMonth, mDay;
    int totalcount=0;

    ArrayList<String> spinnerlist1 = new ArrayList<String>();

    String[] shorttotal=new String[1000] ;
    String[] xml_array=new String[1000] ;
    int pop=0;
    int pop3=0;
    ArrayList<String> items = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quickmenu );

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnok = (Button) findViewById(R.id.btnok);
        btnch = (Button)findViewById(R.id.btnch);
        btnmenu = (Button)findViewById( R.id.btnmenu );
        btnback = (Button)findViewById( R.id.btnback );
        tvday = (TextView) findViewById( R.id.tvday );
        tvday2 = (TextView) findViewById( R.id.tvday2 );
        tvtotal = (TextView) findViewById( R.id.tvtotal );
        spin = (Spinner)findViewById(R.id.spin);
        simpleList = (ListView)findViewById(R.id.Listview);


        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();//화면에 텍스트뷰에 업데이트 해줌.


        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String format_time1 = format1.format (System.currentTimeMillis());
        tvday2.setText(format_time1);


        spinnerlist1.add( "선택하세요" );
        spinnerlist1.add( "함께외출돌봄" );
        spinnerlist1.add( "일상가사돌봄" );
        spinnerlist1.add( "간병간호돌봄" );
        spinnerlist1.add( "목욕단정돌봄" );
        spinnerlist1.add( "산책말벗돌봄" );
        spinnerlist1.add( "24시간돌봄" );

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist1 );
        spin.setAdapter( adapter1 );

        spin.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dolbom = spin.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Quickmenu.this , Writeframe.class);
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


        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기
                try{
                    URL url = new URL(SERVER_ADDRESS + "/quick_list.php?"
                            + "date=" + URLEncoder.encode(day, "UTF-8")
                            + "&dolbom=" + URLEncoder.encode(dolbom, "UTF-8")
                    );
                    url.openStream();
                    getXmlData2("quick_list.xml");
                } catch(Exception e) {
                    Toast.makeText(Quickmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int a=0;
                while(xml_array[a]!=null)
                {
                    a++;
                }
                for(int t=1; t<a; t+=15)      //////////////////////글 보여주기
                {
                    items.add(xml_array[t]);
                    adapter.notifyDataSetChanged();
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기
                try {
                    URL url = new URL(SERVER_ADDRESS + "/quick_total.php?"
                            + "date=" + URLEncoder.encode(day, "UTF-8")
                            + "&dolbom=" + URLEncoder.encode(dolbom, "UTF-8")
                    );
                    url.openStream();
                    getXmlData3("quick_total.xml");
                } catch (Exception e) {
                    Toast.makeText(Quickmenu.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                while (shorttotal[totalcount] != null) {
                    tvtotal.setText(shorttotal[totalcount]);
                    totalcount++;
                }
                //////////////////////////////////////////////////////////////////////total1 가져오기 End
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////글 가져오기 End
            }
        });


        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvday.setText("");
                tvday2.setText("");
                tvtotal.setText("");
                spin.setSelection(0);
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
    }////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData2 End






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
    }////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData3 End








    public void mOnClick(View v){
        switch(v.getId()){
            //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌
            case R.id.btnch:
                //여기서 리스너도 등록함
                new DatePickerDialog(Quickmenu.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
        }
    }

    //날짜 대화상자 리스너 부분1
    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow();
                }
            };

    //텍스트뷰의 값을 업데이트 하는 메소드
    void UpdateNow(){
        tvday.setText(String.format("%d-%d-%d", mYear, mMonth + 1, mDay));
        day=String.format("%d-%d-%d", mYear, mMonth + 1, mDay)+" 00:00:00";    //day="\""+String.format("%d-%d-%d", mYear, mMonth + 1, mDay)+" 00:00:00\"";
        Toast.makeText(getApplication(),"day="+day,Toast.LENGTH_SHORT).show();
    }







}
