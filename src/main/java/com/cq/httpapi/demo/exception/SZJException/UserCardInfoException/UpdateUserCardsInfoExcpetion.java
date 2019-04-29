package com.cq.httpapi.demo.exception.SZJException.UserCardInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class UpdateUserCardsInfoExcpetion extends SZJException {
    public UpdateUserCardsInfoExcpetion(int errorCode, String message) {
        super(errorCode, message);
    }
}
