package com.epam.horelov.exception;

public class CustomException extends IllegalArgumentException {

    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
