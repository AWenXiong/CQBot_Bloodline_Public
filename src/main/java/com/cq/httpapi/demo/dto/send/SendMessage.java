package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SendMessage extends Response {

    private String message_type;
    private String user_id;
    private String group_id;
    private String discuss_id;
    private String message;
    private boolean auto_escape;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message_type", this.getMessage_type());
            jsonObject.put("user_id", this.getUser_id());
            jsonObject.put("group_id", this.getGroup_id());
            jsonObject.put("discuss_id", this.getDiscuss_id());
            jsonObject.put("message", this.getMessage_type());
            jsonObject.put("auto_escape", this.isAuto_escape());
            UrlKit.sendPost(ApiPath.SEND_MESSAGE, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDiscuss_id() {
        return discuss_id;
    }

    public void setDiscuss_id(String disscuss_id) {
        this.discuss_id = disscuss_id;
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
