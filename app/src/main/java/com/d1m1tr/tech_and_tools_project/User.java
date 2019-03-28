package com.d1m1tr.tech_and_tools_project;

public class User {

    private String userId;
    private String userName;
    private String userEmail;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String userType;

        public User(){


    }

    public User(String userId, String userName, String userEmail, String userType){

        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userType = userType;

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

    public User(String userName, String userEmail){

        this.userName = userName;
        this.userEmail = userEmail;

    }


}
