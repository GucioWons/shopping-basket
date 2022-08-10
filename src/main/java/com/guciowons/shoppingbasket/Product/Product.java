package com.guciowons.shoppingbasket.Product;

public class Product {
    private final int id;
    private String name;
    private String description;
    private double cost;
    private static int counter;

    public Product(String name, String description, double cost) {
        this.id = ++counter;
        this.name = name;
        this.description = description;
        this.cost = cost;
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

    public double getCost(){
        return cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }
}
