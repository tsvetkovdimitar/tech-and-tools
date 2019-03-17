package com.d1m1tr.tech_and_tools_project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ChildList extends ArrayAdapter<Child> {

    private Activity context;
    private List<Child> childList;

    public ChildList(Activity context, List<Child> childList){

        super(context, R.layout.child_list_layout, childList);
        this.context = context;
        this.childList = childList;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.child_list_layout, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.txtView_name);
        TextView textViewEmail = listViewItem.findViewById(R.id.txtView_email);

        Child child = childList.get(position);

        textViewName.setText(child.getChildName());
        textViewEmail.setText(child.getParentEmail());

        return listViewItem;
    }
}
