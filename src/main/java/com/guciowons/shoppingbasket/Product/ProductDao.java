package com.guciowons.shoppingbasket.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> getAll();

    void save(Product product);

    void delete(Product product);

    Optional<Product> findById(int id);

}
