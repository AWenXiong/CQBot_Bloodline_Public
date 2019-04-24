package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SendPrivateMessage extends Response {

    private String user_id;
    private String message;
    private boolean auto_escape;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", this.getUser_id());
            jsonObject.put("message", this.getMessage());
            jsonObject.put("auto_escape", this.isAuto_escape());
            UrlKit.sendPost(ApiPath.SEND_PRIVATE_MESSAGE, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
