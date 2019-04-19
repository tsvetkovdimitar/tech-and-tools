package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ParentDailyActivitiesList extends AppCompatActivity {

    private Boolean firstPageFirstLoad = true;

    private ParentDailyActivitiesAdapter parentDailyActivitiesAdapter;
    private FirebaseFirestore db;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private RecyclerView recyclerView;

    private ArrayList<DailyActivity> dailyActivityArrayList;

    private DocumentSnapshot lastVisible;

    private String parentId;
    private String childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_daily_activities_list);

        getIds();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        dailyActivityArrayList = new ArrayList<>();

        setUpRecyclerView();

        parentDailyActivitiesAdapter = new ParentDailyActivitiesAdapter(ParentDailyActivitiesList.this, dailyActivityArrayList);
        recyclerView.setAdapter(parentDailyActivitiesAdapter);

        loadDataFromFireStore();


    }

    private void setUpRecyclerView() {

        recyclerView = findViewById(R.id.parent_daily_activities_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getIds(){

        if(getIntent().hasExtra("childId") && getIntent().hasExtra("parentId")) {

            parentId = getIntent().getStringExtra("parentId");
            childId = getIntent().getStringExtra("childId");

        }
    }

    private void loadDataFromFireStore() {

        if (dailyActivityArrayList.size() > 0) {

            dailyActivityArrayList.clear();

        }

        if (mUser != null) {

            db = FirebaseFirestore.getInstance();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachBottom = !recyclerView.canScrollVertically(1);

                    if (reachBottom) {

                        loadActivities();

                    }

                }
            });

            Query allChildrenActivitiesQuery = db.collection("users").document(parentId)
                    .collection("children").document(childId)
                    .collection("dailyActivities").orderBy("dateAdded", Query.Direction.DESCENDING).limit(6);

            allChildrenActivitiesQuery.addSnapshotListener(ParentDailyActivitiesList.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (queryDocumentSnapshots != null) {

                        if (firstPageFirstLoad && queryDocumentSnapshots.size() > 0) {

                            lastVisible = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() - 1);

                        }

                        for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                DailyActivity dailyActivity = doc.getDocument().toObject(DailyActivity.class);

                                if (firstPageFirstLoad) {

                                    dailyActivityArrayList.add(dailyActivity);

                                } else {

                                    dailyActivityArrayList.add(0, dailyActivity);

                                }

                                parentDailyActivitiesAdapter.notifyDataSetChanged();
                            }
                        }

                        firstPageFirstLoad = false;
                    }
                }
            });
        }
    }

    private void loadActivities() {

        if(lastVisible != null) {

            final Query loadMoreActivities = db.collection("users").document(parentId)
                    .collection("children").document(childId)
                    .collection("dailyActivities")
                    .startAfter(lastVisible).limit(6);

            loadMoreActivities.addSnapshotListener(ParentDailyActivitiesList.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (mAuth.getCurrentUser() != null) {

                        if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {

                            lastVisible = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() - 1);

                            for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                                if (doc.getType() == DocumentChange.Type.ADDED) {

                                    DailyActivity dailyActivity = doc.getDocument().toObject(DailyActivity.class);
                                    dailyActivityArrayList.add(dailyActivity);

                                    parentDailyActivitiesAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
