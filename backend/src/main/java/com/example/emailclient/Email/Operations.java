package com.example.emailclient.Email;

import com.example.emailclient.Main;
import com.example.emailclient.user.App;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Operations extends Email {
    @Override
    public Boolean sendEmail(Email e, MultipartFile[] file) throws IOException, CloneNotSupportedException {
        if(e==null){
            System.out.println("email is null");
            return false;
        }
        //attachment
        ArrayList<String>sender=e.getAttach();
        int size1=0;
        if(file!=null){
            size1=file.length;
        }

        for(int i=0;i<size1;i++){
            String path="src\\main\\java\\com\\example\\emailclient\\App\\"+e.getFrom()+"\\attachment\\";
            Path filepath = Paths.get(path, file[i].getOriginalFilename());
            file[i].transferTo(filepath);
            sender.add(path+file[i].getOriginalFilename());
        }

        e.setAttach(sender);
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

                //attachment
                ArrayList<String> rec=new ArrayList<>();
                 size1=0;
                if(file!=null){
                    size1=file.length;
                }
                for(int i=0;i<size1;i++){
                    String path="src\\main\\java\\com\\example\\emailclient\\App\\"+reciever+"\\attachment\\";
                    Path filepath = Paths.get(path, file[i].getOriginalFilename());
                    file[i].transferTo(filepath);
                    rec.add(path+file[i].getOriginalFilename());
                }

                String inboxpath = "src\\main\\java\\com\\example\\emailclient\\App\\" + reciever + "\\inbox\\" + getIndex("inbox", reciever) + ".json";
                Email copied = (Email) e.clone();
                copied.setMailindex(getIndex("inbox", reciever));
                copied.setAttach(rec);
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
        if(M.containsKey("key")) {
            setKey(Integer.parseInt(M.get("key")));
        }
        Date now = new Date();
        setDate(now);
        if(M.containsKey("to")) {
            String temp = M.get("to");
            String[] arr = temp.split(",");
            Queue reciever = new LinkedList();
            for (int i = 0; i < arr.length; i++) {
                File rec=new File("src\\main\\java\\com\\example\\emailclient\\App\\" + arr[i]);
                if(rec.exists()) {
                    reciever.add(arr[i]);
                }
            }
            setTo(reciever);
        }
        return getEmail();
    }

    @Override
    public Boolean Copy(Email[] m, String source, String destination,String email) {

        for(int i=0; i< m.length ; i++ ){
            try {
                //get index of source
                int index1 =m[i].getMailindex();
                System.out.println("inside op : "+m[i].subject+" "+m[i].mailindex+" "+index1);
              //  var src = new RandomAccessFile("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+source+"\\"+index1+".json", "r");
                //get index of destination
                setIndex(destination,email);
                int index2 =getIndex(destination,email);
                String sentpath="src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+destination+"\\"+index2+".json";
                m[i].setMailindex(index2);
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                writer.writeValue(Paths.get(sentpath).toFile(), m[i]);
                m[i].setMailindex(index1);
//                var dest = new RandomAccessFile("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+destination+"\\"+index2+".json", "rw");
//                try (var sfc = src.getChannel();
//                     var dfc = dest.getChannel()) {
//
//                    dfc.transferFrom(sfc, 0, sfc.size());
//                }

            }catch(IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    @Override
    public Boolean DeleteEmail(Email[] m, String source, String email) {
        if(m == null){return false;}
        if( source.compareTo("draftd")==0){
            source="draft";
        }
       else if(source.compareTo("trash")!=0 ) {
            Copy(m, source, "trash", email);
        }

        for(int i=0; i<m.length;i++){
            try {
                int mailindex=m[i].getMailindex();
                System.out.println("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+source+"\\"+mailindex+".json");
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
    public Email[] getMails( String foldername, String email) throws IOException {
        String[] content=new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+foldername).list();
        Collections.reverse(Arrays.asList(content));
        int len=content.length;
        ArrayList<Email> res=new ArrayList<Email>();

        ObjectMapper mapper = new ObjectMapper();
        String path="src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+foldername;
        for(int i=0;i<len;i++){
            if(content[i].compareTo("index.txt")==0){
                continue;
            }
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Email curr=mapper.readValue(Paths.get(path+"\\"+content[i]).toFile(),Operations.class);
            res.add(curr);
        }
        App a = new App();

        return    a.SortMails(res.toArray(new Email[res.size()]),"Date");
    }

}