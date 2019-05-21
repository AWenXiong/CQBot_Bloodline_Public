package com.cq.httpapi.demo.exception.SZJException;

public class SZJException extends RuntimeException {

    int errorCode;
    String message;

    public SZJException() {

    }

    public SZJException(SZJErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public SZJException(int errorCode, String message) {
        this.errorCode = SZJErrorCode.valueOf(errorCode).getErrorCode();
        this.message = this.getMessage();
    }


    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
