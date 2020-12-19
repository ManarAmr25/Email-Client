package com.example.emailclient.Email;

public class Search {
    private Strategy strategy;
    public Search(Strategy strategy){
        this.strategy=strategy;
    }
    public Email[] SearchList(Email[] email,String type,String key){
        return strategy.Search(email,type,key);
    }
}
