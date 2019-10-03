package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class join extends AppCompatActivity {

    EditText editid;
    EditText editpassword;
    EditText editcheck;
    EditText editname;
    EditText editbirth;
    RadioGroup radiogender;
    RadioButton men,woman;
    EditText editemail;
    EditText editaddress;
    EditText editnumber;
    Button btnjoin;
    Button btncancel;
    String id="",pw="",name="",birth="",gender="",email="",address="",phone="",ID_result="";

    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editid = (EditText)findViewById(R.id.editid);
        editpassword = (EditText)findViewById(R.id.editpassword);
        editcheck = (EditText)findViewById(R.id.editcheck);
        editname = (EditText)findViewById(R.id.editname);
        editbirth = (EditText)findViewById(R.id.editbirth);
        editemail = (EditText)findViewById(R.id.editemail);
        editaddress = (EditText)findViewById(R.id.editaddress);
        editnumber = (EditText)findViewById(R.id.editnumber);

        btnjoin = (Button)findViewById(R.id.btnjoin);
        btncancel = (Button)findViewById(R.id.btncancel);
        radiogender = (RadioGroup)findViewById(R.id.radiogender);
        men = (RadioButton)findViewById(R.id.men);
        woman = (RadioButton)findViewById(R.id.woman);

        editcheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = editpassword.getText().toString();
                String confirm = editcheck.getText().toString();
                if( password.equals(confirm) ) {
                    editpassword.setBackgroundColor(Color.GREEN);
                    editcheck.setBackgroundColor(Color.GREEN);
                } else {
                    editpassword.setBackgroundColor(Color.RED);
                    editcheck.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        radiogender.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.men){
                    //Toast.makeText(write.this, "고졸", Toast.LENGTH_SHORT).show();
                    gender = men.getText().toString();
                    //hak = "0";
                }
                else if(i == R.id.woman){
                    //Toast.makeText(write.this, "전문대졸", Toast.LENGTH_SHORT).show();
                    gender = woman.getText().toString();
                    //hak = "1";
                }
            }
        } );







        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 아이디 입력 확인
                if( editid.getText().toString().length() == 0 ) {
                    Toast.makeText(join.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    editid.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( editpassword.getText().toString().length() == 0 ) {
                    Toast.makeText(join.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    editpassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if( editcheck.getText().toString().length() == 0 ) {
                    Toast.makeText(join.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    editcheck.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if( !editpassword.getText().toString().equals(editcheck.getText().toString()) ) {
                    Toast.makeText(join.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    editpassword.setText("");
                    editcheck.setText("");
                    editpassword.requestFocus();
                    return;
                }

                // 이름 입력 확인
                if( editname.getText().toString().length() == 0 ) {
                    Toast.makeText(join.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    editname.requestFocus();
                    return;
                }

                // 이메일 아이디 입력 확인
                if( editemail.getText().toString().length() == 0 ) {
                    Toast.makeText(join.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
                    editemail.requestFocus();
                    return;
                }


                ////////////////////////////////////////////////////////////////////////////////////

                runOnUiThread(new Runnable() {
                    public void run() {

                        id= editid.getText().toString();
                        pw= editpassword.getText().toString();
                        name= editname.getText().toString();
                        birth= editbirth.getText().toString();
                        email= editemail.getText().toString();
                        address= editaddress.getText().toString();
                        phone= editnumber.getText().toString();

                        try{//////////////////////////////예약 신청 / 취소

                            URL url = new URL(SERVER_ADDRESS + "/id_create_check.php?"
                                    + "id=" + URLEncoder.encode(id,"UTF-8")
                            );

                            url.openStream();

                            String result = getXmlData("id_create_check.xml", "result");
                            ID_result = result;

                        } catch(Exception e) {

                            Toast.makeText(join.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                            Log.e("Error", e.getMessage());
                        }

                        if(ID_result.equals("0"))
                        {


                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/member_create.php?"
                                        + "id=" + URLEncoder.encode(id,"UTF-8")
                                        + "&pw=" + URLEncoder.encode(pw,"UTF-8")
                                        + "&name=" + URLEncoder.encode(name,"UTF-8")
                                        + "&birth=" + URLEncoder.encode(birth,"UTF-8")
                                        + "&gender=" + URLEncoder.encode(gender,"UTF-8")
                                        + "&email=" + URLEncoder.encode(email,"UTF-8")
                                        + "&address=" + URLEncoder.encode(address,"UTF-8")
                                        + "&phone=" + URLEncoder.encode(phone,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("member_create.xml", "result");
                                ID_result = result;
                            } catch(Exception e) {
                                Toast.makeText(join.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            try{//////////////////////////////예약 신청 / 취소
                                URL url = new URL(SERVER_ADDRESS + "/member_insert_check.php?"
                                        + "id=" + URLEncoder.encode(id,"UTF-8")
                                );
                                url.openStream();
                                String result = getXmlData("member_insert_check.xml", "result");
                                ID_result = result;
                            } catch(Exception e) {
                                Toast.makeText(join.this, "인터넷 연결을 확인하세요.3", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            if(ID_result.equals("1"))
                            {
                                editid.setText("");
                                editpassword.setText("");
                                editname.setText("");
                                editbirth.setText("");
                                radiogender.clearCheck();
                                editemail.setText("");
                                editaddress.setText("");
                                editnumber.setText("");

                                Toast.makeText(join.this, "회원가입 완료.", Toast.LENGTH_SHORT).show();
                                Intent result = new Intent();
                                result.putExtra("id", editid.getText().toString());

                                // 자신을 호출한 Activity로 데이터를 보낸다.
                                setResult(RESULT_OK, result);
                                finish();
                            }
                            else
                                Toast.makeText(join.this, "회원가입 실패.", Toast.LENGTH_SHORT).show();



                        }
                        else if(ID_result.equals("1"))
                        {
                            Toast.makeText(join.this, "해당 아이디가 이미 가입되어 있습니다.", Toast.LENGTH_SHORT).show();


                        }


                    }
                });


//finish();

            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                finish();
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

