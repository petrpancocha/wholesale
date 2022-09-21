package com.petrpancocha.wholesale.exception;

public class EntityNotFoundException extends RuntimeException {

    private String message;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}