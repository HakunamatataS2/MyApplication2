package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Newwriteframe extends AppCompatActivity{

    private Activity activity;
    Button btnback;
    Button btnadd;
    ImageButton imgbtnrefresh;
    TextView tvid;
    TextView tvcategory;
    TextView tvtitle;
    TextView tvcontent;
    ListView simpleList;
    EditText editcomment;
    String number="",id="",cat="",title="",content="",write_result="",cmcontent="",delete_result="",delcmcontent="";
    int total=0;
    int count=0;

    String[] xml_array=new String[1000];
    String[] content_array=new String[12];
    int pop=0;
    ArrayList<String> items = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";
    private static final String SERVER_ADDRESS2 = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newwriteframe);
        activity=this;

        btnback = (Button)findViewById(R.id.btnback);
        btnadd = (Button)findViewById(R.id.btnadd);
        tvid = (TextView)findViewById(R.id.tvid);
        tvcategory = (TextView)findViewById(R.id.tvcategory);
        tvtitle = (TextView)findViewById(R.id.tvtitle);
        tvcontent = (TextView)findViewById(R.id.tvcontent);
        editcomment = (EditText)findViewById(R.id.editcomment);
        imgbtnrefresh = (ImageButton)findViewById(R.id.imgbtnrefresh);
        simpleList = (ListView)findViewById(R.id.Listview);



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,items );
        //simpleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );



        //////////////////////////////////////////////////////////////////////////////////////////////getintent________community에서 가져옴
        Intent intent = getIntent();
        number = intent.getStringExtra("number");
        id = intent.getStringExtra("id");
        cat = intent.getStringExtra("cat");
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        //////////////////////////////////////////////////////////////////////////////////////////////getintent End


        /////////////////////////////////////////////////////////////////////////////////////////////////////값넣기
        tvid.setText(id);
        tvcategory.setText(cat);
        tvtitle.setText(title);
        tvcontent.setText(content);
        /////////////////////////////////////////////////////////////////////////////////////////////////////값넣기 End


        //////////////////////////////////////////////////////////////////////////////////////////////////////comment 가져오기
        if(total==0) {
            try {
                URL url = new URL(SERVER_ADDRESS2 + "/comment_list.php?"
                );
                url.openStream();
                getXmlData2("comment_list.xml");
            } catch (Exception e) {
                Toast.makeText(Newwriteframe.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                Log.e("Error", e.getMessage());
            }
            int b = 0;
            while (xml_array[b] != null) {
                b++;
            }
            for (int t = 1; t < b; t += 6) {
                if (cat.equals(xml_array[t]) && number.equals(xml_array[t + 3]))
                {
                    if (!items.contains(xml_array[t + 4]))
                    {
                        items.add(xml_array[t + 4]);
                        adapter.notifyDataSetChanged();
                        content_array[count] = xml_array[t + 4];
                        count++;
                        //Toast.makeText(Newwriteframe.this, "나들어옴", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////comment 가져오기 End





        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                delcmcontent = content_array[position];
                Toast.makeText(Newwriteframe.this, "delcmcontent="+delcmcontent, Toast.LENGTH_SHORT).show();

                // 다이얼로그 바디
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(activity);

                // 삭제버튼
                alertdialog.setPositiveButton("삭제", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/comment_delete.php?"
                                            + "cat=" + URLEncoder.encode(cat,"UTF-8")
                                            + "&writenumber=" + URLEncoder.encode(number,"UTF-8")
                                            + "&cmcontent=" + URLEncoder.encode(delcmcontent,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("comment_delete.xml", "result");
                                    delete_result = result;
                                } catch(Exception e) {
                                    Toast.makeText(Newwriteframe.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }
                                if(delete_result.equals("1"))
                                {

                                    items.remove(delcmcontent);
                                    adapter.notifyDataSetChanged();


                                    for(int i=0; i<content_array.length;i++)
                                    {
                                        if(content_array[i].equals(delcmcontent))
                                        {
                                            content_array[i] = null;
                                            for (int j = i; j <= content_array.length-i; j++)
                                            {
                                                content_array[j] = content_array[j+1];
                                            }
                                            break;
                                        }
                                    }

                                    for(int q=1;q<xml_array.length;q+=6){
                                        if ((xml_array[q].equals(cat) == true) & (xml_array[q + 3].equals(number) == true))
                                        {
                                            if (delcmcontent.equals(xml_array[q + 4]))
                                            {
                                                if(q==1){
                                                    /*xml_array[q - 1]=null;
                                                    xml_array[q]=null;
                                                    xml_array[q + 1]=null;
                                                    xml_array[q + 2]=null;
                                                    xml_array[q + 3]=null;
                                                    xml_array[q + 4]=null;*/
                                                    for (int j = q-1; j <= xml_array.length-6; j++)
                                                    {
                                                        xml_array[j] = xml_array[j+6];
                                                    }
                                                    break;
                                                }
                                                else{
                                                    /*xml_array[q - 1]=null;
                                                    xml_array[q]=null;
                                                    xml_array[q + 1]=null;
                                                    xml_array[q + 2]=null;
                                                    xml_array[q + 3]=null;
                                                    xml_array[q + 4]=null;*/
                                                    for (int j = q-1; j <= xml_array.length-(j*2); j++)
                                                    {
                                                        xml_array[j] = xml_array[j+6];
                                                    }
                                                    break;
                                                }

                                            }
                                        }
                                    }

                                    Toast.makeText(Newwriteframe.this, "삭제완료.1", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Newwriteframe.this, "삭제실패.1", Toast.LENGTH_SHORT).show();
                                }

                    }
                });

                // 취소버튼
                alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Newwriteframe.this, "취소", Toast.LENGTH_SHORT).show();
                    }
                });

                // 메인 다이얼로그 생성
                AlertDialog alert = alertdialog.create();

                // 타이틀
                alert.setTitle("댓글을 삭제하시겠습니까?");

                // 다이얼로그 보기
                alert.show();
            }
        });




        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total++;
                cmcontent=editcomment.getText().toString();

                try{
                    URL url = new URL(SERVER_ADDRESS + "/comment_create.php?"
                            + "cat=" + URLEncoder.encode(cat,"UTF-8")
                            + "&writeid=" + URLEncoder.encode(id,"UTF-8")
                            + "&loginid=" + URLEncoder.encode(loginid,"UTF-8")
                            + "&writenumber=" + URLEncoder.encode(number,"UTF-8")
                            + "&cmcontent=" + URLEncoder.encode(cmcontent,"UTF-8")
                    );
                    url.openStream();
                    String result = getXmlData("comment_create.xml", "result");
                    write_result = result;
                } catch(Exception e) {
                    Toast.makeText(Newwriteframe.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                if(write_result.equals("1")) {
                    editcomment.setText("");

                    //////////////////////////////////////////////////////////////////////////////////////////////////////comment 가져오기
                    try{
                        URL url = new URL(SERVER_ADDRESS2 + "/comment_list2.php?"
                        );
                        url.openStream();
                        getXmlData2("comment_list2.xml");
                    } catch(Exception e) {
                        Toast.makeText(Newwriteframe.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                        Log.e("Error", e.getMessage());
                    }
                    int b=0;
                    while(xml_array[b]!=null)
                    {
                        b++;
                    }
                    for (int t = 1; t < b; t += 6) {
                        if (cat.equals(xml_array[t]) && number.equals(xml_array[t + 3]))
                        {
                            if (!items.contains(xml_array[t + 4]))
                            {
                                items.add(xml_array[t + 4]);
                                adapter.notifyDataSetChanged();
                                content_array[count] = xml_array[t + 4];
                                count++;
                                Toast.makeText(Newwriteframe.this, "나들어옴", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                }
            }
        });


        imgbtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                for (int t = 1; t < xml_array.length; t += 6)
                {
                    if ((xml_array[t].equals(cat)) & (xml_array[t + 3].equals(number)))//if (TextUtils.equals(xml_array[t],cat) & TextUtils.equals(xml_array[t + 3],number)) {
                    {
                        if (!items.contains(xml_array[t + 4]))
                        {
                            items.add(xml_array[t + 4]);
                            adapter.notifyDataSetChanged();
                            content_array[count] = xml_array[t + 4];
                            count++;
                            Toast.makeText(Newwriteframe.this, "나들어옴", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
*/


                for(int i=0;i<content_array.length;i++){
                    if(content_array[i]==null){
                        break;
                    }
                    Toast.makeText(Newwriteframe.this, "content_array["+i+"]="+content_array[i], Toast.LENGTH_SHORT).show();
                }

                /*
                try {
                    URL url = new URL(SERVER_ADDRESS2 + "/comment_list.php?"
                    );
                    url.openStream();
                    getXmlData2("comment_list.xml");
                } catch (Exception e) {
                    Toast.makeText(Newwriteframe.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", e.getMessage());
                }
                int b = 0;
                while (xml_array[b] != null) {
                    b++;
                }
                for (int t = 1; t < b; t += 6) {
                    if ((xml_array[t].equals(cat) == true) & (xml_array[t + 3].equals(number) == true)) {
                        if (!items.contains(xml_array[t + 4])) {
                            items.add(xml_array[t + 4]);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                Toast.makeText(Newwriteframe.this, "refresh OK", Toast.LENGTH_SHORT).show();*/
            }

        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Community.class);
                startActivityForResult(intent, 1000);
            }
        });


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
        String rss = SERVER_ADDRESS2 + "/";
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










}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////class End


















