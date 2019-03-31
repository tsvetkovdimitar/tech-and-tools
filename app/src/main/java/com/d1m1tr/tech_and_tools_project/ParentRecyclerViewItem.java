package com.d1m1tr.tech_and_tools_project;

public class ParentRecyclerViewItem {

    private String parentName;
    private String parentEmail;

    public ParentRecyclerViewItem(){


    }

    public ParentRecyclerViewItem(String parentName, String parentEmail){

        this.parentName = parentName;
        this.parentEmail = parentEmail;

    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
}
