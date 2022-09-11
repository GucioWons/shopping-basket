package com.guciowons.shoppingbasket.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rating {

    private BigDecimal rate;
    private Integer count;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(rate, rating.rate) && Objects.equals(count, rating.count) && Objects.equals(additionalProperties, rating.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, count, additionalProperties);
    }
}
