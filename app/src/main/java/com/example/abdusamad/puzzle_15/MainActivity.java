package com.example.abdusamad.puzzle_15;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private ArrayList<Integer> data;

    private Coordinate space;
    private static ArrayList<String> textN;
    private static int spaceX,spaceY;
    private static ArrayList<Integer> coordinateX;
    private static ArrayList<Integer> coordinateY;
    private int scoreCount;
    private  boolean currentlyTime = false;
    public int score;
    public static int COUNT=4;
    public static int size=COUNT*COUNT;
    private TextView textScore;
    public ViewGroup winnerLayout;
    private Chronometer textTimer;
    public RecordSaver recordSaver;
    public TimeSaver timeSaver;
    private TextView record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recordSaver= new RecordSaver(getApplicationContext());
        timeSaver= new TimeSaver(getApplicationContext());
        loadViews();
        loadData();
        loadDataToViews();
        setTitle("Puzzle 15");



    }

    private void loadViews() {
        record=findViewById(R.id.record);
        winnerLayout = findViewById(R.id.winnerLayout);
        textScore = findViewById(R.id.textScore);
        ViewGroup group = findViewById(R.id.group);
        textTimer = findViewById(R.id.textTimer);

        buttons = new Button[COUNT][COUNT];
        for (int i = 0; i < group.getChildCount(); i++) {
            int x = i / COUNT;
            int y = i % COUNT;
            buttons[x][y] = (Button) group.getChildAt(i);
            buttons[x][y].setOnClickListener(this::click);
            buttons[x][y].setSoundEffectsEnabled(true);
            buttons[x][y].setTag(new Coordinate(x, y));
        }
    }

    private void loadData() {
        data = new ArrayList<>();
        for (int i = 1; i <= size-1; i++) {
            data.add(i);
        }
       Collections.shuffle(data);
    }
    private void loadDataToViews() {


        textTimer.setBase(SystemClock.elapsedRealtime());
        textTimer.start();
        textScore.setText("" + (scoreCount = 0));

        for (int i = 0; i < size-1; i++) {
            buttons[i / COUNT][i % COUNT].setText(data.get(i).toString());
        }

        if (space != null) {
            buttons[space.getX()][space.getY()].setBackgroundResource(R.drawable.bg_button);
        }
        buttons[COUNT-1][COUNT-1].setText("");
        buttons[COUNT-1][COUNT-1].setBackgroundResource(R.color.colorButton);
        space = (Coordinate) buttons[COUNT-1][COUNT-1].getTag();

    }

    public void click(View view) {
        Button button = (Button) view;
        Coordinate c = (Coordinate) button.getTag();

        int dx = Math.abs(c.getX() - space.getX());
        int dy = Math.abs(c.getY() - space.getY());
        if (dx + dy==1) {
            textScore.setText("" + (++scoreCount));
            buttons[space.getX()][space.getY()].setText(button.getText());
            buttons[space.getX()][space.getY()].setBackgroundResource(R.drawable.bg_button);
            button.setText("");
            button.setBackgroundResource(R.color.colorButton);
            space = c;
            if (isWinner()) {
                winnerLayout.setVisibility(View.VISIBLE);
                recordSaver.setRecord(scoreCount);
                textTimer.stop();
                String time=textTimer.getText().toString();
                timeSaver.setTime(time);

                Toast.makeText(this, "You are winner!", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private boolean isWinner() {
        if (space.getY() != COUNT-1 && space.getX() != COUNT-1) return false;
        boolean cond = true;
        for (int i = 0; i < size-1; i++) {
            cond &= buttons[i / COUNT][i % COUNT].getText().equals(String.valueOf(i + 1));
        }
        return cond;
    }

    @Override
    protected void onStart() {
        super.onStart();
        record.setText(recordSaver.getRecord("RECORD1")+"");
        if (record.getText().equals("5000")) record.setText("0");
    }

    public void finishClick(View view) {
        finish();
    }

    public void restartClick(View view) {
        loadData();
        loadDataToViews();
        record.setText(recordSaver.getRecord("RECORD1")+"");
    }

    public void newGameClick(View view) {
        winnerLayout.setVisibility(View.GONE);
        restartClick(view);
}

    public void exitClick(View view) {
       finish();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView textView = findViewById(R.id.textScore);

        score = Integer.parseInt(textView.getText().toString());
        outState.putInt("scoreCount",scoreCount);
        outState.putInt("SCORE", score);
        outState.putLong("time", textTimer.getBase());
        outState.putBooleanArray("isTiming", new boolean[]{currentlyTime});

        coordinateX = new ArrayList<>();
        coordinateY = new ArrayList<>();
        textN = new ArrayList<>();


        for (int i = 0; i < size; i++) {
            int x = i / COUNT;
            int y = i % COUNT;

            if (buttons[x][y] != buttons[space.getX()][space.getX()]) {
                coordinateX.add(((Coordinate) buttons[x][y].getTag()).getX());
                coordinateY.add(((Coordinate) buttons[x][y].getTag()).getY());

                textN.add(buttons[x][y].getText().toString());
            } else {
                coordinateX.add(((Coordinate) buttons[x][y].getTag()).getX());
                coordinateY.add(((Coordinate) buttons[x][y].getTag()).getY());

                textN.add(buttons[x][y].getText().toString());
            }
        }


        spaceX = space.getX();
        spaceY = space.getY();
        outState.putInt("SPACEX", spaceX);
        outState.putInt("SPACEY", spaceY);

        outState.putIntegerArrayList("COORDINATEX", coordinateX);
        outState.putIntegerArrayList("COORDINATEY", coordinateY);
        outState.putStringArrayList("TEXTN", textN);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            TextView textView = findViewById(R.id.textScore);
            score = savedInstanceState.getInt("SCORE");
            scoreCount=savedInstanceState.getInt("scoreCount");
            textView.setText("" + score);
            textTimer.setBase(savedInstanceState.getLong("time"));
            if (savedInstanceState.getBoolean("isTiming")){
                textTimer.start();
                currentlyTime = savedInstanceState.getBoolean("isTiming");
            }


            spaceX = savedInstanceState.getInt("SPACEX");
            spaceY = savedInstanceState.getInt("SPACEY");

            coordinateX = savedInstanceState.getIntegerArrayList("COORDINATEX");
            coordinateY = savedInstanceState.getIntegerArrayList("COORDINATEY");

            textN = savedInstanceState.getStringArrayList("TEXTN");

            for (int i = 0; i < size; i++) {
                int x = i / COUNT;
                int y = i % COUNT;

                if (buttons[x][y] != buttons[spaceX][spaceY]) {
                    buttons[x][y].setTag(new Coordinate(coordinateX.get(i), coordinateY.get(i)));
                    String btnText = textN.get(i);
                    buttons[x][y].setText(btnText);

                } else {
                    space = (Coordinate) buttons[x][y].getTag();
                    buttons[x][y].setTag(new Coordinate(spaceX, spaceY));
                    buttons[x][y].setText("");

                }
            }


        }
    }



}
