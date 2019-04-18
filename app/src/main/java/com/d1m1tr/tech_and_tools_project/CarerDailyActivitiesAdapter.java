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

public class CarerDailyActivitiesAdapter extends RecyclerView.Adapter<CarerDailyActivitiesAdapter.ViewHolder> {

    private CarerDailyActivitiesList carerDailyActivitiesList;
    private ArrayList<DailyActivity> dailyActivityArrayList;

    public CarerDailyActivitiesAdapter(CarerDailyActivitiesList carerDailyActivitiesList, ArrayList<DailyActivity> dailyActivityArrayList){

        this.carerDailyActivitiesList = carerDailyActivitiesList;
        this.dailyActivityArrayList = dailyActivityArrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(carerDailyActivitiesList.getBaseContext());
        View view = layoutInflater.inflate(R.layout.carer_daily_activity_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder carerDailyActivitiesViewHolder, int i) {

        carerDailyActivitiesViewHolder.mType.setText(dailyActivityArrayList.get(i).getActivityType());
        carerDailyActivitiesViewHolder.mNote.setText(dailyActivityArrayList.get(i).getActivityNote());

        long milliseconds = dailyActivityArrayList.get(i).getDateAdded().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();
        carerDailyActivitiesViewHolder.mDateCreated.setText(dateString);


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

            mType = itemView.findViewById(R.id.carer_daily_activity_type);
            mNote = itemView.findViewById(R.id.carer_daily_activity_note);
            mDateCreated = itemView.findViewById(R.id.carer_daily_activity_date_added);

        }
    }
}
