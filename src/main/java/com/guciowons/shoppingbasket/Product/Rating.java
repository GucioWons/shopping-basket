package com.guciowons.shoppingbasket.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Rating {

    private BigDecimal rate;
    private Integer count;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

}
