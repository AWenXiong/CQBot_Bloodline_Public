package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetGroupKick extends Response {

    private String group_id;
    private String user_id;
    private boolean reject_add_request = false;
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
            jsonObject.put("group_id", this.getGroup_id());
            jsonObject.put("user_id", this.getUser_id());
            jsonObject.put("reject_add_request", this.isReject_add_request());
            UrlKit.sendPost(this.ip + ApiPath.SET_GROUP_KICK.getUrlPath(), jsonObject);
        } catch (Exception e) {

        }
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isReject_add_request() {
        return reject_add_request;
    }

    public void setReject_add_request(boolean reject_add_reqeust) {
        this.reject_add_request = reject_add_reqeust;
    }
}
