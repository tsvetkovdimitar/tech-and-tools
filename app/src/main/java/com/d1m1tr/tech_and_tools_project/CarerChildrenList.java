package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CarerChildrenList extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ArrayList<Child> childrenList;
    ChildrenRecyclerViewAdapter childrenRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer_children_list);

        childrenList = new ArrayList<>();

        setUpRecyclerView();
        setUpFireBase();
        loadDataFromFireStore();
    }

    private void loadDataFromFireStore() {

        if(childrenList.size() > 0){

            childrenList.clear();

        }

        db.collection("users").document("bMjtaYmYqFewpeCSVYsJiUUyEKe2").collection("children").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot documentSnapshot: task.getResult()){

                    Child child = new Child(documentSnapshot.getString("childName"),
                                            documentSnapshot.getString("childAge"),
                                            documentSnapshot.getDate("dateRegistered"),
                                            documentSnapshot.getString("parentId"));

                    childrenList.add(child);

                    childrenRecyclerViewAdapter = new ChildrenRecyclerViewAdapter(CarerChildrenList.this, childrenList);
                    recyclerView.setAdapter(childrenRecyclerViewAdapter);

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(CarerChildrenList.this, "Error ---l---", Toast.LENGTH_LONG).show();
                Log.w("---l---", e.getMessage());

            }
        });

    }

    private void setUpFireBase() {

        db = FirebaseFirestore.getInstance();

    }

    private void setUpRecyclerView() {

        recyclerView = findViewById(R.id.carer_children_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
