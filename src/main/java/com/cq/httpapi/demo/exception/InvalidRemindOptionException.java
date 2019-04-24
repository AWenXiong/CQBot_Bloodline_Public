package com.cq.httpapi.demo.exception;

public class InvalidRemindOptionException extends RuntimeException {

    public InvalidRemindOptionException(String desc) {
        super(desc);
    }
}
