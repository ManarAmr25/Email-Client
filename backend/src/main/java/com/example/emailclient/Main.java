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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
public class Main {
    public User user ;
    public App a= new App();
    public int page=1 ;
    public int currentindex=0;
    public String foldername;
    public Folder folder;
    public Operations op;
    public Email[] currentPage;
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
        currentPage=new Email[0];
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
        op.DeleteEmail(delete.toArray(new Email[delete.size()]),"trash", user.getEmail());
    }
    //input (map >> email,password)
    //output(String:error,username)error>> true or false

    public String SignIn(String email,String password) throws IOException {
        if(a.signIn(null,email,password)){
            String path="src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+"info.json";
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(Paths.get(path).toFile(), User.class);
            AutoDelete();
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
        return names.toArray(new String[names.size()]);
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
        return c.emails.toArray(new String[c.emails.size()]);
    }

    public String[] SortContacts(){
        int size = user.getContacts().size();
        Contact[] s=user.getContacts().toArray(new Contact[user.getContacts().size()]);
        for(int i = 0; i<size-1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (s[i].name.compareTo(s[j].name) > 0) {
                    Contact temp = s[i];
                    s[i]=s[j];
                    s[j]=temp;
                } }
        }
        String[] N = new String[s.length];
        for(int i=0;i<s.length;i++){
            N[i]=s[i].name;

        }
        return N;
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
            //  System.out.println("in main "+file.length);
            op.sendEmail(e,file);
            if(foldername != null && foldername.compareTo("draft")==0){
                Email[]x=new Email[1];
                x[0]=e;
                x[0].setMailindex(currentindex);
                System.out.println("after delete draft : "+x[0].getMailindex());
                op.DeleteEmail(x,"draftd",user.getEmail());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
    }

    public void Senddraft(Email x,MultipartFile[] file){

        try {
            op.setIndex("draft", x.getFrom());
            x.setMailindex( op.getIndex("draft",x.getFrom()));
            String Sentpath = "src\\main\\java\\com\\example\\emailclient\\App\\" + x.getFrom()+"\\draft\\" + op.getIndex("draft",x.getFrom())+ ".json";

            //attach
            ArrayList<String> rec=x.getAttach();
            int size1=0;
            if(file!=null){
                size1=file.length;
            }
            for(int i=0;i<size1;i++){
                String path="src\\main\\java\\com\\example\\emailclient\\App\\"+user.getEmail()+"\\attachment\\";
                Path filepath = Paths.get(path, file[i].getOriginalFilename());
                file[i].transferTo(filepath);
                rec.add(path+file[i].getOriginalFilename());
            }

            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            writer.writeValue(Paths.get(Sentpath).toFile(), x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listMails(){
        try {
            this.currentPage=op.getMails(foldername, user.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getCurrentpage(){
        int start=10*(page-1);
        if(start>=currentPage.length){
            return new String[0];
        }
        int end=start+10;
        if(end>=currentPage.length){
            end=currentPage.length;
        }
        return getDescription(Arrays.copyOfRange(currentPage,start,end));
    }
    public Map<String, String> openMail(String index){
        Email current=this.currentPage[Integer.parseInt(index)];
        currentindex=current.getMailindex();
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
        currentPage =a.SearchMails(e,type,key);
        return getCurrentpage();
    }
    //input()
    //output()
    public String[] sorting(String type) throws IOException {
        Email[] e =getAll();
        currentPage=a.SortMails(e,type);
        return getCurrentpage();
    }
    //input()
    //output()
    public String filtering(String index,String mode,String dest) throws IOException {
        String str="";
        File d=new File("src\\main\\java\\com\\example\\emailclient\\App\\"+this.user.getEmail()+"\\"+dest);
        if(!d.exists()){
            d.mkdir();
            new File(d+"\\index.txt").createNewFile();
            str=dest;
        }
        Email[] e =getAll();
        String key;
        if(mode.compareTo("subject")==0){
            key=this.currentPage[Integer.parseInt(index)].getSubject();
        }
        else{
            key=this.currentPage[Integer.parseInt(index)].getFrom();
        }
        Email[] f=op.Filtering(e,key,mode);
        op.Copy(f,foldername,dest, user.getEmail());
        return str;
    }
    public String copyMails(String[] index,String dest) throws IOException {
        String str="";
        File d=new File("src\\main\\java\\com\\example\\emailclient\\App\\"+this.user.getEmail()+"\\"+dest);
        if(!d.exists()){
            d.mkdir();
            new File(d+"\\index.txt").createNewFile();
            str=dest;
        }
        op.Copy(getByIndex(index),foldername,dest, user.getEmail());
        return str;
    }
    public String[] deleteMails(String[] index){

        op.DeleteEmail(getByIndex(index),foldername, user.getEmail());
        listMails();
        page=1;
        return getCurrentpage();
    }

    public Email[] getByIndex(String[] index){
        Email[] e=new Email[index.length];
        for(int i=0;i<index.length;i++){
            e[i]=this.currentPage[Integer.parseInt(index[i])];
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
            String pri;
            switch (e[i].getKey()){
                case 1:
                    pri = "Top";
                    break;
                case 2:
                    pri = "High";
                    break;
                case 3:
                    pri = "Normal";
                    break;
                case 4:
                    pri = "Low";
                    break;
                default:
                    pri = "";
            }
            res[i]=e[i].getSubject()+"\t From :  "+e[i].getFrom()+"\t To : "+e[i].getTo() + "\t Priority :" + pri + "\t Date : " + e[i].getDate();
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