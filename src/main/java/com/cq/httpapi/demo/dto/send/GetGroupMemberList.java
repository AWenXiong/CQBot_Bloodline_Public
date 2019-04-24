package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class GetGroupMemberList extends Response {

    private String group_id;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("group_id", this.getGroup_id());
            UrlKit.sendPost(ApiPath.GET_GROUP_MEMBER_LIST, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
