package com.cq.httpapi.demo.handler.httphandler.notice;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 添加好友通知
 * 均未测试，理论可用
 */
public class FriAddHttpReqHandler {

    private Map<String, Object> header;
    private Map<String, Object> requestMsg;

    public FriAddHttpReqHandler(JSONObject header, JSONObject requestMsg) {
        this.header = header.getInnerMap();
        this.requestMsg = requestMsg.getInnerMap();
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public Map<String, Object> getRequestMsg() {
        return requestMsg;
    }

    public String getPostType() {
        return this.requestMsg.get("post_type").toString();
    }

    public String getTime() {
        return this.requestMsg.get("time").toString();
    }

    public String getSelfId() {
        return this.requestMsg.get("self_id").toString();
    }

    public String getNoticeType() {
        return this.requestMsg.get("notice_type").toString();
    }

    public String getUserId() {
        return this.requestMsg.get("user_id").toString();
    }

}
