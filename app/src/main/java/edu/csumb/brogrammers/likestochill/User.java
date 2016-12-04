package edu.csumb.brogrammers.likestochill;

import android.app.Application;
import android.util.Log;

/**
 * Created by BRX01 on 11/11/2016.
 */

public class User {
    private String userId;

    private String fName;
    private String lName;

    private String userLocation;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private String userAbout;

    public User(){

    }

    public User(String userId, String fName, String lName, String userLocation, String userEmail, String userGender,
                String userDOB, String userAbout) {

        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.userLocation = userLocation;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userDOB = userDOB;
        this.userAbout = userAbout;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", fName=" + fName + ", lName=" + lName + ", userLocation=" + userLocation
                + ", userEmail=" + userEmail + ", userGender=" + userGender + ", userDOB=" + userDOB + ", userAbout="
                + userAbout + "]";
    }
}
