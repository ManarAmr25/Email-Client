package com.example.emailclient.Email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Date_ implements Strategy{
    @Override
    public Email[] Sort(Email[] m) {
        int size = m.length;
        for(int i = 0; i<size-1; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i].getDate().compareTo(m[j].getDate()) < 0) {
                    Email temp = m[i];
                    m[i]=m[j];
                    m[j]=temp;
                }
            }
        }
        return m; }
        //String pattern
    @Override
    public Email[] Search(Email[] email,String key) {
        ArrayList<Email> searched= new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int j = 0; j < email.length; j++) {
            if (dateFormat.format(email[j].getDate()).compareTo(key) == 0) {
                searched.add(email[j]);
            }}

        return (Email[]) searched.toArray();
    }
}
