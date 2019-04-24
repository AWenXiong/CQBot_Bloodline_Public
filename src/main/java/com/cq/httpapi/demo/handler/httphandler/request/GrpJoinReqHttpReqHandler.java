package com.cq.httpapi.demo.handler.httphandler.request;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 加群请求/邀请
 * 均未测试，理论可用
 */
public class GrpJoinReqHttpReqHandler {

    private Map<String, Object> header;
    private Map<String, Object> requestMsg;

    public GrpJoinReqHttpReqHandler(JSONObject header, JSONObject requestMsg) {
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

    public String getRequestType() {
        return this.requestMsg.get("request_type").toString();
    }

    public String getSubType() {
        return this.requestMsg.get("sub_type").toString();
    }

    public String getGroupId() {
        return this.requestMsg.get("group_id").toString();
    }

    public String getUserId() {
        return this.requestMsg.get("user_id").toString();
    }

    public String getComment() {
        return this.requestMsg.get("comment").toString();
    }

    public String getFlag() {
        return this.requestMsg.get("flag").toString();
    }
}
