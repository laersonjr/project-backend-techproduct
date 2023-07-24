package com.laerson.techsolutio.techproduct.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "User already exists.";

    public UserAlreadyExistsException() {
        super((ERROR_MESSAGE));
    }

}
