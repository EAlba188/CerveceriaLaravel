package com.example.cervecerialaravel.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cerveza {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("location")
    @Expose
    private String location;

    public Cerveza(long id, String url, String brand, double price, String type, int amount, String location) {
        this.id = id;
        this.picture = url;
        this.brand = brand;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.location = location;
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

