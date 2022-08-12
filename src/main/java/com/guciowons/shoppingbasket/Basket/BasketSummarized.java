package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;

import java.math.BigDecimal;
import java.util.List;

public class BasketSummarized {
    private final List<MultiProduct> products;
    private final BigDecimal cost;

    public BasketSummarized(List<MultiProduct> products, BigDecimal cost) {
        this.products = products;
        this.cost = cost;
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
