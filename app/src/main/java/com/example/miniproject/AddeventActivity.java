package com.example.miniproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddeventActivity extends AppCompatActivity {
    private EditText etEventName, etEventDate;
    private Button btnAddEvent;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent); // Ensure your layout file name matches

        etEventName = findViewById(R.id.etEventName);
        etEventDate = findViewById(R.id.etEventDate);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        db = new DatabaseHelper(this);

        btnAddEvent.setOnClickListener(view -> {
            String eventName = etEventName.getText().toString().trim();
            String eventDate = etEventDate.getText().toString().trim();

            // Basic validation
            if (eventName.isEmpty() || eventDate.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.addEvent(eventName, eventDate)) {
                Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity and return to the previous one
            } else {
                Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
