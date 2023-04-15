package com.example.Team7project;

public class ReadWriteUserDetails {
    public String name,age,mobile,gender;
    public boolean storeowner;

    public ReadWriteUserDetails(String name, String age, String mobile, String gender,boolean storeowner) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.gender = gender;
        this.storeowner=storeowner;
    }
}
