package com.laerson.techsolutio.techproduct.domain.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "User not found.";

    public UserNotFoundException() {
        super((ERROR_MESSAGE));
    }

}
