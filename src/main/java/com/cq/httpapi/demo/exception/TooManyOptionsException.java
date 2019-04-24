package com.cq.httpapi.demo.exception;

public class TooManyOptionsException extends RuntimeException {

    public TooManyOptionsException(String message) {
        super(message);
    }
}
