package com.example.itemshopping.domain;

public class Item {
    private String name;
    private float value;
    private int quantity;
    private double price;
    public Item(){

    }
    public Item(String name, float value, int quantity){
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.price = this.value * this.quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return "El producto " + name + " tiene un precio de " + value +
                " y el precio con una cantidad de " + quantity + " es de " + price;
    }
}
