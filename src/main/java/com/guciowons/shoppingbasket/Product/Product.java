package com.guciowons.shoppingbasket.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document
public class Product {

    @Id
    private String id;
    private Integer externalId;
    private String title;
    private BigDecimal price;
    private String description;
    private String image;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("appId")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("externalId")
    public Integer getExternalId() {
        return externalId;
    }
    @JsonProperty("id")
    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description, image);
    }
}
