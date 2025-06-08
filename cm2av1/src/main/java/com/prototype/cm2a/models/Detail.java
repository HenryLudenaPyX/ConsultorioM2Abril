package com.prototype.cm2a.models;

public class Detail {
    private int line;
    private int quantity;
    private String description;
    private double unitPrice;
    private double totalPrice;

    public Detail() {}

    public Detail(int line, int quantity, String description, double unitPrice, double totalPrice) {
        this.line = line;
        this.quantity = quantity;
        this.description = description;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
