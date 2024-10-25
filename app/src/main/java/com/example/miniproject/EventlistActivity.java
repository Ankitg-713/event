package com.example.miniproject;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class EventlistActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventlist);

        listView = findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        // Fetch all events and display in ListView
        loadEvents();
    }

    private void loadEvents() {
        Cursor cursor = db.getAllEvents();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No events found", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> eventList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String eventName = cursor.getString(cursor.getColumnIndexOrThrow("event_name"));
            String eventDate = cursor.getString(cursor.getColumnIndexOrThrow("event_date"));
            eventList.add(eventName + " - " + eventDate);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventList);
        listView.setAdapter(adapter);
    }
}