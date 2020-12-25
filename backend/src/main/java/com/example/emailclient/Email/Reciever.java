package com.example.emailclient.Email;

import java.util.ArrayList;
import java.util.Queue;

public class Reciever implements Strategy{
    @Override
    public Email[] Sort(Email[] m) {
        int size = m.length;
        for(int i = 0; i<size-1; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (((String) m[i].getTo().peek()).compareTo(((String) m[i].getTo().peek())) > 0) {
                    Email temp = m[i];
                    m[i]=m[j];
                    m[j]=temp;
                }
            }
        }
        return m;
    }

    @Override
    public Email[] Search(Email[] email,String key) {
        ArrayList<Email> searched= new ArrayList<>();
        for (int j = 0; j < email.length; j++) {
            Queue receiver = email[j].getTo();
            while(!receiver.isEmpty()){
                if(((String)receiver.remove()).compareTo(key)==0){
                    searched.add(email[j]);
                    break;
                } }
        }
        return searched.toArray(new Email[searched.size()]);
    }
}
