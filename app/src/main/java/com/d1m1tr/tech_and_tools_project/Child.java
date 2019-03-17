package com.d1m1tr.tech_and_tools_project;

public class Child {

    String childId;
    String childName;
    String parentEmail;

    public Child(){



    }

    public Child(String childId, String childName, String parentEmail){

        this.childId = childId;
        this.childName = childName;
        this.parentEmail = parentEmail;

    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
}
