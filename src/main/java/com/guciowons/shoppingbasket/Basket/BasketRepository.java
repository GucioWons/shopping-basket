package com.guciowons.shoppingbasket.Basket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {
}
