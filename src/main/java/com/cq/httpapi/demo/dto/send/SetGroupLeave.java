package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetGroupLeave extends Response {

    private String group_id;
    private boolean is_dismiss = false;
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
            jsonObject.put("is_dismiss", this.is_dismiss);
            UrlKit.sendPost(this.ip + ApiPath.SET_GROUP_LEAVE.getUrlPath(), jsonObject);
        } catch (Exception e) {

        }
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String grout_id) {
        this.group_id = grout_id;
    }

    public boolean isIs_dismiss() {
        return is_dismiss;
    }

    public void setIs_dismiss(boolean is_dismiss) {
        this.is_dismiss = is_dismiss;
    }
}
