package com.d1m1tr.tech_and_tools_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllParentsFragment extends Fragment {

   private View usersView;
   private RecyclerView allParentsRecyclerView;
   private List<User> usersList;

   private FirebaseFirestore firebaseFirestore;

   private ParentsRecyclerAdapter parentsRecyclerAdapter;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public AllParentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        usersView = inflater.inflate(R.layout.fragment_all_parents, container, false);

        usersList = new ArrayList<>();
        allParentsRecyclerView = usersView.findViewById(R.id.all_parents_list_view);

        parentsRecyclerAdapter = new ParentsRecyclerAdapter(usersList);
        allParentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allParentsRecyclerView.setAdapter(parentsRecyclerAdapter);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if(mUser != null){

            firebaseFirestore = FirebaseFirestore.getInstance();

            Query allParentsInDescendingOredrQuery = firebaseFirestore.collection("users").orderBy("timestamp", Query.Direction.DESCENDING);

           allParentsInDescendingOredrQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if(queryDocumentSnapshots != null){

                        for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                User user = doc.getDocument().toObject(User.class);
                                usersList.add(user);

                                parentsRecyclerAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                }
            });

        }



        return usersView;
    }

}
