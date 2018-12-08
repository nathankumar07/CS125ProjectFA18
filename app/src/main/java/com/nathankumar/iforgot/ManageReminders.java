package com.nathankumar.iforgot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class ManageReminders extends AppCompatActivity {

    TextView viewthing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reminders);

        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });

        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        viewthing = (TextView) findViewById(R.id.mini);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/" + year;
                viewthing.setText("Viewing Reminders For: " + date);
            }
        });
    }

    public void onButtonClick(){
        Intent myIntent = new Intent(getBaseContext(), HomePage.class);
        startActivity(myIntent);
    }
}
