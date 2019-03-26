package com.d1m1tr.tech_and_tools_project;

public class User {

    private String userId;
    private String userName;
    private String userEmail;

    public User(){


    }

    public User(String userId, String userName, String userEmail){

        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;

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
