package com.cq.httpapi.demo.dto.SZJ;

public class SZJResponse {

    public String message;
    public int errorCode;
    public boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(boolean success, int errorCode, String message) {
        setSuccess(success);
        setErrorCode(errorCode);
        setMessage(message);
    }
}
