package com.example.Authentication.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private Long userId;
    private String supplier;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, double price, int quantity, Long userId,String supplier) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
        this.supplier=supplier;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
