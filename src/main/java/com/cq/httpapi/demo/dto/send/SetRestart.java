package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetRestart extends Response {

    private boolean clean_log = false;
    private boolean clean_cache = false;
    private boolean clean_event = false;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clean_log", this.isClean_log());
            jsonObject.put("clean_cache", this.isClean_cache());
            jsonObject.put("clean_event", this.isClean_event());
            UrlKit.sendPost(ApiPath.SET_RESTART, jsonObject);
        } catch (Exception e) {

        }
    }

    public boolean isClean_log() {
        return clean_log;
    }

    public void setClean_log(boolean clean_log) {
        this.clean_log = clean_log;
    }

    public boolean isClean_cache() {
        return clean_cache;
    }

    public void setClean_cache(boolean clean_cache) {
        this.clean_cache = clean_cache;
    }

    public boolean isClean_event() {
        return clean_event;
    }

    public void setClean_event(boolean clean_event) {
        this.clean_event = clean_event;
    }
}
