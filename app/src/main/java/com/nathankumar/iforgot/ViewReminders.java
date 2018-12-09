package com.nathankumar.iforgot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewReminders extends AppCompatActivity {
    public StartupActivity act = new StartupActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminders);
        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
    }
    public void createRecyclerViews() {
        ListView UR_ListView = (ListView) findViewById(R.id.UG_list);
        ListView CR_RecyclerView = (ListView) findViewById(R.id.CR_list);
        ListView CR_RecyclerView2 = (ListView) findViewById(R.id.CR_list2);
        ArrayList<Reminder> urgentReminder = getUrgentReminder();
        ArrayList<Reminder> casualReminder = getCasualReminder();
        ArrayList<Reminder> routineReminder = getRoutineReminder();
        setListViews(urgentReminder, UR_ListView);
        setListViews(casualReminder, CR_RecyclerView);
        setListViews(routineReminder, CR_RecyclerView2);
    }
    public void setListViews(ArrayList<Reminder> reminder, ListView display) {
        ListAdapter current_list = new ArrayAdapter<Reminder>(this, R.layout.format_bullet_point, reminder);
        display.setAdapter(current_list);
    }
    public ArrayList<Reminder> getUrgentReminder() {
        ArrayList<Reminder> list_UG = new ArrayList<>();
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getType() == 0) {
                list_UG.add(r);
            }
        }
        return list_UG;
    }
    public ArrayList<Reminder> getCasualReminder() {
        ArrayList<Reminder> list_CR = new ArrayList<>();
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getType() == 1) {
                list_CR.add(r);
            }
        }
        return list_CR;
    }
    public ArrayList<Reminder> getRoutineReminder() {
        ArrayList<Reminder> list_CR = new ArrayList<>();
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getType() == 2) {
                list_CR.add(r);
            }
        }
        return list_CR;
    }

    public void onButtonClick(){
        Intent myIntent = new Intent(getBaseContext(), HomePage.class);
        startActivity(myIntent);
    }
}
