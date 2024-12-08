package com.rifat.exam.model;

import java.util.Date;

public class Product {

    private Integer id;         // Primary Key
    private String name;        // Product name
    private Double price;       // Product price
    private Date mfgDate;       // Manufacturing date
    private String remarks;     // Remarks for the product

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(Integer id, String name, Double price, Date mfgDate, String remarks) {
        this.id = id;
        this.name = name != null ? name.toUpperCase() : null; // Automatically set name to uppercase
        this.price = price;
        this.mfgDate = mfgDate;
        this.remarks = remarks;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name.toUpperCase() : null; // Ensure name is always uppercase
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Override toString() method for easy printing
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mfgDate=" + mfgDate +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
