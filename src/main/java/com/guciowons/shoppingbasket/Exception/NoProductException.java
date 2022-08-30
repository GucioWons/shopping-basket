package com.guciowons.shoppingbasket.Exception;

public class NoProductException extends ApiException{
    public NoProductException(String errorMessage) {
        super(errorMessage);
    }
}
