package com.cq.httpapi.demo.exception.SZJException.UserGroupInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetAllGroupsException extends SZJException {

    public GetAllGroupsException(int errorCode, String message) {
        super(errorCode, message);
    }
}
