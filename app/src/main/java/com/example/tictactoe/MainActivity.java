package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0: Circle , 1: Cross, -1: Empty
    int activePlayer = 1;
    int[] gameStats = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameStats[tappedCounter] == -1 && gameActive)
        {
            gameStats[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions)
            {
                if (gameStats[winningPosition[0]] == gameStats[winningPosition[1]] && gameStats[winningPosition[1]] == gameStats[winningPosition[2]] && gameStats[winningPosition[0]] != -1)
                {
                    // Someone has won
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 0)
                    {
                        winner = "Cross";
                    }
                    else
                    {
                        winner = "Circle";
                    }
                    Button playButton = (Button) findViewById(R.id.playButton);
                    TextView winnerTextview = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextview.setText(winner+" has won!");
                    playButton.setVisibility(view.VISIBLE);
                    winnerTextview.setVisibility(view.VISIBLE);
                }
                if(gameStats[0] != -1 && gameStats[1] != -1 && gameStats[2] != -1 && gameStats[3] != -1 && gameStats[4] != -1 && gameStats[5] != -1 && gameStats[6] != -1 && gameStats[7] != -1 && gameStats[8] != -1)
                {
                    gameActive = false;
                    String winner = "It's a tie";
                    Button playButton = (Button) findViewById(R.id.playButton);
                    TextView winnerTextview = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextview.setText(winner+"!");
                    playButton.setVisibility(view.VISIBLE);
                    winnerTextview.setVisibility(view.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view)
    {
        Button playButton = (Button) findViewById(R.id.playButton);
        TextView winnerTextview = (TextView) findViewById(R.id.winnerTextView);
        playButton.setVisibility(view.INVISIBLE);
        winnerTextview.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0; i<gameStats.length; i++)
        {
            gameStats[i]= -1;
        }
        activePlayer = 1;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}