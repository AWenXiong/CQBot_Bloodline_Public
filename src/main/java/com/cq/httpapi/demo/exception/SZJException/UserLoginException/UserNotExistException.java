package com.cq.httpapi.demo.exception.SZJException.UserLoginException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class UserNotExistException extends SZJException {

    public UserNotExistException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
