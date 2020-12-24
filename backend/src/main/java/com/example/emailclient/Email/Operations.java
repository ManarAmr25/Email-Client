package com.example.emailclient.Email;

import com.example.emailclient.Main;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Operations extends Email {
    @Override
    public Boolean sendEmail(Email e) throws IOException, CloneNotSupportedException {
        if(e==null){
            return false;
        }
        setIndex("sent", e.getFrom());
        e.setMailindex( getIndex("sent",e.getFrom()));
        String Sentpath = "src\\main\\java\\com\\example\\emailclient\\App\\" + e.getFrom()+"\\sent\\" + getIndex("sent",e.getFrom())+ ".json";
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        writer.writeValue(Paths.get(Sentpath).toFile(), e);
        while(0 < e.getTo().size()){
            String reciever =(String)e.getTo().remove();
            setIndex("inbox", reciever);
            String inboxpath = "src\\main\\java\\com\\example\\emailclient\\App\\" + reciever+"\\inbox\\" + getIndex("inbox",reciever)+ ".json";
            Email copied = (Email)e.clone();
            copied.setMailindex(getIndex("inbox",reciever));
            Queue r = new LinkedList();
            r.add(reciever);
            copied.setTo(r);
            writer.writeValue(Paths.get(inboxpath).toFile(), copied);
        }


        return true;
    }

    @Override
    public Email composeEmail(Map<String,String> M) throws CloneNotSupportedException {
        setBody(M.get("body"));
        setFrom(M.get("from"));
        setSubject(M.get("subject"));
        setKey(Integer.parseInt(M.get("key")));
        Date now = new Date();
        setDate(now);
        String temp = M.get("to");
        String [] arr = temp.split(",");
        Queue reciever = new LinkedList();
        for(int i=0 ; i< arr.length ; i++){
            reciever.add(arr[i]);
        }
        setTo(reciever);

        return getEmail();
    }

    @Override
    public Boolean Copy(Email[] m, String source, String destination,String email) {

        for(int i=0; i< m.length ; i++ ){
            try {
                //get index of source
                int index1 =m[i].getMailindex();
                var src = new RandomAccessFile("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+source+"\\"+index1+".json", "r");
                //get index of destination
                setIndex(destination,email);
                int index2 =getIndex(destination,email);
                new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+source+"\\"+index2+".json");
                var dest = new RandomAccessFile("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+destination+"\\"+index2+".json", "rw");
                try (var sfc = src.getChannel();
                     var dfc = dest.getChannel()) {

                    dfc.transferFrom(sfc, 0, sfc.size());
                }
            }catch(IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    @Override
    public Boolean DeleteEmail(Email[] m, String source, String email) {
        if(m == null){return false;}
        if(source.compareTo("trash")!=0) {
            Copy(m, source, "trash", email);
        }
        for(int i=0; i<m.length;i++){
            try {
                int mailindex=m[i].getMailindex();
                File sourceFile= new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+source+"\\"+mailindex+".json");
                if (deleteDirectory(sourceFile)) {
                    System.out.println("Folder deleted successfully");
                }
                else {
                    System.out.println("Failed to delete Folder");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}
        return true;
    }
    boolean deleteDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectory(entry);
                }
            }
        }
        if (!file.delete()) {
            return false;
        }
        else {return true;}
    }

    @Override
    public int getIndex(String Foldername, String username) {
        int index = 0;
        File f2 = new File("src\\main\\java\\com\\example\\emailclient\\App\\"+username+"\\"+Foldername+"\\"+ "index.txt");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(f2));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            String line = in.readLine();
            if(line!=null&&!line.isEmpty()){
                index = Integer.parseInt(line);
            }
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public void setIndex(String Foldername, String username) throws IOException {
        int x= getIndex(Foldername,username);
        x++;
        FileWriter data1 = new FileWriter("src\\main\\java\\com\\example\\emailclient\\App\\"+username+"\\"+Foldername+"\\index.txt",false);
        data1.write(Integer.toString(x));
        data1.close();
    }
    @Override
    public Email[] Filtering(Email[] emails, String key, String mode) {
        if(mode.equals("sender")){
            return new BySender().meets(emails,key);
        }
        else {
            return new BySubject().meets(emails,key);
        }
    }

    @Override
    public ArrayList<Email> getMails(int page, String foldername, String email) throws IOException {
        String[] content=new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+foldername).list();
        Collections.reverse(Arrays.asList(content));
        int len=content.length;
       ArrayList<Email> res=new ArrayList<Email>();
        int start =10*(page-1);
        int end=10+start;

        //get all mails if mode is true


        ObjectMapper mapper = new ObjectMapper();
        String path="src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+foldername;
        for(int i=start;i<end&&i<len;i++){
            if(content[i].compareTo("index.txt")==0){
                continue;
            }
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Email curr=mapper.readValue(Paths.get(path+"\\"+content[i]).toFile(),Operations.class);
            res.add(curr);
        }
        
        return res;
    }

}