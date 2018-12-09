package com.nathankumar.iforgot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
        //writeToFile(json, StartupActivity.this);
        editor.putString("reminders", json);
        editor.apply();
    }

    public ArrayList<Reminder> loadData() {
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("reminders", null);
                //readFromFile(StartupActivity.this);
        Type type = new TypeToken<ArrayList<Reminder>>() {}.getType();
        ArrayList<Reminder> temp =  gson.fromJson(json, type);
        if (temp == null) {
            return new ArrayList<Reminder>();
        }
        return temp;
    }
    /**private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }**/
}
