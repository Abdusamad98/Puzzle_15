package com.example.abdusamad.puzzle_15;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    public ViewGroup recordLayout;
    public RecordSaver recordSaver;
    public TimeSaver timeSaver;

    public TextView record1;
    public TextView record2;
    public TextView record3;

    public TextView time1;
    public TextView time2;
    public TextView time3;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        recordSaver= new RecordSaver(getApplicationContext());
        timeSaver= new TimeSaver(getApplicationContext());
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }

    }

    public void Records(View view){

        recordLayout = findViewById(R.id.recordLayout);
        recordLayout.setVisibility(View.VISIBLE);


        record1=findViewById(R.id.record1);
        record2=findViewById(R.id.record2);
        record3=findViewById(R.id.record3);



        record1.setText(""+recordSaver.getRecord("RECORD1"));
        record2.setText(""+recordSaver.getRecord("RECORD2"));
        record3.setText(""+recordSaver.getRecord("RECORD3"));

        if(record1.getText().equals("5000")) record1.setText("0");
        if(record2.getText().equals("5000")) record2.setText("0");
        if(record3.getText().equals("5000")) record3.setText("0");

           timeRecords();

    }

    public void timeRecords(){


        time1=findViewById(R.id.time1);
        time2=findViewById(R.id.time2);
        time3=findViewById(R.id.time3);


   if(timeSaver.getRecord("TIME1")!=5000) time1.setText("0"+timeSaver.getRecord("TIME1")/60+":"+timeSaver.getRecord("TIME1")%60); else time1.setText("00:00");
   if(timeSaver.getRecord("TIME2")!=5000) time2.setText("0"+timeSaver.getRecord("TIME2")/60+":"+timeSaver.getRecord("TIME2")%60); else time2.setText("00:00");
   if(timeSaver.getRecord("TIME3")!=5000) time3.setText("0"+timeSaver.getRecord("TIME3")/60+":"+timeSaver.getRecord("TIME3")%60); else time3.setText("00:00");

    }

    public void deleteRecords(View view){
        recordSaver.clear();
        timeSaver.clear();
        Toast.makeText(this,"Your records succesfully deleted! ",Toast.LENGTH_SHORT).show();
    }


    public void back(View view){
        recordLayout.setVisibility(View.INVISIBLE);
    }


    public void backing(View view){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
