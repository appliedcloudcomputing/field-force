package com.acc.fieldforce.database.User;

/**
 * Created by Nilesh on 5/5/2015.
 */
public class User {
    String name;
    String emailId;
    String password;
    String mobile_no;
    String location;

    public User(){

    }

    public User(String name, String emailId, String password, String mobile_no, String location) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.mobile_no = mobile_no;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
