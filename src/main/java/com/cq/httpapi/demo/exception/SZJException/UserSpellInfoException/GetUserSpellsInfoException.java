package com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetUserSpellsInfoException extends SZJException {
    public GetUserSpellsInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
