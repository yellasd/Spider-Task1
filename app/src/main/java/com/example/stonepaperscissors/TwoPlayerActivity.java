package com.example.stonepaperscissors;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TwoPlayerActivity extends AppCompatActivity {

    static int scorepl1 = 0;
    static int scorepl2 = 0;
    RelativeLayout r1, r2;
    ImageButton rockp1, rockp2, paper1, paper2, scissors1, scissors2;
    ImageView player1Choice, player2Choice;
    int choice1 = -1, choice2 = -1;
    TextView p1, p2;
    static int round = Integer.parseInt(Myapp.rounds);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);


        rockp1 = findViewById(R.id.rock1);
        paper1 = findViewById(R.id.paper1);
        scissors1 = findViewById(R.id.scissors1);
        rockp2 = findViewById(R.id.rock2);
        paper2 = findViewById(R.id.paper2);
        scissors2 = findViewById(R.id.scissors2);
        player1Choice = findViewById(R.id.player1Choice);
        player2Choice = findViewById(R.id.player2Choice);
        r1 = findViewById(R.id.p1back);
        r2 = findViewById(R.id.p2back);
        p1 = findViewById(R.id.player_1_score);
        p2 = findViewById(R.id.player_2_score);
        p1.setText(getResources().getString(R.string.p_1, SplashFragment.p1, scorepl1));
        p2.setText(getResources().getString(R.string.p_2, SplashFragment.p2, scorepl2));
    }

    public void rock1(View view) {
        round--;
        makeInvisiblep1();
        choice1 = 0;
        goAhead();
    }

    public void paper1(View view) {
        round--;
        makeInvisiblep1();
        choice1 = 1;
        goAhead();
    }

    public void scissors1(View view) {
        round--;
        makeInvisiblep1();
        choice1 = 2;
        goAhead();
    }

    public void makeInvisiblep1() {
        rockp1.setVisibility(View.GONE);
        paper1.setVisibility(View.GONE);
        scissors1.setVisibility(View.GONE);
    }

    public void rock2(View view) {
        makeInvisiblep2();
        choice2 = 0;
        goAhead();
    }

    public void paper2(View view) {
        makeInvisiblep2();
        choice2 = 1;
        goAhead();
    }

    public void scissors2(View view) {
        makeInvisiblep2();
        choice2 = 2;
        goAhead();
    }

    public void makeInvisiblep2() {
        rockp2.setVisibility(View.GONE);
        paper2.setVisibility(View.GONE);
        scissors2.setVisibility(View.GONE);
    }

    public void goAhead() {
        if (choice1 < 0 || choice2 < 0) return;
        makeVisible();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (choice1 == choice2) {
                    r1.setBackgroundColor(Color.parseColor("#90CAF9"));
                    r2.setBackgroundColor(Color.parseColor("#90CAF9"));
                    Toast.makeText(getApplicationContext(), "It's a draw", Toast.LENGTH_SHORT).show();
                    endGame();
                    return;
                }
                if ((choice1 == 1 && choice2 == 0) || (choice1 == 0 && choice2 == 2) || (choice1 == 2 && choice2 == 1)) {
                    r1.setBackgroundColor(Color.parseColor("#00C853"));
                    r2.setBackgroundColor(Color.parseColor("#E57373"));
                    Toast.makeText(getApplicationContext(), "Player 1 won", Toast.LENGTH_SHORT).show();
                    scorepl1 += 1;
                    p1.setText(getResources().getString(R.string.p_1, SplashFragment.p1,scorepl1));
                    endGame();
                    return;
                }
                r2.setBackgroundColor(Color.parseColor("#00C853"));
                r1.setBackgroundColor(Color.parseColor("#E57373"));
                scorepl2 += 1;
                p2.setText(getResources().getString(R.string.p_2, SplashFragment.p2, scorepl2));
                Toast.makeText(getApplicationContext(), "Player 2 won", Toast.LENGTH_SHORT).show();
                endGame();
            }
        }, 2000);
    }

    public void makeVisible() {
        if (choice1 == 0) {
            player1Choice.setImageDrawable(getResources().getDrawable(R.drawable.rock_rev));
            player1Choice.setVisibility(View.VISIBLE);
        }
        if (choice1 == 1) {
            player1Choice.setImageDrawable(getResources().getDrawable(R.drawable.paper_rev));
            player1Choice.setVisibility(View.VISIBLE);
        }
        if (choice1 == 2) {
            player1Choice.setImageDrawable(getResources().getDrawable(R.drawable.scissors_rev));
            player1Choice.setVisibility(View.VISIBLE);
        }
        if (choice2 == 0) {
            player2Choice.setImageDrawable(getResources().getDrawable(R.drawable.rock));
            player2Choice.setVisibility(View.VISIBLE);
        }
        if (choice2 == 1) {
            player2Choice.setImageDrawable(getResources().getDrawable(R.drawable.paper));
            player2Choice.setVisibility(View.VISIBLE);
        }
        if (choice2 == 2) {
            player2Choice.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
            player2Choice.setVisibility(View.VISIBLE);
        }
    }

    public void endGame() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (round != 0) {
                    Intent intent = new Intent(getApplicationContext(), TwoPlayerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                } else {
                    if (scorepl1 > scorepl2)
                        Toast.makeText(getApplicationContext(), "Player 1 won this round!", Toast.LENGTH_SHORT).show();
                    if (scorepl1 == scorepl2)
                        Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
                    if (scorepl1 < scorepl2)
                        Toast.makeText(getApplicationContext(), "Player2 won this round!", Toast.LENGTH_SHORT).show();
                    scorepl1 = 0;
                    scorepl2 = 0;
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(i);
                }
            }
        }, 3000);
    }
}