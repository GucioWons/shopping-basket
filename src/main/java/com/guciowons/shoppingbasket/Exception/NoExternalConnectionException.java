package com.guciowons.shoppingbasket.Exception;

public class NoExternalConnectionException extends ApiException{
    public NoExternalConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
