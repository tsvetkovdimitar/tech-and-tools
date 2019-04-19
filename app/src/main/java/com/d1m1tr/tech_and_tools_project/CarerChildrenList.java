package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class CarerChildrenList extends AppCompatActivity{

    private ChildrenRecyclerViewAdapter childrenRecyclerViewAdapter;
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private ArrayList<Child> childrenList;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer_children_list);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        childrenList = new ArrayList<>();

        setUpRecyclerView();

        childrenRecyclerViewAdapter = new ChildrenRecyclerViewAdapter(CarerChildrenList.this, childrenList);
        recyclerView.setAdapter(childrenRecyclerViewAdapter);

        setUpFireBase();
        loadDataFromFireStore();
    }

    private void loadDataFromFireStore() {

        if(childrenList.size() > 0){

            childrenList.clear();

        }

        if(mUser != null) {

            if (getIntent().hasExtra("userId")) {

                String parentId = getIntent().getStringExtra("userId");

                db.collection("users").document(parentId).collection("children")
                        .addSnapshotListener(CarerChildrenList.this, new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                                if (e != null) {

                                    Toast.makeText(CarerChildrenList.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                                } else {

                                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                                        if (doc.getType() == DocumentChange.Type.ADDED) {

                                            String childId = doc.getDocument().getId().trim();

                                            Child child = doc.getDocument().toObject(Child.class).withId(childId);
                                            childrenList.add(child);

                                            childrenRecyclerViewAdapter.notifyDataSetChanged();

                                        }

                                    }

                                }

                            }
                        });
            }
        }

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
