package com.example.emailclient.Email;

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
    public Email[] Search(Email[] email,String type,String key) {
        return new Email[0];
    }
}
