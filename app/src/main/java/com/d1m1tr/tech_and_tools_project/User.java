package com.d1m1tr.tech_and_tools_project;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class User {

    private String userId;
    private String userName;
    private String userEmail;
    private @ServerTimestamp
    Date timestamp;


    public User(String userId, String userName, String userEmail, String userType, Date timestamp){

        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userType = userType;
        this.timestamp = timestamp;

    }

    public User(){


    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
