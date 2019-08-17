package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendPrivateMessage extends Response {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String user_id;
    private String message;
    private boolean auto_escape;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", this.getUser_id());
            jsonObject.put("message", this.getMessage());
            jsonObject.put("auto_escape", this.isAuto_escape());
            UrlKit.sendPost(this.ip + ApiPath.SEND_PRIVATE_MESSAGE.getUrlPath(), jsonObject);
            logger.warn(jsonObject.toString());
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
