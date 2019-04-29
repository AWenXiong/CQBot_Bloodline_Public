package com.cq.httpapi.demo.exception.SZJException.QueueException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetQueueInfoCardException extends SZJException {
    public GetQueueInfoCardException(int errorCode, String message) {
        super(errorCode, message);
    }
}
