package com.cq.httpapi.demo.exception.SZJException.UserCardInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetUserCardsInfoException extends SZJException {
    public GetUserCardsInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
