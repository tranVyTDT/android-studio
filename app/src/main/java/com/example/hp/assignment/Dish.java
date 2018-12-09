package com.example.hp.assignment;

public class Dish {
    String name;
    String price;

    public Dish () {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Dish(String name, String price) {
        this.name = name;
        this.price = price;
    }
}


