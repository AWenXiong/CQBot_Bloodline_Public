package com.cq.httpapi.demo.handler.httphandler.message;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 群消息处理
 * 基本可用，未进行边界测试
 */
public class GrpMsgHttpReqHandler implements MsgHttpReqHandler {

    private Map<String, Object> header;
    private Map<String, Object> requestMsg;

    public GrpMsgHttpReqHandler(JSONObject header, JSONObject requestMsg) {
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

    public String getSubType() {
        return this.requestMsg.get("sub_type").toString();
    }

    @Override
    public String getMessageId() {
        return this.requestMsg.get("message_id").toString();
    }

    public String getGroupId() {
        return this.requestMsg.get("group_id").toString();
    }

    @Override
    public String getUserId() {
        return this.requestMsg.get("user_id").toString();
    }

    public Object getAnoymous() {
        return this.requestMsg.get("anoymous");
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
