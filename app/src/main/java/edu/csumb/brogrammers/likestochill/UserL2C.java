package edu.csumb.brogrammers.likestochill;

/**
 * Created by wiseR3B3L on 11/23/16.
 */

public class UserL2C {


    /**
     * fName : Sean
     * lName : O'Fallon
     * userAbout : About Sean
     * userEmail : sofallon@csumb.edu
     * userGender : M
     * userLocation : 93955
     */


    private String fName;
    private String lName;
    private String userId;
    private String userAbout;
    private String userDOB;
    private String userEmail;
    private String userGender;
    private String userLocation;


    public UserL2C(String fName, String lName, String userId, String userAbout, String userDOB, String userEmail, String userGender, String userLocation) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.userAbout = userAbout;
        this.userDOB = userDOB;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userLocation = userLocation;
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

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", userId=" + userId +
                ", userAbout='" + userAbout + '\'' +
                ", userDOB='" + userDOB + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userLocation='" + userLocation + '\'' +
                '}';
    }
}
