package com.example.emailclient;

import com.example.emailclient.Email.Email;
import com.example.emailclient.Email.Operations;
import com.example.emailclient.user.Contact;
import com.example.emailclient.user.ContactManipulation;
import com.example.emailclient.user.User;
import com.example.emailclient.user.UserBuilding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@SpringBootApplication
public class EmailClientApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EmailClientApplication.class, args);
       /* App a = new App();
        Map<String,String> m =new HashMap<>();
        m.put("username","nada");
        m.put("password","263");
        m.put("email","nada@yahoo.com");
        System.out.println(a.signUp(m));*/
        /*PriorityQueue<Entry> q = new PriorityQueue<>();
        q.add(new Entry("hello", 1));
        q.add(new Entry("first", 2));
        System.out.println(q.peek().getKey());*/
        // creating a Calendar object
      /*  Calendar c = Calendar.getInstance();

        // set Month
        // MONTH starts with 0 i.e. ( 0 - Jan)
        c.set(Calendar.MONTH, 2);

        // set Date
        c.set(Calendar.DATE, 26);

        // set Year
        c.set(Calendar.YEAR, 2000);

        // creating a date object with specified time.
        Date dateOne = c.getTime();
        Calendar d = Calendar.getInstance();

        // set Month
        // MONTH starts with 0 i.e. ( 0 - Jan)
        d.set(Calendar.MONTH, 2);

        // set Date
        d.set(Calendar.DATE, 16);

        // set Year
        d.set(Calendar.YEAR, 1996);
        d.set(Calendar.HOUR, 9);
        Date dateTWO = d.getTime();
        Email m1= new Operations();
        m1.setDate(dateTWO);
        Email m2= new Operations();
        m2.setDate(dateOne);
        Email[] m = new Email[2];
        m[0]=m1;
        m[1]=m2;
        System.out.println(m[0].getDate());
        System.out.println(m[1].getDate());
        App a=new App();
        Email[] n=a.SortMails(m,"Date");
        System.out.println(n[0].getDate());
        System.out.println(n[1].getDate());*/
        UserBuilding u= new UserBuilding();
        ArrayList<String> l = new ArrayList<>();
        l.add("nada@gmail.com");
        u.setUsername("nada");
        u.setPassword("jhhj");
        u.setEmail("hkkhh@yahoo.com");
        User user=u.build();

        ContactManipulation m=new ContactManipulation(user);
        m.AddContact("test",l);
        System.out.println(u.getContacts().get(0).name);






    }
}
