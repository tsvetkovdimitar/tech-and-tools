package com.d1m1tr.tech_and_tools_project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ChildActivityList extends ArrayAdapter<DailyActivity> {

    private Activity context;
    private List<DailyActivity> dailyActivityList;

    public ChildActivityList(Activity context, List<DailyActivity> dailyActivityList){

        super(context, R.layout.child_activity_list_layout, dailyActivityList);
        this.context = context;
        this.dailyActivityList = dailyActivityList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.child_activity_list_layout, null, true);

        TextView textViewActivityType = listViewItem.findViewById(R.id.textViewActivityType);
        TextView textViewActivityNote = listViewItem.findViewById(R.id.textViewActivityNote);

        DailyActivity dailyActivity = dailyActivityList.get(position);

        textViewActivityType.setText(dailyActivity.getActivityType());
        textViewActivityNote.setText(dailyActivity.getActivityNote());

        return listViewItem;
    }
}
