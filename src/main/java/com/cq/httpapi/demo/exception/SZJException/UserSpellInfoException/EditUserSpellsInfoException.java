package com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class EditUserSpellsInfoException extends SZJException {
    public EditUserSpellsInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
