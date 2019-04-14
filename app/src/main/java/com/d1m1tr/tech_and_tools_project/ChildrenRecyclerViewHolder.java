package com.d1m1tr.tech_and_tools_project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ChildrenRecyclerViewHolder extends RecyclerView.ViewHolder{

    public TextView mName;
    public TextView mAge;
    public TextView mDateRegistered;

    public ChildrenRecyclerViewHolder(View itemView){
        super(itemView);

        mName = itemView.findViewById(R.id.children_list_child_name);
        mAge = itemView.findViewById(R.id.children_list_child_age);
        mDateRegistered = itemView.findViewById(R.id.children_list_child_registered);
    }
}
