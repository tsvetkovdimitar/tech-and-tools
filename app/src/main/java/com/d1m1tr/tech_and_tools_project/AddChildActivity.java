package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddChildActivity extends AppCompatActivity {

    private TextView textViewChildName;
    private EditText editTextActivityType;
    private EditText editTextNote;
    private ListView listViewActivities;
    private Button btnAddActivity;

    DatabaseReference databaseActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_activity);

        textViewChildName = findViewById(R.id.textViewChildName);
        editTextActivityType = findViewById(R.id.edt_activity_name);
        editTextNote = findViewById(R.id.edt_activity_note);
        listViewActivities = findViewById(R.id.listviewActivities);
        btnAddActivity = findViewById(R.id.btn_add_activity);

        Intent intent = getIntent();

        String id = intent.getStringExtra(ProfileActivity.CHILD_ID);
        String name = intent.getStringExtra(ProfileActivity.CHILD_NAME);

        textViewChildName.setText(name);

        databaseActivities = FirebaseDatabase.getInstance().getReference("activities").child(id);

        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addChildActivity();

            }
        });

    }

    private void addChildActivity(){

        String activityType = editTextActivityType.getText().toString().trim();
        String activityNote = editTextNote.getText().toString().trim();

        if(!(TextUtils.isEmpty(activityType) && TextUtils.isEmpty(activityNote))){

            String id = databaseActivities.push().getKey();

            DailyActivity dailyActivity = new DailyActivity(id, activityType, activityNote);

            databaseActivities.child(id).setValue(dailyActivity);

            Toast.makeText(this, "Activity successfully added", Toast.LENGTH_LONG).show();

        }
        else{

            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();

        }

    }
}
