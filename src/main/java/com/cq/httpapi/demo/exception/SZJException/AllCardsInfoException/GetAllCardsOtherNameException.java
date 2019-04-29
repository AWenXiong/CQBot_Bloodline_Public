package com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetAllCardsOtherNameException extends SZJException {

    public GetAllCardsOtherNameException(int errorCode, String message) {
        super(errorCode, message);
    }
}
