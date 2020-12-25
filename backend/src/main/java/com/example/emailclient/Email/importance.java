package com.example.emailclient.Email;

import java.util.ArrayList;

public class importance implements Strategy{

    @Override
    public Email[] Sort(Email[] m) {
        PriorityQueue p = new PriorityQueue();
        Email[] sorted = new Email[m.length];
        for(int i=0;i<m.length;i++){
            p.insert(m[i],m[i].getKey());
        }
        for(int i=0;!p.isEmpty();i++ ){
            sorted[i]=(Email)p.removeMin();
        }
        return sorted;
    }

    @Override
    public Email[] Search(Email[] email,String key) {
        ArrayList<Email> searched= new ArrayList<>();
        for (int j = 0; j < email.length; j++) {
            if (email[j].getKey() == Integer.parseInt(key)) {
                searched.add(email[j]);
            }}
        return  searched.toArray(new Email[searched.size()]);
    }
}
