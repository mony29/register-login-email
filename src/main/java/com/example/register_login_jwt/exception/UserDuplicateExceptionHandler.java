package com.example.register_login_jwt.exception;

public class UserDuplicateExceptionHandler extends RuntimeException{
    public UserDuplicateExceptionHandler(String message){
        super(message);
    }
}
