package com.prototype.cm2a.models;

public class TypeAttention {
    private int id;
    private String name;
    private String aditional;
    private String price;

    public TypeAttention() {}

    public TypeAttention(int id, String name, String aditional, String price) {
        this.id = id;
        this.name = name;
        this.aditional = aditional;
        this.price = price;
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

    public String getAditional() {
        return aditional;
    }

    public void setAditional(String aditional) {
        this.aditional = aditional;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
