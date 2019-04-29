package com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class DeleteUserSpellException extends SZJException {
    public DeleteUserSpellException(int errorCode, String message) {
        super(errorCode, message);
    }
}
