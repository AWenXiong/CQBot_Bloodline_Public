package com.cq.httpapi.demo.kit;

import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;

public class RestfulEntityKit<T> {

    private boolean success;
    private int errorCode;
    private String message;
    private T data;

    public RestfulEntityKit(boolean success, int errorCode, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

    public RestfulEntityKit(boolean success, int errorCode, String message, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public RestfulEntityKit(boolean success) {
        this.success = success;
    }

    public RestfulEntityKit(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RestfulEntityKit(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public RestfulEntityKit(SZJErrorCode szjErrorCode) {
        this.success = false;
        this.errorCode = szjErrorCode.getErrorCode();
        this.message = szjErrorCode.getMessage();
    }

    public static RestfulEntityKit getFailure(String message) {
        return new RestfulEntityKit(false, message);
    }

    public static <T> RestfulEntityKit<T> getFailure(int errorCode, String message, T res) {
        return new RestfulEntityKit<>(false, errorCode, message, res);
    }

    public static <T> RestfulEntityKit<T> getFailure(SZJErrorCode szjErrorCode) {
        return new RestfulEntityKit<>(false, szjErrorCode.getErrorCode(), szjErrorCode.getMessage());
    }

    public static <T> RestfulEntityKit<T> getFailure(SZJErrorCode szjErrorCode, T res) {
        return new RestfulEntityKit<>(false, szjErrorCode.getErrorCode(), szjErrorCode.getMessage(), res);
    }

    public static RestfulEntityKit getSuccess() {
        return new RestfulEntityKit(true);
    }

    public static <T> RestfulEntityKit<T> getSuccess(T data) {
        return new RestfulEntityKit<>(true, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
