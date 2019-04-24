package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetDiscussLeave extends Response {

    private String discuss_id;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("discuss_id", this.getDiscuss_id());
            UrlKit.sendPost(ApiPath.SET_DISCUSS_LEAVE, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getDiscuss_id() {
        return discuss_id;
    }

    public void setDiscuss_id(String discuss_id) {
        this.discuss_id = discuss_id;
    }
}
