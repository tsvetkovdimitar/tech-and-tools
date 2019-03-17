package com.d1m1tr.tech_and_tools_project;

public class DailyActivity {

    private String activityId;
    private String activityType;
    private String activityNote;

    public DailyActivity(){



    }

    public DailyActivity(String activityId, String activityType, String activityNote){

        this.activityId = activityId;
        this.activityType = activityType;
        this.activityNote = activityNote;

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
