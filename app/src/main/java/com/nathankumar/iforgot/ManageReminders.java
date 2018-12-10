package com.nathankumar.iforgot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
                int newMonth = month + 1;
                String date = newMonth + "/" + dayOfMonth + "/" + year;
                viewthing.setText("Viewing Reminders For: " + date);
                ListView date_ListView = (ListView) findViewById(R.id.datelist);
                ArrayList<String> dates = getUrgentReminder(date);
                setListViews(dates, date_ListView);
            }
        });
    }

    public void onButtonClick(){
        Intent myIntent = new Intent(getBaseContext(), HomePage.class);
        startActivity(myIntent);
    }
    public void setListViews(ArrayList<String> reminder, ListView display) {
        ListAdapter current_list = new ArrayAdapter<String>(this, R.layout.format_bullet_point, reminder);
        display.setAdapter(current_list);
    }
    public ArrayList<String> getUrgentReminder(String date) {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Reminder> data = loadData();
        for (Reminder r : data) {
            if (r.getDate().equals(date)) {
                temp.add(r.getName() + ": " + r.getDescription());
            }
        }
        return temp;
    }

    public void saveData(List<Reminder> input) {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(input);
        //writeToFile(json, StartupActivity.this);
        editor.putString("reminders", json);
        editor.apply();
    }

    public ArrayList<Reminder> loadData() {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("reminders", "");
        //readFromFile(StartupActivity.this);
        Type type = new TypeToken<ArrayList<Reminder>>() {}.getType();
        ArrayList<Reminder> temp =  gson.fromJson(json, type);
        if (temp == null) {
            return new ArrayList<Reminder>();
        }
        return temp;
    }
}
