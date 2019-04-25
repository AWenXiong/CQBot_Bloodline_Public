package com.cq.httpapi.demo.exception.SZJException.BackEndDataException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class InvitationCodeException extends SZJException {
    public InvitationCodeException(int errorCode, String message) {
        super(errorCode, message);
    }
}
