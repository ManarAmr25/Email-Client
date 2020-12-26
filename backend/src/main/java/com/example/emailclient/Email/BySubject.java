package com.example.emailclient.Email;

import java.util.ArrayList;

public class BySubject implements IFilter{
    @Override
    public Email[] meets(Email[] emails , String key) {
        ArrayList<Email> res= new ArrayList<>();

        for (int i=0;i<emails.length;i++){
            if(emails[i].getSubject().equals(key)){
                res.add(emails[i]);
            }
        }
        System.out.println("in filter");
        System.out.println(res.size());
        System.out.println(res.get(0).subject);
        System.out.println(res.get(0).mailindex);
        return res.toArray(new Email[res.size()]);
    }
}
