package com.guciowons.shoppingbasket.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findProductByExternalId() {
        Product product = new Product(1, "Test product", new BigDecimal(10), "Test product");
        underTest.save(product);
        Product response = underTest.findProductByExternalId(1).orElse(null);
        assertThat(response).isNotNull();
    }

    @Test
    void findProductByWrongExternalId() {
        Product response = underTest.findProductByExternalId(1).orElse(null);
        assertThat(response).isNull();
    }
}