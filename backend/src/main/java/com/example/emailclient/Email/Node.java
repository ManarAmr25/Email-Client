package com.example.emailclient.Email;

public class Node {
    int key;
    Object element;
    Node next;


    public Node(int k, Object o, Node n) {
        key=k;
        element=o;
        next=n;
    }
    public Object getelement() {
        return element;
    }
    public Node getnext() {
        return next;
    }
    public int getkey() {
        return key;
    }
    public void setnext(Node n) {
        next=n;
    }
}
