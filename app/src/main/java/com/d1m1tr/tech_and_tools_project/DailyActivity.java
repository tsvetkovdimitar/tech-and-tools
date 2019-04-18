package com.d1m1tr.tech_and_tools_project;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class DailyActivity {

    private String activityType;
    private String activityNote;
    private @ServerTimestamp
    Date dateAdded;


    public DailyActivity(){



    }

    public DailyActivity(String activityType, String activityNote, Date dateAdded){

        this.activityType = activityType;
        this.activityNote = activityNote;
        this.dateAdded = dateAdded;

    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityNote() {
        return activityNote;
    }

    public void setActivityNote(String activityNote) {
        this.activityNote = activityNote;
    }
}
