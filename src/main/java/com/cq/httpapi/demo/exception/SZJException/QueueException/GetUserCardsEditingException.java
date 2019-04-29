package com.cq.httpapi.demo.exception.SZJException.QueueException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetUserCardsEditingException extends SZJException {
    public GetUserCardsEditingException(int errorCode, String message) {
        super(errorCode, message);
    }
}
