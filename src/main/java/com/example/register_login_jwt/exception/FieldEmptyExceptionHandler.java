package com.example.register_login_jwt.exception;

public class FieldEmptyExceptionHandler extends RuntimeException {
    public FieldEmptyExceptionHandler(String message) {
        super(message);
    }
}

