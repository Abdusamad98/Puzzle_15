package com.example.abdusamad.puzzle_15;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
              finish();
                Intent intent=new Intent(SplashActivity.this,EnteranceActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}
