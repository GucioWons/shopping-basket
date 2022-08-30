package com.guciowons.shoppingbasket.Exception;

public class ApiException extends RuntimeException{
    public ApiException(String errorMessage){
        super(errorMessage);
    }
}