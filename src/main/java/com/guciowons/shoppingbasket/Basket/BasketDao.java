package com.guciowons.shoppingbasket.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketDao {
    List<Basket> getAll();

    void save(Basket basket);

    void delete(Basket basket);

    Optional<Basket> findById(int id);
}
