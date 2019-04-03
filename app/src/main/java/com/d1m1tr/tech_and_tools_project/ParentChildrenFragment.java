package com.d1m1tr.tech_and_tools_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParentChildrenFragment extends Fragment {

    private View childrenView;
    private RecyclerView childrenRecyclerView;
    private List<Child> childrenList;
    private ParentChildrenRecyclerAdapter parentChildrenRecyclerAdapter;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference childrenRef;

    public ParentChildrenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = currentUser.getUid();

        childrenView = inflater.inflate(R.layout.fragment_parent_all_children, container, false);

        childrenList = new ArrayList<>();
        childrenRecyclerView = childrenView.findViewById(R.id.parent_children_list_view);

        parentChildrenRecyclerAdapter = new ParentChildrenRecyclerAdapter(childrenList);

        childrenRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        childrenRecyclerView.setAdapter(parentChildrenRecyclerAdapter);

        if(mUser != null){



            firebaseFirestore = FirebaseFirestore.getInstance();
            childrenRef = firebaseFirestore.collection("users");

            childrenRef.document(uid).collection("children").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    if(queryDocumentSnapshots != null){

                        for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                Child child = doc.getDocument().toObject(Child.class);
                                childrenList.add(child);

                                parentChildrenRecyclerAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                }
            });

        }



//        firebaseFirestore.collection("users").document(uid).collection("children").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if(queryDocumentSnapshots != null){
//
//                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
//
//                        if (doc.getType() == DocumentChange.Type.ADDED) {
//
//                            Child child = doc.getDocument().toObject(Child.class);
//                            childrenList.add(child);
//
//                            parentChildrenRecyclerAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//                }
//            }
//        });

        // Inflate the layout for this fragment
        return childrenView;
    }

}
