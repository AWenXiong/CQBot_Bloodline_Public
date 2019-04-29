package com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetAllSpellsInfoException extends SZJException {

    public GetAllSpellsInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
