package com.example.emailclient.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ContactManipulation {
    User user;
    public ContactManipulation(User user){
        this.user=user;
    }

    public String[] ListContacts(){
        int size = 0;
        if(user.contacts != null){
            size = user.contacts.size();
        }
        String[] contacts= new String[size];
        for (int i=0; i<size;i++){
            contacts[i]=user.contacts.get(i).name;
        }
        return contacts;
    }

    public String[] ViewContact(String name){
        Contact c = SearchContact(name);
        return (String[]) c.emails.toArray();
    }

    // return error if exists
    public boolean AddContact(String name, ArrayList<String> emails) throws IOException {
        if(SearchContact(name) == null){
            Contact c = new Contact(name,emails);
            this.user.contacts.add(c);
            this.Update();
            return true;}
        return false;
    }


    public boolean EditContact(String oldname,String newname , ArrayList<String> emails) throws IOException {
        if(oldname.compareTo(newname)==0){
            this.user.contacts.set(this.user.contacts.indexOf(SearchContact(oldname)),new Contact(oldname,emails));
            this.Update();
            return true;
        }else{
            if(SearchContact(newname)==null){
                this.user.contacts.set(this.user.contacts.indexOf(SearchContact(oldname)),new Contact(newname,emails));
                this.Update();
                return true;
            }
            else{
                return false;
            }
        }

    }

    //array contacts >> name
    public void DeleteContact(String[] contact) throws IOException {
        for(int i=0;i<contact.length;i++){
            this.user.contacts.remove(SearchContact(contact[i]));}
        this.Update();
    }

    private Contact SearchContact(String name){
        int size = 0;
        if(user.contacts != null){
            size = user.contacts.size();
        }
        for(int i=0; i< size;i++){
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
    public void Update() throws IOException {
        String path ="src\\main\\java\\com\\example\\emailclient\\App\\"+this.user.email+"\\";
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        writer.writeValue(Paths.get(path+"info.json").toFile(), this.user);
    }

}