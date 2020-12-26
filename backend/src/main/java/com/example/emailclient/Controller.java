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
    Boolean isDraft=false;
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

    @GetMapping("/dltCont")
    public String[] removeContact(@RequestParam(value = "names") String names) throws IOException {
        return app.RemoveContact(names);
    }

    @GetMapping("/editCont")
    public String edit_Contact(@RequestParam(value = "oldname") String oldname,@RequestParam(value = "newname") String newname,@RequestParam(value = "adresses") String adresses) throws IOException {
        return app.EditContact(oldname,newname,adresses);
    }

    @GetMapping("/searchCont")
    public String[] searchContact(@RequestParam(value = "name") String name){
        return  app.SearchContactname(name);
    }

    @GetMapping("/listCont")
    public String[] listContacts(){
        return app.listContacts();
    }

    @GetMapping("/showCont")
    public String[] showContact(@RequestParam(value = "user") String name){
        return app.showContact(name);
    }

    @GetMapping("/sortCont")
    public String[] sortContact(){
        return app.SortContacts();
    }



    //email
    @GetMapping("/compose")
    public void compose(@RequestParam String info ){
        System.out.println(info);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        try {
            map = mapper.readValue(info, Map.class);
            System.out.println(map);
            temp=app.ComposeMail(map);
            // MultipartFile[] file;
            //app.SendMail(temp,null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    @PostMapping(value="/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void SendMail(@RequestBody MultipartFile[] file){
        if(file==null){
            System.out.println("empty");
        }else{
            System.out.println("size >> "+file.length);
            System.out.println(file[0].getOriginalFilename());
        }
        if(isDraft){
            app.Senddraft(temp,file);
        }else{
            app.SendMail(temp,file);
        }
        temp=null;
        isDraft=false;
    }

    @GetMapping("/sendDraft")
    public void sendDraft(@RequestParam(value = "info") String info){
        System.out.println(info);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        try {
            map = mapper.readValue(info, Map.class);
            temp=app.ComposeMail(map) ;
            isDraft=true;
           // app.Senddraft(temp);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/listMails")
    public String[] listmail(@RequestParam(value = "foldername") String foldername,@RequestParam(value = "page") String page){
        app.foldername=foldername;
        System.out.println("folder : " + foldername + " page : " + page);
        app.page= Integer.parseInt(page);
        if(app.page==1){
            app.listMails();
        }
        return app.getCurrentpage();
    }

    @GetMapping("/openMail")
    public Map<String, String> openmail(@RequestParam(value = "index") String index){
        return app.openMail(index);
    }

    @GetMapping("/searchMails")
    public String[] searchmails(@RequestParam(value = "type") String type,@RequestParam(value = "key") String key) throws IOException {
        app.page=1;
        System.out.println("key "+key+" type "+type);
        return app.Searching(type,key);
    }

    @GetMapping("/sortMails")
    public String[] sortmails(@RequestParam(value = "type") String type) throws IOException {
        app.page=1;
        return app.sorting(type);
    }

    //what key
    @GetMapping("/filterMails")
    public String filtermails(@RequestParam(value = "index") String index,@RequestParam(value = "mode") String mode,@RequestParam(value = "dest") String dest) throws IOException {
        System.out.println("index " + index + " mode " + mode + " dest " + dest);
        return app.filtering(index,mode,dest);
    }

    @GetMapping("/copyMails")
    public String copymails(@RequestParam(value = "index") String index,@RequestParam(value = "des") String des) throws IOException {
        System.out.println("copied" + index);
        String[] index2 = index.split(",");
        return app.copyMails(index2,des);
    }

    @GetMapping("/dltMails")
    public String[] deletemails(@RequestParam(value = "index") String index){
        String[] index2 = index.split(",");
        return app.deleteMails(index2);
    }

    //folder
    @GetMapping("/listFolders")
    public String[] folderlist(){
        return app.listFolders();
    }

    @GetMapping("/createFolder")
    public String createfolder(@RequestParam(value = "name") String name){
        return app.createFolder(name);
    }
    @GetMapping("/renameFolder")
    public String renamefolder(@RequestParam(value = "old") String old ,@RequestParam(value = "n") String n){
        System.out.println("old "+old+" new "+n);
        return app.renameFolder(old,n);
    }
    @GetMapping("/dltFolder")
    public String deletefolder(@RequestParam(value = "name") String name){
        System.out.println("name "+name);
        return app.deleteFolder(name);
    }

}