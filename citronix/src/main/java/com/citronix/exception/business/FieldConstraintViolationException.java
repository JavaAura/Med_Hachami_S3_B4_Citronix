package com.citronix.exception.business;

public class FieldConstraintViolationException  extends RuntimeException{
    public FieldConstraintViolationException(String message) {
        super(message);
    }

}
