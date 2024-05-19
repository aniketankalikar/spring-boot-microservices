package com.advaya.userservice.exceptions;

public class InvalidTokenValueException extends RuntimeException{
    public InvalidTokenValueException(String token) {
        super(token);
    }
}
