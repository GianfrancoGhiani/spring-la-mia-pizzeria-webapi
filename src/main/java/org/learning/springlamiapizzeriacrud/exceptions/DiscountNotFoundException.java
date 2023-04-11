package org.learning.springlamiapizzeriacrud.exceptions;

public class DiscountNotFoundException extends RuntimeException{
    public DiscountNotFoundException(String message) {
        super(message);
    }
}
