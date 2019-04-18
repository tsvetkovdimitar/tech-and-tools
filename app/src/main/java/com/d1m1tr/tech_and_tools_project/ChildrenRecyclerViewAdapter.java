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

public class ChildrenRecyclerViewAdapter extends RecyclerView.Adapter<ChildrenRecyclerViewAdapter.ViewHolder>{

//    private OnItemClickListener clicklistener;

    private List<Child> childrenList;
    private CarerChildrenList carerChildrenList;

    public ChildrenRecyclerViewAdapter(CarerChildrenList carerChildrenList, List<Child> childrenList){

        this.childrenList = childrenList;
        this.carerChildrenList = carerChildrenList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carer_child_list_item, viewGroup, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mName.setText(childrenList.get(i).getChildName());
        viewHolder.mAge.setText(childrenList.get(i).getChildAge());

        final String childId = childrenList.get(i).id;
        final String parentId = childrenList.get(i).getParentId();

        long milliseconds = childrenList.get(i).getDateRegistered().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();
        viewHolder.mDateRegistered.setText(dateString);

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dailyActivitiesIntent = new Intent(view.getContext(), CarerDailyActivitiesList.class);
                dailyActivitiesIntent.putExtra("childId", childId);
                dailyActivitiesIntent.putExtra("parentId", parentId);
                view.getContext().startActivity(dailyActivitiesIntent);

            }
        });

    }

//    public void setClickListener(OnItemClickListener clicklistener){
//
//        this.clicklistener = clicklistener;
//
//    }

    @Override
    public int getItemCount() {

        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view;

        private TextView mName;

        private TextView mAge;

        private TextView mDateRegistered ;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            view = itemView;

//            view.setOnClickListener(this);

            mName = itemView.findViewById(R.id.children_list_child_name);
            mAge = itemView.findViewById(R.id.children_list_child_age);
            mDateRegistered = itemView.findViewById(R.id.children_list_child_registered);


        }

//        @Override
//        public void onClick(View view) {
//
//            if(clicklistener != null){
//
//                clicklistener.itemClicked(view, getAdapterPosition());
//
//            }
//
//        }
    }

//    public interface OnItemClickListener{
//
//        void itemClicked(View view, int position);
//
//
//    }

}
