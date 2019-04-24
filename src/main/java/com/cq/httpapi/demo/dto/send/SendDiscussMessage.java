package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SendDiscussMessage extends Response {

    private String discuss_id;
    private String message;
    private boolean auto_escape;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("discuss_id", this.getDiscuss_id());
            jsonObject.put("message", this.getMessage());
            jsonObject.put("auto_escape", this.isAuto_escape());
            UrlKit.sendPost(ApiPath.SEND_DISCUSS_MESSAGE, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getDiscuss_id() {
        return discuss_id;
    }

    public void setDiscuss_id(String discuss_id) {
        this.discuss_id = discuss_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAuto_escape() {
        return auto_escape;
    }

    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }
}
