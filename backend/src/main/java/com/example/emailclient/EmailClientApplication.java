package com.example.emailclient;


import com.example.emailclient.user.User;
import com.example.emailclient.user.UserBuilding;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
@CrossOrigin
@SpringBootApplication
@RestController

public class EmailClientApplication {

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        SpringApplication.run(EmailClientApplication.class, args);

        Queue x=new LinkedList();
        x.add("hi");
        x.add("no");
        System.out.println("me"+x.toString());
    }








//    @PostMapping(value="/request",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void g(@RequestParam(name="file", required = false) MultipartFile[] file){
//        if(file!=null) {
//            System.out.println(file.length);
//        }
//        else {
//            System.out.println("empty");
//        }
        //System.out.println(file[0].getOriginalFilename());
//        for (int i=0;i<file.length;i++){
//            System.out.println(file[i].getOriginalFilename());
//        }
     //   {"info":"{\"password\":\"ffffffffffffffff\",\"email\":\"fffff\",\"gender\":\"1\",\"date\":\"2000-01-01\",\"firstname\":\"ff\",\"lastname\":\"ff\"}"}
    }




