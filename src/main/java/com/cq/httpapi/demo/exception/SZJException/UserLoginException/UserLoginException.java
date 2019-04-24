package com.cq.httpapi.demo.exception.SZJException.UserLoginException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class UserLoginException extends SZJException {

    public UserLoginException(int errorCode, String message) {
        super(errorCode, message);
    }
}
