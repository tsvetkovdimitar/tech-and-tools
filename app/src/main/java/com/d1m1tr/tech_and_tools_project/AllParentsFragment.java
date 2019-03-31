package com.d1m1tr.tech_and_tools_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllParentsFragment extends Fragment {

   private View parentsView;
   private RecyclerView allParentsRecyclerView;

   private DatabaseReference databaseReference;

    public AllParentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        parentsView = inflater.inflate(R.layout.fragment_all_parents, container, false);

        allParentsRecyclerView = getActivity().findViewById(R.id.all_parents_list_view);



        return parentsView;

    }





}
