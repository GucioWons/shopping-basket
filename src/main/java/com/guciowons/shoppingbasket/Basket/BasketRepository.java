package com.guciowons.shoppingbasket.Basket;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BasketRepository implements BasketDao{
    private List<Basket> DB = new ArrayList<>();

    @Override
    public List<Basket> getAll() {
        return DB;
    }

    @Override
    public void save(Basket basket) {
        DB.add(basket);
    }

    @Override
    public void delete(Basket basket) {
        DB.remove(basket);
    }

    @Override
    public Optional<Basket> findById(int id) {
        return DB.stream().filter(basket -> basket.getId() == id).findFirst();
    }
}
