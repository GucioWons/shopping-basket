package com.guciowons.shoppingbasket.Product;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductDao{
    private static List<Product> DB = Arrays.asList(new Product("Monitor", "Monitor description"),
            new Product("Mouse", "Mouse description"),
            new Product("Keyboard", "Keyboard description"),
            new Product("Microphone", "Microphone description"),
            new Product("Computer", "Computer description"));

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
