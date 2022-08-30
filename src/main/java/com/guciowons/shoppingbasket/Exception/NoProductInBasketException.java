package com.guciowons.shoppingbasket.Exception;

public class NoProductInBasketException extends ApiException{
    public NoProductInBasketException(String errorMessage) {
        super(errorMessage);
    }
}
