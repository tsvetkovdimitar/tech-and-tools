package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ParentChildrenRecyclerAdapter extends RecyclerView.Adapter<ParentChildrenRecyclerAdapter.ViewHolder> {

    private List<Child> childrenList;

    public ParentChildrenRecyclerAdapter(List<Child> childrenList){

        this.childrenList = childrenList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_list_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String childName = childrenList.get(i).getChildName();
        viewHolder.setChildName(childName);

    }

    @Override
    public int getItemCount() {

        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view;
        private TextView childName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }

        public void setChildName(String childNameText){

            childName = view.findViewById(R.id.parents_children_child_name);
            childName.setText(childNameText);

        }
    }
}
