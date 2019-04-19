package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ParentDailyActivitiesAdapter extends RecyclerView.Adapter<ParentDailyActivitiesAdapter.ViewHolder> {

    private ParentDailyActivitiesList parentDailyActivitiesList;
    private ArrayList<DailyActivity> dailyActivityArrayList;

    public ParentDailyActivitiesAdapter(ParentDailyActivitiesList parentDailyActivitiesList, ArrayList<DailyActivity> dailyActivityArrayList){

        this.parentDailyActivitiesList = parentDailyActivitiesList;
        this.dailyActivityArrayList = dailyActivityArrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(parentDailyActivitiesList.getBaseContext());
        View view = layoutInflater.inflate(R.layout.parent_daily_activity_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder parentDailyActivitiesViewHolder, int i) {

        parentDailyActivitiesViewHolder.mType.setText(dailyActivityArrayList.get(i).getActivityType());
        parentDailyActivitiesViewHolder.mNote.setText(dailyActivityArrayList.get(i).getActivityNote());

        long milliseconds = dailyActivityArrayList.get(i).getDateAdded().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();
        parentDailyActivitiesViewHolder.mDateCreated.setText(dateString);

    }

    @Override
    public int getItemCount() {

        return (dailyActivityArrayList != null) ? dailyActivityArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

        public TextView mType;
        public TextView mNote;
        public TextView mDateCreated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            mType = itemView.findViewById(R.id.parent_daily_activity_type);
            mNote = itemView.findViewById(R.id.parent_daily_activity_note);
            mDateCreated = itemView.findViewById(R.id.parent_daily_activity_date_added);

        }
    }
}

