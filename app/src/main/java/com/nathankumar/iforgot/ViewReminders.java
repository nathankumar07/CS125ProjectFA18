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
        createRecyclerViews();
        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
    }
    public void createRecyclerViews() {
        ListView UR_ListView = (ListView) findViewById(R.id.listone);
        ListView CR_RecyclerView = (ListView) findViewById(R.id.listtwo);
        ListView CR_RecyclerView2 = (ListView) findViewById(R.id.listthree);
        ArrayList<String> urgentReminder = getUrgentReminder();
        ArrayList<String> casualReminder = getCasualReminder();
        ArrayList<String> routineReminder = getRoutineReminder();
        setListViews(urgentReminder, UR_ListView);
        setListViews(casualReminder, CR_RecyclerView);
        setListViews(routineReminder, CR_RecyclerView2);
    }
    public void setListViews(ArrayList<String> reminder, ListView display) {
        ListAdapter current_list = new ArrayAdapter<String>(this, R.layout.format_bullet_point, reminder);
        display.setAdapter(current_list);
    }
    public ArrayList<String> getUrgentReminder() {
        ArrayList<String> list_UG = new ArrayList<>();
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getType() == 0) {
                list_UG.add(r.getName() + ": " + r.getDescription());
            }
        }
        return list_UG;
    }
    public ArrayList<String> getCasualReminder() {
        ArrayList<String> list_CR = new ArrayList<>();
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getType() == 1) {
                list_CR.add(r.getName() + ": " + r.getDescription());
            }
        }
        return list_CR;
    }
    public ArrayList<String> getRoutineReminder() {
        ArrayList<String> list_CR = new ArrayList<>();
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getType() == 2) {
                list_CR.add(r.getName() + ": " + r.getDescription());
            }
        }
        return list_CR;
    }

    public void onButtonClick(){
        Intent myIntent = new Intent(getBaseContext(), HomePage.class);
        startActivity(myIntent);
    }
}
