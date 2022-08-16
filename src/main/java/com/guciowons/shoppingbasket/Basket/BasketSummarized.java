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

        private final BigDecimal summarizedCost;

        public MultiProduct(Product product, int quantity, BigDecimal summarizedCost) {
            this.product = product;
            this.quantity = quantity;
            this.summarizedCost = summarizedCost;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getSummarizedCost() {
            return summarizedCost;
        }
    }
}
