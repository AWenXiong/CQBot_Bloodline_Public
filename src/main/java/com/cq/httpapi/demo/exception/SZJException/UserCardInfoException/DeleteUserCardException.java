package com.cq.httpapi.demo.exception.SZJException.UserCardInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class DeleteUserCardException extends SZJException {
    public DeleteUserCardException(int errorCode, String message) {
        super(errorCode, message);
    }
}
