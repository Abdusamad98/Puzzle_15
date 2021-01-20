package com.example.abdusamad.puzzle_15;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.Collections;

public class RecordSaver{
    private SharedPreferences preferences;

    RecordSaver(Context context) {
        preferences = context.getSharedPreferences("Example", Context.MODE_PRIVATE);
    }

    public int getRecord(String k) {
        return preferences.getInt(k, 5000);
    }


    public void setRecord(int count) {

        ArrayList<Integer> recordlar=new ArrayList<>();
        recordlar.add(getRecord("RECORD1"));
        recordlar.add(getRecord("RECORD2"));
        recordlar.add(getRecord("RECORD3"));
        recordlar.add(count);
        Collections.sort(recordlar);

        preferences.edit().putInt("RECORD1",recordlar.get(0)).apply();
        preferences.edit().putInt("RECORD2", recordlar.get(1)).apply();
        preferences.edit().putInt("RECORD3", recordlar.get(2)).apply();

    }

    public void clear(){
        preferences.edit().clear().apply();
    }







    }


class TimeSaver {
    private SharedPreferences preferences;

    TimeSaver(Context context) {
        preferences = context.getSharedPreferences("ExampleTime", Context.MODE_PRIVATE);
    }
    public  int getSeconds(String time){
        int seconds=0;
        String array[]=time.split(":");
        if(array.length==2){
            seconds=Integer.parseInt(array[0])*60 + Integer.parseInt(array[1]);
        }
        else if (array.length==3){
            seconds=Integer.parseInt(array[0])*60*60+Integer.parseInt(array[1])*60+Integer.parseInt(array[2]);
        }
        return seconds;
    }


    public void setTime(String timeText) {

        ArrayList<Integer> timeInts=new ArrayList<>();
        timeInts.add(getRecord("TIME1"));
        timeInts.add(getRecord("TIME2"));
        timeInts.add(getRecord("TIME3"));
        timeInts.add(getSeconds(timeText));
        Collections.sort(timeInts);


        preferences.edit().putInt("TIME1",timeInts.get(0)).apply();
        preferences.edit().putInt("TIME2",timeInts.get(1)).apply();
        preferences.edit().putInt("TIME3",timeInts.get(2)).apply();
    }

    public int getRecord(String k) {
        return preferences.getInt(k, 5000);
    }

    public void clear(){
        preferences.edit().clear().apply();
    }
}