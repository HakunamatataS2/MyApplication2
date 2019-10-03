package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.myapplication.MainActivity.loginid;
import static com.example.myapplication.MainActivity.loginpassword;

public class Myintroduce extends AppCompatActivity {

    Button btnback;
    TextView tvid;
    EditText editpsd;
    EditText editnewpsd;
    EditText editnewpsdchk;
    TextView tvname;
    EditText editemail;
    EditText editaddress;
    EditText editphone;
    Button btntal;
    Button btnedit;
    Button btncancel;
    String tal_result="",update_result="";
    String psd="",newpsd="",newpsdchk="";
    String email="",address="",phone="";

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myintroduce);

        tvid = (TextView) findViewById(R.id.tvid);
        editpsd = (EditText)findViewById(R.id.editpsd);
        editnewpsd = (EditText)findViewById(R.id.editnewpsd);
        editnewpsdchk = (EditText)findViewById(R.id.editnewpsdchk);
        tvname = (TextView) findViewById(R.id.tvname);
        editemail = (EditText)findViewById(R.id.editemail);
        editaddress = (EditText)findViewById(R.id.editaddress);
        editphone = (EditText)findViewById(R.id.editphone);
        btntal = (Button)findViewById(R.id.btntal);
        btnedit = (Button)findViewById(R.id.btnedit);
        btnback = (Button) findViewById(R.id.btnback);
        btncancel = (Button)findViewById(R.id.btncancel);

        tvid.setText(loginid);
        if(loginid.equals("1")){
            tvname.setText("hong");
        }
        else if(loginid.equals("2")){
            tvname.setText("kim");
        }
        else if(loginid.equals("3")){
            tvname.setText("lee");
        }



        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                psd = editpsd.getText().toString();
                newpsd = editnewpsd.getText().toString();
                newpsdchk = editnewpsdchk.getText().toString();
                email = editemail.getText().toString();
                address = editaddress.getText().toString();
                phone = editphone.getText().toString();

                if(newpsd.equals(newpsdchk) && loginpassword.equals(psd)){
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////수정
                    try{
                        URL url = new URL(SERVER_ADDRESS + "/update_create.php?"
                                + "id=" + URLEncoder.encode(loginid,"UTF-8")
                                + "&password=" + URLEncoder.encode(newpsd,"UTF-8")
                                + "&email=" + URLEncoder.encode(email,"UTF-8")
                                + "&address=" + URLEncoder.encode(address,"UTF-8")
                                + "&phone=" + URLEncoder.encode(phone,"UTF-8")
                        );
                        url.openStream();
                        String result = getXmlData("update_create.xml", "result");
                        update_result = result;
                    } catch(Exception e) {
                        Toast.makeText(Myintroduce.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                        Log.e("Error", e.getMessage());
                    }
                    if(update_result.equals("1"))
                    {
                        Toast.makeText(Myintroduce.this, "수정완료.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), mypage.class);
                        startActivityForResult(intent, 1000);
                    }
                    else
                        Toast.makeText(Myintroduce.this, "수정실패.", Toast.LENGTH_SHORT).show();
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////수정 End
                }
                else{
                    Toast.makeText(Myintroduce.this, "비밀번호 일치하지 않음.", Toast.LENGTH_SHORT).show();
                }












            }
        });

        btntal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginpassword.equals(psd)){
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////탈퇴
                    try{
                        URL url = new URL(SERVER_ADDRESS + "/tal_create.php?"
                                + "id=" + URLEncoder.encode(loginid,"UTF-8")
                        );
                        url.openStream();
                        String result = getXmlData("tal_create.xml", "result");
                        tal_result = result;
                    } catch(Exception e) {
                        Toast.makeText(Myintroduce.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                        Log.e("Error", e.getMessage());
                    }
                    if(tal_result.equals("1"))
                    {
                        Toast.makeText(Myintroduce.this, "탈퇴완료.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(intent, 1000);
                    }
                    else
                        Toast.makeText(Myintroduce.this, "탈퇴실패.", Toast.LENGTH_SHORT).show();
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////탈퇴 End
                }
                else if(loginpassword != psd){
                    Toast.makeText(Myintroduce.this, "비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Myintroduce.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mypage.class);
                startActivityForResult(intent, 1000);
            }
        } );

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mypage.class);
                startActivityForResult(intent, 1000);
            }
        });













    }/////////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End






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








}
