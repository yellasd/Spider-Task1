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

public class SinglePlayerActivity extends AppCompatActivity {

    static int scorep1=0;
    static int scorep2=0;
    RelativeLayout r1, r2;
    ImageButton rock, paper, scissors;
    ImageView playerChoice, computerChoice;
    TextView p, c;
    static int round = Integer.parseInt(Myapp.rounds);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissors = findViewById(R.id.scissors);
        playerChoice = findViewById(R.id.playerSelection);
        computerChoice = findViewById(R.id.compChoice);
        r1 = findViewById(R.id.p1back);
        r2 = findViewById(R.id.p2back);
        p = findViewById(R.id.p);
        c = findViewById(R.id.c);
        p.setText(getResources().getString(R.string.p, SplashFragment.p1, scorep2));
        c.setText(getResources().getString(R.string.computer_score,scorep1));
    }

    public void rock(View view) {
        round--;
        makeInvisible();
        evaluate(getChoice(),0);
        endGame();
    }

    public void paper(View view) {
        round--;
        makeInvisible();
        evaluate(getChoice(),1);
        endGame();
    }

    public void scissors(View view) {
        round--;
        makeInvisible();
        evaluate(getChoice(),2);
        endGame();
    }

    public int getChoice() {
        double p = Math.random();
        return (int) (Math.random() * 3);
    }

    public void makeInvisible() {
        rock.setVisibility(View.GONE);
        paper.setVisibility(View.GONE);
        scissors.setVisibility(View.GONE);
    }

    public void evaluate(final int player1, final int player2) {
        makeVisible(player1, player2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (player1 == player2) {
                    r1.setBackgroundColor(Color.parseColor("#90CAF9"));
                    r2.setBackgroundColor(Color.parseColor("#90CAF9"));
                    Toast.makeText(getApplicationContext(), "It's a draw", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((player1 == 1 && player2 == 0) || (player1 == 0 && player2 == 2) || (player1 == 2 && player2 == 1)) {
                    r1.setBackgroundColor(Color.parseColor("#00C853"));
                    r2.setBackgroundColor(Color.parseColor("#E57373"));
                    Toast.makeText(getApplicationContext(), "Computer won", Toast.LENGTH_SHORT).show();
                    scorep1 += 1;
                    c.setText(getResources().getString(R.string.computer_score,scorep1));
                    return;
                }
                r2.setBackgroundColor(Color.parseColor("#00C853"));
                r1.setBackgroundColor(Color.parseColor("#E57373"));
                scorep2 += 1;
                p.setText(getResources().getString(R.string.p,SplashFragment.p1,scorep2));
                Toast.makeText(getApplicationContext(), "You won", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    public void makeVisible(int p1choice, int p2choice) {
        if (p1choice == 0) {
            computerChoice.setImageDrawable(getResources().getDrawable(R.drawable.rock));
            computerChoice.setVisibility(View.VISIBLE);
        }
        if (p1choice == 1) {
            computerChoice.setImageDrawable(getResources().getDrawable(R.drawable.paper));
            computerChoice.setVisibility(View.VISIBLE);
        }
        if (p1choice == 2) {
            computerChoice.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
            computerChoice.setVisibility(View.VISIBLE);
        }
        if (p2choice == 0) {
            playerChoice.setImageDrawable(getResources().getDrawable(R.drawable.rock));
            playerChoice.setVisibility(View.VISIBLE);
        }
        if (p2choice == 1) {
            playerChoice.setImageDrawable(getResources().getDrawable(R.drawable.paper));
            playerChoice.setVisibility(View.VISIBLE);
        }
        if (p2choice == 2) {
            playerChoice.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
            playerChoice.setVisibility(View.VISIBLE);
        }
    }

    public void endGame() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (round != 0) {
                    Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                } else {
                    if (scorep1 > scorep2)
                        Toast.makeText(getApplicationContext(), "Oh! You lost this round", Toast.LENGTH_SHORT).show();
                    if (scorep1 == scorep2)
                        Toast.makeText(getApplicationContext(), "Interesting, it's a draw!", Toast.LENGTH_SHORT).show();
                    if (scorep1 < scorep2)
                        Toast.makeText(getApplicationContext(), "Excellent, You won this round!", Toast.LENGTH_SHORT).show();
                    scorep1 = 0;
                    scorep2 = 0;
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(i);
                }
            }
        }, 3000);
    }
}