package com.projects.waseem.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/**
 * Created by Waseem on 5/22/2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, MainBoard.class);
        startActivity(intent);

    }

    public void showScoreBoard(View view) {
        Intent intent = new Intent(MainActivity.this, ScoreBoard.class);
        startActivity(intent);
    }

    public void exit(View view) {
        System.exit(0);
    }
}
