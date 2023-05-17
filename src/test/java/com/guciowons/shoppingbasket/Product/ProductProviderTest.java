package com.guciowons.shoppingbasket.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductProviderTest {

    @Mock
    private ExternalProductClient externalProductClient;
    private ProductProvider underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProductProvider(externalProductClient);
    }

    @Test
    void getProducts() {
        ExternalProduct externalProduct = new ExternalProduct();
        externalProduct.setId(1);
        externalProduct.setTitle("Product");
        externalProduct.setDescription("Description");
        externalProduct.setPrice(BigDecimal.valueOf(10));

        when(externalProductClient.getExternalProducts()).thenReturn(List.of(externalProduct));

        List<Product> products = underTest.getProducts();

        assertThat(products).hasSize(1);
        assertThat(products).allSatisfy(product -> {
            assertThat(product.getId()).isNull();
            assertThat(product.getExternalId()).isEqualTo(externalProduct.getId());
            assertThat(product.getTitle()).isEqualTo(externalProduct.getTitle());
            assertThat(product.getDescription()).isEqualTo(externalProduct.getDescription());
            assertThat(product.getPrice()).isEqualTo(externalProduct.getPrice());
        });
    }
}