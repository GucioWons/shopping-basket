package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Product> content = new ArrayList<>();
    private double sum;

    public List<Product> getContent() {
        return content;
    }

    public double getSum() {
        return sum;
    }

    public void addProduct(Product product){
        content.add(product);
        sum+= product.getCost();
    }

    public void removeProduct(Product product){
        content.remove(product);
        sum-= product.getCost();
    }
}
