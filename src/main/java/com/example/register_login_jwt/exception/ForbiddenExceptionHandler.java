package com.example.register_login_jwt.exception;

public class ForbiddenExceptionHandler extends RuntimeException{
    public ForbiddenExceptionHandler(String message) {
        super(message);
    }
}
