package com.cq.httpapi.demo.exception.SZJException;

public class SZJException extends RuntimeException {

    int errorCode;
    String message;

    public SZJException() {

    }

    public SZJException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
