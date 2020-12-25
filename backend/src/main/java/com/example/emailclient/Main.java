package com.example.emailclient;

import com.example.emailclient.Email.Email;
import com.example.emailclient.Email.Operations;
import com.example.emailclient.Folder.Folder;
import com.example.emailclient.user.App;
import com.example.emailclient.user.Contact;
import com.example.emailclient.user.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
public class Main {
    public User user ;
    public App a= new App();
    public int page=1 ;
    public String foldername;
    public Folder folder;
    public Operations op;
    public ArrayList<Email> currentPage;
    //operations of user
    public Main(){
        this.Logout();
    }


    public void Logout(){
        user=new User();
        page=0;
        foldername=new String();
        folder=new Folder();
        op=new Operations();
        currentPage=new ArrayList<Email>();
        a=new App();
    }

    public void AutoDelete() throws IOException {
        foldername="trash";
        Email[] e =getAll();
        ArrayList<Email> delete = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateFormat.format(new Date()));
        LocalDate returnvalue = date.minusDays(30);
        for(int i=0;i<e.length;i++){
            if(LocalDate.parse(dateFormat.format(e[i].getDate())).compareTo(returnvalue) < 0){
                delete.add(e[i]);
            }
        }
        op.DeleteEmail((Email[]) delete.toArray(),"trash", user.getEmail());
    }
    //input (map >> email,password)
    //output(String:error,username)error>> true or false

    public String SignIn(String email,String password) throws IOException {
        if(a.signIn(null,email,password)){
            String path="src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+"info.json";
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(Paths.get(path).toFile(), User.class);
            //AutoDelete();
            return user.getUsername();
        }
        return "";

    }

    //input(map >> address,password,gender(1:male,2:female),date,firstname,lastname)
    //output(String:error,username) error>> true or false

    public String SignUp(Map<String,String> info) throws IOException {
        info.put("username",info.get("firstname")+" "+info.get("lastname"));
        if(a.signUp(info)){
            System.out.println("this is main " + info);
            String path="src\\main\\java\\com\\example\\emailclient\\App\\"+info.get("address")+"\\"+"info.json";
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(Paths.get(path).toFile(), User.class);
            return user.getUsername();
        }

        return "";
    }

    //input(String:name,String:adresses(split>>,))
    //output(nameError: true or false)
    public String AddContact(String name,String adresses) throws IOException {

        String [] arr = adresses.split(",");
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, arr);
        if(SearchContact(name) == null){
            Contact c = new Contact(name,list1);
            this.user.getContacts().add(c);
            this.Update();
            return "true";
        }
        return "false";
    }

    //input(String:name>>(split>>,)  >> index
    //output() >> ??
    public String[] RemoveContact(String names) throws IOException {
        String [] arr = names.split(",");

        for(int i=0;i<arr.length;i++){
            this.user.getContacts().remove(SearchContact(arr[i]));}
        this.Update();
        return listContacts();
    }

    //input(String:oldname,newname,adresses)
    //output(true or false)
    public String EditContact(String oldname,String newname,String adresses) throws IOException {
        String [] arr = adresses.split(",");
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, arr);
        if(oldname.compareTo(newname)==0){
            this.user.getContacts().set(this.user.getContacts().indexOf(SearchContact(oldname)),new Contact(oldname,list1));
            this.Update();
            return "true";
        }else{
            if(SearchContact(newname)==null){
                this.user.getContacts().set(this.user.getContacts().indexOf(SearchContact(oldname)),new Contact(newname,list1));
                this.Update();
                return "true";
            }
            else{
                return "false";
            }
        }



    }

    //input(String:substring)
    //output(list)
    public String[] SearchContactname(String sub){
        ArrayList<String> names = new ArrayList<>();
        for(int i=0; i< user.getContacts().size();i++){
            if(user.getContacts().get(i).name.contains(sub)){
                names.add(user.getContacts().get(i).name);
            }
        }
        return (String[]) names.toArray();
    }

    public String[] listContacts(){
        int size = 0;
        if(user.getContacts() != null){
            size = user.getContacts().size();
        }
        String[] contacts= new String[size];
        for (int i=0; i<size;i++){
            contacts[i]=user.getContacts().get(i).name;
        }
        return contacts;
    }

    public String[] showContact(String name){

        Contact c = SearchContact(name);
        return (String[]) c.emails.toArray();
    }

    public String[] SortContacts(){
        int size = user.getContacts().size();
        for(int i = 0; i<size-1; i++) {
            for (int j = i + 1; j < user.getContacts().size(); j++) {
                if (user.getContacts().get(i).name.compareTo(user.getContacts().get(j).name) > 0) {
                    Contact temp = user.getContacts().get(i);
                    user.getContacts().add(i,user.getContacts().get(j));
                    user.getContacts().add(j,temp); } }
        }
        return listContacts();
    }

    //operations on Emails
    //input(map)  from >> ??
    //output()
    public Email ComposeMail(Map<String,String> m){

        try {
            m.put("from", user.getEmail());
            System.out.println(user.getEmail());
            Email x=op.composeEmail(m);
           // op.sendEmail(x);
            return x;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

    }
    public void SendMail(Email e, MultipartFile[] file){
        try {
            op.sendEmail(e,file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
    }
    // error in queue ??
    public void Senddraft(Map<String,String> m){
        m.put("from", user.getEmail());
        try {
            Email x=op.composeEmail(m);
            op.setIndex("draft", x.getFrom());
            x.setMailindex( op.getIndex("draft",x.getFrom()));
            String Sentpath = "src\\main\\java\\com\\example\\emailclient\\App\\" + x.getFrom()+"\\draft\\" + op.getIndex("draft",x.getFrom())+ ".json";
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            writer.writeValue(Paths.get(Sentpath).toFile(), x);
        } catch (CloneNotSupportedException | IOException e) {
            e.printStackTrace();
        }
    }

    public String[] listMails(){
        try {
            this.currentPage=op.getMails(page++,foldername, user.getEmail());
            return getDescription((Email[]) this.currentPage.toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public Map<String, String> openMail(String index){
        Email current=this.currentPage.get(Integer.parseInt(index));
        Map<String,String> res=new HashMap<>();
        res.put("from",current.getFrom());
        res.put("date", String.valueOf(current.getDate()));
        res.put("subject",current.getSubject());
        res.put("body",current.getBody());
        res.put("key", String.valueOf(current.getKey()));
        Queue To=current.getTo();
        String to=new String();
        while (To.size()>0){
            to+=To.remove();
            if(To.size()>0){
                to+=",";
            }
        }
        res.put("to",to);
        return res;
    }
    //input()
    //output()
    public String[] Searching(String type, String key) throws IOException {
        Email[] e =getAll();
        Email[]s =a.SearchMails(e,type,key);
        return getDescription(s);
    }
    //input()
    //output()
    public String[] sorting(String type) throws IOException {
        Email[] e =getAll();
        Email[] s=a.SortMails(e,type);
        return getDescription(s);
    }
    //input()
    //output()
    public void filtering(String key,String mode,String dest) throws IOException {
        Email[] e =getAll();
        Email[] f=op.Filtering(e,key,mode);
        op.Copy(f,foldername,dest, user.getEmail());
    }
    public void copyMails(String[] index,String dest){
        op.Copy(getByIndex(index),foldername,dest, user.getEmail());
    }
    public void deleteMails(String[] index){

        op.DeleteEmail(getByIndex(index),foldername, user.getEmail());
    }

    public Email[] getByIndex(String[] index){
        Email[] e=new Email[index.length];
        for(int i=0;i<index.length;i++){
            e[i]=this.currentPage.get(Integer.parseInt(index[i]));
        }
        return e;
    }
    public Email[] getAll() throws IOException {
        String[] content=new File("src\\main\\java\\com\\example\\emailclient\\App\\"+user.getEmail()+"\\"+foldername).list();
        Email[] res=new Email[content.length-1];
        ObjectMapper mapper = new ObjectMapper();
        String path="src\\main\\java\\com\\example\\emailclient\\App\\"+user.getEmail()+"\\"+foldername;
        for(int i=0, j=0;i<content.length;i++,j++){
            if(content[i].compareTo("index.txt")==0){
                j--;
                continue;
            }
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Email curr=mapper.readValue(Paths.get(path+"\\"+content[i]).toFile(),Operations.class);
            res[j]=curr;
        }
        return res;
    }
    public String[] getDescription(Email[] e){
        String[] res=new String[e.length];
        for(int i=0;i<e.length;i++){
            res[i]=e[i].getSubject()+"  "+e[i].getFrom()+"  "+e[i].getTo();
        }
        return res;
    }
    //operations on Folder
    public void openFolder(String name){
        this.foldername=name;
        this.page=1;
    }
    public String[] listFolders(){
        return folder.getNames(user.getEmail());
    }
    public String createFolder(String name){
        boolean x= folder.create(name, user.getEmail());
        if(x){
            return "true";
        }
        else {
            return "false";
        }
    }

    public String renameFolder(String old ,String newName){
        try {
            boolean x = folder.rename(newName,old, user.getEmail());
            if(x){
                return "true";
            }
            else{
                return "false";
            }
        } catch (Exception e) {
            return "false";
        }


    }

    public String deleteFolder(String name){
        try {
            boolean x=folder.delete(name, user.getEmail());
            if (x){
                return "true";
            }
            else {
                return "false";
            }
        } catch (Exception e) {
            return "false";
        }
    }
    private Contact SearchContact(String name){
        int size = 0;
        if(user.getContacts() != null){
            size = user.getContacts().size();
        }
        for(int i=0; i< size;i++){
            if(user.getContacts().get(i).name.compareTo(name) == 0){
                return user.getContacts().get(i);
            }
        }
        return null;
    }
    public void Update() throws IOException {
        String path ="src\\main\\java\\com\\example\\emailclient\\App\\"+this.user.getEmail()+"\\";
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        writer.writeValue(Paths.get(path+"info.json").toFile(), this.user);
    }


}