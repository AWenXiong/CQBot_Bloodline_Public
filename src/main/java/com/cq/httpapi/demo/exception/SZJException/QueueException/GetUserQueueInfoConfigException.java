package com.cq.httpapi.demo.exception.SZJException.QueueException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class GetUserQueueInfoConfigException extends SZJException {
    public GetUserQueueInfoConfigException(int errorCode, String message) {
        super(errorCode, message);
    }
}
