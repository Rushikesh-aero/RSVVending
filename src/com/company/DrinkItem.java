package com.company;

import javax.swing.*;

public class DrinkItem extends JButton {

    int id;
    String name;
    double price;

    public DrinkItem(){

    }
    public DrinkItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;

        this.setText(name);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
