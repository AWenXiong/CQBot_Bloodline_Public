package com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetEnemyCardException extends SZJException {
    public GetEnemyCardException(int errorCode, String message) {
        super(errorCode, message);
    }
}
