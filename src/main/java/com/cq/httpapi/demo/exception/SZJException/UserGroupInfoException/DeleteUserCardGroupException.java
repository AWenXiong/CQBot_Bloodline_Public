package com.cq.httpapi.demo.exception.SZJException.UserGroupInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class DeleteUserCardGroupException extends SZJException {
    public DeleteUserCardGroupException(int errorCode, String message) {
        super(errorCode, message);
    }
}
