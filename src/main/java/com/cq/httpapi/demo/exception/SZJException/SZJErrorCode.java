package com.cq.httpapi.demo.exception.SZJException;

public enum SZJErrorCode {

    OPENID_ERROR(1, "Openid不存在"),
    ARGUMENT_NULL(2, "参数为空"),
    ARGUMENT_INVALID(3, "参数不合法"),
    UNKNOWN_EXCEPTION(9, "未知异常"),

    USER_NOT_EXIST(4, "用户不存在");

    private int errorCode;
    private String message;

    private SZJErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static SZJErrorCode valueOf(int errorCode) {
        for (SZJErrorCode szjErrorCode : values()) {
            if (szjErrorCode.getErrorCode() == errorCode) {
                return szjErrorCode;
            }
        }
        return null;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
