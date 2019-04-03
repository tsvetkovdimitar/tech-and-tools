package com.d1m1tr.tech_and_tools_project;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
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
   private DocumentSnapshot lastVisible;

    private FirebaseAuth mAuth;

    public AllParentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        usersView = inflater.inflate(R.layout.fragment_all_parents, container, false);

        usersList = new ArrayList<>();
        allParentsRecyclerView = usersView.findViewById(R.id.all_parents_list_view);

        parentsRecyclerAdapter = new ParentsRecyclerAdapter(usersList);
        allParentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allParentsRecyclerView.setAdapter(parentsRecyclerAdapter);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){

            firebaseFirestore = FirebaseFirestore.getInstance();

            allParentsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if(reachedBottom){

                        String name = lastVisible.getString("userName");
                        Toast.makeText(container.getContext(), "Reached" + name, Toast.LENGTH_LONG).show();

                        loadUsers();

                    }
                }
            });

            Query allParentsInDescendingOrderQuery = firebaseFirestore.collection("users").orderBy("timestamp", Query.Direction.DESCENDING);

           allParentsInDescendingOrderQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if(queryDocumentSnapshots != null){

                        lastVisible = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() -1);

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

    public void loadUsers(){

            Query loadMoreUsersQuery = firebaseFirestore.collection("users")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .startAfter(lastVisible)
                    .limit(5);

            loadMoreUsersQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if(mAuth.getCurrentUser() != null){

                        if (!queryDocumentSnapshots.isEmpty()) {

                            lastVisible = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() - 1);

                            for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                                if (doc.getType() == DocumentChange.Type.ADDED) {

                                    User user = doc.getDocument().toObject(User.class);
                                    usersList.add(user);

                                    parentsRecyclerAdapter.notifyDataSetChanged();
                                }

                            }
                        }
                    }
                }
            });

    }

}
