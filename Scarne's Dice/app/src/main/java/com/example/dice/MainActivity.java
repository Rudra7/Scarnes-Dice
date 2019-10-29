package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int uTurnScore=0,uTotalScore=0,cTurnScore=0,cTotalScore=0,value=0,won=-1;
    Handler handler;
    ImageView image;
    Random random;
    Button roll,hold;
    TextView display,result;
    //This function just recreates the whole program from beginning when reset button pressed
    public void restart(View view)
    {
        recreate();
    }
    //To check who is the winner
    public void winCheck()
    {
        //I have made some UI part to invisible so that when the result is displayed only reset button should be functional
        if (uTotalScore>=100)
        {
            result.setText("Player Wins!!!!!");
            result.setVisibility(View.VISIBLE);
            roll.setEnabled(false);
            hold.setEnabled(false);
            image.setVisibility(View.INVISIBLE);
            won=1;
        }
        else if (cTotalScore>=100)
        {
            result.setText("Computer Wins!!!!");
            image.setVisibility(View.INVISIBLE);
            result.setVisibility(View.VISIBLE);
            roll.setEnabled(false);
            hold.setEnabled(false);
            won=1;
        }
    }
    public int pickNumber()
    {
        value=0;
        while (value==0)
            value=random.nextInt(7);
        switch (value)
        {
            case 1:
                //Image is made invisible so that when the some number repeats it should not create confusion
                image.setVisibility(View.INVISIBLE);
                image.setImageResource(R.drawable.dice1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        image.setVisibility(View.VISIBLE);
                    }
                }, 300);
                break;
            case 2:
                image.setVisibility(View.INVISIBLE);
                image.setImageResource(R.drawable.dice2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        image.setVisibility(View.VISIBLE);
                    }
                }, 300);
                break;
            case 3:
                image.setVisibility(View.INVISIBLE);
                image.setImageResource(R.drawable.dice3);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        image.setVisibility(View.VISIBLE);
                    }
                }, 300);
                break;
            case 4:
                image.setVisibility(View.INVISIBLE);
                image.setImageResource(R.drawable.dice4);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        image.setVisibility(View.VISIBLE);
                    }
                }, 300);
                break;
            case 5:
                image.setVisibility(View.INVISIBLE);
                image.setImageResource(R.drawable.dice5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        image.setVisibility(View.VISIBLE);
                    }
                }, 300);
                break;
            case 6:
                image.setVisibility(View.INVISIBLE);
                image.setImageResource(R.drawable.dice6);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        image.setVisibility(View.VISIBLE);
                    }
                }, 300);
                break;
        }
        return value;
    }
    public void computerTurn()
    {
        int temp=0;
        value=0;
        //When its computer turn user should not play so two buttons are disabled
        roll.setEnabled(false);
        hold.setEnabled(false);
        value=pickNumber();
        if (value==1)
        {
            cTurnScore=0;
            cTotalScore+=cTurnScore;
            display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore);
            roll.setEnabled(true);
            hold.setEnabled(true);
        }
        else if (cTurnScore<20)
        {
            cTurnScore+=value;
            display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore+" Computer turn score : "+cTurnScore);
            if (cTotalScore>60)
            {
                temp=cTurnScore+cTotalScore;
                if (temp>=100)
                {
                    cTotalScore+=cTurnScore;
                    winCheck();
                    if (won==-1)
                    {
                        display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore);
                    }
                }
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    computerTurn();
                }
            }, 2000);
        }
        else
        {
            cTotalScore+=cTurnScore;
            Toast.makeText(this, "Computer holds!!", Toast.LENGTH_SHORT).show();
            winCheck();
            if (won==-1)
            {
                display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore);
                roll.setEnabled(true);
                hold.setEnabled(true);
                cTurnScore=0;
            }
        }
    }
    public void rollFunc(View view)
    {
        value=0;
        value=pickNumber();
        if (value==1)
        {
            roll.setEnabled(false);
            uTurnScore=0;
            display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    computerTurn();
                }
            }, 1500);
        }
        else
        {
            uTurnScore+=value;
            display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore+" Your turn score : "+uTurnScore);
        }
    }
    public void holdFunc(View view)
    {
        uTotalScore+=uTurnScore;
        display.setText("Your score : "+uTotalScore+" Computer score : "+cTotalScore);
        winCheck();
        if (won==-1)
        {
            uTurnScore=0;
            computerTurn();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random=new Random();
        image=findViewById(R.id.imageView);
        display=findViewById(R.id.scoreText);
        roll=findViewById(R.id.rollButton);
        hold=findViewById(R.id.holdButton);
        result=findViewById(R.id.finalResult);
        handler = new Handler();
    }
}
