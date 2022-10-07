package com.guciowons.shoppingbasket.PriceRecord;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Document
public class PriceRecord {
    @Id
    private BigInteger id;
    private String productId;
    private BigDecimal price;
    private LocalDateTime dateTime;

    public PriceRecord(String productId, BigDecimal price, LocalDateTime dateTime) {
        this.productId = productId;
        this.price = price;
        this.dateTime = dateTime;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
