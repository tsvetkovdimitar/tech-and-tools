package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class CarerDailyActivitiesList extends AppCompatActivity {

    FloatingActionButton addDailyActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer_daily_activites_list);

        addDailyActivityBtn = findViewById(R.id.add_activity_action_button);

        if(getIntent().hasExtra("childId")) {

            String childId = getIntent().getStringExtra("childId");

            Toast.makeText(CarerDailyActivitiesList.this, "The child ID is: " + childId, Toast.LENGTH_LONG).show();
        }
    }
}
