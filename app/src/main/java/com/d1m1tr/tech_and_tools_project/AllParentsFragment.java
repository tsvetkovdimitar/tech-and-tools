package com.d1m1tr.tech_and_tools_project;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllParentsFragment extends Fragment {

   private View parentsView;
   private RecyclerView recyclerView;

   private DatabaseReference databaseReference;

    public AllParentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        parentsView = inflater.inflate(R.layout.fragment_all_parents, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        recyclerView = (RecyclerView) parentsView.findViewById(R.id.all_parents_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return parentsView;

    }

    @Override
    public void onStart(){

        super.onStart();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions
                .Builder<ParentRecyclerViewItem>()
                .setQuery(databaseReference, ParentRecyclerViewItem.class)
                .build();

        final FirebaseRecyclerAdapter<ParentRecyclerViewItem, ParentsViewHolder> adapter = new FirebaseRecyclerAdapter<ParentRecyclerViewItem, ParentsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ParentsViewHolder holder, int position, @NonNull ParentRecyclerViewItem model) {

                holder.setName(model.getParentName());

            }

            @NonNull
            @Override
            public ParentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_parents_item_layout, viewGroup, false);
                ParentsViewHolder viewHolder = new ParentsViewHolder(view);

                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){


    }

    public static class ParentsViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public ParentsViewHolder(@NonNull View itemView) {

            super(itemView);

            mView = itemView;

        }

        public void setName(String name){

            TextView parentName = mView.findViewById(R.id.all_parents_layout_item_name);
            parentName.setText(name);


        }
    }


}
