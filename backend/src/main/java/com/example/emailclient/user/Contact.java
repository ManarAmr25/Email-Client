package com.example.emailclient.user;

import java.util.ArrayList;

public class Contact {
    public String name;
    public ArrayList<String> emails;

    public Contact(String name,ArrayList<String> emails){
        this.name=name;
        this.emails=emails;
    }


}