package com.example.emailclient;

import com.example.emailclient.Email.Email;
import com.example.emailclient.Email.Operations;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        ArrayList<String> res= new ArrayList<String>();
        res.add("test");
        res.add("hi");
        String[] a=  res.toArray(new String[res.size()]);
        System.out.println(a[0]+" "+a[1]);
    }

}
