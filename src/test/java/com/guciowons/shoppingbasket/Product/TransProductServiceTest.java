package com.guciowons.shoppingbasket.Product;

import com.guciowons.shoppingbasket.PriceRecord.PriceRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PriceRecordRepository priceRecordRepository;
    private TransProductService underTest;

    @BeforeEach
    void setUp(){
        underTest = new TransProductService(productRepository, priceRecordRepository);
    }
    @Test
    void insertProduct() {
        Product product = new Product(
                1,
                "Product",
                BigDecimal.valueOf(10),
                "Description");

        underTest.insertProduct(product);

        verify(productRepository).save(product);
        verify(priceRecordRepository).save(any());
    }

    @Test
    void updateProduct() {
        Product product = new Product(
                1,
                "Product",
                BigDecimal.valueOf(10),
                "Description");
        Product newProduct = new Product(
                1,
                "Updated product",
                BigDecimal.valueOf(20),
                "Description updated");

        underTest.updateProduct(product, newProduct);

        verify(productRepository).save(newProduct);
        verify(productRepository).save(any());
    }
}