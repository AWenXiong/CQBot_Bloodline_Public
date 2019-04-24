package com.cq.httpapi.demo.handler.httphandler.message;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 讨论组消息
 * 均未测试，理论可用
 */
public class DisMsgHttpReqHandler implements MsgHttpReqHandler {

    private Map<String, Object> header;
    private Map<String, Object> requestMsg;

    public DisMsgHttpReqHandler(JSONObject header, JSONObject requestMsg) {
        this.header = header.getInnerMap();
        this.requestMsg = requestMsg.getInnerMap();
    }

    @Override
    public String getPostType() {
        return this.requestMsg.get("post_type").toString();
    }

    @Override
    public String getTime() {
        return this.requestMsg.get("time").toString();
    }

    @Override
    public String getSelfId() {
        return this.requestMsg.get("self_id").toString();
    }

    @Override
    public String getMessageType() {
        return this.requestMsg.get("message_type").toString();
    }

    public String getDiscussId() {
        return this.requestMsg.get("discuss_id").toString();
    }

    @Override
    public String getMessageId() {
        return this.requestMsg.get("message_id").toString();
    }

    @Override
    public String getUserId() {
        return this.requestMsg.get("user_id").toString();
    }

    @Override
    public String getMessage() {
        return this.requestMsg.get("message").toString();
    }

    @Override
    public String getRawMessage() {
        return this.requestMsg.get("raw_message").toString();
    }

    @Override
    public String getFont() {
        return this.requestMsg.get("font").toString();
    }

    @Override
    public Object getSender() {
        return this.requestMsg.get("sender");
    }

    @Override
    public Map<String, Object> getHeader() {
        return header;
    }

    @Override
    public Map<String, Object> getRequestMsg() {
        return requestMsg;
    }
}
