package com.laerson.techsolutio.techproduct.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Product not found.";

    public ProductNotFoundException() {
        super(ERROR_MESSAGE);
    }

}
