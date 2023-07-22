package com.laerson.techsolutio.techproduct.domain.exception;

public class IncorrectPasswordException extends RuntimeException {

    private static final String PASSWORD_ERROR_MESSAGE = "Incorrect username or password.";

    public IncorrectPasswordException() {
        super(PASSWORD_ERROR_MESSAGE);
    }
}
