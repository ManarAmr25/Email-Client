package com.example.emailclient.Email;

public class Search {
    private Strategy strategy;
    public Search(Strategy strategy){
        this.strategy=strategy;
    }
    public Email[] SearchList(Email[] email,String key){
        return strategy.Search(email,key);
    }
}
