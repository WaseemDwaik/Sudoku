package com.projects.waseem.sudoku;
/**
 * Created by Waseem on 5/22/2016.
 */
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        LinearLayout ll = (LinearLayout) findViewById(R.id.LL1);
        SharedPreferences sp = getSharedPreferences("highScores", MODE_PRIVATE);
        Map<String, ?> hs = sp.getAll();
        int[] highscores = new int[hs.size()];
        int count = 0, limit;
        for (Map.Entry<String, ?> entry : hs.entrySet()) {
            highscores[count] = Integer.parseInt(entry.getValue().toString());
            count++;
        }
        if (hs.size() >= 10)//if less than 10 score set limit to current amount of scores
            limit = 10;
        else
            limit = count;
        highscores = doSelectionSort(highscores);//sort all scores
        for (int x = 0; x < limit; x++) {//put all high scores on screen (10 or < )
            TextView tv = new TextView(getApplicationContext());
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize((getResources().getDimension(R.dimen.textsize)));
            tv.setTextColor(Color.parseColor("#FF000000"));
            int min = (int) Math.floor(highscores[x] / 60);
            int sec = highscores[x] % 60;
            tv.setText((x + 1) + " - " + min + " : " + sec);
            ll.addView(tv);
        }

    }

    public int[] doSelectionSort(int[] arr) {//Selection sort for high scores

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;

            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }


}
