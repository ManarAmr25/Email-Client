package com.example.emailclient.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserBuilding implements UserBuilder {
    private User user;
    public UserBuilding(){
        user = new User();
    }
    @Override
    public User build() throws Exception {
        //validation of user
        //validation of username and password

        if(user.username==null||user.username.isEmpty()){
            throw new Exception("empty username");
        }
        //regex for special characters
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(user.username);
        if(m.find()){
            throw new Exception("Invalid username");
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

        String path ="src\\main\\java\\com\\example\\emailclient\\App\\"+user.email+"\\";
        new File("src\\main\\java\\com\\example\\emailclient\\App\\"+user.email).mkdir();
        new File(path+"attachment").mkdir();
        new File(path+"inbox").mkdir();
        new File(path+"inbox/index.txt").createNewFile();
        new File(path+"sent").mkdir();
        new File(path+"sent/index.txt").createNewFile();
        new File(path+"draft").mkdir();
        new File(path+"draft/index.txt").createNewFile();
        new File(path+"trash").mkdir();
        new File(path+"trash/index.txt").createNewFile();
        user.contacts=new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        writer.writeValue(Paths.get(path+"info.json").toFile(), this.user);
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

    @Override
    public UserBuilder setContacts(ArrayList<Contact> contacts) {
        user.contacts=contacts;
        return this;
    }
}