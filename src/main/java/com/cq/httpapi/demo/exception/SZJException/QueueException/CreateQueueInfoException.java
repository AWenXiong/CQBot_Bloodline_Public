package com.cq.httpapi.demo.exception.SZJException.QueueException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class CreateQueueInfoException extends SZJException {
    public CreateQueueInfoException(int errorCode, String message) {
        super(errorCode, message);
    }
}
