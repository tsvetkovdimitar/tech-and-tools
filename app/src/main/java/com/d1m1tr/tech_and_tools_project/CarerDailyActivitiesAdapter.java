package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

public class CarerDailyActivitiesAdapter extends RecyclerView.Adapter<CarerDailyActivitiesViewHolder> {

    private CarerDailyActivitesList carerDailyActivitesList;
    private ArrayList<DailyActivity> dailyActivityArrayList;

    public CarerDailyActivitiesAdapter(CarerDailyActivitesList carerDailyActivitesList, ArrayList<DailyActivity> dailyActivityArrayList){

        this.carerDailyActivitesList = carerDailyActivitesList;
        this.dailyActivityArrayList = dailyActivityArrayList;

    }

    @NonNull
    @Override
    public CarerDailyActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(carerDailyActivitesList.getBaseContext());
        View view = layoutInflater.inflate(R.layout.carer_daily_activity_list_item, viewGroup, false);

        return new CarerDailyActivitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarerDailyActivitiesViewHolder carerDailyActivitiesViewHolder, int i) {

        carerDailyActivitiesViewHolder.mType.setText(dailyActivityArrayList.get(i).getActivityType());
        carerDailyActivitiesViewHolder.mNote.setText(dailyActivityArrayList.get(i).getActivityNote());

        long milliseconds = dailyActivityArrayList.get(i).getDateAdded().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();
        carerDailyActivitiesViewHolder.mDateCreated.setText(dateString);


    }

    @Override
    public int getItemCount() {

        return dailyActivityArrayList.size();
    }
}
