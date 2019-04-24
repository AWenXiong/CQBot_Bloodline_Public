package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetGroupSpecialTitle extends Response {

    private String group_id;
    private String user_id;
    private String special_title;
    private Long duration = new Long(-1);

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("group_id", this.getGroup_id());
            jsonObject.put("user_id", this.getUser_id());
            jsonObject.put("special_title", this.getSpecial_title());
            jsonObject.put("duration", getDuration());
            UrlKit.sendPost(ApiPath.SET_GROUP_SPECIAL_TITLE, jsonObject);
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

    public String getSpecial_title() {
        return special_title;
    }

    public void setSpecial_title(String special_title) {
        this.special_title = special_title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
