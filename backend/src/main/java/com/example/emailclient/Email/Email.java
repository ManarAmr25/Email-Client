package com.example.emailclient.Email;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class Email implements Cloneable, Serializable {

    protected String body;
    protected String from;
    protected String date;
    protected Queue to= new LinkedList();
    protected String subject;
    protected int index;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public void setMailindex(int b){
        this.index=b;
    }
    protected  int getMailindex(){return index;}
    protected void setBody(String b){
        this.body=b;
    }
    protected void setFrom(String f){
        this.from=f;
    }
    protected void setDate(String d){
        this.date=d;
    }
    protected void setSubject(String s){
        this.subject=s;
    }
    protected void setTo(Queue t){
        this.to=t;
    }
    protected String getBody(){
        return body;
    }
    protected String getFrom(){
        return from;
    }
    protected String getDate(){
        return date;
    }
    protected String getSubject(){
        return subject;
    }
    protected Queue getTo(){
        return to;
    }
    protected Email getEmail() throws CloneNotSupportedException {
        return (Email)this.clone();
    }
    public abstract Boolean sendEmail(Email e) throws CloneNotSupportedException, IOException;
    public abstract Email composeEmail(Map<String, String> M) throws CloneNotSupportedException;
    abstract Boolean Copy(Email [] e, String source, String destination,String email);
    abstract Boolean DeleteEmail(Email [] e, String source,String email);
    abstract int getIndex(String Foldername , String username);
    abstract void setIndex(String Foldername , String username) throws IOException;
}