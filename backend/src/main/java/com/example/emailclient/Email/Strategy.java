package com.example.emailclient.Email;

public interface Strategy {
    public Email[] Sort(Email[] m);
    public Email[] Search(Email[] email,String key);
}
