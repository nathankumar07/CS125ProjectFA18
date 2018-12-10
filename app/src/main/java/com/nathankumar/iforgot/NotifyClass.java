package com.nathankumar.iforgot;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NotifyClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Urgent Reminders for today:")
                .setContentText(getText())
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());

    }
    public String getText() {
        ArrayList<Reminder> reminders = loadData();
        boolean isThereData = false;
        String text = "";
        for (Reminder r : reminders) {
            if (r.getType() == 0) {
                isThereData = true;
                text = text + r.getName() + ": " + r.getDescription() + "     ";
            }
        }
        if (isThereData) {
            return text;
        } else {
            text = "no reminders for today!";
            return text;
        }
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
