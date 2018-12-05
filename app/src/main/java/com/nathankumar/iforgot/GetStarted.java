package com.nathankumar.iforgot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
        Button casualButton = (Button) findViewById(R.id.button);
        casualButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClickTwo();
            }
        });
        Button urgentButton = (Button) findViewById(R.id.button2);
        urgentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClickThree();
            }
        });
        Button routineButton = (Button) findViewById(R.id.button3);
        routineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClickFour();
            }
        });
    }

    public void onButtonClick(){
        Intent myIntent = new Intent(getBaseContext(), HomePage.class);
        startActivity(myIntent);
    }
    public void onButtonClickTwo(){
        Intent myIntent = new Intent(getBaseContext(), CasualActivity.class);
        startActivity(myIntent);
    }
    public void onButtonClickThree(){
        Intent myIntent = new Intent(getBaseContext(), UrgentReminder.class);
        startActivity(myIntent);
    }
    public void onButtonClickFour(){
        Intent myIntent = new Intent(getBaseContext(), RoutineActivity.class);
        startActivity(myIntent);
    }
}
