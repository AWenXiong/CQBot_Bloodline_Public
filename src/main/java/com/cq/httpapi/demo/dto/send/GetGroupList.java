package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class GetGroupList extends Response {

    private String ip;
    private JSONObject groupList;

    public static JSONObject getGroupList(String selfId) {
        GetGroupList getGroupList = new GetGroupList();
        getGroupList.setIp(selfId);
        getGroupList.execute();
        return getGroupList.groupList;
    }

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
            this.groupList = UrlKit.sendPost(this.ip + ApiPath.GET_GROUP_LIST.getUrlPath(), jsonObject);
        } catch (Exception e) {

        }
    }
}
