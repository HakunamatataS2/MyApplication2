package com.example.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editid,editpw;
    String pw,ID_result;
    Button join, login;
    static String loginid="";
    static String loginpassword="";

    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editid = (EditText)findViewById(R.id.editid);
        editpw = (EditText)findViewById(R.id.editpw);
        login = (Button)findViewById(R.id.login);
        join = (Button)findViewById(R.id.join);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                runOnUiThread(new Runnable() {
                    public void run() {

                        loginid= editid.getText().toString();
                        loginpassword= editpw.getText().toString();

                        try{//////////////////////////////예약 신청 / 취소

                            URL url = new URL(SERVER_ADDRESS + "/login.php?"
                                    + "id=" + URLEncoder.encode(loginid,"UTF-8")
                                    + "&pw=" + URLEncoder.encode(loginpassword,"UTF-8")
                            );

                            url.openStream();

                            String result = getXmlData("login.xml", "result");
                            ID_result = result;

                        } catch(Exception e) {

                            Toast.makeText(MainActivity.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                            Log.e("Error", e.getMessage());
                        }

                        if(ID_result.equals("0"))
                        {
                            Toast.makeText(MainActivity.this, "아이디 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();

                        }
                        else if(ID_result.equals("1"))
                        {
                            Toast.makeText(MainActivity.this, "로그인성공.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivityForResult(intent, 1000);
                            //  finish();
                        }

                    }
                });

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), join.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);

                editid.setText("");
                editpw.setText("");
            }
        });

    }




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
    }

}
