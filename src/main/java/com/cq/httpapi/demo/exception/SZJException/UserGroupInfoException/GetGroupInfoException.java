package com.cq.httpapi.demo.exception.SZJException.UserGroupInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetGroupInfoException extends SZJException {
    public GetGroupInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
