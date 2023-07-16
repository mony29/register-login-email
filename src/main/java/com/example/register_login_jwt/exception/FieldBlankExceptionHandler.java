package com.example.register_login_jwt.exception;

public class FieldBlankExceptionHandler extends RuntimeException {
    public FieldBlankExceptionHandler(String message) {
        super(message);
    }
}