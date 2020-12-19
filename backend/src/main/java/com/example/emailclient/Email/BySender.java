package com.example.emailclient.Email;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;

public class BySender implements IFilter {
    @Override
    public Email[] meets(Email[] emails,String key) {
       ArrayList<Email> res= new ArrayList<>();

        for (int i=0;i<emails.length;i++){
                if(emails[i].getFrom().equals(key)){
                    res.add(emails[i]);
                }
        }
        return res.toArray(new Email[res.size()]);
    }
}
