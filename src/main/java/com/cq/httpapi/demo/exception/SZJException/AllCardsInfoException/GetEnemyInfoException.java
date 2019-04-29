package com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetEnemyInfoException extends SZJException {
    public GetEnemyInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
