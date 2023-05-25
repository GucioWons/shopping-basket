package com.guciowons.shoppingbasket.Product;

import com.guciowons.shoppingbasket.Exception.NoExternalConnectionException;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private ProductService underTest;

    @Mock
    private ProductProvider productProvider;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private TransProductService transProductService;

    @BeforeEach
    void setUp(){
        underTest = new ProductService(productProvider, productRepository, transProductService);
    }

    @Test
    void refreshNewProductsInDatabase() {
        List<Product> products = List.of(
                new Product(1, "Product 1", BigDecimal.valueOf(10), "Description"),
                new Product(2, "Product 2", BigDecimal.valueOf(20), "Description")
        );

        when(productProvider.getProducts()).thenReturn(products);

        underTest.refreshProductsInDatabase();

        verify(productProvider).getProducts();
        verify(productRepository).findProductByExternalId(1);
        verify(productRepository).findProductByExternalId(2);
        verify(transProductService).insertProduct(products.get(0));
        verify(transProductService).insertProduct(products.get(1));
    }

    @Test
    void refreshExistingProductsInDatabase(){
        List<Product> products = List.of(
                new Product(1, "Product 1", BigDecimal.valueOf(10), "Description"),
                new Product(2, "Product 2", BigDecimal.valueOf(20), "Description")
        );
        Product product1 = products.get(0);
        Product product2 = products.get(1);
        product1.setId("first");
        product2.setId("second");

        when(productProvider.getProducts()).thenReturn(products);
        when(productRepository.findProductByExternalId(1)).thenReturn(Optional.of(product1));
        when(productRepository.findProductByExternalId(2)).thenReturn(Optional.of(product2));

        underTest.refreshProductsInDatabase();

        verify(transProductService).updateProduct(products.get(0), product1);
        verify(transProductService).updateProduct(products.get(1), product2);
    }

    @Test
    void refreshProductsInDatabaseWithException(){
        when(productProvider.getProducts()).thenThrow(FeignException.class);

        NoExternalConnectionException exception = assertThrows(NoExternalConnectionException.class,
                () -> underTest.refreshProductsInDatabase());

        assertEquals("Error connecting to external api. Products will be downloaded in one hour", exception.getMessage());
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