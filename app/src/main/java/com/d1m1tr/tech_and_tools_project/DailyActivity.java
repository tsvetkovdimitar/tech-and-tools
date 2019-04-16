package com.d1m1tr.tech_and_tools_project;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class DailyActivity {

    private String activityId;
    private String activityType;
    private String activityNote;
    private @ServerTimestamp
    Date dateAdded;


    public DailyActivity(){



    }

    public DailyActivity(String activityId, String activityType, String activityNote){

        this.activityId = activityId;
        this.activityType = activityType;
        this.activityNote = activityNote;

    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
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
