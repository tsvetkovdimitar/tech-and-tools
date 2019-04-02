package com.d1m1tr.tech_and_tools_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParentChildrenFragment extends Fragment {

    private RecyclerView childrenRecyclerView;

    public ParentChildrenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        childrenRecyclerView = getActivity().findViewById(R.id.parent_children_list_view);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_all_children, container, false);
    }

}
