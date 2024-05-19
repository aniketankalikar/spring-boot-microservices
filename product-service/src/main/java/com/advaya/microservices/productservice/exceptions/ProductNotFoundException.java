package com.advaya.microservices.productservice.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String id)
    {
        super(id);
    }
}
