package com.cq.httpapi.demo.exception.SZJException.UserLoginException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class UserOpenIdNotExistException extends SZJException {

    public UserOpenIdNotExistException(int errorCode, String message) {
        super(errorCode, message);
    }
}
