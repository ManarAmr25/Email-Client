package com.example.emailclient.Email;

public class Attachments implements Strategy {
    @Override
    public Email[] Sort(Email[] m) {
        return new Email[0];
    }

    @Override
    public Email[] Search(Email[] email,String key) {
        return null;
    }
}
