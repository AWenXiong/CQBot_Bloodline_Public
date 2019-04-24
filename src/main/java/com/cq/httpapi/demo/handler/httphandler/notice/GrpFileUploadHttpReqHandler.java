package com.cq.httpapi.demo.handler.httphandler.notice;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 群文件上传通知
 * 均未测试，理论可用
 */
public class GrpFileUploadHttpReqHandler implements GrpNoticeHttpHandler {

    private Map<String, Object> header;
    private Map<String, Object> requestMsg;

    public GrpFileUploadHttpReqHandler(JSONObject header, JSONObject requestMsg) {
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

    public String getGroupId() {
        return this.requestMsg.get("group_id").toString();
    }

    public String getUserId() {
        return this.requestMsg.get("user_id").toString();
    }

    public String getFile() {
        return this.requestMsg.get("file").toString();
    }
}
