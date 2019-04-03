package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CarerProfileActivity extends AppCompatActivity {

    public static final String CHILD_NAME = "childName";
    public static final String CHILD_ID = "childId";
    private static final String TAG = "DocSnippets";

    private String name;

    private TextView userName;
    private String userId;


//    private EditText childName;
//    private EditText parentEmail;
//    private Button btnAddChild;

    private ProgressBar progressbar;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;

//    private DatabaseReference databaseChild;

//    private ListView listViewChildren;

    private List<Child> childrenList;

    private Toolbar carerProfileToolbar;
    private BottomNavigationView carerProfileBottomNavView;

    private AllParentsFragment allParentsFragment;
    private CarerHomeFragment carerHomeFragment;
    private CarerAccountFragment carerAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer_profile);

//        userEmail = findViewById(R.id.userEmail);
//
//        childName = findViewById(R.id.edt_child_name);
//        parentEmail = findViewById(R.id.edt_parent_email);

        userName = findViewById(R.id.userName);
//        btnAddChild = findViewById(R.id.btn_add_child);

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

//        firebaseFirestore.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                if(task.isSuccessful()){
//
//                    if(task.getResult().exists()){
//
//                        Toast.makeText(CarerProfileActivity.this, "Data exists", Toast.LENGTH_LONG).show();
//                        String name = task.getResult().getString("name");
//                        userName.setText(name);
//
//                    }
//                    else{
//
//                        Toast.makeText(CarerProfileActivity.this, "Data does not exists", Toast.LENGTH_LONG).show();
//
//                    }
//
//
//
//                }
//                else {
//
//                    Toast.makeText(CarerProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//        });

//        databaseChild = FirebaseDatabase.getInstance().getReference("child");

//        userEmail.setText(mUser.getEmail());

//        listViewChildren = findViewById(R.id.list_children);

        childrenList = new ArrayList<>();

        carerProfileToolbar = findViewById(R.id.carer_profile_toolbar);
        setSupportActionBar(carerProfileToolbar);
        getSupportActionBar().setTitle("Nana Carer Profile");

        if(mAuth.getCurrentUser() != null){


            carerProfileBottomNavView = findViewById(R.id.carer_bottom_nav);

            //Fragments
            allParentsFragment = new AllParentsFragment();
            carerHomeFragment = new CarerHomeFragment();
            carerAccountFragment = new CarerAccountFragment();

            replaceFragment(allParentsFragment);

            carerProfileBottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch(menuItem.getItemId()){

                        case R.id.btn_carer_bottom_parents:
                            replaceFragment(allParentsFragment);
                            return true;

                        case R.id.btn_bottom_carer_account:
                            replaceFragment(carerAccountFragment);
                            return true;


                        case R.id.btn_bottom_carer_home:
                            replaceFragment(carerHomeFragment);
                            return true;

                        default:
                            return false;

                    }
                }
            });

        }



//        btnAddChild.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                addChild();
//
//            }
//        });

//        listViewChildren.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Child child = childrenList.get(i);
//
//                Intent intent = new Intent(getApplicationContext(), AddChildActivity.class);
//
//                intent.putExtra(CHILD_ID, child.getChildId());
//                intent.putExtra(CHILD_NAME, child.getChildName());
//
//                startActivity(intent);
//
//            }
//        });
//
//        listViewChildren.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Child child = childrenList.get(i);
//
//                showUpdateDialog(child.getChildId(), child.getChildName());
//                return true;
//            }
//        });




    }

    @Override
    protected void onStart(){

        super.onStart();

//        databaseChild.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){
//
//                    Child child = childSnapshot.getValue(Child.class);
//
//                    childrenList.add(child);
//
//                }
//
//                ChildList adapter = new ChildList(CarerProfileActivity.this, childrenList);
//                listViewChildren.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    private void showUpdateDialog(final String childId, String childName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inslater = getLayoutInflater();

        final View dialogView = inslater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.update_child_name);
        final EditText editTextEmail = dialogView.findViewById(R.id.update_parent_name);
        final Button btnUpdate = dialogView.findViewById(R.id.btn_update_child_info);

        dialogBuilder.setTitle("Update activity information for " + childName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                if(TextUtils.isEmpty(name)){

                    editTextName.setError("Name is required");
                    return;

                }
                else if(TextUtils.isEmpty(email)){

                    editTextEmail.setError("Email is required");
                    return;
                }

//                updateChild(childId, name, email);

                alertDialog.dismiss();

            }
        });

    }

//    private boolean updateChild(String childId, String childName, String parentEmail){
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("child").child(childId);
//
//        Child child = new Child(childId, childName, childAge, parentEmail);
//
//        databaseReference.setValue(child);
//
//        Toast.makeText(this, "Child information updated successfully", Toast.LENGTH_LONG).show();
//
//        return true;
//
//    }

//    private void addChild(){
//
//        String name = childName.getText().toString().trim();
//        String email = parentEmail.getText().toString().trim();
//
//        if(!(TextUtils.isEmpty(name) && (TextUtils.isEmpty(email)))){
//
//            String id = databaseChild.push().getKey();
//
//            Child child = new Child(id, name, email);
//
//            databaseChild.push().setValue(child);
//
//            Toast.makeText(this, "Child added", Toast.LENGTH_LONG).show();
//
//        }
//        else{
//
//            Toast.makeText(this, "Please, fill up all fields", Toast.LENGTH_LONG).show();
//
//        }
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.carer_profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){

            case R.id.btn_logout_carer:
                logOut();
                return true;

            default:
                return false;
        }

    }

    public void logOut(){

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(CarerProfileActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.carer_frame_layout, fragment);
        fragmentTransaction.commit();

    }
}
