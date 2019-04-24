package com.cq.httpapi.demo.exception.SZJException.BackEndDataException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class SZJInvitationNotExistException extends SZJException {
    public SZJInvitationNotExistException(int errorCode, String message) {
        super(errorCode, message);
    }
}
