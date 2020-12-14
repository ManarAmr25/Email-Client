package com.example.emailclient.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserBuilding implements UserBuilder {
    private  User user;
    public UserBuilding(){
        user = new User();
    }
    @Override
    public User build() throws Exception {
        //validation of user
        //validation of username and password
        //regex for special characters
        if(user.username==null||user.username.isEmpty()){
            throw new Exception("empty username");
        }
        if(user.password==null||user.password.isEmpty()){
            throw new Exception("empty password");
        }

        //validation of email and its format
        if(user.email==null||user.email.isEmpty()) {
            throw new Exception("empty email");
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.email);
        if(!matcher.matches()){
            throw new Exception("wrong email");
        }
        //add default folders  name: username
        return user;
    }

    @Override
    public UserBuilder setUsername(String name) {
        user.username=name;
        return this;
    }

    @Override
    public UserBuilder setPassword(String password) {
        user.password=password;
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        user.email=email;
        return this;
    }

    @Override
    public UserBuilder setFirstname(String firstname) {
        user.firstname=firstname;
        return this;
    }

    @Override
    public UserBuilder setLastname(String lastname) {
        user.lastname=lastname;
        return this;
    }

    @Override
    public UserBuilder setGender(String gender) {
        user.gender=gender;
        return this;
    }

    @Override
    public UserBuilder setPhoneNumber(String phonenum) {
        user.phoneNumber=phonenum;
        return this;
    }

    @Override
    public UserBuilder setDateofbirth(String date) {
        user.dateOfBirth=date;
        return this;
    }

    @Override
    public UserBuilder setAddress(String address) {
        user.address=address;
        return this;
    }

    @Override
    public UserBuilder setCountry(String country) {
        user.country=country;
        return this;
    }
}
