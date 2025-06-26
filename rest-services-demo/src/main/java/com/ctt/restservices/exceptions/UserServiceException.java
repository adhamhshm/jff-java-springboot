package com.ctt.restservices.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 123123123123123L;

    public UserServiceException(String message) {
        super(message);
    }
}
