package com.cq.httpapi.demo.exception.SZJException.QueueException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class AddUserCardsEditingException extends SZJException {
    public AddUserCardsEditingException(int errorCode, String message) {
        super(errorCode, message);
    }
}
