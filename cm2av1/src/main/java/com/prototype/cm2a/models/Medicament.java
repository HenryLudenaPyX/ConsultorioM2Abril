package com.prototype.cm2a.models;

public class Medicament {
    private int id;
    private String name;
    private String aditional;
    private String price;
    private String unitPrice;
    private int stock;
    private String fabricationDate;
    private String expirationDate;
    private String status;

    public Medicament() {}

    public Medicament(int id, String name, String aditional, String status){
        this.id = id;
        this.name = name;
        this.aditional = aditional;
        this.status = status;
    }

    public Medicament(int id, String name, String aditional, String price, String unitPrice, int stock, String fabricationDate, String expirationDate,  String status) {
        this.id = id;
        this.name = name;
        this.aditional = aditional;
        this.price = price;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.fabricationDate = fabricationDate;
        this.expirationDate = expirationDate;
        this.status = status;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(String fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
