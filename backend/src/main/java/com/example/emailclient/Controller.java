package com.example.emailclient;

import com.example.emailclient.Email.BySender;
import com.example.emailclient.Email.BySubject;
import com.example.emailclient.Email.Email;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
@CrossOrigin
public class Controller {
Main app =new Main();
    @GetMapping("/signIn")
    public String signin(@RequestParam(value="email") String email, @RequestParam(value = "password") String password) throws IOException {
        return app.SignIn(email,password);
    }

    @PostMapping("/signUp")
    public String signup(@RequestBody String info) throws IOException {
        Map<String,String> information=new HashMap<>();

        return app.SignUp(information);
    }

    //contact
    public void addContact(){}
    public void removeContact(){}
    public void edit_Contact(){}
    public void searchContact(){}
    public void showContact(){}
    public void sortContact(){}

    //email
    public void send(){}
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

