package com.example.emailclient;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class EmailClientApplication {

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        SpringApplication.run(EmailClientApplication.class, args);
        /*ObjectMapper mapper = new ObjectMapper();
        String path ="src\\main\\java\\com\\example\\emailclient\\App\\"+"mariam@yahoo.com\\sent\\";
        File y = new File(path);
        String[] z=y.list();
       // System.out.println(((Operations) x).getDate());
        System.out.println(z[0]+"\n"+z[1]);*/
//      Map<String,String> m=new HashMap<String,String>();
//      m.put("body","test");
//      m.put("from","mariam@yahoo.com");
//      m.put("key","3");
//      m.put("to","mera@yahoo.com");
//Operations op=new Operations();
////  Email x=op.composeEmail(m);
////  op.sendEmail(x);
//ArrayList<Email> res=op.getMails(1,"sent","mariam@yahoo.com");
//System.out.println("size: "+res.size());
//System.out.println(res.get(0).getBody());
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        String path="src\\main\\java\\com\\example\\emailclient\\App\\mariam@yahoo.com\\sent\\1.json";
//        Email curr=mapper.readValue(Paths.get(path).toFile(),Operations.class);
//        System.out.println(curr.getBody());

//        String[]x=new String[]{"1.json","2.json","index.json"};
//        Collections.reverse(Arrays.asList(x));
//
//        for(int i=0;i<x.length;i++){
//            if(x[i].compareTo("index.json")!=0) {
//                System.out.println(x[i]);
//            }
//        }
        FileWriter data1 = new FileWriter("src\\main\\java\\com\\example\\emailclient\\App\\mariam@yahoo.com\\sent\\index.txt",false);
        data1.write(Integer.toString(5));
        data1.close();


    }

}
