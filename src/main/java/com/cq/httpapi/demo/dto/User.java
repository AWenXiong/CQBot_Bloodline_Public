package com.cq.httpapi.demo.dto;

public enum User {

    DOLLYBELU("5020"),
    ROBOTTESTGROUP("9275");

    private String userId;

    User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
