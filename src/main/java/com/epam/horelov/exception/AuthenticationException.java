package com.epam.horelov.exception;

public class AuthenticationException extends IllegalArgumentException{

    public AuthenticationException(String msg) {
        super(msg);
    }

}
