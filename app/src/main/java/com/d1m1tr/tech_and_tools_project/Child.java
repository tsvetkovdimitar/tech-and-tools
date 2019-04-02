package com.d1m1tr.tech_and_tools_project;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class Child {


    private String childName;
    private String childAge;
    private @ServerTimestamp
    Date dateRegistered;
    private String parentId;


    public Child(){



    }

    public Child(String childName, String childAge, Date dateRegistered, String parentId){

        this.childName = childName;
        this.childAge = childAge;
        this.dateRegistered = dateRegistered;
        this.parentId = parentId;

    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
