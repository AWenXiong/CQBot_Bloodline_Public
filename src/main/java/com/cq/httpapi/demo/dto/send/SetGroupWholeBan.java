package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetGroupWholeBan extends Response {

    private String group_id;
    private boolean enable = true;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("group_id", this.getGroup_id());
            jsonObject.put("enable", this.isEnable());
            UrlKit.sendPost(ApiPath.SET_GROUP_WHOLE_BAN, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
