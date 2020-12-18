package com.example.emailclient;

import com.example.emailclient.Email.*;
import com.example.emailclient.user.User;
import com.example.emailclient.user.UserBuilding;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class App {
    //maps for existed usernames and emails;
    private Map<String,String> usernames = new HashMap<>();
    private Map<String,String> emails = new HashMap<>();
    private User user;
    public App(){
        readFrombase();
    }
    public boolean validate(String username,String email,String password,int mode){
        if(mode==1) {
            if (usernames.get(username) != null || emails.get(email) != null) {
                return false;
            }
            else{
                return true;
            }
        }
        else{
            if (usernames.size() == 0) {
                return false;
            }
            String p1= usernames.get(username),
                    p2= emails.get(email);
            if(p1==null){
                p1="";
            }
            if(p2==null){
                p2="";
            }
            if (p1 == null  && p2==null ) {
                return false;
            }
            else if(p1.compareTo(password)==0||p2.compareTo(password)==0){
                return true;
            }

        }
        return true;
    }
    public void writeTObase(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get("src\\main\\java\\com\\example\\emailclient\\App\\usernames.json").toFile(), usernames);
            writer.writeValue(Paths.get("src\\main\\java\\com\\example\\emailclient\\App\\emails.json").toFile(), emails);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    public void readFrombase(){
        try {
            ObjectMapper mapper = new ObjectMapper();

            usernames = (Map<String, String>) mapper.readValue(Paths.get("src\\main\\java\\com\\example\\emailclient\\App\\usernames.json").toFile(), Map.class);
            emails = (Map<String, String>) mapper.readValue(Paths.get("src\\main\\java\\com\\example\\emailclient\\App\\emails.json").toFile(), Map.class);

        } catch (Exception ex) {
            File directory = new File("./");
            System.out.println(directory.getAbsolutePath());
            System.out.println("Exception");
        }
    }
    public boolean signUp(Map<String, String> info){
         String username=info.get("username");
         String email=info.get("email");

         if(!validate(username,email,null,1)){
             return false;
         }

         //create new user
        UserBuilding builder=new UserBuilding();
        for (Map.Entry<String, String> curr : info.entrySet()) {
            switch (curr.getKey()){
                case "username":
                    builder.setUsername(curr.getValue());
                    break;
                case "password":
                    builder.setPassword(curr.getValue());
                    break;
                case "email":
                    builder.setEmail(curr.getValue());
                    break;
                case "firstname":
                    builder.setFirstname(curr.getValue());
                    break;
                case "lastname" :
                    builder.setLastname(curr.getValue());
                    break;
                case "gender":
                    builder.setGender(curr.getValue());
                    break;
                case "phonenumber":
                    builder.setPhoneNumber(curr.getValue());
                    break;
                case "date":
                    builder.setDateofbirth(curr.getValue());
                    break;
                case "address":
                    builder.setAddress(curr.getValue());
                    break;
                case "country":
                    builder.setCountry(curr.getValue());
                    break;
            }
        }
        try {
            user= builder.build();
            usernames.put(info.get("username"),info.get("password"));
            emails.put(info.get("email"),info.get("password"));
            writeTObase();
            System.out.println("Signed up");
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public boolean signIn(String username , String email , String password){
        if( !validate(username,email,password,2)){
            return false;
        }
        System.out.println("Signed in");

        return true;
    }

    public Email[] SortMails(Email[] mails,String type){
        Sort sorting;
        switch (type){
            case "Sender":
                sorting = new Sort(new SenderSort());
                break;
            case "Date":
               sorting = new Sort(new DateSort());
                break;
            case "Receiver":
                sorting=new Sort(new RecieverSort());
                break;
            case "Importance":
                sorting=new Sort(new ImportanceSort());
                break;
            case "Subject":
                sorting=new Sort(new SubjectSort());
                break;
            case "Body":
                sorting=new Sort(new BodySort());
                break;
            case "Attachments":
                sorting=new Sort(new AttachmentsSort());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return sorting.SortList(mails);
    }

}
