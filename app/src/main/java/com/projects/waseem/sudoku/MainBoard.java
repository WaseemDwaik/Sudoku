package com.projects.waseem.sudoku;
/**
 * Created by Waseem on 5/22/2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainBoard extends AppCompatActivity {
    private EditText[] texts;
    SudokuGenerator sg = new SudokuGenerator();
    int sec = 0, min = 0;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);
        time = (EditText) findViewById(R.id.editText);
        texts = new EditText[81];
        for (int x = 0; x < texts.length; x++) {
            int resId = getResources().getIdentifier("editText" + (x + 1), "id", getPackageName());
            texts[x] = (EditText) findViewById(resId);
            texts[x].setFocusable(true);
        }

        int[][] board = sg.nextBoard();
        sg.print(board);
        int ed = 0;
        for (int j = 0; j < 9; j++)//set board with data/puzzle
            for (int x = 0; x < 9; x++) {
                if (board[j][x] != 0) {
                    texts[ed].setText(board[j][x] + "");
                    texts[ed].setFocusable(false);
                } else {
                    texts[ed].setText("");
                }

                ed++;
            }
        Timer T = new Timer();//timer for high scores
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(min + ":" + sec);
                        sec++;
                        if (sec == 60) {
                            min += 1;
                            sec = 0;
                        }
                    }
                });
            }
        }, 1000, 1000);


    }

    public void checkBoard(View view) {//check if board was solved
        int[][] checkBoard = new int[9][9];
        int ed = 0;
        for (int j = 0; j < 9; j++)
            for (int x = 0; x < 9; x++) {
                if (isNumber(texts[ed].getText().toString()))
                    checkBoard[j][x] = Integer.parseInt(texts[ed].getText().toString());
                else
                    checkBoard[j][x] = 0;
                ed++;
            }
        SudokuSolver ss = new SudokuSolver(MainBoard.this);
        if (ss.checkAnswer(checkBoard)) {//if it was solved save score and reset
            Toast.makeText(MainBoard.this, "You correctly solved the puzzle in ( " + min + ":" + sec + " )", Toast.LENGTH_LONG).show();
            addScore(sec, min);
            resetBoard(view);
        }

    }

    boolean isNumber(String x) {//check if number
        try {
            Integer.parseInt(x);
        } catch (Exception ex) {
            return false;

        }
        return true;
    }

    public void resetBoard(View view) {//generate new board and reset timer
        int[][] board = sg.nextBoard();
        int ed = 0;
        for (int x = 0; x < texts.length; x++) {
            texts[x].setFocusable(true);
            texts[x].setFocusableInTouchMode(true);
        }
        for (int j = 0; j < 9; j++)
            for (int x = 0; x < 9; x++) {
                if (board[j][x] != 0) {
                    texts[ed].setText(board[j][x] + "");
                    texts[ed].setFocusable(false);
                } else {
                    texts[ed].setText("");

                }

                ed++;
            }
        sec = 0;
        min = 0;


    }

    public void addScore(int sec, int min) {//Add score to shared preferences
        SharedPreferences sp = getSharedPreferences("highScores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int score = sec + (min * 60);
        editor.putInt("score" + sp.getAll().size(), score);
        editor.commit();
    }

}
