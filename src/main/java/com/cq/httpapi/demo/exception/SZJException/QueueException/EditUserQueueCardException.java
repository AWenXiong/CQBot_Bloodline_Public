package com.cq.httpapi.demo.exception.SZJException.QueueException;

import com.cq.httpapi.demo.exception.SZJException.SZJException;

public class EditUserQueueCardException extends SZJException {
    public EditUserQueueCardException(int errorCode, String message) {
        super(errorCode, message);
    }
}
