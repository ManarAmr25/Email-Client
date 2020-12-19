package com.example.emailclient.Email;

public class Sort {
private Strategy strategy;
    public Sort(Strategy strategy){
    this.strategy=strategy;
    }
    public Email[] SortList(Email[] e){
    return strategy.Sort(e);
}
}
