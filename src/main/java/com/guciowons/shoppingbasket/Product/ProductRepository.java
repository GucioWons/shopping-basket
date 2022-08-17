package com.guciowons.shoppingbasket.Product;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductDao{
    private List<Product> DB = Arrays.asList(new Product(1, "Monitor", "Monitor description", new BigDecimal("499.99")),
            new Product(2, "Mouse", "Mouse description", new BigDecimal("99.99")),
            new Product(3, "Keyboard", "Keyboard description", new BigDecimal("149.99")),
            new Product(4, "Microphone", "Microphone description", new BigDecimal("199.99")),
            new Product(5, "Computer", "Computer description", new BigDecimal("1999.99"))
    );

    @Override
    public List<Product> getAll() {
        return DB;
    }

    @Override
    public void save(Product product) {
        DB.add(product);
    }

    @Override
    public void delete(Product product) {
        DB.remove(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return DB.stream().filter(product -> product.getId() == id).findFirst();
    }
}
