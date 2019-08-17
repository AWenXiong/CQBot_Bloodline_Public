package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetGroupAnonymousBan extends Response {

    private String group_id;
    private Object anonymous;
    private String anonymous_flag;
    private Long duration;
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
            jsonObject.put("anonymous", this.getAnonymous());
            jsonObject.put("anonymous_flag", this.getAnonymous_flag());
            jsonObject.put("duration", this.getDuration());
            UrlKit.sendPost(this.ip + ApiPath.SET_GROUP_ANONYMOUS_BAN.getUrlPath(), jsonObject);
        } catch (Exception e) {

        }
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Object getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Object anonymous) {
        this.anonymous = anonymous;
    }

    public String getAnonymous_flag() {
        return anonymous_flag;
    }

    public void setAnonymous_flag(String anonymous_flag) {
        this.anonymous_flag = anonymous_flag;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
