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
    UrgentReminder act = new UrgentReminder();
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
        ArrayList<Reminder> reminders = act.loadData();
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
}
