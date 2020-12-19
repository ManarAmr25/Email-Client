package com.example.emailclient.Email;

public interface IFilter {
    Email[] meets(Email[] emails,String key);

}
