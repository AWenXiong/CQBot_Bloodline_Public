package com.cq.httpapi.demo.dto;

public enum User {

    DOLLYBELU("502063298"),
    ROBOTTESTGROUP("927582523");

    private String userId;

    User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
