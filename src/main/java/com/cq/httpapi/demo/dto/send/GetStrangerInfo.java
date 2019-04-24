package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class GetStrangerInfo extends Response {

    private String user_id;
    private boolean no_cache = false;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", this.getUser_id());
            jsonObject.put("no_cache", this.isNo_cache());
            UrlKit.sendPost(ApiPath.GET_STRANGER_INFO, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isNo_cache() {
        return no_cache;
    }

    public void setNo_cache(boolean no_cache) {
        this.no_cache = no_cache;
    }
}
