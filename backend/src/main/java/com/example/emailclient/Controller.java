package com.example.emailclient;

import com.example.emailclient.Email.BySender;
import com.example.emailclient.Email.BySubject;
import com.example.emailclient.Email.Email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
@CrossOrigin
public class Controller {
Main app =new Main();
Email temp;
    @GetMapping("/signIn")
    public String signin(@RequestParam(value="email") String email, @RequestParam(value = "password") String password) throws IOException {
        return app.SignIn(email,password);
    }

    @PostMapping("/signUp")
    public String signup(@RequestBody String info) throws IOException {
        Map<String,String> information=new HashMap<>();
        System.out.println(info);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(info, Map.class);
        String json =map.get("info");
        System.out.println(map.get("info"));
        information= mapper.readValue(json, Map.class);
        System.out.println(information);

        return app.SignUp(information);
    }

    //contact
    @GetMapping("/addCont")
    public String addContact(@RequestParam(value = "name") String name,@RequestParam(value = "adresses") String adresses) throws IOException {
        return app.AddContact(name,adresses);
    }
    public String[] removeContact(String names) throws IOException {
        return app.RemoveContact(names);

    }
    public String edit_Contact(String oldname,String newname,String adresses) throws IOException {
        return app.EditContact(oldname,newname,adresses);
    }
    public String[] searchContact(String name){
        return  app.SearchContactname(name);
    }
    @GetMapping("/listCont")
    public String[] listContacts(){
        return app.listContacts();
    }
    public String[] showContact(String name){
        return app.showContact(name);
    }
    public String[] sortContact(){
        return app.SortContacts();
    }



    //email
    @PostMapping(value="/compose",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void compose(@RequestParam(value = "info") String content ){

        Map<String,String> information=new HashMap<>();
        System.out.println(content);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        try {
            map = mapper.readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String json =map.get("info");
        System.out.println(json);
        try {
            information= mapper.readValue(json, Map.class);
            System.out.println(information);
            temp=app.ComposeMail(information);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    @PostMapping(value="/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void SendMail(@RequestParam(value = "file") MultipartFile[] file){
        app.SendMail(temp, file);
        temp=null;
    }


    public void sendDraft(){}
    public void listmail(){}
    public void openmail(){}
    public void searchmails(){}
    public void sortmails(){}
    public void filtermails(){}
    public void copymails(){}
    public void deletemails(){}

    //folder
    public void openfolder(){}
    public void folderlist(){}
    public void createfolder(){}
    public void renamefolder(){}
    public void deletefolder(){}


}

