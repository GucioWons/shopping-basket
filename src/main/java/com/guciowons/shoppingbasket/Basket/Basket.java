package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;

import java.util.HashMap;

public class Basket {
    private HashMap<Product, Integer> content = new HashMap<>();
    private double sum;

    public Basket() {
    }

    public HashMap<Product, Integer> getContent() {
        return content;
    }

    public double getSum() {
        return sum;
    }

    public void setContent(HashMap<Product, Integer> content) {
        this.content = content;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void addProduct(Product product, int quantity){
        if(!content.containsKey(product)){
            content.put(product, quantity);
        }else{
            int current = content.get(product);
            content.put(product, current+quantity);
        }
        sum += product.getCost()*quantity;
    }

    public void removeProduct(Product product, int quantity){
        int current = content.get(product);
        if(current <= quantity){
            quantity = current;
            content.remove(product);
        }else{
            content.put(product, current-quantity);
        }
        sum -= product.getCost()*quantity;
    }
}
