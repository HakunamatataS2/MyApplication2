package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.myapplication.MainActivity.loginid;

public class Writeintro extends AppCompatActivity {

    EditText editinto;
    Button btnok;
    Button btncancel;
    String introduce="",write_result="";

    String[] xml_array=new String[1000] ;
    int pop=0;

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writeintro);

        editinto = (EditText)findViewById(R.id.editinto);
        btnok = (Button)findViewById(R.id.btnok);
        btncancel = (Button)findViewById(R.id.btncancel);



        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                runOnUiThread(new Runnable() {
                    public void run() {

                        introduce = editinto.getText().toString();

                        try{
                            URL url = new URL(SERVER_ADDRESS + "/intro_create_check.php?"
                                    + "id=" + URLEncoder.encode(loginid,"UTF-8")
                            );
                            url.openStream();
                            String result = getXmlData("intro_create_check.xml", "result");
                            write_result = result;
                            //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////////////

                        } catch(Exception e) {
                            Toast.makeText(Writeintro.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                            Log.e("Error", e.getMessage());
                        }


                        if(write_result.equals("1"))
                        {
                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/intro_create.php?"
                                        + "id=" + URLEncoder.encode(loginid,"UTF-8")
                                        + "&introduce=" + URLEncoder.encode(introduce,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("intro_create.xml", "result");
                                write_result = result;
                                //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();///////////////////////////////////////
                            } catch(Exception e) {
                                Toast.makeText(Writeintro.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }


                            if(write_result.equals("1"))
                            {
                                editinto.setText("");
                                Toast.makeText(Writeintro.this, "완료.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), mypage.class);
                                startActivityForResult(intent, 1000);
                            }
                            else
                                Toast.makeText(Writeintro.this, "실패.", Toast.LENGTH_SHORT).show();



                        }
                        else if(write_result.equals("0"))
                        {
                            Toast.makeText(Writeintro.this, "다시해주세요.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });



            }
        });


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mypage.class);
                startActivityForResult(intent, 1000);
            }
        });



    }///////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End










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











}///////////////////////////////////////////////////////////////////////////////////////////////////////////class End
