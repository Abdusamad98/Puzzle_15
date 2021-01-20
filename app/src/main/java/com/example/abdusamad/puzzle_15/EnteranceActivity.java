package com.example.abdusamad.puzzle_15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EnteranceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterance);


    }

    public void playGame(View view){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void about(View view){
        Intent intent =new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
    public void setting(View view){
        Intent intent =new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

    public void exit (View view){
        finish();
    }
}
