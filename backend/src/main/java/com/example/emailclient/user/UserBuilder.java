package com.example.emailclient.user;

import java.util.ArrayList;

public interface UserBuilder {
    User build() throws Exception;
    UserBuilder setUsername(String name);
    UserBuilder setPassword(String password);
    UserBuilder setEmail(String email);
    UserBuilder setFirstname(String firstname);
    UserBuilder setLastname(String lastname);
    UserBuilder setGender(String gender);
    UserBuilder setPhoneNumber(String phonenum);
    UserBuilder setDateofbirth(String date);
    UserBuilder setAddress(String address);
    UserBuilder setCountry(String country);
    ArrayList<Contact> getContacts();
}
