package com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetEnemyLevelException extends SZJException {
    public GetEnemyLevelException(int errorCode, String message) {
        super(errorCode, message);
    }
}
