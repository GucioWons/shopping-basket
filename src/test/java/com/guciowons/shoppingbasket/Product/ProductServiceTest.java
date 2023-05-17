package com.guciowons.shoppingbasket.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductProvider productProvider;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private TransProductService transProductService;
    private ProductService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProductService(productProvider, productRepository, transProductService);
    }

    @Test
    void refreshProductsInDatabase() {
        List<Product> products = List.of(
                new Product(1, "Product 1", BigDecimal.valueOf(10), "Description"),
                new Product(2, "Product 2", BigDecimal.valueOf(20), "Description")
        );

        when(productProvider.getProducts()).thenReturn(products);

        underTest.refreshProductsInDatabase();

        verify(productProvider).getProducts();
        verify(transProductService).insertProduct(products.get(0));
        verify(transProductService).insertProduct(products.get(1));
    }

    @Test
    void getProducts() {
        underTest.getProducts();

        verify(productRepository).findAll();
    }

    @Test
    void getProductById() {
        underTest.getProductById("first");

        verify(productRepository).findById("first");
    }
}