package com.cq.httpapi.demo.dto.SZJ;

import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;

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

    public void setError(SZJException e) {
        setSuccess(false);
        setErrorCode(e.getErrorCode());
        setMessage(e.getMessage());
    }

    public void setError(SZJErrorCode szjErrorCode) {
        setSuccess(false);
        setErrorCode(szjErrorCode.getErrorCode());
        setMessage(szjErrorCode.getMessage());
    }

    public void setError(boolean success, int errorCode, String message) {
        setSuccess(success);
        setErrorCode(errorCode);
        setMessage(message);
    }
}
