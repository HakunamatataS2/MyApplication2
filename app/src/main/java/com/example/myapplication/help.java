package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.ViewFlipper;

public class help extends AppCompatActivity implements View.OnTouchListener{

    Button btnback;
    ViewFlipper flipper1,flipper2;
    float xAtDown;
    float xAtUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        btnback = (Button)findViewById( R.id.btnback );
        flipper1 = (ViewFlipper) findViewById(R.id.viewflipper1);
        flipper2 = (ViewFlipper) findViewById(R.id.viewflipper2);
        flipper1.setOnTouchListener(this);
        flipper2.setOnTouchListener(this);

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("서비스소개") ;
        tabHost1.addTab(ts1)  ;

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("돌봄이서비스") ;
        tabHost1.addTab(ts2) ;

        btnback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(intent, 1000);
            }
        } );
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return
        if (v != flipper1 && v != flipper2)
            return false;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xAtDown = event.getX(); // 터치 시작지점 x좌표 저장
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            xAtUp = event.getX(); // 터치 끝난지점 x좌표 저장
                if (xAtUp < xAtDown) {
                    // 왼쪽 방향 에니메이션 지정
                    if(v == flipper1)
                    {
                        flipper1.setInAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_left_in ) );

                        flipper1.setOutAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_left_out ) );

                        // 다음 view 보여줌
                        flipper1.showNext();
                    }
                    else{
                        flipper2.setInAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_left_in ) );

                        flipper2.setOutAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_left_out ) );

                        // 다음 view 보여줌
                        flipper2.showNext();
                    }
                } else if (xAtUp > xAtDown) {
                    // 오른쪽 방향 에니메이션 지정
                    if( v == flipper1) {
                        flipper1.setInAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_right_in ) );
                        flipper1.setOutAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_right_out ) );
                        // 전 view 보여줌
                        flipper1.showPrevious();
                    }
                    else{
                        flipper2.setInAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_right_in ) );
                        flipper2.setOutAnimation( AnimationUtils.loadAnimation( this,
                                R.anim.push_right_out ) );
                        // 전 view 보여줌
                        flipper2.showPrevious();
                    }
                }
            }
        return true;
    }
}
