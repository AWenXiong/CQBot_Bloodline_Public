package com.cq.httpapi.demo.exception.SZJException.UserLoginException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetUserInfoException extends SZJException {
    public GetUserInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
