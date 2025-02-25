package com.example.Authentication.entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int quantity;
    private String supplier;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //  Constructor with parameters
    public Product(String name, double price, int quantity, User user,String supplier) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
        this.supplier=supplier;
    }

    //Default constructor (required by JPA)
    public Product() {
    }

    //  Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
