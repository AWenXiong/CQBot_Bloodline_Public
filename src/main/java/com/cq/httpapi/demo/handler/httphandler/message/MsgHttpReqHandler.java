package com.cq.httpapi.demo.handler.httphandler.message;

import java.util.Map;

public interface MsgHttpReqHandler {

    String getPostType();

    String getSelfId();

    String getTime();

    String getMessageType();

    String getMessage();

    String getMessageId();

    String getRawMessage();

    String getFont();

    Object getSender();

    String getUserId();

    Map<String, Object> getHeader();

    Map<String, Object> getRequestMsg();
}
