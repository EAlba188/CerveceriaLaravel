package com.example.cervecerialaravel.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cerveza {

    private long id;
    private String picture, brand, type, location;
    private double price;
    private int amount;


    public Cerveza(long id, String url, String brand, double price, String type, int amount, String location) {
        this.id = id;
        this.picture = url;
        this.brand = brand;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.location = location;
    }

    public Cerveza(){
        this.id = id;
        this.picture = null;
        this.brand = null;
        this.price = 0;
        this.type = null;
        this.amount = 0;
        this.location = null;
    }

    @Override
    public String toString() {
        return "Cerveza{" +
                "id=" + id +
                ", url='" + picture + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", location='" + location + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return picture;
    }

    public void setUrl(String url) {
        this.picture = url;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

