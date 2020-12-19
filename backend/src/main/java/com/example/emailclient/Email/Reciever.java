package com.example.emailclient.Email;

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
    public Email[] Search(Email[] email,String type,String key) {
        return new Email[0];
    }
}
