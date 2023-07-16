package com.example.register_login_jwt.exception;

public class NotValidValueExceptionHandler extends RuntimeException {
    public NotValidValueExceptionHandler(String message) {
        super(message);
    }
}