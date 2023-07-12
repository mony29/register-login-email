package com.example.register_login_jwt.exception;

public class UnauthorizedExceptionHandler extends RuntimeException{
    public UnauthorizedExceptionHandler(String message){
        super(message);
    }
}
