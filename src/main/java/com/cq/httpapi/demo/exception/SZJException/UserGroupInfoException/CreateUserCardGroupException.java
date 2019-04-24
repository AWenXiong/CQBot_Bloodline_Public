package com.cq.httpapi.demo.exception.SZJException.UserGroupInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class CreateUserCardGroupException extends SZJException {

    public CreateUserCardGroupException(int errorCode, String message) {
        super(errorCode, message);
    }
}
