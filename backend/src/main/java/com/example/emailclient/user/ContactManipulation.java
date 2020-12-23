package com.example.emailclient.user;

import java.util.ArrayList;

public class ContactManipulation {
    User user;
    public ContactManipulation(User user){
        this.user=user;
    }

    public String[] ListContacts(){
        String[] contacts= new String[user.contacts.size()];
        for (int i=0; i<user.contacts.size();i++){
            contacts[i]=user.contacts.get(i).name;
        }
        return contacts;
    }

    public String[] ViewContact(String name){
        Contact c = SearchContact(name);
        return (String[]) c.emails.toArray();
    }

    // return error if exists
    public boolean AddContact(String name, ArrayList<String> emails){
        if(SearchContact(name) == null){
            Contact c = new Contact(name,emails);
            this.user.contacts.add(c);
            return true;}
        return false;
    }


    public boolean EditContact(String oldname,String newname , ArrayList<String> emails){
        if(oldname.compareTo(newname)==0){
            this.user.contacts.set(this.user.contacts.indexOf(SearchContact(oldname)),new Contact(oldname,emails));
            return true;
        }else{
            if(SearchContact(newname)==null){
                this.user.contacts.set(this.user.contacts.indexOf(SearchContact(oldname)),new Contact(newname,emails));
                return true;
            }
            else{
                return false;
            }
        }

    }

    //array contacts >> name
    public void DeleteContact(String[] contact){
        for(int i=0;i<contact.length;i++){
            this.user.contacts.remove(SearchContact(contact[i]));}
    }

    private Contact SearchContact(String name){
        for(int i=0; i< user.contacts.size();i++){
            if(user.contacts.get(i).name.compareTo(name) == 0){
                return user.contacts.get(i);
            }
        }
        return null;
    }
    public String[] SearchName(String sub){
        ArrayList<String> names = new ArrayList<>();
        for(int i=0; i< user.contacts.size();i++){
            if(user.contacts.get(i).name.contains(sub)){
                names.add(user.contacts.get(i).name);
            }
        }
        return (String[]) names.toArray();
    }


    public void SortContact(){
        int size = user.contacts.size();
        for(int i = 0; i<size-1; i++) {
            for (int j = i + 1; j < user.contacts.size(); j++) {
                if (user.contacts.get(i).name.compareTo(user.contacts.get(j).name) > 0) {
                    Contact temp = user.contacts.get(i);
                    user.contacts.add(i,user.contacts.get(j));
                    user.contacts.add(j,temp); } }
        }
    }

}