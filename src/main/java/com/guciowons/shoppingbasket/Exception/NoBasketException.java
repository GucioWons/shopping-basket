package com.guciowons.shoppingbasket.Exception;

public class NoBasketException extends ApiException{
    public NoBasketException(String errorMessage) {
        super(errorMessage);
    }
}
