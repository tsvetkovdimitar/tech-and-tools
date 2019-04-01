package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class ParentsRecyclerAdapter extends RecyclerView.Adapter<ParentsRecyclerAdapter.ViewHolder> {

    private List<User> usersList;

    public ParentsRecyclerAdapter(List<User> usersList){

        this.usersList = usersList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_parents_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String userName = usersList.get(i).getUserName();

        viewHolder.setParentName(userName);

        String userEmail = usersList.get(i).getUserEmail();

        viewHolder.setParentEmail(userEmail);

        long milliseconds = usersList.get(i).getTimestamp().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy HH:mm", new Date(milliseconds)).toString();

        viewHolder.setRegisteredTimestamp(dateString);

    }

    @Override
    public int getItemCount() {

        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view;

        private TextView userName;

        private TextView userEmail;

        private TextView userRegisteredTimestamp;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            view = itemView;
        }

        public void setParentName(String parentNameText){

            userName = view.findViewById(R.id.all_parents_layout_item_name);
            userName.setText(parentNameText);

        }

        public void setParentEmail(String parentEmailText){

            userEmail = view.findViewById(R.id.all_parents_layout_item_email);
            userEmail.setText(parentEmailText);

        }

        public void setRegisteredTimestamp(String userRegisteredTimeStampText){

            userRegisteredTimestamp = view.findViewById(R.id.user_registration_timestamp);
            userRegisteredTimestamp.setText(userRegisteredTimeStampText);
        }

    }

}
