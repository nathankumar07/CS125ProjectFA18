package com.nathankumar.iforgot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(StartupActivity.this, HomePage.class);
            startActivity(intent);
            SharedPreferences.Editor editor = preferences.edit();

            //For Debugging Purposes ONLY, will reset On Boarding screen every other startup.
            editor.putString("FirstTimeInstall" , "No");
            editor.apply();
            //End of debugging segment
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall" , "Yes");
            editor.apply();
        }

        Button finishButton = (Button) findViewById(R.id.finish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
    }
    public void onButtonClick(){
        List<Reminder> reminders = new ArrayList<>();
        saveData(reminders);

        Intent myIntent = new Intent(getBaseContext(), HomePage.class);
        startActivity(myIntent);
    }

    public void saveData(List<Reminder> input) {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(input);
        editor.putString("reminders", json);
        editor.apply();
    }

    public List<Reminder> loadData() {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("reminders", null);
        Type type = new TypeToken<ArrayList<Reminder>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
