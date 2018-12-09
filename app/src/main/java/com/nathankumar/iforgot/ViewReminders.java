package com.nathankumar.iforgot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewReminders extends AppCompatActivity {
    public StartupActivity act = new StartupActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminders);
        createListViews();
        final EditText UR_text = (EditText)findViewById(R.id.UR_textView);
        final EditText CR_text = (EditText)findViewById(R.id.CR_TextView);
        final EditText CR_text2 = (EditText)findViewById(R.id.CR_TextView2);
        Button backButton = (Button) findViewById(R.id.back);
        Button Done1 = (Button) findViewById(R.id.Done1);
        Button Done2 = (Button) findViewById(R.id.Done2);
        Button Done3 = (Button) findViewById(R.id.Done3);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonClick();
            }
        });
        Done1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (UR_text.getText().toString().equals("")) {
                    updateList(UR_text.getText().toString());
                } else {
                    Toast noResponseNoted = new Toast.makeText(ViewReminders.this, "Please input a valid activity", Toast.LENGTH_SHORT);
                    noResponseNoted.show();
                }
            }
        });
        Done2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CR_text.getText().toString().equals("")) {
                    updateList(CR_text.getText().toString());
                } else {
                    Toast noResponseNoted = new Toast.makeText(ViewReminders.this, "Please input a valid activity", Toast.LENGTH_SHORT);
                    noResponseNoted.show();
                }
            }
        });
        Done3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CR_text2.getText().toString().equals("")) {
                    updateList(CR_text2.getText().toString());
                } else {
                    Toast noResponseNoted = new Toast.makeText(ViewReminders.this, "Please input a valid activity", Toast.LENGTH_SHORT);
                    noResponseNoted.show();
                }
            }
        });
    }
    public void updateList(String text) {
        boolean doesExist = false;
        ArrayList<Reminder> temp = act.loadData();
        for (Reminder r : temp) {
            if (r.getName().equals(text)) {
                doesExist = true;
                temp.remove(r);
                break;
            }
        }
        if (doesExist) {
            act.saveData(temp);
        } else {
            Toast noResponseNoted = new Toast.makeText(ViewReminders.this, "Please input a valid activity", Toast.LENGTH_SHORT);
            noResponseNoted.show();
        }
    }
    public void createListViews() {
        ListView UR_ListView = (ListView) findViewById(R.id.listone);
        ListView CR_RecyclerView = (ListView) findViewById(R.id.listtwo);
        ListView CR_RecyclerView2 = (ListView) findViewById(R.id.listthree);
        ArrayList<String> urgentReminder = getUrgentReminder();
        ArrayList<String> casualReminder = getCasualReminder();
        ArrayList<String> routineReminder = getRoutineReminder();
        if (urgentReminder != null) {
            setListViews(urgentReminder, UR_ListView);
        }
        if (casualReminder != null) {
            setListViews(casualReminder, CR_RecyclerView);
        }
        if (routineReminder != null) {
            setListViews(routineReminder, CR_RecyclerView2);
        }
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
