package com.guciowons.shoppingbasket.Product;

public class Product {
    private final int id;
    private String name;
    private String description;
    private static int counter;

    public Product(String name, String description) {
        this.id = ++counter;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
