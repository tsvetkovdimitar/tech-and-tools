package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ParentProfileActivity extends AppCompatActivity {

    private String name;

    private TextView userName;

    private Toolbar parentToolBar;

    private BottomNavigationView parentProfileBottomNavView;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;

    private ParentAccountFragment parentAccountFragment;
    private ParentChildrenFragment parentChildrenFragment;
    private ParentHomeFragment parentHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        parentToolBar = findViewById(R.id.parent_profile_toolbar);
        setSupportActionBar(parentToolBar);

        getSupportActionBar().setTitle("NANA Parent Profile");

        userName = findViewById(R.id.parentName);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = mUser.getUid();

        db.collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    DocumentSnapshot document = task.getResult();

                    if(document.exists()){

                        name = document.getString("userName");
                        String greeting = getString(R.string.carer_profile_greeting_text);
                        greeting += " " + name;
                        userName.setText(greeting);

                    }

                }

            }
        });

        if(mAuth.getCurrentUser() != null){


            parentProfileBottomNavView = findViewById(R.id.parent_bottom_nav);

            parentAccountFragment = new ParentAccountFragment();
            parentHomeFragment = new ParentHomeFragment();
            parentChildrenFragment = new ParentChildrenFragment();

            replaceFragment(parentChildrenFragment);

            parentProfileBottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch(menuItem.getItemId()){

                        case R.id.btn_parent_bottom_children:
                            replaceFragment(parentChildrenFragment);
                            return true;

                        case R.id.btn_bottom_parent_home:
                            replaceFragment(parentHomeFragment);
                            return true;


                        case R.id.btn_bottom_parent_account:
                            replaceFragment(parentAccountFragment);
                            return true;

                        default:
                            return false;

                    }
                }
            });

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.parent_profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.btn_logout_parent:
                logOut();
                return true;

            case R.id.btn_parent_add_children:
                AddChildDialog addChildDialog = new AddChildDialog();
                addChildDialog.show(getSupportFragmentManager(), "Add Child Dialog");
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

    private void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.parent_frame_layout, fragment);
        fragmentTransaction.commit();

    }

}
