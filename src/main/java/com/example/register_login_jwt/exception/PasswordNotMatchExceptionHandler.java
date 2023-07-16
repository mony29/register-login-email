package com.example.register_login_jwt.exception;

public class PasswordNotMatchExceptionHandler extends RuntimeException {
    public PasswordNotMatchExceptionHandler(String message) {
        super(message);
    }
}
