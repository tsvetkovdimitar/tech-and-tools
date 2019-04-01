package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class ParentProfileActivity extends AppCompatActivity {

    private Toolbar parentToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        parentToolBar = findViewById(R.id.parent_profile_toolbar);
        setSupportActionBar(parentToolBar);

        getSupportActionBar().setTitle("NANA Parent Profile");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.parent_profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){

            case R.id.btn_logout_parent:
                logOut();
                return true;

            default:
                return false;
        }

    }

    public void logOut(){

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ParentProfileActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

}
