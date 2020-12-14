package com.example.emailclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EmailClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailClientApplication.class, args);
        App a = new App();
        Map<String,String> m =new HashMap<>();
        m.put("username","mariam");
        m.put("password","44");
        m.put("email","mariam@yahoo.com");
        System.out.println(a.signIn(null,"mariam@yahoo.com","44"));
    }

}
