package com.example.itemshopping.domain;

public class Node {
    public Object data;
    public Node next;
    public Node prev;
    public Node(Object data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
