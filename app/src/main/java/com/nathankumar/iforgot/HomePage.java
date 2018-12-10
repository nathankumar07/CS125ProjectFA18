package com.nathankumar.iforgot;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Intent newIntent = new Intent(getApplicationContext(),NotifyClass.class);
        PendingIntent PD = PendingIntent.getBroadcast(getApplicationContext(),100,newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager AM = (AlarmManager) getSystemService(ALARM_SERVICE);
        AM.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AM.INTERVAL_DAY, PD);

        Button getStartedButton = (Button) findViewById(R.id.getStarted);
        Button manageRemindersButton = (Button) findViewById(R.id.manageReminders);
        Button viewRemindersButton = (Button) findViewById(R.id.viewReminders);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
        manageRemindersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClickTwo();
            }
        });
        viewRemindersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClickViewReminders();
            }
        });
    }

    public void onButtonClick(){
        Intent myIntent = new Intent(getBaseContext(), GetStarted.class);
        startActivity(myIntent);
    }

    public void onButtonClickTwo(){
        Intent myIntent = new Intent(getBaseContext(), ManageReminders.class);
        startActivity(myIntent);
    }

    public void onButtonClickViewReminders() {
        Intent myIntentTwo = new Intent(getBaseContext(), ViewReminders.class);
        startActivity(myIntentTwo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
