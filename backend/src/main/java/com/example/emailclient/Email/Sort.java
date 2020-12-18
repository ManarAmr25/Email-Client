package com.example.emailclient.Email;

public class Sort {
private ISortStrategy strategy;
    public Sort(ISortStrategy strategy){
    this.strategy=strategy;
    }
public Email[] SortList(Email[] e){
    return strategy.Sort(e);
}
}
