package com.example.emailclient.Email;

public class ImportanceSort implements ISortStrategy {
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
}
