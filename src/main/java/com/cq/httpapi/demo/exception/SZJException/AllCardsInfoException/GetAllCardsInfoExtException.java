package com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetAllCardsInfoExtException extends SZJException {
    public GetAllCardsInfoExtException(int errorCode, String message) {
        super(errorCode, message);
    }
}
