package com.cq.httpapi.demo.exception.SZJException.BackEndDataException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class InvitationNotExistException extends SZJException {
    public InvitationNotExistException(int errorCode, String message) {
        super(errorCode, message);
    }
}
