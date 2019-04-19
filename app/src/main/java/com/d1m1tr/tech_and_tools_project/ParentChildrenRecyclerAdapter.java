package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
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

        final String childId = childrenList.get(i).id;
        final String parentId = childrenList.get(i).getParentId();

        String childAge = childrenList.get(i).getChildAge();
        viewHolder.setChildAge(childAge);

        long milliseconds = childrenList.get(i).getDateRegistered().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();

        viewHolder.setChildDateRegistered(dateString);

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dailyActivitiesIntent = new Intent(view.getContext(), ParentDailyActivitiesList.class);
                dailyActivitiesIntent.putExtra("childId", childId);
                dailyActivitiesIntent.putExtra("parentId", parentId);
                view.getContext().startActivity(dailyActivitiesIntent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view;

        private TextView childName;
        private TextView childAge;
        private TextView childDateRegistered;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            view = itemView;
        }

        public void setChildName(String childNameText){

            childName = view.findViewById(R.id.parents_children_child_name);
            childName.setText(childNameText);

        }

        public void setChildAge(String childAgeText){

            childAge = view.findViewById(R.id.parents_child_age);
            childAge.setText(childAgeText);


        }

        public void setChildDateRegistered(String childDateRegisteredText){

            childDateRegistered = view.findViewById(R.id.parent_child_date_registered);
            childDateRegistered.setText(childDateRegisteredText);
        }
    }
}
