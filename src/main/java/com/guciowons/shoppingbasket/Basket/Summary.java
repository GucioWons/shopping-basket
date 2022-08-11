package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Summary {
    private final List<MultiProduct> products;
    private final double cost;

    public Summary(HashMap<Product, Integer> basketProducts) {
        this.products = productsMapToList(basketProducts);
        this.cost = countPrices();
    }

    private List<MultiProduct> productsMapToList(HashMap<Product, Integer> basketProducts){
        List<MultiProduct> productslist = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : basketProducts.entrySet()) {
            Product key = entry.getKey();
            int value = entry.getValue();
            productslist.add(new MultiProduct(key, value));
        }
        return productslist;
    }

    private double countPrices(){
        double price = 0;
        for(MultiProduct multiProduct : products){
            price += multiProduct.product.getCost() * multiProduct.quantity;
        }
        return price;
    }

    public List<MultiProduct> getProducts() {
        return products;
    }

    public double getCost() {
        return cost;
    }

    private class MultiProduct{
        private final Product product;
        private final int quantity;

        public MultiProduct(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
