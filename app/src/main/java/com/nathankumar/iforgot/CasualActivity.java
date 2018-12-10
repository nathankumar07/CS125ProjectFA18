package com.nathankumar.iforgot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CasualActivity extends AppCompatActivity {
    public StartupActivity act = new StartupActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casual);
        Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
    }
    public void onButtonClick() {
        EditText edit1 = (EditText)findViewById(R.id.name);
        EditText edit2 = (EditText)findViewById(R.id.desc);
        EditText edit3 = (EditText)findViewById(R.id.date);
        createNewCasualReminder(edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString());
        Context context = getApplicationContext();
        CharSequence text = "Created new casual reminder!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent myIntent = new Intent(getBaseContext(), ViewReminders.class);
        startActivity(myIntent);
    }
    public void createNewCasualReminder(String name, String desc, String date) {
        ArrayList<Reminder> temp = loadData();
        Reminder newCasualReminder = new Reminder(1, name, desc, date);
        temp.add(newCasualReminder);
        saveData(temp);
        //reminder.add(newCasualReminder);
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
