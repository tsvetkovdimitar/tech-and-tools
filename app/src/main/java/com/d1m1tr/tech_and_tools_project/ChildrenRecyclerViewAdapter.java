package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

public class ChildrenRecyclerViewAdapter extends RecyclerView.Adapter<ChildrenRecyclerViewHolder> {

    CarerChildrenList carerChildrenList;
    ArrayList<Child> childrenList;

    public ChildrenRecyclerViewAdapter(CarerChildrenList carerChildrenList, ArrayList<Child> childrenList) {

        this.carerChildrenList = carerChildrenList;
        this.childrenList = childrenList;
    }

    @NonNull
    @Override
    public ChildrenRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(carerChildrenList.getBaseContext());
        View view = layoutInflater.inflate(R.layout.carer_child_list_item, viewGroup, false);

        return new ChildrenRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenRecyclerViewHolder childrenRecyclerViewHolder, int i) {

        childrenRecyclerViewHolder.mName.setText(childrenList.get(i).getChildName());
        childrenRecyclerViewHolder.mAge.setText(childrenList.get(i).getChildAge());

        long milliseconds = childrenList.get(i).getDateRegistered().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();
        childrenRecyclerViewHolder.mDateRegistered.setText(dateString);

    }

    @Override
    public int getItemCount() {

        return childrenList.size();
    }
}
