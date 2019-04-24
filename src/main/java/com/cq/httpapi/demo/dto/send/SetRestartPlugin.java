package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetRestartPlugin extends Response {

    private Long delay;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("delay", this.getDelay());
            UrlKit.sendPost(ApiPath.SET_RESTART_PLUGIN, jsonObject);
        } catch (Exception e) {

        }
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }
}
