package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class GetGroupList extends Response {

    private JSONObject groupList;

    public static JSONObject getGroupList() {
        GetGroupList getGroupList = new GetGroupList();
        getGroupList.execute();
        return getGroupList.groupList;
    }

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            this.groupList = UrlKit.sendPost(ApiPath.GET_GROUP_LIST, jsonObject);
        } catch (Exception e) {

        }
    }
}
