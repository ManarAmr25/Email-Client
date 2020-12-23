package com.example.emailclient.user;

import java.util.ArrayList;

public class User {
    protected String username;
    protected String password;
    protected String email;
    protected String firstname;
    protected String lastname;
    protected String gender;
    protected String phoneNumber;
    protected String dateOfBirth;
    protected String address;
    protected String country;
    protected ArrayList<Contact> contacts;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }


}
