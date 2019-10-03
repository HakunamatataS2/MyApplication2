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

public class Searchlist extends AppCompatActivity {

    Button btnback;
    TextView tvtitle;
    ListView simpleList;
    String dolbom="";

    String[] xml_array=new String[1000];
    int pop=0;
    ArrayList<String> items = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        Intent intent = getIntent();
        dolbom = intent.getExtras().getString("search");


        try{//////////////////////////////////////////////////////////////////////////////////////////////////////검색어 db 찾기,가져오기
            URL url = new URL(SERVER_ADDRESS + "/item_search.php?"
                    + "dolbom=" + URLEncoder.encode(dolbom,"UTF-8")
            );
            url.openStream();
            getXmlData2("item_search.xml");
        } catch(Exception e) {
            Toast.makeText(Searchlist.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
        int b=0;
        while(xml_array[b]!=null)
        {
            b++;
        }//////////////////////////////////////////////////////////////////////////////////////////////////////검색어 db 찾기,가져오기 End


        btnback = (Button) findViewById(R.id.btnback);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        simpleList = (ListView) findViewById(R.id.Listview);


        tvtitle.setText(dolbom);


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items) ;
        simpleList.setAdapter(adapter);
        simpleList.setBackgroundColor( Color.BLACK );


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Searchlist.this , Writeframe.class);
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



        for(int t=1; t<b; t+=15)//////////////////////////////////////////////////////////////////////////////////simpleList 글 보여주기
        {
            items.add(xml_array[t]);
            adapter.notifyDataSetChanged();
        }



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(intent, 1000);
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
    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getXmlData2 End




}
