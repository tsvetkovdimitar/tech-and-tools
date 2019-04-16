package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CarerDailyActivitiesViewHolder extends RecyclerView.ViewHolder {

    public TextView mType;
    public TextView mNote;
    public TextView mDateCreated;

    public CarerDailyActivitiesViewHolder(@NonNull View itemView) {
        super(itemView);

        mType = itemView.findViewById(R.id.carer_daily_activity_type);
        mNote = itemView.findViewById(R.id.carer_daily_activity_note);
        mDateCreated = itemView.findViewById(R.id.carer_daily_activity_date_added);

    }
}
