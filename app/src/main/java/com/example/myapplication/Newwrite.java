package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity.loginid;

public class Newwrite extends AppCompatActivity {

    Spinner spin0;
    EditText edittitle;
    EditText editcontent;
    Button btncancel;
    Button btnok;
    TextView tvid;
    String category="",write_result="",id="",content="",title="";
    int categoryposition;

    ArrayList<String> spinnerlist0 = new ArrayList<String>();

    private static final String SERVER_ADDRESS = "http://192.168.35.27/dolbom/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newwrite);

        tvid = (TextView) findViewById(R.id.tvid);
        editcontent = (EditText) findViewById(R.id.editcontent);
        edittitle = (EditText)findViewById(R.id.edittitle);
        btncancel = (Button)findViewById(R.id.btncancel);
        btnok = (Button)findViewById(R.id.btnok);
        spin0 = (Spinner)findViewById(R.id.spin0);

        tvid.setText(loginid);

        spinnerlist0.add( "선택하세요" );
        spinnerlist0.add("돌봄이경험담");
        spinnerlist0.add("돌봄이후기");
        spinnerlist0.add("하고싶은이야기");

        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>( this,R.layout.support_simple_spinner_dropdown_item,spinnerlist0 );
        spin0.setAdapter( adapter0 );

        spin0.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = spin0.getSelectedItem().toString();
                categoryposition = position;
                Toast.makeText(Newwrite.this, "category="+category, Toast.LENGTH_SHORT).show();
                Toast.makeText(Newwrite.this, "categoryposition="+categoryposition, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );









        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////btnok
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /////////////////////////////////////////////////////////////////////////////////////////////////////////db저장 experience
                if(categoryposition == 1){  //category.equals("돌봄이경험담")

                    runOnUiThread(new Runnable() {
                        public void run() {

                            id = tvid.getText().toString();
                            content = editcontent.getText().toString();
                            title = edittitle.getText().toString();

                            try{//////////////////////////////예약 신청 / 취소

                                URL url = new URL(SERVER_ADDRESS + "/experience_create_check.php?"
                                        + "title=" + URLEncoder.encode( title,"UTF-8")
                                );

                                url.openStream();

                                String result = getXmlData("experience_create_check.xml", "result");
                                write_result = result;

                            } catch(Exception e) {

                                Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            if(write_result.equals("0"))
                            {

                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/experience_create.php?"
                                            + "id=" + URLEncoder.encode(id,"UTF-8")
                                            + "&cat=" + URLEncoder.encode(category,"UTF-8")
                                            + "&title=" + URLEncoder.encode(title,"UTF-8")
                                            + "&content=" + URLEncoder.encode(content,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("experience_create.xml", "result");
                                    write_result = result;

                                } catch(Exception e) {
                                    Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }

                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/experience_insert_check.php?"
                                            + "title=" + URLEncoder.encode(title,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("experience_insert_check.xml", "result");
                                    write_result = result;
                                    //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();//////////////////////////////////////
                                } catch(Exception e) {
                                    Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.3", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }

                                if(write_result.equals("1"))
                                {
                                    tvid.setText("");
                                    edittitle.setText("");
                                    editcontent.setText("");
                                    spin0.setSelection( 0 );

                                    Toast.makeText(Newwrite.this, "신청완료.", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Community.class);
                                    startActivityForResult(intent, 1000);
                                    /*
                                    Intent result = new Intent();
                                    setResult(RESULT_OK, result);
                                    finish();
                                    */
                                }
                                else
                                    Toast.makeText(Newwrite.this, "신청실패.", Toast.LENGTH_SHORT).show();

                            }
                            else if(write_result.equals("1"))
                            {
                                Toast.makeText(Newwrite.this, "다시 신청해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////runOnUiThread End

                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////db저장 experience End
                /////////////////////////////////////////////////////////////////////////////////////////////////////////db저장 review
                else if (categoryposition == 2) {  //category.equals("돌봄이후기")

                    runOnUiThread(new Runnable() {
                        public void run() {

                            id = tvid.getText().toString();
                            content = editcontent.getText().toString();
                            title = edittitle.getText().toString();

                            try{//////////////////////////////예약 신청 / 취소

                                URL url = new URL(SERVER_ADDRESS + "/review_create_check.php?"
                                        + "title=" + URLEncoder.encode( title,"UTF-8")
                                );

                                url.openStream();

                                String result = getXmlData("review_create_check.xml", "result");
                                write_result = result;

                            } catch(Exception e) {

                                Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            if(write_result.equals("0"))
                            {

                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/review_create.php?"
                                            + "id=" + URLEncoder.encode(id,"UTF-8")
                                            + "&cat=" + URLEncoder.encode(category,"UTF-8")
                                            + "&title=" + URLEncoder.encode(title,"UTF-8")
                                            + "&content=" + URLEncoder.encode(content,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("review_create.xml", "result");
                                    write_result = result;

                                } catch(Exception e) {
                                    Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }

                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/review_insert_check.php?"
                                            + "title=" + URLEncoder.encode(title,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("review_insert_check.xml", "result");
                                    write_result = result;
                                    //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();//////////////////////////////////////
                                } catch(Exception e) {
                                    Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.3", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }

                                if(write_result.equals("1"))
                                {
                                    tvid.setText("");
                                    edittitle.setText("");
                                    editcontent.setText("");
                                    spin0.setSelection( 0 );

                                    Toast.makeText(Newwrite.this, "신청완료.", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Community.class);
                                    startActivityForResult(intent, 1000);
                                    /*
                                    Intent result = new Intent();
                                    setResult(RESULT_OK, result);
                                    finish();
                                    */
                                }
                                else
                                    Toast.makeText(Newwrite.this, "신청실패.", Toast.LENGTH_SHORT).show();

                            }
                            else if(write_result.equals("1"))
                            {
                                Toast.makeText(Newwrite.this, "다시 신청해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////runOnUiThread End

                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////db저장 review End
                /////////////////////////////////////////////////////////////////////////////////////////////////////////db저장 story
                else if (categoryposition == 3) {  //category.equals("하고싶은이야기")

                    runOnUiThread(new Runnable() {
                        public void run() {

                            id = tvid.getText().toString();
                            content = editcontent.getText().toString();
                            title = edittitle.getText().toString();

                            try{//////////////////////////////예약 신청 / 취소

                                URL url = new URL(SERVER_ADDRESS + "/story_create_check.php?"
                                        + "title=" + URLEncoder.encode( title,"UTF-8")
                                );

                                url.openStream();

                                String result = getXmlData("story_create_check.xml", "result");
                                write_result = result;

                            } catch(Exception e) {

                                Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.1", Toast.LENGTH_SHORT).show();
                                Log.e("Error", e.getMessage());
                            }

                            if(write_result.equals("0"))
                            {

                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/story_create.php?"
                                            + "id=" + URLEncoder.encode(id,"UTF-8")
                                            + "&cat=" + URLEncoder.encode(category,"UTF-8")
                                            + "&title=" + URLEncoder.encode(title,"UTF-8")
                                            + "&content=" + URLEncoder.encode(content,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("story_create.xml", "result");
                                    write_result = result;

                                } catch(Exception e) {
                                    Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.2", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }

                                try{
                                    URL url = new URL(SERVER_ADDRESS + "/story_insert_check.php?"
                                            + "title=" + URLEncoder.encode(title,"UTF-8")
                                    );
                                    url.openStream();
                                    String result = getXmlData("story_insert_check.xml", "result");
                                    write_result = result;
                                    //Toast.makeText( write.this, result ,Toast.LENGTH_SHORT ).show();//////////////////////////////////////
                                } catch(Exception e) {
                                    Toast.makeText(Newwrite.this, "인터넷 연결을 확인하세요.3", Toast.LENGTH_SHORT).show();
                                    Log.e("Error", e.getMessage());
                                }

                                if(write_result.equals("1"))
                                {
                                    tvid.setText("");
                                    edittitle.setText("");
                                    editcontent.setText("");
                                    spin0.setSelection( 0 );

                                    Toast.makeText(Newwrite.this, "신청완료.", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Community.class);
                                    startActivityForResult(intent, 1000);
                                    /*
                                    Intent result = new Intent();
                                    setResult(RESULT_OK, result);
                                    finish();
                                    */
                                }
                                else
                                    Toast.makeText(Newwrite.this, "신청실패.", Toast.LENGTH_SHORT).show();

                            }
                            else if(write_result.equals("1"))
                            {
                                Toast.makeText(Newwrite.this, "다시 신청해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////runOnUiThread End

                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////db저장 story End


            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////btnok End







        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Community.class);
                startActivityForResult(intent, 1000);
            }
        });


    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////onCreate End




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





}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////class End
