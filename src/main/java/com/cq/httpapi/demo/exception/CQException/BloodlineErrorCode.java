package com.cq.httpapi.demo.exception.CQException;

public enum BloodlineErrorCode {

    QA_NOT_EXISTS(10001, "问答不存在"),
    QA_UPDATE_FAILURE(10002, "修改问答失败"),

    CARD_NOT_EXISTS(20001, "卡牌不存在"),
    CARD_UPDATE_FAILURE(20002, "修改卡牌数据失败");


    private final Integer errorCode;
    private final String message;
    private String[] params;

    BloodlineErrorCode(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String[] getParams() {
        return params;
    }
}
