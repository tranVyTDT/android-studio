package com.example.hp.assignment;

import java.util.ArrayList;

public class Store {
    public String StoreName;
    public int AmountTables;
    public ArrayList<String> Dishes;
    public ArrayList<Integer> Price;

    public Store(String name, int tables, ArrayList<String> dishes, ArrayList<Integer> price) {
        this.setStoreName(name);
        this.setAmountTables(tables);
        this.setDishes(dishes);
        this.setPrice(price);
    }
    public Store(){}

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }


    public int getAmountTables() {
        return AmountTables;
    }

    public void setAmountTables(int amountTables) {
        AmountTables = amountTables;
    }

    public ArrayList<String> getDishes() {
        return Dishes;
    }

    public void setDishes(ArrayList<String> dishes) {
        Dishes = dishes;
    }

    public ArrayList<Integer> getPrice() {
        return Price;
    }

    public void setPrice(ArrayList<Integer> price) {
        Price = price;
    }
}
