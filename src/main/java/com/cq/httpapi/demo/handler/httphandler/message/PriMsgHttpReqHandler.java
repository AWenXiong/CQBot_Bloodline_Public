package com.cq.httpapi.demo.handler.httphandler.message;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 私聊消息处理
 * 未注释的为已经通过测试的
 */
public class PriMsgHttpReqHandler implements MsgHttpReqHandler {

    private Map<String, Object> header;
    private Map<String, Object> requestMsg;

    public PriMsgHttpReqHandler(JSONObject header, JSONObject requestMsg) {
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

    /**
     * 用于处理好友发送文件
     * Useless
     */
//    public String getNoticeType() { return this.requestMsg.get("notice_type").toString(); }
//    public Object getFile(){ return this.requestMsg.get("file"); }
    @Override
    public Map<String, Object> getHeader() {
        return header;
    }

    @Override
    public Map<String, Object> getRequestMsg() {
        return requestMsg;
    }
}
