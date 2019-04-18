package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class CarerDailyActivitiesList extends AppCompatActivity {

    FloatingActionButton addDailyActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer_daily_activites_list);

        addDailyActivityBtn = findViewById(R.id.add_activity_action_button);

        addDailyActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().hasExtra("childId") && getIntent().hasExtra("parentId")) {

                    String childId = getIntent().getStringExtra("childId");
                    String parentId = getIntent().getStringExtra("parentId");

                    Toast.makeText(CarerDailyActivitiesList.this, "The child ID is: " + childId +
                            " And parent ID is: " + parentId, Toast.LENGTH_LONG).show();

                    openAddDailyActivityDialog(parentId, childId);

                }
            }
        });

    }

    private void openAddDailyActivityDialog(String parentId, String childId) {

        Bundle bundle = new Bundle();
        bundle.putString("parentId", parentId);
        bundle.putString("childId", childId);

        AddDailyActivityDialog addDailyActivityDialog = new AddDailyActivityDialog();
        addDailyActivityDialog.show(getSupportFragmentManager(), "Daily Activity Dialog");
        addDailyActivityDialog.setArguments(bundle);

    }
}
