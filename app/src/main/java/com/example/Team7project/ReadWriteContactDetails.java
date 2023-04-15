package com.example.Team7project;

public class ReadWriteContactDetails {
    String name,email,message;

    public ReadWriteContactDetails(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public ReadWriteContactDetails(){

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
