package com.cq.httpapi.demo.exception.CQException;

public class TooManyOptionsException extends RuntimeException {

    public TooManyOptionsException(String message) {
        super(message);
    }
}
