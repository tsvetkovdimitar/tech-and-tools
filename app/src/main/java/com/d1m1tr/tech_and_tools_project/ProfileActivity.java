package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    public static final String CHILD_NAME = "childName";
    public static final String CHILD_ID = "childId";

    TextView userEmail;
    Button userLogOut;

    EditText childName;
    EditText parentEmail;
    Button btnAddChild;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference databaseChild;

    ListView listViewChildren;

    List<Child> childrenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userEmail = findViewById(R.id.userEmail);
        userLogOut = findViewById(R.id.btn_profile_signout);

        childName = findViewById(R.id.edt_child_name);
        parentEmail = findViewById(R.id.edt_parent_email);
        btnAddChild = findViewById(R.id.btn_add_child);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        databaseChild = FirebaseDatabase.getInstance().getReference("child");

        userEmail.setText(mUser.getEmail());

        listViewChildren = findViewById(R.id.list_children);

        childrenList = new ArrayList<>();

        userLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addChild();

            }
        });

        listViewChildren.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Child child = childrenList.get(i);

                Intent intent = new Intent(getApplicationContext(), AddChildActivity.class);

                intent.putExtra(CHILD_ID, child.getChildId());
                intent.putExtra(CHILD_NAME, child.getChildName());

                startActivity(intent);

            }
        });

        listViewChildren.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Child child = childrenList.get(i);

                showUpdateDialog(child.getChildId(), child.getChildName());
                return true;
            }
        });



    }

    @Override
    protected void onStart(){

        super.onStart();

        databaseChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){

                    Child child = childSnapshot.getValue(Child.class);

                    childrenList.add(child);

                }

                ChildList adapter = new ChildList(ProfileActivity.this, childrenList);
                listViewChildren.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

                updateChild(childId, name, email);

                alertDialog.dismiss();

            }
        });

    }

    private boolean updateChild(String childId, String childName, String parentEmail){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("child").child(childId);

        Child child = new Child(childId, childName, parentEmail);

        databaseReference.setValue(child);

        Toast.makeText(this, "Child information updated successfully", Toast.LENGTH_LONG).show();

        return true;

    }

    private void addChild(){

        String name = childName.getText().toString().trim();
        String email = parentEmail.getText().toString().trim();

        if(!(TextUtils.isEmpty(name) && (TextUtils.isEmpty(email)))){

            String id = databaseChild.push().getKey();

            Child child = new Child(id, name, email);

            databaseChild.child(id).setValue(child);

            Toast.makeText(this, "Child added", Toast.LENGTH_LONG).show();

        }
        else{

            Toast.makeText(this, "Please, fill up all fields", Toast.LENGTH_LONG).show();

        }

    }
}
