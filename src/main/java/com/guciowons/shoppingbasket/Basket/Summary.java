package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;

import java.math.BigDecimal;
import java.util.List;

public class Summary {
    private final List<MultiProduct> products;
    private final BigDecimal cost;

    public Summary(List<MultiProduct> products) {
        this.products = products;
        this.cost = countPrices();
    }

    private BigDecimal countPrices(){
        BigDecimal price = new BigDecimal("0");
        for(MultiProduct multiProduct : products){
            price = price.add(multiProduct.product.getCost().multiply(new BigDecimal(multiProduct.getQuantity())));
        }
        return price;
    }

    public List<MultiProduct> getProducts() {
        return products;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public static class MultiProduct{
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
