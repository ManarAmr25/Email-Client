package com.example.emailclient.user;

import com.example.emailclient.Email.Email;

import java.util.ArrayList;

public class ContactManipulation {
    User user;
    public ContactManipulation(User user){
        this.user=user;
    }

    public void AddContact(String name, ArrayList<String> emails){
        this.user.contacts.add(new Contact(name,emails));
    }

    public void EditContact(Contact before,Contact after){
    this.user.contacts.set(this.user.contacts.indexOf(before),after);
    }

    public void DeleteContact(Contact contact){
    this.user.contacts.remove(contact);
    }
    public Contact SearchContact(String name){
        for(int i=0; i< user.contacts.size();i++){
          if(user.contacts.get(i).name.compareTo(name) == 0){
              return user.contacts.get(i);
          }
        }
       return null;
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
