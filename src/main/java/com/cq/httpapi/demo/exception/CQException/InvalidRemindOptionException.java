package com.cq.httpapi.demo.exception.CQException;

public class InvalidRemindOptionException extends RuntimeException {

    public InvalidRemindOptionException(String desc) {
        super(desc);
    }
}
