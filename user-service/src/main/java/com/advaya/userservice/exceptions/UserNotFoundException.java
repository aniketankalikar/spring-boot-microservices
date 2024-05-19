package com.advaya.userservice.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
    }
}
